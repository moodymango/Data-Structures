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
   /*creates a deep copy of the other deque*/
    public ArrayDeque(ArrayDeque other) {
//        int otherLength = other.items.length;
//        T[] newItems = (T[]) new Object[otherLength];
//        int count = 0;
//        int otherIdx = 0;
//        //iterate through the other array
//        for (int i = 0; i < otherLength; i++) {
//            newItems[count] = other.items[otherIdx];
//        }
    }
    /*adds item t to the front of the deque*/
    public void addFirst(T item) {
        //if rear and front are equal -1, our queue is empty
        if (front == -1 && rear == -1) {
            //then we increment front and rear
            front++;
            rear++;
            //if the array is full then we resize the array, increment rear and front and add the item at rear id
        } else if (isFull()){
            //call resize function
                resize();
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
        if (front == -1 && rear == -1) {
            front++;
            rear++;
        } else if (isFull()) {
            //call resize function
            resize();
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
        //first check if the queue is empty, if so return null
        if (front == -1 && rear == -1) {
         return null;
            //if there is only one element in the array (front and rear pointing to the same arr)
        } else if (front == rear){
            //reassign front and rear to -1 and set the value of the array to null
            T removed = items[front];
            items[front] = null;
            front = rear = -1;
            size--;
            return removed;
        } else {
            //if front is equal to the array.size -1, reassign front to 0 and increment by 1
            T removed = items[front];
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
            front = rear = -1;
            size--;
            return removed;
        } else {
            //else we have more than 1 el in our arr
            T removed = items[rear];
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
    /*resizes the array by making a bigger copy*/
    private  void resize() {
        System.out.println("Add resize function");
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
    private double usageFactor () {
        //returns a ratio of memory that program ues at any given time according to the number of items
        int usage = size / items.length;
        return usage;
    }
}
