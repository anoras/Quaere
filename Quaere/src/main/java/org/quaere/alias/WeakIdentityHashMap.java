package org.quaere.alias;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakIdentityHashMap<K, V> implements Map<K, V> {
    
    public static class IdentityWrapper<U> {
        private int identityHashCode;
        private U object;
        IdentityWrapper(U o) {
            object = o;
        }
        public int hashCode() {
            if (identityHashCode == 0) {
                identityHashCode = System.identityHashCode(object);
            }
            return identityHashCode;
        }

        public boolean equals(Object o) {
            if (o instanceof IdentityWrapper) {
                IdentityWrapper w = (IdentityWrapper) o;
                return w.object == this.object;
            }
            assert false;
            return false;
        }

        public U getObject() {
            return object;
        }
    }
    
    private WeakHashMap<IdentityWrapper<K>, IdentityWrapper<V>> map = new WeakHashMap<IdentityWrapper<K>, IdentityWrapper<V>>();

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(new IdentityWrapper<Object>(key));
    }

    public boolean containsValue(Object value) {
        return map.containsValue(new IdentityWrapper<Object>(value));
    }

    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    public V get(Object key) {
        return map.get(new IdentityWrapper<Object>(key)).getObject();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V put(K key, V value) {
        IdentityWrapper<V> w = map.put(new IdentityWrapper<K>(key), new IdentityWrapper<V>(value));
        if (w == null) {
            return null;
        }
        return w.getObject();
    }

    public void putAll(Map< ? extends K, ? extends V> m) {
        for (Object o : m.entrySet()) {
            Map.Entry< ? extends K, ? extends V> e = (Map.Entry< ? extends K, ? extends V>) o;
            put(e.getKey(), e.getValue());
        }
    }

    public V remove(Object key) {
        IdentityWrapper<V> w = map.remove(new IdentityWrapper<Object>(key));
        if (w == null) {
            return null;
        }
        return w.getObject();
    }

    public int size() {
        return map.size();
    }

    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<V>(map.size());
        for (IdentityWrapper<V> x : map.values()) {
            list.add(x.getObject());
        }
        return list;
    }

}
