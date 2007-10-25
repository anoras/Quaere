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
                return current < to;
            }
            public Character next() {
                return current++;
            }
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove elements from a range.");
            }
        };
    }
}
