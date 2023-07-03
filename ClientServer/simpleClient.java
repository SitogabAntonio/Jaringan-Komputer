import java.io.*;
import java.net.*;

public class simpleClient {

  public final static int REMOTE_PORT = 5000;

  public static void main(String args[]) throws Exception {

    Socket cl = null;

    BufferedReader is = null;

    DataOutputStream os = null;

    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    String userInput = null;
    String output = null;

    try {
      cl = new Socket(args[0], REMOTE_PORT );
      is = new BufferedReader(new InputStreamReader(cl.getInputStream()));
      os = new DataOutputStream(cl.getOutputStream());
    } catch (UnknownHostException el) {
      System.out.println("Unkown Host: " + el);
    } catch (IOException e2) {
      System.out.println("Error io: " + e2);
    }

    try {
      System.out.println("Masukkan kata kunci: ");
      userInput = stdin.readLine();
      os.writeBytes(userInput + "\n");
    } catch (IOException ex) {
      System.out.println("Error writing to server...." + ex);
    }

    try {
      output = is.readLine();
      System.out.println("Dari server: " + output);
    } catch (IOException e) {
      e.printStackTrace();
    } 

    try {
      is.close();
      os.close();
      cl.close();
    } catch (IOException x) {
      System.out.println("Error writing..." + x);
    }
  }
}
