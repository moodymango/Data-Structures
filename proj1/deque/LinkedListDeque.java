package deque;
/*can implement deque as a stack (Last In, First Out) or queue (First In, First Out)*/
public class LinkedListDeque <T> {
/* private static class representing the elements in the list */
    private class Node {
        public T value;
     /* ensure that node has both next and prev variables to create doubly linked list*/
        public Node prev;
        public Node next;
        //every node is instantiated only with a value, we reassign the next and prev based on it's position in the list
        private Node (T d) {
            value  = d;
        }
    }
    /*  ensure size and sentinel node variables cannot be accessed from child class of outside package*/
    private int size;
    /*using sentinel nodes eliminates many edge cases that arise in implementing ll operations*/
    //want sentinel to be specific Node's containing T value, not just any raw list Element
    private Node sentinel;
    public LinkedListDeque() {
        //instantiate new node with next and prev values pointing to itself
        sentinel = new Node(null);
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
    }
 /*   adds new node to the head of the list*/
    public void addFirst(T value) {
        //first value in the doubly linked list is the next node after the sentinel
        //first we grab the reference to sentinel's current next and save it to make note of it's prev value
        Node currFirst  = sentinel.next;
        //declare and assign newFirst to  instantiation of node passing in value
        Node newFirst = new Node(value);
        //reassign the next of the new first to point to currFirst
        newFirst.next = currFirst;
        //reassign the prev of the currFirst to point to our new node
        currFirst.prev = newFirst;
        //now need to point the sentinel to our new first
        sentinel.next = newFirst;
        //reassign the prev of the new first to be the sentinel
        newFirst.prev = sentinel;
        //increment size by 1 and reassign;
        size += 1;
    }
 /*   adds new node to the tail of the list*/
    public void addLast(T value) {
        //sentinel node also acts as our last node, if we can find the reference to the sentinel node's previous, we can find the tail
        //declare newLast node and assign it the instantiation of new node passing in value
        Node newLast = new Node(value);
        //save a reference to the currLast by access sentinel.prev
        Node currLast = sentinel.prev;
        //first we reassign the next value of the currLast to be the newLast, since its old next value was sentinel
        currLast.next = newLast;
        //reassign the prev of the newLast to point to the currLast
        newLast.prev = currLast;
        //reassign the next value of the newLast to sentinel
        newLast.next = sentinel;
        //reassign sentinel's prev to point to the newLast;
        sentinel.prev = newLast;
        //increment size by 1 and reassign;
        size += 1;
    }
/*   checks to see if there are any nodes in our list*/
    public boolean isEmpty() {
        //if the sentinel's.next property is pointing to itself, we have an empty list
       if (sentinel.next == sentinel) {
           return true;
       }
        return false;
    }
  /* returns the number of nodes in the ll*/
    public int size() {
        return size;
    }
    //the head will never be null, so we can simply access the curr head via the sentinel
    /*Prints the values in the deque from first to last, separated by a space. Once all the values have been printed, print out a new line.*/
    public void printDeque() {
    //iterate through the deque
        //initiate first node at sentinel.next
        Node currNode = sentinel.next;
        int counter = 0;
        //instantiate array of length size in order to hold values of the deque, with string type since we will print the values as strings
        String[] values = new String[size];
        //while the counter is less than or equal to the size variable
        while (counter <= size) {
            //push stringified values to the array
            values[counter] = currNode.value.toString();
            counter++;
            currNode = currNode.next;
        }
        System.out.println(String.join(" ", values));
    }
    public T removeFirst() {
        //first check if  the deque is empty, if not, remove the first
        if (!isEmpty()) {
            //deque is not empty, which means our currFirst is the next value of the sentinel node
            Node currFirst = sentinel.next;
            //reassign the currFirst's next node to the curr prev node
            currFirst.next.prev = currFirst.prev;
            //reassign the sentinel's next to the currNode's next
            sentinel.next = currFirst.next;
            //make sure to remove pointers from the removed node by assigning its next and prev properties as null
            currFirst.next = currFirst.prev = null;
            //decrement and return size
            size--;
            return currFirst.value;
        }
        return null;
    }
    public T removeLast() {
        return null;
    }
    public T get(int idx) {
    return null;
    }
    public T getRecursive(int idx) {
        return null;
    }
}