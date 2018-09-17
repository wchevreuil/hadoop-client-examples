package hbase.client;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseAdminClient {

  public static void main(String[] args) throws IOException, InterruptedException {
    // TODO Auto-generated method stub

    Configuration config = HBaseConfiguration.create();

    // HBaseAdmin admin = new HBaseAdmin(config);
    // for (HTableDescriptor tblDesc : admin.listTables()) {
    // for (HColumnDescriptor colDesc : tblDesc.getColumnFamilies()) {
    // colDesc.setScope(1);
    // admin.modifyColumn(tblDesc.getTableName(), colDesc);
    // }
    // }

    // HTableDescriptor tblDesc = new HTableDescriptor("my_tbl");
    // UserGroupInformation.setConfiguration(config);

    // UserGroupInformation.loginUserFromKeytab("hbase", "/Users/wchevreuil/hbase-simple.keytab");

    HBaseAdmin admin = new HBaseAdmin(config);

    for (HRegionInfo region : admin
        .getTableRegions(TableName.valueOf(Bytes.toBytesBinary("test-3")))) {
      System.out.println(region.getRegionName());
      System.out.println(Bytes.toString(region.getRegionName()));
      System.out.println(Bytes.toString(region.getStartKey()));
      System.out.println("-------------");
    }

    admin.deleteColumn(Bytes.toBytes("test-3"), "cf");

    // Connection con = ConnectionFactory.createConnection(config);
    //
    // Table table = con.getTable(TableName.valueOf(Bytes.toBytesBinary("INDEX_userEvent_joinId")));
    //
    // ResultScanner rs = table.getScanner(Bytes.toBytes("cf"));
    // System.out.println(Bytes.toString(rs.next().getRow()));
    // System.out.println(Bytes.toStringBinary(rs.next().getRow()));

    // for (HColumnDescriptor colDesc : tblDesc.getColumnFamilies()) {
    // colDesc.setScope(1);
    // admin.modifyColumn(tblDesc.getTableName(), colDesc);
    // }

    // for (TableName tbl : admin.listTableNames()) {
    // System.out.println(tbl.getName() + " " + HTable.isTableEnabled(config, tbl.getName()));
    // Thread.sleep(2 * 1000);
    // }
    // String tblName = args[0];
    // int numRegions = Integer.parseInt(args[1]);
    // int counter = 0;
    // System.out.println("iteration: 0");
    // List<HRegionInfo> regions = admin.getTableRegions(Bytes.toBytes(tblName));
    // while (regions.size() > numRegions) {
    // HRegionInfo previous = null;
    // for (HRegionInfo current : regions) {
    // if (!current.isSplit()) {
    // if (previous == null) {
    // previous = current;
    // } else {
    // if (HRegionInfo.areAdjacent(previous, current)) {
    // admin.mergeRegions(current.getEncodedNameAsBytes(), previous.getEncodedNameAsBytes(),
    // true);
    // System.out.println("merged adjacent regions " + current.getEncodedName() + " and "
    // + previous.getEncodedName());
    //
    // previous = null;
    // } else {
    // System.out.println("regions " + current.getEncodedName() + " and "
    // + previous.getEncodedName() + " are not adjacent");
    // }
    // }
    // }
    // }
    // counter++;
    // System.out.println("sleeping for 2 seconds before next iteration...");
    // Thread.sleep(2000);
    // regions = admin.getTableRegions(Bytes.toBytes(tblName));
    // System.out.println("iteration: " + counter);
    // }

  }

}
