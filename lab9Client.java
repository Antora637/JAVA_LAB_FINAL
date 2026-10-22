
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class lab9Client {
    public static void main(String[] args) {

        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter a message: ");
            String message = sc.nextLine();

            output.write(message + '\n');
            output.flush();

            String response = input.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
