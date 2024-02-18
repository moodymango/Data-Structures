package deque;

import java.util.Comparator;

/*maxArrayDeque serves as a hyponym of arrayDeque*/
public class MaxArrayDeque<T> extends ArrayDeque<T> {
   /*creates a maxArrayDeque with the given comparator*/
    //this parameter will accept any object that supports this interface
    Comparator<T> comp;
    //constructor allows us to define the comparator that is used when inserting a T into the maxArrayDeque
   //@source - https://stackoverflow.com/questions/29699103/treeset-constructor-with-comparator-parameter
    public MaxArrayDeque(Comparator<T> c) {
        //make explicit the call to the superclass constructor of arrayDeque
        super();
        this.comp = c;
    }
   /*returns the max el in the deque as governed by the previously given comparator*/
    public T max() {
        //if deque is empty, return null
        if (front == -1 && rear == -1) {
            return null;
        }
        //iterate through the items property
        T maxEx;
        for (T item: items) {
            System.out.println("item is " + item);
        }

        return null;
    }
  /* returns the max el in the deque as governed by the param c*/
    public T max(Comparator<T> c ) {
        //if deque is empty, return null
        if (front == -1 && rear == -1) {
            return null;
        }
        //iterate through the items property
        T maxEl;
       for (T item: items) {
           System.out.println("item is " + item);
       }
        return this.get(0);
    }

}
