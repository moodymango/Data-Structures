package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
/* create an inner class representing a node in the bst*/
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        
        private BSTNode(K key, V val) {
            this.key = key;
            this.value = val;
            this.right = this.left = null;
        }
}
    BSTNode root;
    int size;
    public BSTMap(BSTNode root){
        this.root = root;
        this.size = 0;
    }
    public BSTMap(){
        this.root = null;
        this.size = 0;
    }
    

    @Override
    /** Removes all of the mappings from this map. */
    public void clear(){
        //simply lose the reference to the root node which will allow all the
        // other child nodes to be garbage collected.
        this.root = null;
    }
    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
       return containsKeyHelper(key, root);
    }
    private boolean containsKeyHelper(K key, BSTNode n){
        if (n == null) {
            return false;
        }
        if (key.compareTo(n.key) == 0){
            return true;
            //if key is greater than the current node's key, we want to look
            // to the right of n
        } else if (key.compareTo(n.key) > 0) {
            return containsKeyHelper(key, n.right);
        } else {
            //else, comapreTo yields - num, which means we need to look
            // towards the left
            return containsKeyHelper(key, n.left);
        }
    }
    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get (K key){
        return getRecursive(key, root);
    }
    public V getRecursive(K key, BSTNode n){
        if (n == null) {
            return null;
        }
        if(key.compareTo(n.key) == 0) {
            return n.value;
        } else if (key.compareTo(n.key) < 0) {
            return getRecursive(key, n.left);
        } else {
            return getRecursive(key, n.right);
        }
    }
    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size(){
        throw new UnsupportedOperationException();
    }
    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        throw new UnsupportedOperationException();
    
    }
    public void printInOrder(){}
    @Override
    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }
    @Override
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }
    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    
    
    
}
