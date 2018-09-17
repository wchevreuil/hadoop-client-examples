package dfsclient.test;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.EnumSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.ReadOption;
import org.apache.hadoop.hdfs.DFSConfigKeys;
import org.apache.hadoop.hdfs.client.HdfsDataInputStream;
import org.apache.hadoop.net.unix.DomainSocket;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class NcrDFSClient extends Configured implements Tool {

  private FileSystem fs = null;

  private void readNcr(String filePath) {

    FSDataInputStream is = null;
    Path path = new Path(filePath);
    try {
      is = fs.open(path);
      byte buffer[] = new byte[31457280];
      int i = 0;
      long init = System.currentTimeMillis();
      ByteBuffer bbf = is.read(null, 31457280, EnumSet.of(ReadOption.SKIP_CHECKSUMS));
      // System.out.println("after is.read: " + (System.currentTimeMillis() - init));
      // init = System.currentTimeMillis();
      // bbf.get(buffer);
      while (bbf.position() < bbf.limit()) {
        buffer[i] = bbf.get();
        i++;
      }
      System.out.println("Finished reading NCR in: " + (System.currentTimeMillis() - init));
      System.out.println("bytes read: " + i);
      System.out.println("--------------------------------------");
      is.releaseBuffer(bbf);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  public void read(String filePath) {
    FSDataInputStream is = null;
    try {
      is = fs.open(new Path(filePath));
      byte buffer[] = new byte[31457280];
      int i = 0;
      long init = System.currentTimeMillis();
      while (i < 31457280) {
        try {
          buffer[i] = is.readByte();
        } catch (EOFException e) {
          break;
        }
        i++;
      }

      synchronized (buffer) {
        try {
          buffer.wait(1000 * 60 * 10);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

      System.out.println("Finished reading non NCR in: " + (System.currentTimeMillis() - init));
      System.out.println("bytes read: " + i);
      System.out.println("--------------------------------------");

      System.out
          .println(((HdfsDataInputStream) is).getReadStatistics().getTotalShortCircuitBytesRead());
      System.out.println(((HdfsDataInputStream) is).getReadStatistics().getTotalLocalBytesRead());
      System.out.println(((HdfsDataInputStream) is).getReadStatistics().getRemoteBytesRead());

      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  // @Override
  public int run(String[] args) throws IOException {

    Configuration conf = this.getConf();

    conf.setBoolean(DFSConfigKeys.DFS_CLIENT_READ_SHORTCIRCUIT_KEY, true);

    conf.setInt(DFSConfigKeys.DFS_CLIENT_MMAP_CACHE_SIZE, 31457280);

    conf.setLong(DFSConfigKeys.DFS_CLIENT_MMAP_CACHE_TIMEOUT_MS, 100);

    conf.setBoolean(DFSConfigKeys.DFS_CLIENT_READ_SHORTCIRCUIT_SKIP_CHECKSUM_KEY, true);

    DomainSocket.disableBindPathValidation();

    fs = FileSystem.get(this.getConf());

    for(int i=0; i<5; i++){
      read(args[0]);
      // readNcr(args[0]);
      System.out.println("---------------------");
      System.out.println("Finished iteration " + i);
      System.out.println("---------------------");
    }

    fs.close();

    return 0;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      ToolRunner.run(new Configuration(), new NcrDFSClient(), args);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


}
