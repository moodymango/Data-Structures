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
        T maxEl = items[front];
     for (int i = (front + 1) % items.length; i <= rear; i = (i + 1) % items.length) {
         int compareVal = this.comp.compare(items[i], maxEl);
         //if the compare value is positive, it means the current el at idx i is greater than the maxEl
         //reassign maxEl to the current el
         if (compareVal > 0) {
             maxEl = items[i];
         }
     }
        return maxEl;
    }
  /* returns the max el in the deque as governed by the param c*/
    public T max(Comparator<T> c ) {
        //if deque is empty, return null
        if (front == -1 && rear == -1) {
            return null;
        }
        //iterate through the items property
        T maxEl = items[front];
        for (int i = (front + 1) % items.length; i <= rear; i = (i + 1) % items.length) {
            int compareVal = c.compare(items[i], maxEl);
            //if the compare value is positive, it means the current el at idx i is greater than the maxEl
            //reassign maxEl to the current el
            if (compareVal > 0) {
                maxEl = items[i];
            }
        }
        return maxEl;
    }
}
