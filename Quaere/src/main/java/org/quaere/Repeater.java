package org.quaere;

import java.util.Iterator;

public class Repeater<T> implements Iterable<T> {
    private final T value;
    private final int repititions;

    public Repeater(T value, int repititions) {
        this.value = value;
        this.repititions = repititions;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            public boolean hasNext() {
                return index < repititions;
            }
            public T next() {
                if (index >= repititions) throw new ArrayIndexOutOfBoundsException();
                index++;
                return value;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
