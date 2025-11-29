import ilsi.hash.StaticHashTable;
import ilsi.hash.RandomHashTable;
import ilsi.hash.OpenAddressingHashTable;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("------ This main class is designed to test different implementations of Hash Tables -----\n");

        // Test StaticHashTable
        System.out.println("===== Testing StaticHashTable =====");
        testStaticHashTable();
        
        // Test RandomHashTable
        System.out.println("\n===== Testing RandomHashTable =====");
        testRandomHashTable();
        
        // Test OpenAddressingHashTable
        System.out.println("\n===== Testing OpenAddressingHashTable =====");
        testOpenAddressingHashTable();
        
        // Problem 1: Two-Sum
        System.out.println("\n===== Problem 1: Two-Sum =====");
        testTwoSum();
        
        // Problem 2: Anagram Detection
        System.out.println("\n===== Problem 2: Anagram Detection =====");
        testAnagramDetection();
    }
    
    /**
     * Test StaticHashTable implementation
     */
    public static void testStaticHashTable() {
        StaticHashTable<String, Integer> numbers = new StaticHashTable<String, Integer>();
        numbers.insert("ISLI", 25);
        numbers.insert("GMS", 60);
        numbers.insert("GME", 40);
        numbers.insert("API", 450);

        System.out.println("Search for a key:");
        String filiere = "API";
        Integer eff = numbers.search(filiere);
        if (eff != null)
            System.out.println(filiere + " maps to " + eff);
        else
            System.out.println(filiere + " is not in the map");

        filiere = "GEC";
        eff = numbers.search(filiere);
        if (eff != null)
            System.out.println(filiere + " maps to " + eff);
        else
            System.out.println(filiere + " is not in the map");

        System.out.println("\nList of keys:");
        List<String> keys = numbers.keys();
        for (String k : keys) {
            System.out.println(k);
        }

        System.out.println("\nList of values:");
        List<Integer> values = numbers.values();
        for (Integer v : values) {
            System.out.println(v);
        }
        
        System.out.println("\nDeleting GME...");
        Integer deleted = numbers.delete("GME");
        System.out.println("Deleted value: " + deleted);
        System.out.println("Size after deletion: " + numbers.size());
    }
    
    /**
     * Test RandomHashTable implementation
     */
    public static void testRandomHashTable() {
        RandomHashTable<String, Integer> numbers = new RandomHashTable<String, Integer>();
        numbers.insert("ISLI", 25);
        numbers.insert("GMS", 60);
        numbers.insert("GME", 40);
        numbers.insert("API", 450);

        System.out.println("Search for API: " + numbers.search("API"));
        System.out.println("Search for GEC: " + numbers.search("GEC"));
        System.out.println("Size: " + numbers.size());
    }
    
    /**
     * Test OpenAddressingHashTable implementation
     */
    public static void testOpenAddressingHashTable() {
        OpenAddressingHashTable<String, Integer> numbers = new OpenAddressingHashTable<String, Integer>();
        numbers.insert("ISLI", 25);
        numbers.insert("GMS", 60);
        numbers.insert("GME", 40);
        numbers.insert("API", 450);

        System.out.println("Search for API: " + numbers.search("API"));
        System.out.println("Search for GEC: " + numbers.search("GEC"));
        System.out.println("Size: " + numbers.size());
        
        System.out.println("\nDeleting GMS...");
        Integer deleted = numbers.delete("GMS");
        System.out.println("Deleted value: " + deleted);
        System.out.println("Size after deletion: " + numbers.size());
    }
    
    /**
     * Problem 1: Two-Sum Problem
     * Given an array of integers and a target sum, find indices of two numbers that add up to target
     */
    public static void testTwoSum() {
        int[] T = {2, 7, 11, 15, 3, 6};
        int N = 9;
        
        System.out.println("Array: [2, 7, 11, 15, 3, 6]");
        System.out.println("Target sum: " + N);
        
        int[] result = twoSum(T, N);
        
        if (result != null) {
            System.out.println("Found at indices: " + result[0] + " and " + result[1]);
            System.out.println("Values: " + T[result[0]] + " + " + T[result[1]] + " = " + N);
        } else {
            System.out.println("No two numbers sum to " + N);
        }
        
        // Test another case
        System.out.println("\nTest case 2:");
        int[] T2 = {1, 3, 5, 7, 9};
        int N2 = 12;
        System.out.println("Array: [1, 3, 5, 7, 9]");
        System.out.println("Target sum: " + N2);
        
        int[] result2 = twoSum(T2, N2);
        if (result2 != null) {
            System.out.println("Found at indices: " + result2[0] + " and " + result2[1]);
            System.out.println("Values: " + T2[result2[0]] + " + " + T2[result2[1]] + " = " + N2);
        } else {
            System.out.println("No two numbers sum to " + N2);
        }
    }
    
    /**
     * Two-Sum solution using hash table
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int[] twoSum(int[] T, int N) {
        // Use hash table to store value -> index mapping
        StaticHashTable<Integer, Integer> map = new StaticHashTable<>();
        
        for (int i = 0; i < T.length; i++) {
            int complement = N - T[i];
            
            // Check if complement exists in hash table
            Integer complementIndex = map.search(complement);
            if (complementIndex != null) {
                return new int[] {complementIndex, i};
            }
            
            // Store current number and its index
            map.insert(T[i], i);
        }
        
        return null; // No solution found
    }
    
    /**
     * Problem 2: Anagram Detection
     * Check if two strings are anagrams (same characters, different order)
     */
    public static void testAnagramDetection() {
        String[][] testCases = {
            {"listen", "silent"},
            {"triangle", "integral"},
            {"apple", "pale"},
            {"Dormitory", "dirty room"},
            {"The Eyes", "They See"},
            {"hello", "world"}
        };
        
        for (String[] testCase : testCases) {
            String s1 = testCase[0];
            String s2 = testCase[1];
            boolean result = areAnagrams(s1, s2);
            System.out.println("\"" + s1 + "\" and \"" + s2 + "\" are anagrams: " + result);
        }
    }
    
    /**
     * Check if two strings are anagrams using hash table
     * Time complexity: O(n + m) where n and m are lengths of strings
     * Space complexity: O(k) where k is number of unique characters
     */
    public static boolean areAnagrams(String s1, String s2) {
        // Normalize strings: remove spaces, punctuation, convert to lowercase
        String normalized1 = normalizeString(s1);
        String normalized2 = normalizeString(s2);
        
        // Different lengths cannot be anagrams
        if (normalized1.length() != normalized2.length()) {
            return false;
        }
        
        // Use hash table to count character frequencies
        StaticHashTable<Character, Integer> charCount = new StaticHashTable<>();
        
        // Count characters in first string
        for (int i = 0; i < normalized1.length(); i++) {
            char c = normalized1.charAt(i);
            Integer count = charCount.search(c);
            if (count == null) {
                charCount.insert(c, 1);
            } else {
                charCount.insert(c, count + 1);
            }
        }
        
        // Subtract characters from second string
        for (int i = 0; i < normalized2.length(); i++) {
            char c = normalized2.charAt(i);
            Integer count = charCount.search(c);
            if (count == null) {
                return false; // Character not in first string
            } else {
                charCount.insert(c, count - 1);
            }
        }
        
        // Check if all counts are zero
        List<Integer> counts = charCount.values();
        for (Integer count : counts) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Normalize string by removing spaces, punctuation and converting to lowercase
     */
    private static String normalizeString(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
