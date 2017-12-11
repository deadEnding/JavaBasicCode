package design.systemdesign.LRU;

public class Pair<K, V> {

    public K key;

    public V val;

    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair p = (Pair) obj;
            return key.equals(p.key) && val.equals(p.val);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return key.hashCode() ^ val.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[key=%s,val=%s]",
                getClass().getName(), key.toString(), val.toString());
    }
}
