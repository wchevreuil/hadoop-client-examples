package dfsclient.test;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class DFSClientTest {

  /**
   * @param args
   */
  public static void main(String[] args) {

    String nnHost = args[0]; // "hdfs://host-10-16-8-107.openstacklocal:8020"

    String fileToRead = args[1];

    try {
      Configuration config = new Configuration();
//       DistributedFileSystem fs = new DistributedFileSystem();
      // fs.initialize(new URI(nnHost), config);
      // System.out.println(fs.getWorkingDirectory().getName());
      // DatanodeInfo[] dns = fs.getDataNodeStats();
      // for (DatanodeInfo dn : dns) {
      // System.out.println("--------------");
      // System.out.println(dn.getHostName());
      // System.out.println("DFS Used: " + dn.getDfsUsedPercent());
      // System.out.println("Remaining: " + dn.getRemainingPercent());
      // System.out.println("Capacity: " + dn.getCapacity());
      // }

      // UserGroupInformation.setConfiguration(config);

      // UserGroupInformation.loginUserFromKeytab("hbase", "/Users/wchevreuil/hbase-simple.keytab");

      final DistributedFileSystem fs = (DistributedFileSystem)FileSystem.get
          (config);

      // FSDataInputStream is = fs.open(new Path(fileToRead));
      //
      // long init = System.currentTimeMillis();
      // while (is.read() >= 0) {
      //
      // }
      // System.out.println("Finished in: " + (System.currentTimeMillis() - init));
      //
      // for (FileStatus f : fs.listStatus(new Path(
      // "/hbase/WALs/host-10-17-81-177.coe.cloudera.com,60020,1462378543188"))) {
      //
      // System.out.println(f.getPath().getName() + " - " + new Date(f.getAccessTime()));
      //
      // }

      // FSDataOutputStream os = fs.create(new Path("/test/my-test10"));
      int i = 0;
      // while (i < (50 * 1024 * 1024)) {
      // os.writeBytes("test");
      // System.out.println("writing test " + i);
      // os.hflush();
      // os.getWrappedStream().flush();
      // synchronized (os) {
      // os.wait(70000);
      // }
      // i++;
      // }

      OutputStream os = new BufferedOutputStream(fs.create(new Path
          ("/test/test-open-for-write")));

      System.out.println(" file created, waiting...");

//      synchronized (os) {
//        os.wait(300000);
//      }

      while (i < (50)) {

        System.out.println(" waiting before write test " + i);

        synchronized (os) {
          os.wait(70000);
        }

        os.write("1".getBytes(), 0, "1".getBytes().length);

        System.out.println("wrote test " + i);

        synchronized (os) {
          os.wait(30000);
        }
        i++;

        os.flush();

        fs.rename(new Path("/test/my-test17"), new
            Path("/test/my-test17-renamed"));

        fs.recoverLease(new
            Path("/test/my-test17-renamed"));

      }

      // is.close();
      // fs.close();
      fs.close();
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
