import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static ServerSocket serverSocket;
    private static List<Socket> clients = new ArrayList<>(); // Using a List to store connected client connections
    private static Map<String, List<Socket>> groups = new HashMap<>(); // Using a Map to store client connections for each group

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(1234);
        System.out.println("Server started.");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Accepting client connection
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            clients.add(clientSocket);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        String message;

                        while (true) {
                            message = in.readLine(); // Receiving message from the client
                            System.out.println("Received from " + clientSocket.getInetAddress().getHostAddress() + ": " + message);

                            // Check if message contains a group name
                            if (message.startsWith("[") && message.contains("]")) {
                                String groupName = message.substring(1, message.indexOf("]")).toLowerCase();

                                // Add client connection to group
                                if (!groups.containsKey(groupName)) {
                                    groups.put(groupName, new ArrayList<>());
                                }
                                groups.get(groupName).add(clientSocket);
                                System.out.println("Added " + clientSocket.getInetAddress().getHostAddress() + " to group " + groupName);

                                // Sending group join message to all connected clients in the group
                                for (Socket socket : groups.get(groupName)) {
                                    if (socket != clientSocket) {
                                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                                        out.println(clientSocket.getInetAddress().getHostAddress() + " has joined the chat.");
                                    }
                                }
                            } else {
                                // Sending client message to all connected clients in the group
                                for (Socket socket : groups.getOrDefault("", clients)) {
                                    if (socket != clientSocket) {
                                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                                        out.println(message);
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error handling client: " + clientSocket.getInetAddress().getHostAddress() + " [" + e.getMessage() + "]");

                        // Remove client connection from List and all groups on error
                        clients.remove(clientSocket);
                        for (List<Socket> group : groups.values()) {
                            group.remove(clientSocket);
                        }
                    }
                }
            });
            t.start();
        }
    }
}