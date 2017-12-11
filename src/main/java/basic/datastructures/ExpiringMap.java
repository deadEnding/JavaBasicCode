package basic.datastructures;

import java.util.HashMap;

/**
 * @author: yuhui
 * @date: 14/06/2017
 */


public class ExpiringMap<K, V> {

    private HashMap<K, Value> map;

    public ExpiringMap() {
        map = new HashMap<>();
    }

    public void put(K key, V val, long duration) {
        long expiredTime = System.currentTimeMillis() + duration;
        map.put(key, new Value<>(val, expiredTime));
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        Value<V> value = map.get(key);
        if (value.isExpired()) {
            map.remove(key);
            return null;
        }

        return (V) map.get(key).val;   // 为什么要强制类型转换
    }

    private class Value<V> {
        private V val;
        private long expiredTime;

        public Value(V val, long expiredTime) {
            this.val = val;
            this.expiredTime = expiredTime;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiredTime;
        }
    }

}
