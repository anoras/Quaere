package org.quaere;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DSL_AggregateOperatorsTest {
    @Test
    public void canApplyCountToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Double count = DSL.count.in(Double.class, numbers);
        Assert.assertEquals(9D, count);
    }
    @Test
    public void canSpecifyClassWhenApplyingCountToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Integer count = DSL.count.in(Integer.class, numbers);
        Assert.assertEquals(9, count);
    }
    @Test
    public void canApplyCountToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Double sum = DSL.count.in(Double.class, numbers);
        Assert.assertEquals(9D, sum);
    }
    @Test
    public void canSpecifyClassWhenApplyingCountToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Integer count = DSL.count.in(Integer.class, numbers);
        Assert.assertEquals(9, count);
    }
    @Test
    public void canApplyCountToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Double count = DSL.count.in(Double.class, numbers);
        Assert.assertEquals(9D, count);
    }
    @Test
    public void canSpecifyClassWhenApplyingCountToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Integer count = DSL.count.in(Integer.class, numbers);
        Assert.assertEquals(9, count);
    }
    @Test
    public void canApplySumToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Double sum = DSL.sum.of(Double.class, numbers);
        Assert.assertEquals(45D, sum);
    }
    @Test
    public void canSpecifyClassWhenApplyingSumToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Integer sum = DSL.sum.of(Integer.class, numbers);
        Assert.assertEquals(45, sum);
    }
    @Test
    public void canApplySumToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Double sum = DSL.sum.of(Double.class, numbers);
        Assert.assertEquals(45D, sum);
    }
    @Test
    public void canSpecifyClassWhenApplyingSumToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Integer sum = DSL.sum.of(Integer.class, numbers);
        Assert.assertEquals(45, sum);
    }
    @Test
    public void canApplySumToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Double sum = DSL.sum.of(Double.class, numbers);
        Assert.assertEquals(45D, sum);
    }
    @Test
    public void canSpecifyClassWhenApplyingSumToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Integer sum = DSL.sum.of(Integer.class, numbers);
        Assert.assertEquals(45, sum);
    }
    @Test
    public void canApplyMinToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Double min = DSL.min.in(Double.class, numbers);
        Assert.assertEquals(1D, min);
    }
    @Test
    public void canSpecifyClassWhenApplyingMinToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Integer min = DSL.min.in(Integer.class, numbers);
        Assert.assertEquals(1, min);
    }
    @Test
    public void canApplyMinToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Double min = DSL.min.in(Double.class, numbers);
        Assert.assertEquals(1D, min);
    }
    @Test
    public void canSpecifyClassWhenApplyingMinToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Integer min = DSL.min.in(Integer.class, numbers);
        Assert.assertEquals(1, min);
    }
    @Test
    public void canApplyMinToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Double min = DSL.min.in(Double.class, numbers);
        Assert.assertEquals(1D, min);
    }
    @Test
    public void canSpecifyClassWhenApplyingMinToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Integer min = DSL.min.in(Integer.class, numbers);
        Assert.assertEquals(1, min);
    }
    @Test
    public void canApplyMaxToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Double max = DSL.max.in(Double.class, numbers);
        Assert.assertEquals(9D, max);
    }
    @Test
    public void canSpecifyClassWhenApplyingMaxToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Integer max = DSL.max.in(Integer.class, numbers);
        Assert.assertEquals(9, max);
    }
    @Test
    public void canApplyMaxToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Double max = DSL.max.in(Double.class, numbers);
        Assert.assertEquals(9D, max);
    }
    @Test
    public void canSpecifyClassWhenApplyingMaxToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Integer max = DSL.max.in(Integer.class, numbers);
        Assert.assertEquals(9, max);
    }
    @Test
    public void canApplyMaxToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Double max = DSL.max.in(Double.class, numbers);
        Assert.assertEquals(9D, max);
    }
    @Test
    public void canSpecifyClassWhenApplyingMaxToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Integer max = DSL.max.in(Integer.class, numbers);
        Assert.assertEquals(9, max);
    }
    @Test
    public void canApplyAvgerageToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Double avg = DSL.avg.in(Double.class, numbers);
        Assert.assertEquals(5D, avg);
    }
    @Test
    public void canSpecifyClassWhenApplyingAverageToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        Integer avg = DSL.avg.in(Integer.class, numbers);
        Assert.assertEquals(5, avg);
    }
    @Test
    public void canApplyAverageToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Double avg = DSL.avg.in(Double.class, numbers);
        Assert.assertEquals(5D, avg);
    }
    @Test
    public void canSpecifyClassWhenApplyingAverageToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        Integer avg = DSL.avg.in(Integer.class, numbers);
        Assert.assertEquals(5, avg);
    }
    @Test
    public void canApplyAverageToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Double avg = DSL.avg.in(Double.class, numbers);
        Assert.assertEquals(5D, avg);
    }
    @Test
    public void canSpecifyClassWhenApplyingAverageToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        Integer avg = DSL.avg.in(Integer.class, numbers);
        Assert.assertEquals(5, avg);
    }
}
