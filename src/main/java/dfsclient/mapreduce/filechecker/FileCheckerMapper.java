package dfsclient.mapreduce.filechecker;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FileCheckerMapper extends Mapper<Text, Text, Text, Text> {

  @Override
  public void map(Text key, Text value, Context c) {
  }

}
