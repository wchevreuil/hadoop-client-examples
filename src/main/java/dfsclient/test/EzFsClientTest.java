package dfsclient.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.fs.Path;

/**
 * Created by wchevreuil on 27/10/2017.
 */
public class EzFsClientTest {


  public static void main(String[] args) throws Exception{

    Configuration config = new Configuration();

    UserGroupInformation.setConfiguration(config);

    UserGroupInformation.loginUserFromKeytab("hdfs",
        args[1]);

    Path path = new Path(args[0]);

    FileSystem fs = path.getFileSystem(config);

    FSDataOutputStream fsOut = fs.create(path);

    System.out.println(">>>> os instance: " + fsOut.getWrappedStream().getClass());

    fsOut.write("testing".getBytes());

    fsOut.flush();

    fs.close();

  }


}
