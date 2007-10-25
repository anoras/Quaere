package org.quaere;

import org.junit.Test;
import org.junit.Assert;
import org.quaere.CharacterRange;

import java.util.Iterator;

public class CharacterRangeTest {
    @Test
    public void canIterateRange()
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
}
