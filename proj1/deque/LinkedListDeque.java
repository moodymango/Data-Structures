package deque;

import com.google.common.base.Objects;

import java.util.Iterator;

/*can implement deque as a stack (Last In, First Out) or queue (First In, First Out)*/
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    /* private class representing the elements in the list */
    private class Node {
        private final T value;
        /* ensure that node has both next and prev variables to create doubly linked list*/
        private Node prev;
        private Node next;
        
        //every node is instantiated only with a value
        //we reassign the next and prev based on it's position in the list
        private Node(T d) {
            value = d;
        }
    }
    
    private int size;
    /*using sentinel nodes eliminates many edge cases that arise in implementing ll operations*/
    //want sentinel to be specific Node's containing T value, not just any raw list Element
    private final Node sentinel;
    
    public LinkedListDeque() {
        //instantiate new node with next and prev values pointing to itself
        sentinel = new Node(null);
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
    }
    
    /*creates a deep copy of other*/
//    public LinkedListDeque(LinkedListDeque other) {
//        //instantiate a new linked list
//        sentinel = new Node(null);
//        sentinel.prev = sentinel;
//        sentinel.next = sentinel;
//        Node currNew = sentinel;
//        size = 0;
//        //iterate through the other linked list
//        Node currOther = other.sentinel.next;
//        //loop while currOther is not equal to other.sentinel
//        while (currOther != other.sentinel) {
//            //instead of assigning currNew.next the reference to the currOther node
//            we make a new node passing in currOther's value
//            Node copiedNode = new Node(currOther.value);
//            currNew.next = copiedNode;
//            //assign the prev of the copied node to be currNew
//            copiedNode.prev = currNew;
//            //reassign currNew to the copied node to continue iterating through our new deque
//            currNew = copiedNode;
//            currOther = currOther.next;
//        }
//    }
    /*   Adds new node to the head of the list*/
    @Override
    public void addFirst(T value) {
        //first value in the doubly linked list is the next node after the sentinel
        //first we grab the reference to sentinel's current next
        //save it to make note of it's prev value
        Node currFirst = sentinel.next;
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
    
    /*   Adds new node to the tail of the list*/
    @Override
    public void addLast(T value) {
        //declare newLast node and assign it the instantiation of new node passing in value
        Node newLast = new Node(value);
        //save a reference to the currLast by access sentinel.prev
        Node currLast = sentinel.prev;
        //first we reassign the next value of the currLast to be the newLast,
        // since its old next value was sentinel
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
    
    /* Returns the number of nodes in the ll*/
    @Override
    public int size() {
        return size;
    }
    
    /*Prints the values in the deque from first to last, separated by a space.
    Once all the values have been printed, print out a new line.*/
    @Override
    public void printDeque() {
        //iterate through the deque
        //initiate first node at sentinel.next
        Node currNode = sentinel.next;
        if (currNode == null) {
            return;
        }
        int counter = 0;
        String[] values = new String[size];
        //while the counter is less than or equal to the size variable
        while (counter < size) {
            //push stringified values to the array
            values[counter] = currNode.value.toString();
            counter++;
            currNode = currNode.next;
        }
        System.out.println(String.join(" ", values));
    }
    
    /*Removes the first node in the deque and returns it's value'*/
    @Override
    public T removeFirst() {
        //first check if  the deque is empty, if not, remove the first
        if (isEmpty()) {
            return null;
        }
        //deque is not empty, which means our currFirst is the next value of the sentinel node
        Node currFirst = sentinel.next;
        T currVal = currFirst.value;
        //reassign the currFirst's next node to the curr prev node
        currFirst.next.prev = currFirst.prev;
        //reassign the sentinel's next to the currNode's next
        sentinel.next = currFirst.next;
        //make sure to remove pointers from the removed node
        // by assigning its next and prev properties as null
        currFirst.next = currFirst.prev = null;
        //decrement and return size
        size--;
        return currVal;
    }
    
    /*Removes the last node in the deque and returns its value*/
    @Override
    public T removeLast() {
        //if the deque is empty, return null
        Node currNode = sentinel.prev;
        //if the currNode is the sentinel node, the current node is pointing to itself
        if (currNode == sentinel) {
            return null;
        }
        //reassign the next value of the node before currNode to point to the sentinel
        currNode.prev.next = sentinel;
        //reassign the prev value of the sentinel to the currNode.prev
        sentinel.prev = currNode.prev;
        //decrement size
        size--;
        //simply return the currNode
        return currNode.value;
        
    }
    
    /*Iteratively gets the item at the given index*/
    @Override
    public T get(int idx) {
        //declare currNode at currNode.next
        Node currNode = sentinel.next;
        int count = 0;
        //keep loop iterating as long as the currNode is not pointing to the sentinel
        while (currNode != sentinel) {
            //if the value of the counter is equal to the value of the idx
            if (idx == count) {
                // return the value at the currNode
                return currNode.value;
            }
            //else we increment counter by one, and reassign the currNode to currNode.next
            count++;
            currNode = currNode.next;
        }
        return null;
    }
    
    /*Recursively gets the value of the node at the desired idx*/
    public T getRecursive(int idx) {
        Node currNode = sentinel.next;
        int depth = 0;
        // @source - recursive pattern found in top answer of following stack overflow query:
        //how to use idx variable in recursion
        return getRecursiveHelper(currNode, idx, depth);
    }
    
    private T getRecursiveHelper(Node curr, int idx, int depth) {
        //depth keeps track of where we are in our linked list
        //if depth is equal to the idx, simply return the value of the node
        if (curr == sentinel) {
            return null;
        }
        if (depth == idx) {
            return curr.value;
        }
        //otherwise we recursively call the function, passing in the curr.next, idx, and depth++;
        return getRecursiveHelper(curr.next, idx, ++depth);
    }
    
    //returns an iterator method
    @Override
    public Iterator<T> iterator() {
        return new LLIterator();
    }
    
    private class LLIterator implements Iterator<T> {
        //instantiate a curr node variable to keep track of what node we are on in our ll.
        private Node curr;
        
        private LLIterator() {
            curr = sentinel.next;
        }
        
        public boolean hasNext() {
            //as long as the curr node.next is NOT pointing to the sentinel
            // we have a valid next value;
            return (curr != sentinel);
        }
        
        //next method returns the current val and moves forward to next item
        public T next() {
            //save value of the curr node
            T currVal = curr.value;
            //move forward in the ll by reassign curr to curr.next
            curr = curr.next;
            return currVal;
        }
    }
    
    /*Returns whether the parameter o is equal to the deque*/
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        //check if o is an instance of deque or a linked list deque
        if (!(o instanceof Deque)) {
            return false;
        }
        if (o instanceof LinkedListDeque) {
            //downcast o into a ll deque
            LinkedListDeque<T> otherDeque = (LinkedListDeque<T>) o;
            //check if the sizes are the same
            if (this.size() != otherDeque.size()) {
                return false;
            }
            //iterate through both the original and other deque by using original's iterator
            Iterator<T> originalIt = this.iterator();
            Iterator<T> otherIt = otherDeque.iterator();
            while (originalIt.hasNext()) {
                if (!Objects.equal(originalIt.next(), otherIt.next())) {
                    return false;
                }
            }
        }
        return true;
    }
}
