package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    //this parameter will accept any object that supports this interface
    private Comparator<T> comp;
   /*creates a maxArrayDeque with the given comparator*/
    public MaxArrayDeque(Comparator<T> c) {
        //make explicit the call to the superclass constructor of arrayDeque
        super();
        this.comp = c;
    }
   /*returns the max el in the deque as governed by the previously given comparator*/
    public T max() {
        //if deque is empty, return null
        if (size() == 0) {
            return null;
        }
        //iterate through the deque
        T maxEl = this.get(0);
        for(T item : this){
            int compareVal = this.comp.compare(item, maxEl);
            if (compareVal > 0) {
                maxEl = item;
            }
        }
        return maxEl;
    }
  /* returns the max el in the deque as governed by the param c*/
    public T max(Comparator<T> c ) {
        //if deque is empty, return null
        if (size() == 0) {
            return null;
        }
        //iterate through the deque
        T maxEl = this.get(0);
        for (T item: this) {
            int compareVal = c.compare(item, maxEl);
            if (compareVal > 0) {
                maxEl = item;
            }
        }
        return maxEl;
    }
}
