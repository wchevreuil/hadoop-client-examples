package hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.security.access.AccessControlClient;
import org.apache.hadoop.hbase.security.access.UserPermission;
import org.apache.hadoop.security.UserGroupInformation;

public class HBaseNewAdmin {

  public static void main(String[] args) throws Throwable {
    // TODO Auto-generated method stub

    Configuration config = HBaseConfiguration.create();

    // Connection connection = ConnectionFactory.createConnection(config);

    UserGroupInformation.setConfiguration(config);
    UserGroupInformation.loginUserFromKeytab("hbase/host-10-17-81-171.coe.cloudera.com@EXAMPLE.COM",
      "/Users/wchevreuil/hbase.keytab");

    for (UserPermission perm : AccessControlClient.getUserPermissions(config, "test")) {
      System.out.println(perm);
    }

    // Configuration config = HBaseConfiguration.create();
    // HTable table = new HTable(config, "test");
    // Scan scan = new Scan();
    // ResultScanner result = table.getScanner(scan);
    // for (Result r : result) {
    // byte[] b = r.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("c1"));
    // System.out.println("--------->printing results");
    // System.out.println(b);
    // String out = new String(b);
    // System.out.println("--------->printing results to string");
    // System.out.println(out);
    //
    // }

  }

}
