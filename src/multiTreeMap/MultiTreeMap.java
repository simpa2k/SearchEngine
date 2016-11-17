package multiTreeMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class MultiTreeMap<K extends Comparable<K>, V> {

    private TreeMap<K, HashSet<V>> treeMap = new TreeMap<>();

    public void insert(K key, V value) {

        HashSet<V> bucket = treeMap.get(key);
        
        if(bucket == null) {

            bucket = new HashSet<V>();
            bucket.add(value);

            treeMap.put(key, bucket);

        } else {
            bucket.add(value);
        }
    }

    public HashSet<V> get(K key) {
        return treeMap.get(key);
    }

    @Override
    public String toString() {
        return treeMap.toString();
    }

}
