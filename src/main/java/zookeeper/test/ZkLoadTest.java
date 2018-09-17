package zookeeper.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

public class ZkLoadTest {

  private static final Id WORLD_ANYONE = new Id();

  private static final ACL ACL_PUBLIC = new ACL();

  static {

    WORLD_ANYONE.setId("anyone");

    WORLD_ANYONE.setScheme("world");

    ACL_PUBLIC.setId(WORLD_ANYONE);

    ACL_PUBLIC.setPerms(31);
  }

  public static void main(String[] args) throws Exception {

    final ZooKeeper zk = new ZooKeeper("10.17.81.106:2181", 1000, new Watcher() {

      public void process(WatchedEvent event) {
        // TODO Auto-generated method stub
        System.out.println(event.getClass() + " ----->" + event);
        System.out.println("event type: " + event.getType());
      }
    });

    List<ACL> acls = new ArrayList<ACL>();

    acls.add(ACL_PUBLIC);

    zk.create("/load-test", "lod-test-data".getBytes(), acls, CreateMode.PERSISTENT);

    // zk.delete("/load-test", -1);

    for (int i = 0; i < 10_000_00; i++) {

      zk.create("/load-test/load-test-" + i, "lod-test-data".getBytes(), acls,
        CreateMode.PERSISTENT);
      // zk.delete("/load-test-" + i, -1);
      System.out.println(i);

    }

  }

}
