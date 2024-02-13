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

      int N = 500;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 2);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              L.addLast(randVal);
              System.out.println("addLast(" + randVal + ")");
          } else if (operationNumber == 1) {
              // size
              int size = L.size();
              System.out.println("size: " + size);
          }
      }
  }
}
