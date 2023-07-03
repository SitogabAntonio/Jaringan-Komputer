
import java.io.*;
import java.net.*;

//define class comClient
public class comClient {

    //set constant value for HOSTNAME
    public static final String HOSTNAME = "localhost";
    
    //set constant value for PORT
    public static final int PORT = 5000;

    //define main method
    public static void main(String[] args) {
        try {
            //connect to server with HOSTNAME and PORT
            Socket clientSocket = new Socket(HOSTNAME, PORT);
            
            //print "Aplikasi client hidup..."
            System.out.println("Aplikasi client hidup...");
            
            //create BufferedReader to read input from user
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            
            //create BufferedReader to read input from server
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            //create DataOutputStream to send data to server
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //loop until connection is terminated
            while (true) {
                //print "Masukkan pesan :", read input from user and store it in sentence variable
                System.out.print("Masukkan pesan : ");
                String sentence = inFromUser.readLine();
                
                //send sentence to server through outToServer stream
                outToServer.writeBytes(sentence + '\n');
                
                //read response from server and store it in modifiedSentence variable
                String modifiedSentence = inFromServer.readLine();
                
                //check if modifiedSentence equals "koneksi diputuskan oleh client", if yes break the loop
                if (modifiedSentence.equals("koneksi diputuskan oleh client")) {
                    break;
                }
                
                //if not, print the modifiedSentence
                System.out.println("Balasan: " + modifiedSentence);
            }
            
            //close the clientSocket
            clientSocket.close();
            
            //print "Aplikasi client selesai..."
            System.out.println("Aplikasi client selesai...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
