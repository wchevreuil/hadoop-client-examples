package hbase.client;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class EdmundsClient {

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub

    // String encoded =
    // "\\\\xF8\\\\xB9B2!$\\\\x9C\\\\x0A\\\\xFEG\\\\xC0\\\\xE3\\\\x8B\\\\x1B\\\\xFF\\\\x15";
    // StringBuilder b = new StringBuilder();
    // for (String sub : encoded.split("x")) {
    // String[] words = sub.split("\\\\");
    // if (words.length > 0) {
    // // System.out.println((char) Integer.parseInt(words[0].substring(0, 2), 16));
    // // System.out.println(Integer.parseInt(words[0].substring(0, 2), 16));
    // char ch = (char) Integer.parseInt(words[0].substring(0, 2), 16);
    // System.out.println(String.format("\\x%02X", ch & 0xFF));
    // b.append((char) Integer.parseInt(words[0].substring(0, 2), 16));
    // if (words[0].length() > 2) {
    // System.out.println(words[0].substring(2, words[0].length()));
    // b.append(words[0].substring(2, words[0].length()));
    // }
    // }
    // }

    byte[] bytes = {
        (byte)0b11111000,(byte)0b10111001,(byte)0b01000010,(byte)0b00110010,(byte)0b00100001,
        (byte)0b00100100,(byte)0b10011100,(byte)0b00001010,(byte)0b11111110,(byte)0b01000111,
        (byte)0b11000000,(byte)0b11100011,(byte)0b10001011,(byte)0b00011011,(byte)0b11111111,
        (byte)0b00010101
    };

    System.out.println(Bytes.toString(bytes));

    System.out.println(Bytes.toStringBinary(bytes));

    System.out.println(">>>>");
    for (byte b : bytes) {
      System.out.println(b + " - " + (b & 0b11111111));
    }
    System.out.println(">>>>");

    // System.out.println(Bytes.toStringBinary(Bytes.toBytes(b.toString())));

    Configuration config = HBaseConfiguration.create();
    // //
    // UserGroupInformation.setConfiguration(config);
    // //
    // UserGroupInformation.loginUserFromKeytab("hbase", "/Users/wchevreuil/hbase-simple.keytab");
    // //
    // System.out.println(UserGroupInformation.getCurrentUser());
    //
    // splits the region by the crazy row key value
    // HBaseAdmin admin = new HBaseAdmin(config);
    //
    // admin.split(Bytes.toBytes("test,,1484176886893.0f4ba152c19cccefbbe8e2356a8ec19a."), bytes);

    // System.out.println("done splitting");
    // for (HRegionInfo region : admin.getTableRegions(Bytes.toBytes("test"))) {
    // System.out.println(region.getRegionNameAsString());
    // System.out.println(">>>> " + region.getRegionNameAsString());
    // for (byte b : region.getRegionName()) {
    // System.out.println(b);
    // }
    // System.out.println(">>>>");
    // // admin.majorCompact(region.getRegionName());
    // // System.out.println("major compacted: " + region.getRegionNameAsString());
    // }
//    admin.majorCompact(
//      "test,\\xF8\\xB9B2!$\\x9C\\x0A\\xFEG\\xC0\\xE3\\x8B\\x1B\\xFF\\x15,1481745228583.b4bc69356d89018bfad3ee106b717285.");
//
//    System.out.println("done major compacting");

    // Adds a row with the crazy row key value
    HTable table = new HTable(config, "test");
    //
    // Put put = new Put(bytes);
    //
    // put.add(Bytes.toBytes("cf1"), Bytes.toBytes("1"), Bytes.toBytes("value"));
    //
    // table.put(put);
    //
    // table.close();

  }

}
