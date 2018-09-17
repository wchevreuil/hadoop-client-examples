package dfsclient;

import org.apache.hadoop.hdfs.server.blockmanagement.BlockInfo;
import org.apache.hadoop.hdfs.protocol.Block;
import org.apache.hadoop.hdfs.util.FoldedTreeSet;

import java.util.Random;

/**
 * Created by wchevreuil on 22/11/2017.
 */
public class TestFoldedTreeSet {

  public static void main(String[] args) {

    FoldedTreeSet<BlockInfo> tree = new FoldedTreeSet<>();

    int lastBlockId = 1_000_000;

    long totalTime = System.currentTimeMillis();

    for (int i = 0; i < lastBlockId; i++) {

      Block block = new Block(i);

      BlockInfo bInfo = new BlockInfo(block, (short) 1);

      long start = System.currentTimeMillis();

      tree.add(bInfo);

      long lasting = (System.currentTimeMillis() - start);

      if (lasting > 0) {

        System.out.println(">>>tree adding took: " + lasting + "ms");

      }

    }

    System.out.println(">>> Total for adding: " + (System
        .currentTimeMillis() - totalTime));

//    Random randomGenerator = new Random();

    int initDelete = 0;
    while (true) {
      //remove 1,000 at random
      for (int i = initDelete; i < initDelete + 1000; i++) {

//        int bId = randomGenerator.nextInt(lastBlockId);

//        Block block = new Block(bId);
        Block block = new Block(i);

        BlockInfo bInfo = new BlockInfo(block, (short) 1);

        long start = System.currentTimeMillis();

        tree.remove(bInfo);

        long lasting = (System.currentTimeMillis() - start);

        if (lasting > 0)

          System.out.println(">>>tree removal of " + i + " took: " + lasting +
              "ms");

      }

      initDelete += 1000;

      System.out.println(">>> current tree size: " + tree.size());

      //add 1,000 new
      for (int i = lastBlockId; i < (lastBlockId + 1000); i++) {

        Block block = new Block(i);

        BlockInfo bInfo = new BlockInfo(block, (short) 1);

        tree.add(bInfo);

      }

      lastBlockId += 1000;

      System.out.println(">>> current tree size: " + tree.size());

    }
  }
}
