package org.quaere;

import java.util.Iterator;

public class CharacterRange implements Iterable<Character> {
    private final Character from;
    private final Character to;
    private final int inc;
    public CharacterRange(Character from, Character to) {
        this.from = from;
        this.to = to;
        inc=Comparer.compare(to,from);
    }
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private Character current = from;
            public boolean hasNext() {
                if (inc>0) return current<to+1;
                else return current>to-1;

            }
            public Character next() {
                if (!hasNext()) throw new ArrayIndexOutOfBoundsException();
                if (inc>0) {
                    return current++;
                } else {
                    return current--;
                }
            }
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove elements from a range.");
            }
        };
    }
}
