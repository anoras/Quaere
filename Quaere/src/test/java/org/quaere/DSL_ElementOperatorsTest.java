package org.quaere;

import junit.framework.Assert;
import org.junit.Test;


import java.util.Arrays;

public class DSL_ElementOperatorsTest {
    @Test
    public void canGetFirstFromArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Assert.assertEquals(5, (int) DSL.first(numbers));
    }
    @Test
    public void canGetFirstFromIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2, 0);
        // Note: Eclipse fails to build this test if the result from "first" is not casted to first's R.
        Assert.assertEquals((Integer) 5, (Integer) DSL.first(numbers));
    }
    @Test
    public void canGetFirstFromQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2, 0));
        // Note: Eclipse fails to build this test if the result from "first" is not casted to first's R.
        Assert.assertEquals((Integer) 5, (Integer) DSL.first(numbers));
    }
}
