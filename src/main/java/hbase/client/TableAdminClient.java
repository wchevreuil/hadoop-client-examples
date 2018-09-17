package hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.regionserver.HRegionFileSystem;
import org.apache.hadoop.hbase.util.FSTableDescriptors;

/**
 * Created by wchevreuil on 08/11/2017.
 */
public class TableAdminClient {

  public static void main(String[] args) throws Exception {

    Configuration config = new Configuration();

    FileSystem fs = FileSystem.get(config);

    if(args[0].equals("-checkTableInfo")) {

      HTableDescriptor descriptor = FSTableDescriptors.getTableDescriptorFromFs
          (fs, new Path(args[1]));

      System.out.println(descriptor.toString());

    } else {

      HRegionInfo regionInfo = HRegionFileSystem.loadRegionInfoFileContent
          (fs, new Path(args[1]));

      System.out.println(regionInfo.toString());
    }
  }

}
