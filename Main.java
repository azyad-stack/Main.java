
import ilsi.hash.StaticHashTable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("------ This main class is designed to test different implementations of Hash Tables -----");

        StaticHashTable<String, Integer> numbers = new StaticHashTable<String, Integer>();
        numbers.insert("ISLI", 25);
        numbers.insert("GMS", 60);
        numbers.insert("GME", 40);
        numbers.insert("API", 450);

        System.out.println("Search for a key : ");

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

        System.out.println("List of keys : ");
        List<String> keys = numbers.keys();
        for (String k : keys) {
            System.out.println(k);
        }

        System.out.println("List of values : ");
        List<Integer> values = numbers.values();
        for (Integer v : values) {
            System.out.println(v);
        }

    }
}