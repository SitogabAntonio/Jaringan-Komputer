import java.io.*;
import java.net.*;

public class simpleServer {
    public final static int TESTPORT = 5000;
    
    
    public static void main(String args[]) {
        ServerSocket checkServer = null;    
        String line;
        BufferedReader is = null;          
        DataOutputStream os = null;        
        Socket clientSocket = null;        
        
        
        try {
            checkServer = new ServerSocket(TESTPORT);
            System.out.println("Aplikasi Server hidup...");    
        } catch (IOException e) {
            System.out.println(e);  
        }

        
        try {
            clientSocket = checkServer.accept();
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
            os = new DataOutputStream(clientSocket.getOutputStream());                        
        } catch (Exception ei) {
            ei.printStackTrace();
        }

        
        try {
            line = is.readLine();      
            System.out.println("Terima : " + line);
            if (line.compareTo("Salam") == 0) {  
                os.writeBytes("salam juga");
            } else {                            
                os.writeBytes("Maaf, saya tidak mengerti");
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        
        try {
            os.close();             
            is.close();             
            clientSocket.close();   
        } catch (IOException ic) {
            ic.printStackTrace();
        }
    }
}
