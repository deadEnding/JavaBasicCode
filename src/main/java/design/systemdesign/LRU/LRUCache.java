package design.systemdesign.LRU;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class LRUCache<K, V> implements Cache<K, V>, Serializable {

    private class Node {
        K key;
        V val;
        long expire;
        Node prev, next;

        public Node(K key, V val, long expire) {
            this.key = key;
            this.val = val;
            this.expire = expire;
        }
    }

    private int capacity;
    private ConcurrentHashMap<K, Node> map;
    private Node dummy;

    private Object lock = new Object();


    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new ConcurrentHashMap<>();
        dummy = new Node(null, null, -1);
        dummy.prev = dummy.next = dummy;
    }

    private void link(Node prev, Node node) {
        synchronized (lock) {
            Node next = prev.next;
            prev.next = node;
            node.next = next;
            node.prev = prev;
            next.prev = node;
        }
    }

    private void unlink(Node node) {
        synchronized (lock) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private void addLast(Node node) {
        link(dummy.prev, node);
    }

    private void remove0(K key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
            map.remove(key);
        }
    }

    private void removeFirst() {
        remove0(dummy.next.key);
    }

    private void moveToLast(Node node) {
        unlink(node);
        addLast(node);
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);
        if (node.expire < System.currentTimeMillis()) {
            remove(key);
            return null;
        }

        moveToLast(node);
        return node.val;
    }

    @Override
    public void put(K key, V val) {
        put(key, val, -1);
    }

    @Override
    public void put(K key, V val, long validTime) {
        long ex = validTime > 0 ? System.currentTimeMillis() + validTime : Long.MAX_VALUE;
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = val;
            node.expire = ex;
            moveToLast(node);
        } else {
            Node node = new Node(key, val, ex);
            addLast(node);
            if (map.size() > capacity) {
                removeFirst();
            }
        }
    }

    @Override
    public void remove(K key) {
        remove0(key);
    }

    @Override
    public ArrayList<Pair> getAll() {
        ArrayList<Pair> pairs = new ArrayList<>(capacity);
        synchronized (lock) {
            long t = System.currentTimeMillis();
            Node p = dummy.next;
            while (p != dummy) {
                if (p.expire < t) {
                    remove(p.key);
                } else {
                    pairs.add(new Pair(p.key, p.val));
                }
                p = p.next;
            }
        }
        return pairs;
    }

    @Override
    public int size() {
        return map.size();
    }
}
