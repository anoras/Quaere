package org.quaere;

import org.junit.Test;
import junit.framework.Assert;

import java.util.Iterator;

public class RepeaterTest {
    @Test
    public void hasCorrectNumberOfRepitions() {
        Repeater<String> r = new Repeater<String>("S", 10);
        Iterator<String> riter = r.iterator();
        for (int i = 0; i < 10; i++) Assert.assertEquals("S", riter.next());
        Assert.assertFalse(riter.hasNext());
    }
}
