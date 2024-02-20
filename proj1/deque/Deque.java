package deque;

public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int idx);
    /*returns true if the deque is empty*/
    default public boolean isEmpty() {
        if (size() == 0) {
            return false;
        }
        return true;
    }
}
