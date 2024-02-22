package deque;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;
public class ArrayDequeTest {
    @Test
    public void addFirstEmpty() {
        int [] items = {8,0,0,0,0,0,0,0};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8);
        assertTrue("Should add 8 to Deque when removeFirst is called on an empty Deque,", 8 == testDeque.get(0));
    }
    @Test
    public void testResizeBigger() {
        int [] items = {8,1,2,3,4,5,6,7};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8);
        testDeque.addFirst(7);
        testDeque.addFirst(6);
        testDeque.addFirst(5);
        testDeque.addFirst(4);
        testDeque.addFirst(3);
        testDeque.addFirst(2);
        testDeque.addFirst(1);

        int oldSize = testDeque.size();
        //add new item to the testDeque
        testDeque.addLast(11);
        testDeque.addFirst(20);
        int newSize = testDeque.size();
        System.out.println("old size and new size are "+ oldSize + " " + newSize);
        assertTrue(newSize > oldSize);
    }
    @Test
    public void testResizeSmaller() {
        int [] items = {8,0,0,0,0,0,0,7};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8);
        testDeque.addFirst(7);
        int oldSize = testDeque.size();
        //remove one element
        int removed = testDeque.removeFirst();
        //get new size
        int newSize = testDeque.size();
        assertTrue(newSize < oldSize);
    }
    @Test
    public void addFirstNotFull() {
        int [] items = {8,0,0,0,0,5,6,7};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8);
        testDeque.addFirst(7);
        testDeque.addFirst(6);
        testDeque.addFirst(5);
        assertTrue("Should add first correctly,", 5 == testDeque.get(0));
    }
    @Test
    public void addLastEmpty() {
        int [] items = {8,0,0,0,0,0,0,0};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addLast(8);
        assertTrue("Should add 8 to Deque when removeFirst is called on an empty Deque,", 8 == testDeque.get(0));
    }
    @Test
    public void addLastNotFull() {
        int [] items = {8,7,6,5,0,0,0,0};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addLast(8);
        testDeque.addLast(7);
        testDeque.addLast(6);
        testDeque.addLast(5);
        assertTrue("Should add 5 to Deque when removeFirst is called on an Deque,", 5 == testDeque.get(3));
        testDeque.printDeque();
    }
    @Test
    public void removeFirst() {
        int [] items = {8,7,6,5,0,0,0,0};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addLast(8);
        testDeque.addLast(7);
        testDeque.addLast(6);
        testDeque.addLast(5);
       int removed = testDeque.removeFirst();
       assertTrue(removed == 8);
    }
    @Test
    public void removeLast() {
        int [] items = {8,7,6,5,0,0,0,0};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addLast(8);
        testDeque.addLast(7);
        testDeque.addLast(6);
        testDeque.addLast(5);
        int removed = testDeque.removeLast();
        assertTrue(removed == 5);
    }
    @Test
    public void removeEmpty() {
        ArrayDeque<Object> testDeque = new ArrayDeque<>();
        Object removedFirst = testDeque.removeFirst();
        Object removedLast = testDeque.removeLast();
        assertTrue(removedFirst == null);
        assertTrue(removedLast == null);
    }
    @Test
    public void removeOneFirst() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addLast(8);
        Integer removedFirst = testDeque.removeFirst();
        assertTrue(removedFirst == 8);
    }
    @Test
    public void iteratorTest() {
        int[] items = {8, 7, 6, 5, 12, 11, 10, 9};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8);
        testDeque.addFirst(9);
        testDeque.addFirst(10);
        testDeque.addFirst(11);
        testDeque.addFirst(12);
        testDeque.addLast(7);
        testDeque.addLast(6);
        testDeque.addLast(5);

        //first el
        int first = testDeque.get(0);
        //last el
        int last = testDeque.get(testDeque.size() - 1) ;

        for (int item : testDeque) {
//            System.out.println("item is " + item);
        }
    }
    @Test
    public void iteratorDoublesTest() {
        int [] items = {8,7,6,5,12,11,10,9};
        ArrayDeque<Double> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8.0);
        testDeque.addFirst(9.0);
        testDeque.addFirst(10.0);
        testDeque.addFirst(11.0);
        testDeque.addFirst(12.0);
        testDeque.addLast(7.0);
        testDeque.addLast(6.0);
        testDeque.addLast(5.0);
        //first el
        double first = testDeque.get(0);
        //last el
        double last = testDeque.get(testDeque.size() - 1);
        for (double item : testDeque) {
//            System.out.println("item is "+ item);
        }
    }
    @Test
    public void removeFirstTwice () {
        int [] items = {8,7,6,5,12,11,10,9};
        ArrayDeque<Double> testDeque = new ArrayDeque<>();
        testDeque.addLast(8.0);
        testDeque.addLast(9.0);
        testDeque.addLast(10.0);
        testDeque.addLast(11.0);
        testDeque.addLast(12.0);
        testDeque.addLast(7.0);
        testDeque.addLast(6.0);
        testDeque.addLast(5.0);

        double firstEl = testDeque.removeFirst();
        double secondEl= testDeque.removeFirst();

        assertTrue(secondEl == 9.0);
    }
    @Test
    public void negativeSize() {
        ArrayDeque<Double> testDeque = new ArrayDeque<>();
        testDeque.addLast(8.0);
        testDeque.addLast(9.0);
        testDeque.addLast(10.0);
        testDeque.addFirst(1.0);

        while (testDeque.size() > 0) {
            testDeque.removeLast();
        }
        assertTrue(testDeque.size() == 0);
        assertNull(testDeque.removeLast());
    }
    @Test
    public void addRemoveLastIsEmptyRandom() {
        ArrayDeque<Integer> testD = new ArrayDeque<>();
        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNum = StdRandom.uniform(0, 3);
            if (operationNum == 0) {
                int randVal = StdRandom.uniform(0, 500);
                testD.addLast(randVal);
            } else{
                if(!testD.isEmpty()) {
                    testD.removeLast();
                }
            }
        }
    }
    @Test
    public void addRemoveFirstIsEmptyRandom() {
        ArrayDeque<Integer> testD = new ArrayDeque<>();
        int N = 20000;
        for (int i = 0; i < N; i++) {
            int operationNum = StdRandom.uniform(0, 3);
            if (operationNum == 0) {
                int randVal = StdRandom.uniform(0, 500);
                testD.addFirst(randVal);
            } else{
                if(!testD.isEmpty()) {
                    testD.removeFirst();
                }
            }
        }
    }
    @Test
    public void getMethodWords() {
        double[] items = {8.0, 9.0, 10.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        ArrayDeque<Double> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8.0);
        testDeque.addLast(9.0);
        testDeque.addLast(10.0);
        testDeque.addFirst(1.0);
        double d1 = testDeque.get(0);
        double d2 = testDeque.get(1);
        double d3 = testDeque.get(2);
        double d4 = testDeque.get(3);

        assertTrue(d1 == 1.0);
        assertTrue(d2 == 8.0);
        assertTrue(d3 == 9.0);
        assertTrue(d4 == 10.0);
    }
    @Test
    public void testEquality() {
        //instantiate two different ArrayDeques
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        ArrayDeque<Integer> d2 = new ArrayDeque<>();
        int N = 10;
        for (int i = 0; i < N; i++) {
            //instantiate random number btw 0 and 5
            int operationNum = StdRandom.uniform(0, 3);
            if (operationNum == 0) {
                int randVal = StdRandom.uniform(0, 500);
                d1.addLast(randVal);
                d2.addLast(randVal);
            } else if (operationNum == 1) {
                int randVal = StdRandom.uniform(0, 50);
                d1.addFirst(randVal);
                d2.addFirst(randVal);
            } else if (operationNum == 2) {
                if (!d1.isEmpty()) {
                    d1.removeLast();
                    d2.removeLast();
                }
            }
        }
        //check the equality of both sizes
        assertEquals(d1.size(), d2.size());
        //check the equality of both deques
        d1.printDeque();
        d2.printDeque();
        assertEquals(true, d1.equals(d2));
    }

}
