
import java.io.IOException;
import java.net.*;

public class lab10Server {
    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(6000)) {
            System.out.println("UDP server is running on port 6000...");

            while (true) {
                byte[] receiveBuffer = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(
                        receiveBuffer, receiveBuffer.length);

                socket.receive(receivePacket);

                String message = new String(
                        receivePacket.getData(), 0,
                        receivePacket.getLength());

                System.out.println("Received message: " + message);

                String upperCase = message.toUpperCase();
                byte[] sendBuffer = upperCase.getBytes();

                try {
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer, sendBuffer.length,
                        clientAddress, clientPort);

                socket.send(sendPacket);

                System.out.println("Sent response: " + upperCase);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
