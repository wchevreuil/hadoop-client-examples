package dfsclient.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class DFSHAClient {

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    Configuration config = new Configuration();
    FileSystem fs = FileSystem.get(config);
    Path myPath = new Path("/user/root");
    FileStatus[] files = fs.listStatus(myPath);
    for (FileStatus file : files) {
      System.out.println(file.toString());
    }

    System.out.println(myPath);

  }

}
