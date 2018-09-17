package zookeeper.test;

import java.io.IOException;

import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClusterIdProtos;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperReadData {

  public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
    ZooKeeper zk = new ZooKeeper(args[0], 1000, new Watcher() {

      @Override
      public void process(WatchedEvent event) {
        // TODO Auto-generated method stub

      }
    });

    byte[] result = zk.getData("/hbase/replication/peers/" + args[1], null, null);

    System.out.println(Bytes.toString(result));

    int pblen = ProtobufUtil.lengthOfPBMagic();
    ClusterIdProtos.ClusterId.Builder builder = ClusterIdProtos.ClusterId.newBuilder();
    ClusterIdProtos.ClusterId cid = null;
    cid = builder.mergeFrom(result, pblen, result.length - pblen).build();

    System.out.println(cid.getClusterId());
    // return convert(cid);

  }

}
