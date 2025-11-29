package ilsi.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * The classe StaticHashTable represent a hash table with static hashing.
 * The hashing method used is "multiply-shift"
 *
 * <p>The following example create a hash table of (int, String) pairs :</p>
 * <pre>
 * StaticHashTable<String, Integer> numbers = new StaticHashTable<String, Integer>();
 * numbers.insert("ISLI", 25);
 * numbers.insert("GMS", 60);
 * numbers.insert("GME", 40);
 * numbers.insert("API", 450);
 * * </pre>
 * * <p>To retrieve a number, use the following code:</p>
 * <pre>
 * Integer n = numbers.search("API");
 * if (n != null) System.out.println("API : " + n);
 * </pre>
 */
public class StaticHashTable<K, V> extends HashTable<K, V> {


    protected long a = 6180339887L;
    protected int w = 32;
    protected int l;


    /**
     * Constructs a new empty StaticHashtable with a default initial capacity (16)
     * and load factor (0.75).
     */
    public StaticHashTable (){
        super (16);
        this.l = 4;
        this.m = 1 << l;  // this is 2^l
    }



    /**
     * Constructs a new empty StaticHashtable with the specified initial capacity
     * and default load factor (0.75).
     * @param capacity initial capacity of the hash table
     */
    public StaticHashTable(int capacity) {
        super(  1  <<  ( (int) (Math.log(capacity)/Math.log(2)) )  );
        this.l = (int) (Math.log(capacity)/Math.log(2));
        this.m = 1 << l;
    }



    /**
     * Hashes a key to a corresponding index
     * @param key the key to be hashed
     * @return int - the hash value
     */
    protected int hash (K key){

        int k = 0;
        if (key != null) k = key.hashCode();

        long ka = ((long) a * (long) k) & 0xFFFFFFFFL;  // this is  k*a mod 2^w
        return (int) (ka >>> (w - l));  // this is to take only l bits from ka (from the left - msb)
    }




    /**
     * Associates the specified value with the specified key in this hash table.
     * If the hash table previously contained a mapping for the key, the old value is replaced.
     * * @param key
     * @param value
     * * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public V insert (K key, V value){

        int index = this.hash(key);

        Element<K,V> p = this.table[index];
        while (p != null && !p.key.equals(key)){
            p = p.next;
        }
        if (p == null) { // key does not exist in the Hash Table, so we add (key, value)
            this.n ++ ; // Increment the size
            Element<K,V> elem = new Element<K,V>(key, value);
            elem.next = this.table[index];
            this.table[index] = elem;
            double loadFactor = (double) n/m;
            if (loadFactor > MAX_LOAD) {
                this.size();// TO DO Check if load factor is > than MAX_LOAD
            }
            return null;
        }
        else{           // key already in the hash table, so we modify the value
            V old = p.value;
            p.value = value;
            return old;
        }
    }


    /**
     * Search the value associated with a specified key in this hash table.
     * * @param key
     * * @return the value associated with key if exists, or null if there was no mapping for key.
     */
    public V search (K key){

        int index = this.hash(key);

        Element<K,V> p = this.table[index];
        while (p != null && !p.key.equals(key)){
            p = p.next;
        }
        if (p == null) return null;
        else return p.value;
    }




    /**
     * Removes the mapping for a key from this hash table if it is present.
     * @param key
     * * @return the value to which this hash table previously associated the key, or null if the table contained no mapping for the key.
     */
    public V delete (K key){

        int index = this.hash(key);
        Element<K,V> p = this.table[index];
        Element<K,V> prev = null;
        while (p != null && !p.key.equals(key)){
            prev = p;
            p=p.next;
        }

        if (p == null) return null;
        this.n--;
        V old = p.value;
        if(prev == null){
            this.table[index] = p.next;
        }else {
            prev.next = p.next;
        }
        // TO DO
        return old;
    }




    /**
     * Returns a list of the keys contained in this hash table.
     * @return a linked list of keys
     */
    public List<K> keys (){
        List<K> keys = new LinkedList<K>();
        Element<K,V> p ;
        for (int i = 0; i < m; i++){
            p = this.table[i];
            while (p != null){
                keys.add(p.key);
                p=p.next;
            }
        }
        return keys;
    }


    /**
     * Returns a list of the values contained in this hash table.
     * @return a linked list of values
     */
    public List<V> values (){
        List<V> values = new LinkedList<V>();
        Element<K,V> p ;
        for (int i = 0; i < m; i++){
            p = this.table[i];
            while (p != null){
                values.add(p.value);
                p=p.next;
            }
        }
        return values;
    }



    /**
     * Removes all of the (key, value) from this hash table.
     * The hash table will be empty after this call returns.
     */
    public void clear (){
        for (int i = 0; i < m; i++){
            this.table[i] = null;
        }
        this.n=0;
    }

}