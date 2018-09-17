package hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

public class BulkLoadClient {

  public static void main(String[] args) throws Exception {

    Configuration config = HBaseConfiguration.create();

    HTable table = new HTable(config, args[0]);

    LoadIncrementalHFiles bulkLoad = new LoadIncrementalHFiles(config);

    bulkLoad.doBulkLoad(new Path(args[1]), table);

  }

}
