package deque;

/*Invariants*/
//position of the next item to be inserted is always size
//num of items in the Alist is always size
//position of the last item in the list is always size - 1;
public class ArrayDeque<T> {
    T [] items;
    int size;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }
    public ArrayDeque(ArrayDeque T) {

    }
    /*adds item t to the front of the array*/
    public void addFirst(T item) {

    }
   /*adds an item type t to the back of the deque*/
    public void addLast(T item) {

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

    }
    /*removes and returns the first item in the deque*/
    public T removeFirst() {

    }
  /* removes and returns the items at the back of the deque*/
    public T removeLast() {

    }
    /*gets the item at the given idx*/
    public T get(int idx) {

    }
}
