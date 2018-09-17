package org.apache.hadoop.security;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class DFSDelegationTokenTest {

  public static void main(String[] args) throws IOException, InterruptedException {
    // TODO Auto-generated method stub

    Configuration config = new Configuration();

    config.set("hadoop.security.authentication", "kerberos");

    UserGroupInformation.setConfiguration(config);

    UserGroupInformation.loginUserFromKeytab("hbase", "/Users/wchevreuil/hbase-simple.keytab");

    UserGroupInformation ugi1 = UserGroupInformation.getCurrentUser();

    System.out.println(ugi1);
    // System.out.println(user);

    FileSystem fs = FileSystem.get(config);

    // System.out.println(fs.getDelegationToken(ugi1.getUserName()));

    for (FileStatus file : fs.listStatus(new Path("/hbase"))) {
      System.out.println(file.getPath().getName());
    }

    fs.closeAll();
    fs.close();
    fs.closeAllForUGI(ugi1);

    ugi1.logoutUserFromKeytab();

    UserGroupInformation.setLoginUser(null);

    ugi1.getCredentials();

    UserGroupInformation.setLoginUser(ugi1);

    fs = FileSystem.get(new Configuration());

    for (FileStatus file : fs.listStatus(new Path("/hbase/WALs"))) {
      System.out.println(file.getPath());
    }

  }

}
