package deque;

import java.util.Iterator;
import java.util.Objects;

/*Invariants*/
//circular implementaton: (front = (rear + 1)% length)
/*Circular queue implementation - @source - Jenny's' Lectures CS IT
- https://www.youtube.com/watch?v=dn01XST9-bI*/
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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
    
    /*Creates a deep copy of  other deque. */
//    public ArrayDeque(ArrayDeque<T> other) {
//        //create an empty array with the same size of the other array
//        assign it to newCopy.items
//        this.items = (T[]) new Object[other.size];
//        this.size = other.size;
//        this.front = other.front;
//        this.rear = other.rear;
//        this.minLoadFactor = other.minLoadFactor;
//        this.maxLoadFactor = other.maxLoadFactor;
//        //could simply use System Copy?
//        System.arraycopy(other.items, 0, this.items, 0, other.size);
//    }
    @Override
    /*Adds item t to the front of the deque*/
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
    
    @Override
    /*Adds an item type t to the back of the deque*/
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
            if (rear == items.length - 1) {
                rear = 0;
            } else {
                rear++;
            }
        }
        //add item at the rear idx and increment size by 1;
        items[rear] = item;
        size++;
    }
    
    @Override
    /*Returns the number of items in the deque*/
    public int size() {
        return size;
    }
    
    @Override
    /*Prints the items in the deque from first to last separated by a space*/
    public void printDeque() {
        String[] strItems = new String[size];
        //as we print we would like to start from the front and iterate forwards
        for (int i = 0; i < size; i++) {
            strItems[i] = items[(front + i) % items.length].toString();
        }
        System.out.println(String.join(" ", strItems));
    }
    
    @Override
    /*Removes and returns the first item in the deque*/
    public T removeFirst() {
        //first check if the queue is empty, if so return null
        if (size == 0) {
            return null;
        } else {
            //reassign front and rear to -1 and set the value of the array to null
            T removed = items[front];
            if (front == rear) {
                items[front] = null;
                front = -1;
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
    
    @Override
    /*Removes and returns the items at the back of the deque*/
    public T removeLast() {
        //if the arr is empty, return null
        if (size == 0) {
            return null;
        } else {
            //we save the value of the array
            T removed = items[rear];
            if (front == rear) {
                //else if there is only one element in the array
                items[rear] = null;
                //reassign front and -1 to 0,
                front = -1;
                rear = -1;
                //reassign size to 0
                size = 0;
            } else {
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
    
    @Override
    /*Gets the item at the given idx.*/
    public T get(int idx) {
        if (0 <= idx && idx <= items.length) {
            int correctIdx = (front + idx) % items.length;
            return items[correctIdx];
        }
        return null;
    }
    
    /*Resizes the array by making a bigger or smaller copy.*/
    private void resize(double capacity) {
        //create new array with the size capacity
        //round the capacity to the closest upper int
        int rounded = (int) Math.ceil(capacity);
        rounded = Math.max(1, rounded);
        //check if capacity is zero, if so, simply
        T[] newItems = (T[]) new Object[rounded];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(front + i) % items.length];
        }
        items = newItems;
        if (size == 0) {
            front = -1;
            rear = -1;
        } else {
            front = 0;
            rear = size - 1;
        }
    }
    
    /*Returns a ratio of memory that program ues at any given time*/
    private double usageFactor() {
        return (double) size / items.length;
    }
    
    /* Returns boolean stating should be sized smaller*/
    private boolean sizeSmaller() {
        double minLoadFactor = 0.25;
        return usageFactor() < minLoadFactor;
    }
    
    /*  Returns boolean stating deque should be sized bigger*/
    private boolean sizeBigger() {
        double maxLoadFactor = 0.75;
        return usageFactor() > maxLoadFactor;
    }
    
    @Override
    /* Checks if arrayDeque and o have the same elements in the same order.*/
    public boolean equals(Object o) {
        //if o is in the same location in memory return true
        if (this == o) {
            return true;
        }
        //if o is not an instance of Deque
        if (!(o instanceof Deque)) {
            return false;
        }
        //check if o is an instance of arrayDeque
        if (o instanceof ArrayDeque) {
            //if so then we downcast it into a variable of static type arrayDeque
            ArrayDeque<T> otherDeque = (ArrayDeque<T>) o;
            //check if the sizes are the same
            if (otherDeque.size() != this.size()) {
                return false;
            }
            //utilize an iterator to perform loop for deep comparison
            Iterator<T> originalIt = this.iterator();
            Iterator<T> otherIt = otherDeque.iterator();
            //while looping through the original iterator
            //check if the values of .next() are the same
            while (originalIt.hasNext()) {
                if (!Objects.equals(originalIt.next(), otherIt.next())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    /*  Returns iterator object.*/
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
            idx = (idx + 1) % items.length;
            return currVal;
        }
        
        @Override
        public boolean hasNext() {
            return totalEl < size;
        }
    }
    
}
