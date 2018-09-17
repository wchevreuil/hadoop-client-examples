package hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

public class HBaseSecurity {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    Configuration config = HBaseConfiguration.create();

    String tblName = "security-test-" + System.currentTimeMillis();

    config.set("hbase.zookeeper.quorum", "nightly55-kerberized-1.vpc.cloudera.com");
    config.set("hbase.zookeeper.property.clientPort", "2181");
    config.set("hadoop.security.authentication", "kerberos");
    config.set("hbase.security.authentication", "kerberos");

    try {

      UserGroupInformation.setConfiguration(config);
      UserGroupInformation.loginUserFromKeytab(
        "hbase/host-10-17-81-171.coe.cloudera.com@EXAMPLE.COM", "/Users/wchevreuil/hbase.keytab");

      HBaseAdmin admin = new HBaseAdmin(config);

      HTableDescriptor tableDesc = new HTableDescriptor(Bytes.toBytes(tblName));

      HColumnDescriptor columnDesc = new HColumnDescriptor(Bytes.toBytes("cf1"));

      tableDesc.addFamily(columnDesc);

      admin.createTable(tableDesc);

      System.out.println("table created....");

      System.out.println("performing a put...");

      HTable table = new HTable(config, tblName);

      Put put = new Put(Bytes.toBytes("test-0001"));

      put.add(Bytes.toBytes("cf1"), Bytes.toBytes("c1"), Bytes.toBytes("value-1"));

      table.put(put);

      table.flushCommits();

      System.out.println("waiting for 6 minutes");

      synchronized (tblName) {
        tblName.wait(6 * 60 * 1000);
      }

      System.out.println("performing another put");

      put = new Put(Bytes.toBytes("test-0002"));

      put.add(Bytes.toBytes("cf1"), Bytes.toBytes("c1"), Bytes.toBytes("value-2"));

      table.put(put);

      table.flushCommits();

      System.out.println("2nd put done, bye!");

      admin.disableTable(tblName);

      admin.deleteTable(tblName);

      admin.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
