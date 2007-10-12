package org.quaere.alias;

import java.lang.ref.WeakReference;

public class WeakIdentityHashMap<K, V> {
    private int mask, len, size, deletedCount, level;
    private int maxSize, minSize, maxDeleted;
    private static final int MAX_LOAD = 90;
    private static final WeakReference DELETED_KEY = new WeakReference(null);
    private WeakReference<K>[] keys;
    private V[] values;
    
    public WeakIdentityHashMap() {
        reset(2);
    }
    
    public int size() {
        return size;
    }

    private void checkSizePut() {
        if (deletedCount > size) {
            rehash(level);
        }
        if (size + deletedCount >= maxSize) {
            rehash(level + 1);
        }
    }

    private void checkSizeRemove() {
        if (size < minSize && level > 0) {
            rehash(level - 1);
        } else if (deletedCount > maxDeleted) {
            rehash(level);
        }
    }

    private int getIndex(Object key) {
        return System.identityHashCode(key) & mask;
    }
    
    private void reset(int newLevel) {
        minSize = size * 3 / 4;
        size = 0;
        level = newLevel;
        len = 2 << level;
        mask = len - 1;
        maxSize = (int) (len * MAX_LOAD / 100L);
        deletedCount = 0;
        maxDeleted = 20 + len / 2;
        keys = new WeakReference[len];
        values = (V[]) new Object[len];
    }

    public void put(K key, V value) {
        checkSizePut();
        int index = getIndex(key);
        int plus = 1;
        int deleted = -1;
        do {
            WeakReference k = keys[index];
            if (k == null) {
                // found an empty record
                if (deleted >= 0) {
                    index = deleted;
                    deletedCount--;
                }
                size++;
                keys[index] = new WeakReference(key);
                values[index] = value;
                return;
            } else if (k == DELETED_KEY) {
                if (deleted < 0) {
                    // found the first deleted record
                    deleted = index;
                }
            } else {
                Object r = k.get();
                if (r == null) {
                    delete(index);
                } else if (r == key) {
                    // update existing
                    values[index] = value;
                    return;
                }
            }
            index = (index + plus++) & mask;
        } while(plus <= len);
        throw new Error("hashmap is full");
    }

    public void remove(Object key) {
        checkSizeRemove();
        int index = getIndex(key);
        int plus = 1;
        do {
            WeakReference k = keys[index];
            if (k == null) {
                // found an empty record
                return;
            } else if (k == DELETED_KEY) {
                // continue
            } else {
                Object r = k.get();
                if (r == null) {
                    delete(index);
                } else if (r == key) {
                    // found the record
                    delete(index);
                    return;
                }
            }
            index = (index + plus++) & mask;
            k = keys[index];
        } while(plus <= len);
        // not found
    }
    
    private void delete(int index) {
        keys[index] = DELETED_KEY;
        values[index] = null;
        deletedCount++;
        size--;
    }

    private void rehash(int newLevel) {
        WeakReference[] oldKeys = keys;
        V[] oldValues = values;
        reset(newLevel);
        for (int i = 0; i < oldKeys.length; i++) {
            WeakReference<K> k = oldKeys[i];
            if (k != null && k != DELETED_KEY) {
                K key = k.get();
                if (key != null) {
                    put(key, oldValues[i]);
                }
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        int plus = 1;
        do {
            WeakReference k = keys[index];
            if (k == null) {
                return null;
            } else if (k == DELETED_KEY) {
                // continue
            } else {
                Object r = k.get();
                if (r == null) {
                    delete(index);
                } else if (r == key) {
                    return values[index];
                }
            }
            index = (index + plus++) & mask;
        } while(plus <= len);
        return null;
    }

}
