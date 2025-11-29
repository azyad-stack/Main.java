package ilsi.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * The classe OpenAddressingHashTable represent a hash table with Open Addressing to handle collisions.
 *
 * The probing method can be : "double"
 *
 * <p>The following example create a hash table of (int, String) pairs :</p>
 * <pre>
 *      OpenAddressingHashTable<String, Integer> numbers = new OpenAddressingHashTable<String, Integer>();
 *      numbers.insert("ISLI", 25);
 *      numbers.insert("GMS", 60);
 *      numbers.insert("GME", 40);
 *      numbers.insert("API", 450);
 *
 * </pre>
 *
 * <p>To retrieve a number, use the following code:</p>
 * <pre>
 *      Integer n = numbers.search("API");
 *      if (n != null) System.out.println("API : " + n);
 * </pre>
 */
public abstract class OpenAddressingHashTable<K, V> extends HashTable<K, V> {
    int m = this.capacity;
    int mprime = this.capacity - 1;

    private int h1(int key) {
        int k = Math.abs(key);
        return k % m;
    }

    private int h2(int key) {
        int k = Math.abs(key);
        return 1 + (k % mprime);
    }

    /**
     * Hashes a key to a corresponding index using Double Hashing.
     * Formula: (h1(k) + i * h2(k)) % m
     */

    /**
     * Hashes a key to a corresponding index
     * @param key the key to be hashed
     * @return int - the hash value
     */
    public int hash(K key, int i) {
        // We must convert the Generic K to an int using hashCode()
        int kInt = key.hashCode();
        return (h1(kInt) + i * h2(kInt)) % m;
    }


    /**
     * Associates the specified value with the specified key in this hash table.
     * If the hash table previously contained a mapping for the key, the old value is replaced.
     *
     * @param key
     * @param value
     *
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
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

        System.out.println("Insertion overflow");
        return null;
    }

    /**
     * Search the value associated with a specified key in this hash table.
     *
     * @param key
     *
     * @return the value associated with key if exists, or null if there was no mapping for key.
     */
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
     * @param key
     *
     * @return the value to which this hash table previously associated the key, or null if the table contained no mapping for the key.
     */
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
     * @return a linked list of keys
     */
    public List<K> keys() {
        List<K> keys = new LinkedList<K>();
        Element<K, V> p;

        for (int i = 0; i < m; i++) {
            p = this.table[i];
            if (p != null) {
                keys.add(p.key);
            }
        }

        return keys;
    }

    /**
     * Returns a list of the values contained in this hash table.
     * @return a linked list of values
     */
    public List<V> values() {
        List<V> values = new LinkedList<V>();
        Element<K, V> p;

        for (int i = 0; i < m; i++) {
            p = this.table[i];
            if (p != null) {
                values.add(p.value);
            }
        }

        return values;
    }

    /**
     * Removes all of the (key, value) from this hash table.
     * The hash table will be empty after this call returns.
     */
    public void clear() {
        for (int i = 0; i < m; i++) {
            this.table[i] = null;
        }
        n = 0;
    }

    /**
     * This class represent an element to be stored in a hash table
     * each element is a mapping between a key and a value
     */
    protected static class Element<K, V> {
        protected K key;
        protected V value;

        public Element(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}