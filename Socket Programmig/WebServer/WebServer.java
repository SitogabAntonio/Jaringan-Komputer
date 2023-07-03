import java.io.*;
import java.net.*;
import java.util.*;

public class WebServer {
    public static void main(String[] args) throws Exception {
        ServerSocket listenSocket = new ServerSocket(6789);

        System.out.println("Multithreaded Web Server Started");

        while (true) {
            Socket connectionSocket = listenSocket.accept();
            new ServerThread(connectionSocket).start();
        }
    }
}

class ServerThread extends Thread {
    private Socket connectionSocket;
    ServerThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

   public void run() {
      try {
         BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
         DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
         String requestMessageLine = inFromClient.readLine();

         StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);
         if(tokenizedLine.nextToken().equals("GET")) {
            String fileName = tokenizedLine.nextToken();
            if(fileName.startsWith("/") == true)
            fileName = fileName.substring(1);
    
            File file = new File(fileName);
            int numOfBytes = (int) file.length();
            FileInputStream inFile = new FileInputStream(fileName);
            byte[] fileInBytes = new byte[numOfBytes];
            inFile.read(fileInBytes);
    
            outToClient.writeBytes("HTTP/1.0 200 Document Follows\r");
            if(fileName.endsWith(".jpg"))
               outToClient.writeBytes("Content-Type: image/jpeg\r");
            else if(fileName.endsWith(".gif"))
               outToClient.writeBytes("Content-Type: image/gif\r");
            else if(fileName.endsWith(".html"))
               outToClient.writeBytes("Content-Type: text/html\r");
            else if(fileName.endsWith(".css"))
               outToClient.writeBytes("Content-Type: text/css\r");
            else if(fileName.endsWith(".js"))
               outToClient.writeBytes("Content-Type: application/javascript\r");
            outToClient.writeBytes("Content-Length: " + numOfBytes + "\r");
            outToClient.writeBytes("\r");
            outToClient.write(fileInBytes, 0, numOfBytes);
            connectionSocket.close();
         } else {
            System.out.println("Bad Request Message");
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
}
