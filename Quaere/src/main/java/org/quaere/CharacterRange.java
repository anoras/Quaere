package org.quaere;

import java.util.Iterator;

public class CharacterRange implements Iterable<Character> {
    private final Character from;
    private final Character to;
    public CharacterRange(Character from, Character to) {
        this.from = from;
        this.to = to;
    }
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private Character current = from;
            public boolean hasNext() {
                return current < (to+1);
            }
            public Character next() {
                if (!hasNext()) throw new ArrayIndexOutOfBoundsException();
                return current++;
            }
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove elements from a range.");
            }
        };
    }
}
