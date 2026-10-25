
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class lab9Clientnew {
    public static void main(String[] args) {

        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
             Scanner sc = new Scanner(System.in)) {

            // System.out.print("Enter a message: ");
            // String message = sc.nextLine();
            String message = "a".repeat(1100);

            // Check message size
            if (message.getBytes().length > 1024) {
                System.out.println(
                    "Error: Message exceeds 1024 bytes!");
                return;
            }

            output.write(message + '\n');
            output.flush();

            String response = input.readLine();

            System.out.println("Server response: " + response);

        } catch (ConnectException e) {
            System.out.println(
                "Error: Unable to connect to server!");

        } catch (IOException e) {
            System.out.println(
                "Communication error: " + e.getMessage());
        }
    }
}
