package org.quaere;

import org.junit.Test;
import org.junit.Assert;
import org.quaere.CharacterRange;

import java.util.Iterator;

public class CharacterRangeTest {
    @Test
    public void canCreateRange()
    {
        CharacterRange abcdef=new CharacterRange('A','F');
        Iterator<Character> iter=abcdef.iterator();
        Assert.assertEquals('A',iter.next());
        Assert.assertEquals('B',iter.next());
        Assert.assertEquals('C',iter.next());
        Assert.assertEquals('D',iter.next());
        Assert.assertEquals('E',iter.next());
        Assert.assertEquals('F',iter.next());
        Assert.assertFalse(iter.hasNext());
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void arrayIndexOutOfBoundsExceptionIsThrownWhenIteratorPassesEndOfRange() {
        CharacterRange ab=new CharacterRange('A','B');
        Iterator<Character> riter = ab.iterator();
        riter.next();
        riter.next();
        riter.next();
    }
    @Test
    public void canCreateReversedRange() {
        CharacterRange fedcba=new CharacterRange('f','a');
        Iterator<Character> iter=fedcba.iterator();
        Assert.assertEquals('f',iter.next());
        Assert.assertEquals('e',iter.next());
        Assert.assertEquals('d',iter.next());
        Assert.assertEquals('c',iter.next());
        Assert.assertEquals('b',iter.next());
        Assert.assertEquals('a',iter.next());
        Assert.assertFalse(iter.hasNext());
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void arrayIndexOutOfBoundsExceptionIsThrownWhenIteratorPassesBeginningOfReversedRange() {
        CharacterRange ba=new CharacterRange('B','A');
        Iterator<Character> riter = ba.iterator();
        riter.next();
        riter.next();
        riter.next();
    }}
