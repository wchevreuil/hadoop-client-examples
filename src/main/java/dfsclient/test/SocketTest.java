package dfsclient.test;

import java.io.IOException;

import org.apache.hadoop.net.unix.DomainSocket;

public class SocketTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    DomainSocket sock = null;
    try {
      sock = DomainSocket.connect(args[0]);
      sock.setAttribute(DomainSocket.RECEIVE_TIMEOUT, 200);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
