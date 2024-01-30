package IntList;

import org.junit.Test;

import static org.junit.Assert.*;
public class TestIntList {
   /* the ll that we'll be testing on*/
    static IntList testLL = IntList.of(1, 4, 8, 10, 32, 23);
    @Test
  /*  tests that size function properly returns size of the board*/
    public void testSize() {
        //should expect my size function to return a size of 6
        assertEquals(testLL.size(), 6);
    }

}
