package hbase.client;

import org.apache.hadoop.hbase.mapreduce.ImportTsv;

public class BulkLoadWrapper {

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {

    System.setProperty("importtsv.columns", "HBASE_ROW_KEY,cf1:c1");
    ImportTsv.main(args);

  }

}
