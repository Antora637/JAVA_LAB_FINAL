
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class lab10Client {
    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter a message: ");
            String message = sc.nextLine();

            byte[] sendBuffer = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost");

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
