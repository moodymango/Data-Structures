package deque;
import org.junit.Test;
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
        assertTrue(testDeque.isFull());
    }
    @Test
    public void addFirstEmpty() {
        int [] items = {8,0,0,0,0,0,0,0};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
       testDeque.addFirst(8);
       assertTrue("Should add 8 to Deque when removeFirst is called on an empty Deque,", 8 == testDeque.get(0));
    }
    @Test
    public void addFirstFull() {
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

    }
    @Test
    public void addFirstNotFull() {
        int [] items = {8,0,0,0,0,5,6,7};
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addFirst(8);
        testDeque.addFirst(7);
        testDeque.addFirst(6);
        testDeque.addFirst(5);
        assertTrue("Should add 8 to Deque when removeFirst is called on an empty Deque,", 5 == testDeque.get(5));
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

    }
}
