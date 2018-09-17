package dfsclient.mapreduce.filechecker;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;

public class FileCheckerDriver extends Configured implements Tool {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  @Override
  public int run(String[] args) throws Exception {
    // TODO Auto-generated method stub
    Job job = new Job();

    FileInputFormat.addInputPath(job, new Path(args[0]));

    return 0;
  }

}
