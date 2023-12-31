import java.io.*;
import java.net.*;
import java.util.*;

class WebServer {
    public static void main(String argv[]) throws Exception {
        ServerSocket listenSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = listenSocket.accept();
            RequestHandler handler = new RequestHandler(connectionSocket);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }
}

class RequestHandler implements Runnable {
    private Socket connectionSocket;

    public RequestHandler(Socket socket) {
        this.connectionSocket = socket;
    }

    public void run() {
        try {
            String requestMessageLine;
            String fileName;
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            requestMessageLine = inFromClient.readLine();
            StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

            if (tokenizedLine.nextToken().equals("GET")) {
                fileName = tokenizedLine.nextToken();
                if (fileName.startsWith("/") == true)
                    fileName = fileName.substring(1);

                File file = new File(fileName);
                int numOfBytes = (int) file.length();
                FileInputStream inFile = new FileInputStream(fileName);
                byte[] fileInBytes = new byte[numOfBytes];
                inFile.read(fileInBytes);
                outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");
                if (fileName.endsWith(".jpg"))
                    outToClient.writeBytes("Content-Type: image/jpeg\r\n");
                else if (fileName.endsWith(".gif"))
                    outToClient.writeBytes("Content-Type: image/gif\r\n");
                else if (fileName.endsWith(".html"))
                    outToClient.writeBytes("Content-Type: text/html\r\n");
                else if (fileName.endsWith(".css"))
                    outToClient.writeBytes("Content-Type: text/css\r\n");
                else if (fileName.endsWith(".js"))
                    outToClient.writeBytes("Content-Type: application/javascript\r\n");
                outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
                outToClient.writeBytes("\r\n");
                outToClient.write(fileInBytes, 0, numOfBytes);

                connectionSocket.close();
            } else {
                System.out.println("Bad Request Message");
                connectionSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
