package dfsclient.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class DFSHAAdminClient {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub

    Process p = Runtime.getRuntime().exec(args[0]);

    p.waitFor();

    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

    String line = reader.readLine();
    while (line != null) {
      System.out.println(line);
      line = reader.readLine();
    }

  }

}
