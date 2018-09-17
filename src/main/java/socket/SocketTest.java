package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Security;

public class SocketTest {

  /**
   * @param args
   * @throws IOException
   * @throws InterruptedException
   */
  public static void main(String[] args) throws Exception {

    if (args[0].equals("server")) {

      initServer();

    } else if (args[0].equals("client")) {

      initClient(args[1]);

    }
  }

  public static void initServer() throws Exception {

    ServerSocket serverSocket = new ServerSocket(9999);

    System.out.println("listening on 9999");

    Socket client = serverSocket.accept();

    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

    String line = null;

    while ((line = reader.readLine()) != null) {

      System.out.println(line);

    }
  }

  public static void initClient(String address) throws Exception {

    System.out.println(Security.getProperty("networkaddress.cache.ttl"));

    Socket socket = new Socket(address, 9999);

    System.out.println("connected to " + socket.getRemoteSocketAddress().toString()
        + " will close the socket, wait for 2 mins and try again");

    socket.close();

    synchronized (address) {
      address.wait(2 * 60 * 1000);
    }

    try {

      socket = new Socket(address, 9999);
      socket.close();

    } catch (Throwable t) {

      t.printStackTrace();

      System.out.println("will sleep and try again....");

      synchronized (address) {
        address.wait(2 * 60 * 1000);
      }

      socket = new Socket(address, 9999);

    }
    PrintWriter writer = new PrintWriter(socket.getOutputStream());

    writer.write("Writing to server");

    writer.flush();

    writer.close();

  }

}
