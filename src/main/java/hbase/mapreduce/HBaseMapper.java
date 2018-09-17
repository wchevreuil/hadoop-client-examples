package hbase.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

public class HBaseMapper extends TableMapper<Text, Text> {

  public void map(ImmutableBytesWritable row, Result value, Context context)
      throws InterruptedException, IOException {
    // process data for the row from the Result instance.
    System.out.println(Bytes.toString(row.get()));
    context.getCounter("ROWS_READ", "0").increment(1);
  }

}
