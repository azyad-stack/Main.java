package ilsi.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * The classe StaticHashTable represent a hash table with static hashing.
 * The hashing method used is "multiply-shift"
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
     * Resizes the hash table when load factor exceeds MAX_LOAD
     */
    private void resize() {
        // Double the capacity
        this.l++;
        int oldM = this.m;
        this.m = 1 << l;
        
        // Save old table
        Element<K,V>[] oldTable = this.table;
        
        // Create new table with doubled capacity
        this.table = (Element<K,V>[]) new Element[this.m];
        for (int i = 0; i < this.m; i++) {
            this.table[i] = null;
        }
        
        // Reset size and re-insert all elements
        this.n = 0;
        for (int i = 0; i < oldM; i++) {
            Element<K,V> p = oldTable[i];
            while (p != null) {
                this.insert(p.key, p.value);
                p = p.next;
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this hash table.
     * If the hash table previously contained a mapping for the key, the old value is replaced.
     */
    public V insert (K key, V value){
        int index = this.hash(key);

        Element<K,V> p = this.table[index];
        while (p != null && !p.key.equals(key)){
            p = p.next;
        }
        if (p == null) { // key does not exist in the Hash Table, so we add (key, value)
            this.n++; // Increment the size
            Element<K,V> elem = new Element<K,V>(key, value);
            elem.next = this.table[index];
            this.table[index] = elem;
            
            // Check if load factor exceeds MAX_LOAD and resize if needed
            double loadFactor = (double) n / m;
            if (loadFactor > MAX_LOAD) {
                this.resize();
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
     */
    public V delete (K key){
        int index = this.hash(key);
        Element<K,V> p = this.table[index];
        Element<K,V> prev = null;
        
        while (p != null && !p.key.equals(key)){
            prev = p;
            p = p.next;
        }

        if (p == null) return null;
        
        this.n--;
        V old = p.value;
        
        if(prev == null){
            this.table[index] = p.next;
        } else {
            prev.next = p.next;
        }
        
        return old;
    }

    /**
     * Returns a list of the keys contained in this hash table.
     */
    public List<K> keys (){
        List<K> keys = new LinkedList<K>();
        Element<K,V> p;
        for (int i = 0; i < m; i++){
            p = this.table[i];
            while (p != null){
                keys.add(p.key);
                p = p.next;
            }
        }
        return keys;
    }

    /**
     * Returns a list of the values contained in this hash table.
     */
    public List<V> values (){
        List<V> values = new LinkedList<V>();
        Element<K,V> p;
        for (int i = 0; i < m; i++){
            p = this.table[i];
            while (p != null){
                values.add(p.value);
                p = p.next;
            }
        }
        return values;
    }

    /**
     * Removes all of the (key, value) from this hash table.
     */
    public void clear (){
        for (int i = 0; i < m; i++){
            this.table[i] = null;
        }
        this.n = 0;
    }
}
