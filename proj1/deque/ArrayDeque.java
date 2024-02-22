package deque;
import java.util.Iterator;
/*Invariants*/
//circular implementaton viz (front = (rear + 1)% length)
/*Circular queue implementation - @source - Jenny's' Lectures CS IT
- https://www.youtube.com/watch?v=dn01XST9-bI*/
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private double maxLoadFactor = 0.75;
    private double minLoadFactor = 0.25;
    private T[] items;
    private int size;
   //when we delete from the front, the front pointer is incremented
    private int front = -1;
    //we insert items via the rear idx, so when rear == arr.length, our queue is full
    private int rear = -1;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }
//   /*creates a deep copy of  other deque*/
//    public ArrayDeque(ArrayDeque<T> other) {
//        //create an empty array with the same size of the other array and assign it to newCopy.items
//        this.items = (T[]) new Object[other.size];
//        this.size = other.size;
//        this.front = other.front;
//        this.rear = other.rear;
//        this.minLoadFactor = other.minLoadFactor;
//        this.maxLoadFactor = other.maxLoadFactor;
//        //could simply use System Copy?
//        System.arraycopy(other.items, 0, this.items, 0, other.size);
//    }
    /*adds item t to the front of the deque*/
   @Override
    public void addFirst(T item) {
        //check load factor to see if we are using maximum space for array
        boolean makeBigger = sizeBigger();
        //if true, resize array
        if (makeBigger) {
            //resize array according to capacity
            double multFactor = Math.pow(2, .05);
            double capacity = Math.ceil(items.length * multFactor);
            resize(capacity);
        }
        //if rear and front are equal -1, our queue is empty
        if (front == -1 && rear == -1) {
            //then we increment front and rear
            front++;
            rear++;
        } else {
            // else we can simply add the item at front
           if (front <= 0) {
               front = items.length - 1;
           } else {
               front--;
           }
        }
        //use front idx to add the new item and increment size by one
        items[front] = item;
        size++;
    }
   /*adds an item type t to the back of the deque*/
   @Override
    public void addLast(T item) {
        //check load factor to see if we are using maximum space for array
        boolean makeBigger = sizeBigger();
        //if true, resize array
        if (makeBigger) {
            //resize array according to capacity
            double multFactor = Math.pow(2, .05);
            double capacity = Math.ceil(items.length * multFactor);
            resize(capacity);
        }
        //if the array is empty, simply increment both front and rear pointers
        if (front == -1 && rear == -1) {
            front++;
            rear++;
            // else we have to do additional checks to see where we are in the circular arr
        } else {
            //if rear is equal to size - 1, then we want to reassign rear to 0
            if(rear == items.length - 1 ) {
                rear = 0;
            } else {
                rear++;
            }
        }
        //add item at the rear idx and increment size by 1;
        items[rear] = item;
        size++;
    }
   /* returns the number of items in the deque*/
   @Override
    public int size () {
        return size;
    }
    /*prints the items in the deque from first to last separated by a space*/
    @Override
    public void printDeque() {
        String[] strItems = new String[size];
        int count = 0;
        int i = front;
    //as we print we would like to start from the front and iterate forwards
        while (i <= rear) {
            strItems[count] = items[count].toString();
            count++;
            //function to ensure circular movement through array
            i = (i + 1) % items.length;
        }
        String.join(" ", strItems);
    }
    /*removes and returns the first item in the deque*/
    @Override
    public T removeFirst() {
        //first check if the queue is empty, if so return null
        if (front == -1 && rear == -1) {
            return null;
        } else {
            //reassign front and rear to -1 and set the value of the array to null
            T removed = items[front];
            if (front == rear){
                items[front] = null;
                front = -1 ;
                rear = -1;
                size = 0;
            } else {
                //if front is equal to the array.size -1
                items[front] = null;
                if (front == items.length - 1) {
                    front = 0;
                } else {
                    front++;
                }
                size--;
            }
            if (sizeSmaller()) {
                //calculate resize capacity
                double capacity = items.length / 2;
                resize(capacity);
            }
            return removed;
        }
    }
  /* removes and returns the items at the back of the deque*/
  @Override
    public T removeLast() {
        //if the arr is empty, return null
        if(front == -1 && rear == -1) {
            return null;
        } else {
            T removed = items[rear];
            if(front == rear) {
                //else if there is only one element in the array, we save the value of the array , reassign front and -1 to 0, and decrement size, return deleted item
                items[rear] = null;
                front = -1;
                rear = -1;
                size = 0;
            } else{
                //else we have more than 1 el in our arr
                items[rear] = null;
                //if we have reached the limit of our array (0), we point rear at size - 1;
                if (rear == 0) {
                    rear = items.length - 1;
                } else {
                    rear--;
                }
                //decrement size
                size--;
            }
            //after removing the element, make the array smaller if necessary
            if (sizeSmaller()) {
                //calculate resize capacity
                double capacity = items.length / 2;
                resize(capacity);
            }
            //return deleted
            return removed;
        }
    }
    /*gets the item at the given idx*/
    @Override
    public T get(int idx) {
        int correctIdx = (front + idx) % items.length;
        return items[correctIdx];
    }
    /*resizes the array by making a bigger or smaller copy*/
    private void resize(double capacity) {
        //create new array with the size capacity
        //round the capacity to the closest upper int
        int rounded = (int) Math.ceil(capacity);
        rounded = Math.max(1, rounded);
        T[] newItems = (T[]) new Object[rounded];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(front + i) % items.length];
        }
        items = newItems;
        front = 0;
        rear = size - 1;

    }
    //returns a ratio of memory that program ues at any given time
    private double usageFactor () {
        return (double) size / items.length;
    }
   //returns boolean stating should be sized smaller
    private boolean sizeSmaller () {
        double usage = usageFactor();
        return usage < minLoadFactor;
    }
    //returns boolean stating deque should be sized bigger
    private boolean sizeBigger () {
        return usageFactor() > maxLoadFactor;
    }
   @Override
 /*  checks if arrayDeque has the same elements in the same order*/
    public boolean equals(Object o) {
        //if o is in the same location in memory return true
        if (this == o) {
            return true;
        }
        if(o.getClass() != this.getClass()) {
            return false;
        }
        //what is the thing we are given not an array set, ensure we typecast it as an array set before iterating and checking both objects
       ArrayDeque<T> other = (ArrayDeque<T>) o;
        for (int i = this.front; i <= this.rear; i = (i + 1) % items.length) {
            if (!other.items[i].equals(this.items[i])) {
                return false;
            }
        }
        return true;
    }
    //returns iterator
   @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int idx;
        private int totalEl;
        private ArrayDequeIterator() {
            //reassign idx to the front instance variable
            idx = front;
            totalEl = 0;
        }
       //moves the curr value up by one and returns the current value of the currEl
       @Override
        public T next() {
            //grab current value at the idx variable
            T currVal = items[idx];
            totalEl++;
            //reassign idx to the next element in the array
            idx = (idx + 1 ) % items.length;
            return currVal;
        }
       @Override
        public boolean hasNext() {
            return totalEl < size;
        }
    }

}
