package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
    public void testThreeAddThreeRemove () {
      BuggyAList<Integer> buggyL = new BuggyAList<>();
      AListNoResizing<Integer> correctL = new AListNoResizing<>();
      int count = 10;
      while(count > 7) {
          buggyL.addLast(count);
          correctL.addLast(count);
          count--;
      }
      buggyL.removeLast();
      correctL.removeLast();
      
      assertEquals(buggyL.size(), correctL.size());

      assertEquals(buggyL.removeLast(), correctL.removeLast());
      assertEquals(buggyL.removeLast(), correctL.removeLast());
  }
  @Test
  public void randomizedTest() {
      AListNoResizing<Integer> L = new AListNoResizing<>();
      BuggyAList<Integer> buggyL = new BuggyAList<>();

      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 4);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              L.addLast(randVal);
              buggyL.addLast(randVal);
          } else if (operationNumber == 1) {
              // size
              int size = L.size();
              int buggySize = buggyL.size();
              assertEquals(size, buggySize);
          } else if (operationNumber ==  2) {
              if (L.size() > 0) {
                  assertEquals(L.getLast(), buggyL.getLast());
              }
          } else {
              if (L.size() > 0) {
                  assertEquals(L.removeLast(), buggyL.removeLast());
              }
          }
      }
  }
}
