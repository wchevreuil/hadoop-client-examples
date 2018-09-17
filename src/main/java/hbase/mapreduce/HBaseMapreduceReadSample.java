package hbase.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobPriority;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class HBaseMapreduceReadSample extends Configured implements Tool {

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    ToolRunner.run(new Configuration(), new HBaseMapreduceReadSample(), args);
  }

  public int run(String[] args) throws Exception {
    TableMapReduceUtil.addDependencyJars(getConf(), HBaseConfiguration.class, JobPriority.class);
    Configuration config = HBaseConfiguration.create();
    Job job = new Job(config, "ClouderaSupportSampleRead");
    job.setJarByClass(HBaseMapreduceReadSample.class); // class that contains mapper

    Scan scan = new Scan();
    scan.setCacheBlocks(false);  // don't set to true for MR jobs
    // set other scan attrs

    TableMapReduceUtil.addDependencyJars(job);
    TableMapReduceUtil.initTableMapperJob(
      args[0], // input HBase table name
      scan,             // Scan instance to control CF and attribute selection
      HBaseMapper.class, // mapper
      Text.class, // mapper output key
      Text.class, // mapper output value
      job);
    job.setOutputFormatClass(NullOutputFormat.class);   // because we aren't emitting anything from mapper

    boolean b = job.waitForCompletion(true);
    if (!b) {
      throw new IOException("error with job!");
    }
    return 0;
  }

}
