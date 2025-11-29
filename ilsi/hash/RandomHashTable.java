package ilsi.hash;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The classe RandomHashTable represent a hash table with random hashing.
 * The design of the Family of hash functions can based on : "multiply-shift".
 *
 * <p>The following example create a hash table of (int, String) pairs :</p>
 * <pre>
 *      RandomHashTable<String, Integer> numbers = new RandomHashTable<String, Integer>();
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
public class RandomHashTable<K, V> extends StaticHashTable<K, V> {
      protected long p;
      protected long a;
      protected long b;


    /**
     * Constructs a new empty RandomHashTable with a default initial capacity (16) 
     * and load factor (0.75).
     */
    public RandomHashTable() {
        super(16);
        this.p=1000000007;
        Random rand = new Random();
        this.a = rand.nextInt((int)p-1)+1;
        this.b = rand.nextInt((int)p);
        // TO DO
    }


    /**
     * Constructs a new empty RandomHashTable with the specified initial capacity 
     * and default load factor (0.75).
     * @param capacity initial capacity of the hash table
     */
    public RandomHashTable(int capacity) {
         super(capacity);
         this.p=1000000007;
         Random rand = new Random();
         this.a = rand.nextInt((int)p-1)+1;
         this.b = rand.nextInt((int)p);
        // TO DO
    }


    
    

}
