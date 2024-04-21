package bstmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
/* create an inner class representing a node in the bst*/
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        
        private Node(K key, V val) {
            this.key = key;
            this.value = val;
            this.right = this.left = null;
        }
}
    

    @Override
    public void clear(){
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean containsKey(K key){
        throw new UnsupportedOperationException();
    }
    @Override
    public V get (K key){
        throw new UnsupportedOperationException();
    }
    @Override
    public int size(){
        throw new UnsupportedOperationException();
    }
    @Override
    public void put(K key, V value){
        throw new UnsupportedOperationException();
    
    }
    public void printInOrder(){}
    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    
    
    
}
