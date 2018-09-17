package dfsclient.test;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by wchevreuil on 02/05/2018.
 */
public class ValueConverter {

  public static void main(String[] args){

    System.out.println(Bytes.toHex(Bytes.toBytes("10")));

    System.out.println(Bytes.toString(Bytes.fromHex("393030303031363035383431383430382d39323233333730353139313735363330313434")));

  }
}
