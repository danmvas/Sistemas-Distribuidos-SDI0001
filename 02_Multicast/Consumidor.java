import java.net.*;
import java.io.*;


public class Consumidor {

    public static void main(String[] args) {
        MulticastSocket socket = null;
        DatagramPacket inPacket = null;
        byte[] inBuf = new byte[256];

        try {
          int port = Integer.parseInt(args[0]);
          int num_clients = Integer.parseInt(args[1]);
          InetAddress address = InetAddress.getByName("10.20.221.234");
          
          socket.joinGroup(address);

          int num_received = 0;
          while (num_received < num_clients) {
              socket.receive(inPacket);
              String msg = new String(inBuf, 0, inPacket.getLength());
              String msgInUpperCase = msg.toUpperCase();
              System.out.println("From " + inPacket.getAddress() + " Msg: " + msgInUpperCase);
              num_received++;
          }

          socket.leaveGroup(address);
          socket.close();

        } catch (IOException ioe) {
          System.out.println(ioe);
        }

        
    }

}