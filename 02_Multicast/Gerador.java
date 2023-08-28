import java.io.*;
import java.net.*;

public class Gerador {

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        String message = args[1];

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("10.20.221.234");
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
        }
    }
}
