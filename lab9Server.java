
import java.io.*;
import java.net.*;

public class lab9Server {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("TCP server is running on port 5000...");

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected.");

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    PrintWriter out = new PrintWriter(
                            socket.getOutputStream(), true);

                    String message = in.readLine();
                    System.out.println("Received message: " + message);

                    if (message != null) {
                        String upperCase = message.toUpperCase();
                        out.println(upperCase);
                        System.out.println("Sent message: " + upperCase);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
