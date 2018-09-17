package dfsclient.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.zookeeper.common.IOUtils;

public class SequenceFiles {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    SequenceFiles f = new SequenceFiles();

    // f.readSequence(args[0]);
    f.writeSequence(args[0]);

  }

  private void readSequence(String filePath) throws IOException {

    Configuration config = new Configuration();


    Path path = new Path(filePath);

    SequenceFile.Reader r = new SequenceFile.Reader(config, SequenceFile.Reader.file(path));

    IntWritable key = (IntWritable) ReflectionUtils.newInstance(r.getKeyClass(), config);

    BytesWritable value = (BytesWritable) ReflectionUtils.newInstance(r.getValueClass(), config);

    while (r.next(key, value)) {
      System.out.println("key : " + key + " - value : " + new String(value.getBytes()));
    }
  }

  private void writeSequence(String filePath) throws IOException {

    Configuration config = new Configuration();
    System.out.println(config.get("fs.defaultFS"));

    final String user = "hdfs/nightly57-1.gce.cloudera.com@GCE.CLOUDERA.COM";
    final String keyPath = "/Users/wchevreuil/hdfs-nightly.keytab";
    UserGroupInformation.loginUserFromKeytab(user, keyPath);

    Path path = new Path(filePath);

    // SequenceFile.Writer writer = SequenceFile.createWriter(config, Writer.file(path),
    // Writer.keyClass(Text.class), Writer.valueClass(Text.class), Writer.bufferSize(4096),
    // Writer.replication((short) 3), Writer.blockSize(1073741824),
    // Writer.compression(SequenceFile.CompressionType.BLOCK, new DefaultCodec()),
    // Writer.progressable(null), Writer.metadata(new Metadata()));

    SequenceFile.Writer writer = SequenceFile.createWriter(config,
      SequenceFile.Writer.appendIfExists(true), SequenceFile.Writer.keyClass(Text.class),
      SequenceFile.Writer.valueClass(Text.class), SequenceFile.Writer.file(path),
      SequenceFile.Writer.compression(CompressionType.BLOCK, new BZip2Codec()));

    writer.append(new Text("3"), new Text("Value appended"));

    IOUtils.closeStream(writer);

  }

}
