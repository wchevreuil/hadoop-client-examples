package jmx;

import java.io.IOException;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXClient {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stu

    // JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
    JMXServiceURL url = new JMXServiceURL(args[0]);
    JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

    System.out.println("Success!!!");
  }

}
