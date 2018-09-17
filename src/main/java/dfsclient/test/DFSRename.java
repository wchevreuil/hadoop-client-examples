package dfsclient.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileContext;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DFSRename extends Configured implements Tool {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      ToolRunner.run(new Configuration(), new DFSRename(), args);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void recurse(FileSystem fs, FileContext fsContext, String source) throws Exception {
    long init = System.currentTimeMillis();
    FileStatus[] files = fs.globStatus(new Path(source, "*"));
    System.out.println("on " + source);
    System.out.println("spent on globStatus: " + (System.currentTimeMillis() - init));
    for (FileStatus status : files) {
      if (status.isDirectory()) {
        recurse(fs, fsContext, source + "/" + status.getPath().getName());
      } else {
        init = System.currentTimeMillis();
        Path outputPath = new Path(source + "/moved");
        fs.mkdirs(outputPath);
        fsContext.rename(status.getPath(), new Path(outputPath, status.getPath().getName() + "."
            + System.nanoTime()));
        System.out.println("spent on renaming file " + status.getPath().getName() + ": "
            + (System.currentTimeMillis() - init));
      }
    }
  }


  public int run(String[] args) throws Exception {
    
    FileSystem fs = FileSystem.get(this.getConf());
    FileContext fsContext = FileContext.getFileContext(this.getConf());
    long init = System.currentTimeMillis();
    recurse(fs, fsContext, args[0]);
    System.out.println("total time spent: " + (System.currentTimeMillis() - init));
    // TODO Auto-generated method stub
    return 0;
  }

  public static void moveIndexFiles(final Configuration conf, final String dataOutputPath)
      throws IOException {

    FileSystem fs = FileSystem.get(conf);
    FileContext fsContext = FileContext.getFileContext(conf);
    Path inputPath = new Path(dataOutputPath);
    Path outputPath = new Path(inputPath.getParent().toUri().getPath());

    FileStatus[] fStatus = fs.globStatus(new Path(inputPath, "*"));
    for (FileStatus status : fStatus) {
      String fileName = status.getPath().toUri().getPath();
      if (status.isDirectory()) { // then move
        String subFileName = status.getPath().getName();
        if (subFileName.startsWith("cid")) { // which means it is a column directory
          String dirName = status.getPath().getName();
          Path outputPathWithColumnSplit = new Path(outputPath, dirName);
          boolean created = fs.mkdirs(outputPathWithColumnSplit);
          if (created) {
          }

          FileStatus[] indexFileStatus = fs.globStatus(new Path(status.getPath(), "*"));
          for (FileStatus indexFile : indexFileStatus) {
            String indexFileName = indexFile.getPath().getName();
            if (!indexFileName.startsWith("part")) { // part files are platform files, we don't need
              // them
              Path eventualFileName =
                  new Path(outputPathWithColumnSplit, indexFile.getPath().getName());
              fsContext.rename(indexFile.getPath(), eventualFileName);
            }
          }
        } else if (subFileName.startsWith("JobError")) {
          Path jobErrorOutputPath = new Path(outputPath, "JobError");
          boolean created = fs.mkdirs(jobErrorOutputPath);
          if (created) {
          }

          FileStatus[] jobErrorMinuteFolder = fs.globStatus(new Path(status.getPath(), "*"));
          for (FileStatus jobErrorFolder : jobErrorMinuteFolder) {
            Path eventualFolderName =
                new Path(jobErrorOutputPath, jobErrorFolder.getPath().getName());
            fsContext.rename(jobErrorFolder.getPath(), eventualFolderName);
          }
        }
      }
    }
  }
}
