package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimesEmpty() {
        IntList lst = IntList.of();
        boolean changed = IntListExercises.squarePrimes(lst);
        assertFalse(changed);
    }
    @Test
    public void testSquarePrimesAllPrimes() {
        IntList lst = IntList.of( 2, 3, 7, 11, 13);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 49 -> 121 -> 169", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimesAllComposites() {
        IntList lst = IntList.of(4, 6, 8, 12, 14);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 6 -> 8 -> 12 -> 14", lst.toString());
        assertFalse(changed);
    }

}
