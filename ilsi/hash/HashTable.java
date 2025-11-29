package ilsi.hash;

import java.util.LinkedList;
import java.util.List;


/**
 * This class provides a skeletal implementation of a hash table.
 * HashTable is an abstract class representing a hash table data structure.
 * This class would be extended by 3 classes :
 * 1 - StaticHashTable
 * 2 - RandomHashTable
 * 3 - OpenAddressingHashTable
 * 
 * @param <K> the type of keys maintained by this hash table
 * @param <V> the type of mapped values
 */
public abstract class HashTable <K,V> {
    
    /**
     * This is the capacity of the hash table : the number of slots
     */
    protected int m;

    /**
     * This is the size of the hash table : the number of stored elements
     */
    protected int n;

    /**
     * This is a constant, the max load factor allowed. We fix it to 0.75
     */
    protected final double MAX_LOAD = 0.75;

    /**
     * An array of elements in the forme (key, value)
     */
    protected Element<K,V> [] table; 




    /**
     * Constructor of new empty hashtable with the specified initial capacity 
     * and default load factor (0.75).
     * @param capacity initial capacity of the hash table
     */
    public HashTable (int capacity){
        this.m = capacity;
        this.n = 0;
        this.table = (Element<K,V>[]) new Element[capacity];
        for (int i=0; i<m; i++) table[i] = null;
    }


    /**
     * Constructor of new empty hashtable with a default initial capacity (16) 
     * and load factor (0.75).
     */
    public HashTable (){
        this(16);
    }


    /**
     * Hashes a key to a corresponding index
     * 
     * Should be implemented by subclasses
     * 
     * @param key the key to be hashed
     * @return int - the hash value 
     */
    protected abstract int hash (K key);

    
    
    /**
     * Associates the specified value with the specified key in this hash table. 
     * If the hash table previously contained a mapping for the key, the old value is replaced.
     * 
     * Should be implemented by subclasses
     * 
     * @param key
     * @param value
     * 
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public abstract V insert (K key, V value);


    /**
     * Search the value associated with a specified key in this hash table.
     * 
     * Should be implemented by subclasses 
     * 
     * @param key
     * 
     * @return the value associated with key if exists, or null if there was no mapping for key.
     */
    public abstract V search (K key);


    /**
     * Removes the mapping for a key from this hash table if it is present. 
     * 
     * Should be implemented by subclasses
     * 
     * @param key
     * @return the value to which this hash table previously associated the key, or null if the table contained no mapping for the key.
     */
    public abstract V delete (K key);



    /**
     * Returns a list of the keys contained in this hash table.
     * 
     * Should be implemented by subclasses
     * 
     * @return a linked list of keys
     */
    public abstract List<K> keys ();


    /**
     * Returns a list of the values contained in this hash table.
     * 
     * Should be implemented by subclasses
     * 
     * @return a linked list of values
     */
    public abstract List<V> values ();


    /**
     * Removes all of the (key, value) from this hash table. 
     * The hash table will be empty after this call returns.
     * 
     * Should be implemented by subclasses
     */
    public abstract void clear ();

    
    /**
     * Returns the number of key-value mappings in this hash table.
     */
    public int size (){
        return this.n;
    }

    /**
     * Returns true if this hash table contains no key-value mappings
     */
    public boolean isEmpty (){
        return this.n == 0;
    }



    
    /**
     * This class represent an element to be stored in a hash table
     * each element is a mapping between a key and a value
     */
    protected class Element <K, V> {
        protected K key;
        protected V value;
        protected Element next;
        
        public Element (K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }

        public void setValue (V value){
            this.value = value;
        }
    }

}
