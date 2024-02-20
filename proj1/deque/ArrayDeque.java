package deque;
/*Implementing a circular queue (array) to implement the deque;*/
/*
Circular queue resolves problem of memory wastage, as it reuses empty space from dequeue operations as a linear queue reaches the end
*/
/*Circular queue implementation - @source - Jenny's' Lectures CS IT
- https://www.youtube.com/watch?v=dn01XST9-bI*/

/*Invariants*/
//when we've reached end of queue, can use modulo operator to achieve circular implementaton (front = (rear + 1)% length)

public class ArrayDeque<T> {
    /*private load factor instance variables to help keep array to appropriate size */
   // @source - https://freedium.cfd/https://medium.com/@ohermans1/breaking-free-from-fixed-array-sizes-the-power-of-dynamic-resizing-abf81df691e7
    private double maxLoadFactor = 0.75;
    private double minLoadFactor = 0.25;
    T[] items;
    int size;
   /*create front and rear instance variables to keep track of how much space we use in the circular queue*/
   //when we delete from the front, the front pointer is incremented
    int front = -1;
    //we insert items via the rear idx, so when rear == arr.length, our queue is full
    int rear = -1;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }
   /*creates a deep copy of  other deque*/
    public ArrayDeque(ArrayDeque other) {
        //create an empty array with the same size of the other array and assign it to newCopy.items
        this.items = (T[]) new Object[other.size];
        this.size = other.size;
        this.front = other.front;
        this.rear = other.rear;
        //could simply use System Copy?
        System.arraycopy(other.items, 0, this.items, 0, other.size);
    }
    /*adds item t to the front of the deque*/
    public void addFirst(T item) {
        //check load factor to see if we are using maximum space for array
        boolean makeBigger = sizeBigger();
        //if true, resize array
        if (makeBigger) {
            //resize array according to capacity
            //multiplicative factor comes from following source:
            //@source - https://ece.uwaterloo.ca/~dwharder/aads/Algorithms/Array_resizing/#:~:text=The%20easiest%20and%20most%20convenient,copy%20(amortized)%20per%20insertion.
            //b/c the average percent of empty entries is much slower (see source)
            double multFactor = Math.pow(2, .05);
            double capacity = items.length * multFactor;
            resize(capacity);
        }
        //if rear and front are equal -1, our queue is empty
        if (front == -1 && rear == -1) {
            //then we increment front and rear
            front++;
            rear++;
            //if the array is full then we resize the array, increment rear and front and add the item at rear id
        } else {
            // else we can simply add the item at front
           if (front == 0) {
               front = items.length - 1;
           } else {
               front--;
           }
        }
        //use rear idx to add the new item and increment size by one
        items[front] = item;
        size++;
    }
   /*adds an item type t to the back of the deque*/
    public void addLast(T item) {
        //check load factor to see if we are using maximum space for array
        boolean makeBigger = sizeBigger();
        //if true, resize array
        if (makeBigger) {
            //resize array according to capacity
            //multiplicative factor comes from following source:
            //@source - https://ece.uwaterloo.ca/~dwharder/aads/Algorithms/Array_resizing/#:~:text=The%20easiest%20and%20most%20convenient,copy%20(amortized)%20per%20insertion.
            //b/c the average percent of empty entries is much slower
            double multFactor = Math.pow(2, .05);
            double capacity = items.length * multFactor;
            resize(capacity);
        }
        if (front == -1 && rear == -1) {
            front++;
            rear++;
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
   /*returns true if the deque is empty*/
    public boolean isEmpty() {
        if (size == 0) {
            return false;
        }
        return true;
    }
   /* returns the number of items in the deque*/
    public int size () {
        return size;
    }
    /*prints the items in the deque from first to last separated by a space*/
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
    public T removeFirst() {
        //first check if we should make the array smaller
        boolean makeSmall = sizeSmaller();
        //if true, then we resize array with new capacity
        if(makeSmall) {
            //calculate
        }
        //first check if the queue is empty, if so return null
        if (front == -1 && rear == -1) {
         return null;
            //if there is only one element in the array (front and rear pointing to the same arr)
        } else if (front == rear){
            //reassign front and rear to -1 and set the value of the array to null
            T removed = items[front];
            items[front] = null;
            front = rear = -1;
            size = 0;
            return removed;
        } else {
            //if front is equal to the array.size -1, reassign front to 0 and increment by 1
            T removed = items[front];
            items[front] = null;
            if (front == items.length - 1) {
                front = 0;
            } else {
                front++;
            }
            size--;
            return removed;
        }
    }
  /* removes and returns the items at the back of the deque*/
    public T removeLast() {
        //if the arr is empty, return null
        if(front == -1 && rear == -1) {
            return null;
        } else if(front == rear) {
            //else if there is only one element in the array, we save the value of the array , reassign front and -1 to 0, and decrement size, return deleted item
            T removed = items[rear];
            items[rear] = null;
            front = rear = -1;
            size = 0;
            return removed;
        } else {
            //else we have more than 1 el in our arr
            T removed = items[rear];
            items[rear] = null;
            //if we have reached the limit of our array (0), we point rear at size - 1;
            if (rear == 0) {
                rear = items.length - 1;
            } else {
                rear--;
            }
            //decrement size
            size--;
            //return deleted
            return removed;
        }
    }
    /*gets the item at the given idx*/
    public T get(int idx) {
        return items[idx];
    }
    /*resizes the array by making a bigger or smaller copy*/
    private void resize(double capacity) {
        //create new array with the size capacity
        //round the capacity to the closest int
        int rounded = (int) Math.round(capacity);
        T[] newItems = (T[]) new Object[rounded];
        for (int i = front; i <= rear; i = (i +1) % items.length) {
            newItems[i] = items[i];
        }
        items = newItems;
    }
    /*checks to see if the queue is full*/
    public boolean isFull () {
        if((front == 0) && (rear == items.length - 1)){
            return true;
        }
        if (front == rear + 1) {
            return true;
        }
        return false;
    }
    //returns a ratio of memory that program ues at any given time according to the number of items
    private double usageFactor () {
        int usage = size / items.length;
        return usage;
    }
   //returns boolean that questions whether items instance variable should be sized smaller
    private boolean sizeSmaller () {
        if (usageFactor() < minLoadFactor) {
            return true;
        }
        return false;
    }
    //returns boolean that questions whether items instance variable should be sized bigger
    private boolean sizeBigger () {
        if (usageFactor() > maxLoadFactor) {
            return true;
        }
        return false;
    }

}
