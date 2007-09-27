package org.quaere;

import static org.quaere.DSL.*;

import org.junit.Test;
import junit.framework.Assert;

import java.util.List;
import java.util.Arrays;

public class DSL_ElementOperatorsTest {
    @Test
    public void canGetFirstFromArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Assert.assertEquals(5, (int) first(numbers));
    }
    @Test
    public void canGetFirstFromIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2, 0);
        Assert.assertEquals(5, (int) first(numbers));
    }
    @Test
    public void canGetFirstFromQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2, 0));
        Assert.assertEquals(5, (int) first(numbers));
    }
}
