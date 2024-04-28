package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Imma Duverger
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    private double loadFactor;
    
    private int size;
    
    private HashSet<K> keys;
    

    /** Constructors */
    public MyHashMap() {
        //remember, cannot create an array of parameterized type, so the
        // following will not work:
//        buckets = new Collection<Node>[defaultSize];
        buckets = new Collection[16];
        loadFactor = 0.75;
        keys = new HashSet<>();
        size = 0;
    }

    public MyHashMap(int initialSize) {
        buckets = new Collection[initialSize];
        loadFactor = 0.75;
        keys = new HashSet<>();
        size = 0;
        
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = new Collection[initialSize];
        loadFactor = maxLoad;
        keys = new HashSet<>();
        size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        //selected ArrayList at random
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }
    
    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
      //use buckets.length to instantiate new collection[] and reassign
        // bucket to the new instantiation
        buckets = new Collection[buckets.length];
        keys = new HashSet<>();
        size = 0;
    }
    
    /** Returns true if this map contains a mapping for the specified key. */
   @Override
    public boolean containsKey(K key){
//        //calculate index using key
//       int index = hashIndex(key);
//       //now identify correct bucket based on index
//       Collection<Node> bucket = getBucket(index);
//       //if bucket is null, no bucket was created at that index, and we do
//       // not have a mapping for that specified key
//       if (bucket != null) {
//           return true;
//       }
//       return false;
       return keys.contains(key);
   }
    
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if (!containsKey(key)) {
            return null;
        }
        //else calculate index
        int index = hashIndex(key, buckets.length);
        //identify bucket with the proper index
        Collection<Node> bucket = getBucket(index);
        //iterate through bucket using collection iterator
        Iterator<Node> iterator = bucket.iterator();
        V value = null;
        while (iterator.hasNext()){
            Node currNode = iterator.next();
            //if the key of the current node equals the key arg, return
            // node's value
            if (currNode.key.equals(key)){
                value = currNode.value;
            }
        }
        return value;
    }
    
    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }
    
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
   public void put(K key, V value){
        //first get the correct index and bucket for the curr key
        int index = hashIndex(key, buckets.length);
        Collection<Node> bucket = getBucket(index);
        //if bucket does not exist yet, then create a new one
        if (bucket == null){
            bucket = createBucket();
            //create node and add it to the collection
            bucket.add(new Node(key, value));
            //increment size since we've added a new node
            size++;
        } else {
            // we have to check for a duplicate key
            //create an iterator object to properly iterate through our bucket
            Iterator<Node> iterator = bucket.iterator();
            while(iterator.hasNext()){
                Node currNode = iterator.next();
                if(currNode.key.equals(key)){
                    //reassign the value of the currNode to arg value
                    currNode.value = value;
                    return;
                }
            }
            //else we've finished iterator through the collection
            // encountering no dupes, so we can simply add a new node to our
            // collection
            bucket.add(new Node(key, value));
            size++;
        }
        //add bucket into hashtable at index
        buckets[index] = bucket;
        //add the current key to our hashset instance variable
        keys.add(key);
        //finally we check our load factor after adding our element. if the
        // shouldResize method returns true, then resize,
        if (shouldResize()){
            resize();
        }
       
    }
    
    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        return keys;
    }
    
    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
        return null;
    }
    
    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        return null;
    }
    
    @Override
    public Iterator<K> iterator(){
        return keys.iterator();
    }
   
    //PRIVATE HELPER METHODS
    private void resize(){
       //take the current length of the buckets property and simply double
        // the size to get our new capacity
        Collection<Node>[] newBuckets = new Collection[buckets.length * 2];
        //use the iterator method to iterate through our keys in the current
        // hash map
        Iterator<K> hashIterator = iterator();
        //for each key,
        //grab the current key value pairing from the current buckets
        // recalulate the proper index using the hashIndex
        // function, passing in the key and new capacity
        //then insert the
        while(hashIterator.hasNext()){
            K currKey = hashIterator.next();
            //use current key to grab value
            V currVal = this.get(currKey);
            //create new node to insert into the new buckets variable
            Node newNode = new Node(currKey, currVal);
            //calculate new index to insert new node
            int index = hashIndex(currKey, newBuckets.length);
            //grab bucket at currIdx;
            Collection<Node> bucket =  newBuckets[index];
            if(bucket == null) {
            //create a new bucket
                bucket = createBucket();
            }
            //now simply add the node value into the bucket
            bucket.add(newNode);
            newBuckets[index] = bucket;
        }
        //after iterating, all the elements should be located in their new
        // locations
        //reassign the buckets instance variable
        buckets = newBuckets;
    }
    //calculates the load factor of the hashmap
    private double calculateLoadFactor () {
        return size / buckets.length;
    }
   //returns boolean that checks if the current load factor is greater than
   // class load factor
    private boolean shouldResize(){
        if (calculateLoadFactor() > loadFactor) {
            return true;
        }
        return false;
    }
  /*  returns valid index for element in the hash table*/
    private int hashIndex(K key, int bucketNum){
        //calculate hash
        int hash = key.hashCode();
       //used to account for possible negative hash values
        return Math.floorMod(hash, bucketNum);
    }
    /*returns Collection<Node> bucket at the given index or null if the bucket does not exist*/
    private Collection<Node> getBucket(int index) {
        Collection<Node> bucket = buckets[index];
        if (bucket == null){
            return null;
        }
        return bucket;
    }
}
