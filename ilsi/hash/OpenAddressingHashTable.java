package ilsi.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * The classe OpenAddressingHashTable represent a hash table with Open Addressing to handle collisions.
 * The probing method is double hashing.
 */
public class OpenAddressingHashTable<K, V> extends HashTable<K, V> {
    
    /**
     * Constructs a new empty OpenAddressingHashTable with a default initial capacity (16)
     */
    public OpenAddressingHashTable() {
        super(16);
    }
    
    /**
     * Constructs a new empty OpenAddressingHashTable with specified capacity
     */
    public OpenAddressingHashTable(int capacity) {
        super(capacity);
    }
    
    /**
     * First hash function
     */
    private int h1(int key) {
        int k = Math.abs(key);
        return k % m;
    }

    /**
     * Second hash function for double hashing
     */
    private int h2(int key) {
        int k = Math.abs(key);
        int mprime = m - 1;
        return 1 + (k % mprime);
    }

    /**
     * Hashes a key to a corresponding index using Double Hashing.
     * Formula: (h1(k) + i * h2(k)) % m
     * @param key the key to be hashed
     * @param i the probe number
     * @return int - the hash value
     */
    protected int hash(K key, int i) {
        int kInt = key.hashCode();
        return (h1(kInt) + i * h2(kInt)) % m;
    }
    
    /**
     * Regular hash method (required by parent class)
     */
    @Override
    protected int hash(K key) {
        return hash(key, 0);
    }

    /**
     * Associates the specified value with the specified key in this hash table.
     * If the hash table previously contained a mapping for the key, the old value is replaced.
     */
    @Override
    public V insert(K key, V value) {
        for (int i = 0; i < m; i++) {
            int index = this.hash(key, i);
            Element<K, V> e = table[index];

            if (e == null) {
                table[index] = new Element<>(key, value);
                n++;
                return null;
            }

            if (e.key.equals(key)) {
                V old = e.value;
                e.value = value;
                return old;
            }
        }

        System.out.println("Hash table is full - insertion failed");
        return null;
    }

    /**
     * Search the value associated with a specified key in this hash table.
     */
    @Override
    public V search(K key) {
        for (int i = 0; i < m; i++) {
            int index = this.hash(key, i);
            Element<K, V> e = table[index];

            if (e == null) {
                return null;
            }

            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * Removes the mapping for a key from this hash table if it is present.
     */
    @Override
    public V delete(K key) {
        for (int i = 0; i < m; i++) {
            int index = this.hash(key, i);
            Element<K, V> e = table[index];

            if (e == null) {
                return null;
            }

            if (e.key.equals(key)) {
                V oldValue = e.value;
                table[index] = null;
                n--;
                return oldValue;
            }
        }
        return null;
    }

    /**
     * Returns a list of the keys contained in this hash table.
     */
    @Override
    public List<K> keys() {
        List<K> keys = new LinkedList<K>();
        
        for (int i = 0; i < m; i++) {
            if (table[i] != null) {
                keys.add(table[i].key);
            }
        }
        
        return keys;
    }

    /**
     * Returns a list of the values contained in this hash table.
     */
    @Override
    public List<V> values() {
        List<V> values = new LinkedList<V>();
        
        for (int i = 0; i < m; i++) {
            if (table[i] != null) {
                values.add(table[i].value);
            }
        }
        
        return values;
    }

    /**
     * Removes all of the (key, value) from this hash table.
     */
    @Override
    public void clear() {
        for (int i = 0; i < m; i++) {
            this.table[i] = null;
        }
        n = 0;
    }
}
