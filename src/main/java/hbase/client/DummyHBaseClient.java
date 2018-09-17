package hbase.client;

/**
 * Created by wchevreuil on 29/11/2017.
 */
public class DummyHBaseClient {

  public static void main (String args[]){

    final HBaseClient client = new HBaseClient();

    for(int i=0; i<500; i++){
      Runnable runnable = new Runnable() {

        @Override public void run() {
          System.out.println(" Thread running");
          client.readTableRowkeys("test-1");

        }
      };
      (new Thread(runnable)).start();
    }

  }

}
