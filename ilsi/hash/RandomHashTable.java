package ilsi.hash;

import java.util.Random;

/**
 * The classe RandomHashTable represent a hash table with random hashing.
 * The design of the Family of hash functions is based on multiply-shift with random parameters.
 */
public class RandomHashTable<K, V> extends StaticHashTable<K, V> {
    protected long p;
    protected long randomA;
    protected long randomB;

    /**
     * Constructs a new empty RandomHashTable with a default initial capacity (16) 
     * and load factor (0.75).
     */
    public RandomHashTable() {
        super(16);
        this.p = 1000000007L; // Large prime number
        Random rand = new Random();
        this.randomA = rand.nextInt((int)p - 1) + 1; // a in range [1, p-1]
        this.randomB = rand.nextInt((int)p);          // b in range [0, p-1]
    }

    /**
     * Constructs a new empty RandomHashTable with the specified initial capacity 
     * and default load factor (0.75).
     * @param capacity initial capacity of the hash table
     */
    public RandomHashTable(int capacity) {
        super(capacity);
        this.p = 1000000007L; // Large prime number
        Random rand = new Random();
        this.randomA = rand.nextInt((int)p - 1) + 1; // a in range [1, p-1]
        this.randomB = rand.nextInt((int)p);          // b in range [0, p-1]
    }

    /**
     * Hashes a key to a corresponding index using random hash function
     * Uses universal hashing: h(k) = ((a*k + b) mod p) mod m
     * @param key the key to be hashed
     * @return int - the hash value
     */
    @Override
    protected int hash(K key) {
        int k = 0;
        if (key != null) k = Math.abs(key.hashCode());
        
        // Universal hashing formula: ((a*k + b) mod p) mod m
        long hash = ((randomA * k + randomB) % p) % m;
        return (int) hash;
    }
}
