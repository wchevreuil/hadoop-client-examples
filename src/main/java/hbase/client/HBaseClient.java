package hbase.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
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

//      String[] columns = Arrays.copyOfRange(args, 4, args.length);

      String[] columns = new String[Integer.parseInt(args[4])];

      for(int i=0; i<columns.length; i++){
        columns[i] = "cf:" + i;
      }

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

      List<Put> puts = new ArrayList<>();

      for (long i = root; i < total; i++) {
         
//        Put put = new Put(Bytes.toBytes("\n" + i + "\n"));
         
         for(String cellDesc : familyQualifiers){

           Put put = new Put(Bytes.toBytes("\n" + i + "\n"));

           String[] familyQualifier =  cellDesc.split(":");
           
           System.out.println(">>>> Adding row: test-" +  i + "; cell: " + cellDesc );
           
           put.add(Bytes.toBytes(familyQualifier[0]), Bytes.toBytes(familyQualifier[1]), Bytes.toBytes("val-" + i + "-cell: " + cellDesc));
           
//           table.put(put);

           puts.add(put);
         }
         
       }
       table.batch(puts);
      table.close();
       
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
}
