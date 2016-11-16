package multiTreeMap;

import java.util.HashMap;
import java.util.TreeMap;

public class MultiTreeMap<K1 extends Comparable<K1>, K2, V> {

    private TreeMap<K1, HashMap<K2, V>> treeMap = new TreeMap<>();

    public void insert(K1 key1, K2 key2, V value) {

        HashMap<K2, V> bucket = treeMap.get(key1);
        
        if(bucket == null) {

            bucket = new HashMap<K2, V>();
            bucket.put(key2, value);

            treeMap.put(key1, bucket);

        } else {
            bucket.put(key2, value);
        }
    }

    public V get(K1 key1, K2 key2) {

        HashMap<K2, V> bucket = treeMap.get(key1);
        return bucket.get(key2);
        
    }

    @Override
    public String toString() {
        return treeMap.toString();
    }

}
