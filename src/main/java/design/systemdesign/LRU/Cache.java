package design.systemdesign.LRU;

import java.util.ArrayList;

/**
 * @author: deadend
 * @date: 12:02 PM 11/04/2017
 */


public interface Cache<K, V> {

    V get(K key);

    void put(K key, V val);

    void put(K key, V val, long validTime);

    void remove(K key);

    ArrayList<Pair> getAll();

    int size();
}
