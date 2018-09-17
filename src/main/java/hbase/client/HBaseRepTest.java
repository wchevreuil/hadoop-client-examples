package hbase.client;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.replication.ReplicationAdmin;

public class HBaseRepTest {

  public static void main(String[] args) throws IOException {

    System.setProperty("user.name", "hbase");
    // TODO Auto-generated method stub
    Configuration config = HBaseConfiguration.create();
    System.out.println(">>>>>>>>>> user: " + System.getenv("user.name"));
    ReplicationAdmin admin = new ReplicationAdmin(config);
    System.out.println(">>>>>>>>>> 2");

    admin.enableTableRep(TableName.valueOf(args[0]));

  }

}
