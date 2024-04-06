package flik;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void sameNum(){
        assertTrue(Flik.isSameNumber(5, 5));
        assertTrue(Flik.isSameNumber(-3, -3));
        assertTrue(Flik.isSameNumber(127, 127));
    }
    @Test
    public void diffNum(){
        assertFalse(Flik.isSameNumber(-5, 5));
        assertFalse(Flik.isSameNumber(100000000, 1000000));
        assertFalse(Flik.isSameNumber(9, 6));
    }
    @Test
    public void threeDigitNums(){
        assertEquals(true, Flik.isSameNumber(100, 100));
        assertTrue(Flik.isSameNumber(127, 127));
        assertEquals(true, Flik.isSameNumber(10000, 10000));
    }
    @Test
    public void past128Nums(){
        assertEquals(true, Flik.isSameNumber(10000, 10000));
        assertEquals(true, Flik.isSameNumber(10000000, 10000000));
        assertEquals(true, Flik.isSameNumber(129, 129));
    }
}
