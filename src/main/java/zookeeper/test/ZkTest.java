package zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZkTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {

      final ZooKeeper zk = new ZooKeeper("10.17.81.106:2181", 500, new Watcher() {

        public void process(WatchedEvent event) {
          // TODO Auto-generated method stub
          System.out.println(event.getClass() + " ----->" + event);
          System.out.println("event type: " + event.getType());
        }
      });

      try {
        zk.exists("/test", new Watcher() {

          public void process(WatchedEvent event) {
            // TODO Auto-generated method stub
            System.out.println("Notified 1:" + event.getPath());
            System.out.println("Notified 2:" + event.getType());
            try {
              zk.exists("/test", this);
            } catch (KeeperException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        });
      } catch (KeeperException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      try {
        synchronized (zk) {
          zk.wait(60 * 5 * 1000);
        }

      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
