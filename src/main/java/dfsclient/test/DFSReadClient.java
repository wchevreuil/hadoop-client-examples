package dfsclient.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class DFSReadClient {

  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    Configuration config = new Configuration();

    FileSystem fs = FileSystem.get(config);

    FSDataInputStream is = fs.open(new Path("/test/my-test9"));

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

    String line = null;
    synchronized (config) {
      config.wait(60 * 1000 * 2);
    }
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
      synchronized (config) {
        config.wait(60 * 1000 * 2);
      }

    }

  }

}
