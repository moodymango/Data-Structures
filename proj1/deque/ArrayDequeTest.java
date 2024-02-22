package deque;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
public class ArrayDequeTest {
    @Test
    public void isFulladdFirstOnly() {
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
        testDeque.addFirst(0);
        //test no longer valid because array dynamically resizes.
//        assertTrue(testDeque.isFull());
    }
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
    public void addLastFull() {

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
        System.out.println("first el is " + first);
        System.out.println("last el is " + last);

        for (int item : testDeque) {
            System.out.println("looping through arrayDeque");
            System.out.println("item is " + item);
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
        System.out.println("first el is " + first);
        System.out.println("last el is " + last);

        for (double item : testDeque) {
            System.out.println("looping through arrayDeque");
            System.out.println("item is "+ item);
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
    }

}
