
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class lab10Clientnew {
    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter a message: ");
            String message = sc.nextLine();
            // String message = "a".repeat(1100);

            byte[] sendBuffer = message.getBytes();

            if (sendBuffer.length > 1024) {
                System.out.println(
                    "Error: Message size exceeds 1024 bytes!");
                return;
            }

            InetAddress serverAddress =
                    InetAddress.getByName("localhost");

            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer, sendBuffer.length,
                    serverAddress, 6000);

            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(
                    receiveBuffer, receiveBuffer.length);

            socket.receive(receivePacket);

            String response = new String(
                    receivePacket.getData(), 0,
                    receivePacket.getLength());

            System.out.println("Server response: " + response);

        } catch (PortUnreachableException e) {
            System.out.println("Error: Server is not reachable!");

        } catch (IOException e) {
            System.out.println(
                "Error: Unable to communicate with server!");
        }
    }
}
