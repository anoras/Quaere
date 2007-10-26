package org.quaere.dsl;

import org.junit.Assert;
import org.junit.Test;
import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.expressions.*;

import java.util.Arrays;

public class AggregationClauseBuilderTest {
    @Test
    public void canApplyMinOperatorToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("min",new Identifier("n"));
        Double min = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(1D, min);
    }
    @Test
    public void canApplyMinOperatorToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("min",new Identifier("n"));
        Double min = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(1D, min);
    }
    @Test
    public void canApplyMinOperatorToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("min",new Identifier("n"));
        Double min = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(1D, min);
    }
    @Test
    public void canApplyMinOperatorWithLiteralQualifierExpression() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("min",new Identifier("n"));
        Double min = operatorBuilder.by("n + 1").in(Double.class,numbers);
        Assert.assertEquals(2D, min);
    }
    @Test
    public void canApplyMinOperatorWithQualifierExpression() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("min",new Identifier("n"));
        Double min = operatorBuilder.by(
                new PlusOperator(
                        new Statement(
                                Arrays.<Expression>asList(new Identifier("n"))
                        ),
                        new Statement(
                                Arrays.<Expression>asList(new Constant(1))
                        )
                )
        ).in(Double.class,numbers);
        Assert.assertEquals(2D, min);
    }
    @Test
    public void canApplyMaxOperatorToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("max",new Identifier("n"));
        Double max = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(9D, max);
    }
    @Test
    public void canApplyMaxOperatorToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("max",new Identifier("n"));
        Double max = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(9D, max);
    }
    @Test
    public void canApplyMaxOperatorToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("max",new Identifier("n"));
        Double max = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(9D, max);
    }
    @Test
    public void canApplyMaxOperatorWithLiteralQualifierExpression() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("max",new Identifier("n"));
        Double max = operatorBuilder.by("n + 1").in(Double.class,numbers);
        Assert.assertEquals(10D, max);
    }
    @Test
    public void canApplyMaxOperatorWithQualifierExpression() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("max",new Identifier("n"));
        Double max = operatorBuilder.by(
                new PlusOperator(
                        new Statement(
                                Arrays.<Expression>asList(new Identifier("n"))
                        ),
                        new Statement(
                                Arrays.<Expression>asList(new Constant(1))
                        )
                )
        ).in(Double.class,numbers);
        Assert.assertEquals(10D, max);
    }
    @Test
    public void canApplyAverageOperatorToArray() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("average",new Identifier("n"));
        Double average = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(5D, average);
    }
    @Test
    public void canApplyAverageOperatorToIterable() {
        Iterable<Integer> numbers = Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2);
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("average",new Identifier("n"));
        Double average = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(5D, average);
    }
    @Test
    public void canApplyAverageOperatorToQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2));
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("average",new Identifier("n"));
        Double average = operatorBuilder.by("").in(Double.class,numbers);
        Assert.assertEquals(5D, average);
    }
    @Test
    public void canApplyAverageOperatorWithLiteralQualifierExpression() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("average",new Identifier("n"));
        Double average = operatorBuilder.by("n + 1").in(Double.class,numbers);
        Assert.assertEquals(6D, average);
    }
    @Test
    public void canApplyAverageOperatorWithQualifierExpression() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2};
        AggregationOperatorBuilder<Double> operatorBuilder = new AggregationOperatorBuilderImpl<Double>("average",new Identifier("n"));
        Double average = operatorBuilder.by(
                new PlusOperator(
                        new Statement(
                                Arrays.<Expression>asList(new Identifier("n"))
                        ),
                        new Statement(
                                Arrays.<Expression>asList(new Constant(1))
                        )
                )
        ).in(Double.class,numbers);
        Assert.assertEquals(6D, average);
    }
}
