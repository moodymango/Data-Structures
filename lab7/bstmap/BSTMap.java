package bstmap;

import java.util.Iterator;
import java.util.Set;

//compare implementation to the following link:
//https://algs4.cs.princeton.edu/32bst/BST.java.html
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
/* create an inner class representing a node in the bst*/
    private class BSTNode {
        private final K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        private int size;
        
        private BSTNode(K key, V val) {
            this.key = key;
            this.value = val;
            this.right = this.left = null;
            this.size = 1;
            
        }
}
    private BSTNode root;
    public BSTMap(){
        this.root = null;
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
    private V getRecursive(K key, BSTNode n){
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
        return sizeHelper(root);
    }
    private int sizeHelper(BSTNode n){
        if(n == null) {
            return 0;
        }
        return n.size;
    }
    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        root = putRecursive(key, value, root);
    }
    private BSTNode putRecursive(K key, V value, BSTNode n){
        //starting at the root, if the node is null, then place a new node there
        if(n == null) {
            n = new BSTNode(key, value);
            return n;
           
        }
        if(key.compareTo(n.key) < 0) {
            n.left = putRecursive(key, value, n.left);
        } else if (key.compareTo(n.key) > 0) {
            n.right = putRecursive(key, value, n.right);
        } else {
            n.value = value;
        }
        n.size = 1 + sizeHelper(n.left) + sizeHelper(n.right);
        return n;
    }
    public void printInOrder(){
        inOrderHelper(root);
    }
    private void inOrderHelper(BSTNode n){
        //left, root, right
        if(n == null) {
            return;
        }
        inOrderHelper(n.left);
        System.out.println(n.key);
        inOrderHelper(n.right);
    }
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
