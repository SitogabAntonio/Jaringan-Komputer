import java.io.*;
import java.net.*;

public class Client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2345);
            System.out.println("Connected to server on " + socket.getRemoteSocketAddress());

            Thread t = new Thread(new ClientThread(socket));
            t.start();

            BufferedReader terminalInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = terminalInput.readLine();
                out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientThread implements Runnable {
        Socket socket;
        BufferedReader in;

        public ClientThread(Socket socket) {
            this.socket = socket;

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (true) {
                    String msg = in.readLine();
                    System.out.println("Samuel SI: " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
