  package dfsclient.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.server.namenode.NameNode;
import org.apache.hadoop.security.UserGroupInformation;

import java.security.PrivilegedExceptionAction;

  /**
 * Created by wchevreuil on 18/07/2018.
 */
public class CheckAccessTest {

  public static void main(String[] args) throws Exception {

    Configuration config = new Configuration();

    UserGroupInformation.loginUserFromSubject(null);

    final String path = args[0];

    if(args.length == 4) {

      UserGroupInformation.setConfiguration(config);

      UserGroupInformation.loginUserFromKeytab(args[1],
          args[2]);

      UserGroupInformation ugi = UserGroupInformation.createProxyUser(args[3],
          UserGroupInformation.getLoginUser());

      ugi.doAs(new PrivilegedExceptionAction<Void>() {
        public Void run() throws Exception{

          Configuration config = new Configuration();

          DFSClient client = new DFSClient(NameNode.getAddress(config), config);

          client.checkAccess(path, FsAction.READ_EXECUTE);

          return null;
        }
      });


    } else {

      DFSClient client = new DFSClient(NameNode.getAddress(config), config);

      client.checkAccess(args[0], FsAction.READ_EXECUTE);

    }
  }
}
