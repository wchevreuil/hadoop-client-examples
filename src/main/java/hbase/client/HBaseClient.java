package hbase.client;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseClient {

  /**
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws Exception {
  
    switch (args[0]) {
    case "scan_table":

      (new HBaseClient()).readTableRowkeys(args[1]);

      break;

    case "insert_rows":

      String tableName = args[1];

      long start = Long.parseLong(args[2]);

      long end = Long.parseLong(args[3]);

      String[] columns = Arrays.copyOfRange(args, 4, args.length);

      (new HBaseClient()).bulkInsertData(tableName, columns, start, end);

      break;

    default:
      System.out.println("Wrong options!");
      System.out.println("Usage: [option] table_name [option_args]");
      System.out.println("-------");

    }

  }
  
  public void readTableRowkeys(String tableName){

    Configuration config = HBaseConfiguration.create();
    config.set("","");

    try {
      while (true) {
      HTable table = new HTable(config, tableName);

      Scan scan = new Scan();

      ResultScanner result = table.getScanner(scan);

      for (Result r : result) {

        System.out.println(Bytes.toString(r.getRow()));

      }

      table.close();
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }    
    
    
  }

  public void bulkInsertData(String tableName, String[] familyQualifiers, long root, long total)
      throws IOException {

    Configuration config = HBaseConfiguration.create();

    // UserGroupInformation.setConfiguration(config);

    // UserGroupInformation.loginUserFromKeytab("hbase", "/Users/wchevreuil/hbase-simple.keytab");

    // System.out.println(UserGroupInformation.getCurrentUser());

    try {
      // while (true) {
      HTable table = new HTable(config, tableName);

       for (long i = root; i < total; i++) {
         
        Put put = new Put(Bytes.toBytes("\n" + i + "\n"));
         
         for(String cellDesc : familyQualifiers){
           
           String[] familyQualifier =  cellDesc.split(":");
           
           System.out.println(">>>> Adding row: test-" +  i + "; cell: " + cellDesc );
           
           put.add(Bytes.toBytes(familyQualifier[0]), Bytes.toBytes(familyQualifier[1]), Bytes.toBytes("val-" + i));
           
           table.put(put);
           
         }
         
       }
       
      table.close();
       
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
}
