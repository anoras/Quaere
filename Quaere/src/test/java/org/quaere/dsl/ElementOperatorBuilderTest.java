package org.quaere.dsl;

import org.junit.Test;
import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.expressions.*;
import junit.framework.Assert;

import java.util.Arrays;

public class ElementOperatorBuilderTest {
    @Test
    public void canOmitPredicateGetFirstElementInQueryable() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2, 0));
        ElementOperatorBuilder elementOperatorBuilder = new ElementOperatorBuilderImpl("first");
        Integer firstNum = elementOperatorBuilder.in(numbers).as("n").where("");
        Assert.assertEquals(5, (int) firstNum);
    }
    @Test
    public void canUseLiteralPredicateToRestrictSelection() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2, 0));
        ElementOperatorBuilder elementOperatorBuilder = new ElementOperatorBuilderImpl("first");
        Integer firstNum = elementOperatorBuilder.in(numbers).as("n").where("n < 4");
        Assert.assertEquals(1, (int) firstNum);
    }
    @Test
    public void canUsePredicateToRestrictSelection() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(5, 4, 1, 3, 9, 8, 6, 7, 2, 0));
        ElementOperatorBuilder elementOperatorBuilder = new ElementOperatorBuilderImpl("first");
        LessThanOperator nLessThanFour = new LessThanOperator(
                new Statement(
                        Arrays.<Expression>asList(
                                new Identifier("n")
                        )
                ),
                new Statement(
                        Arrays.<Expression>asList(
                                new Constant(4)
                        )
                )
        );
        Integer firstNum = elementOperatorBuilder.in(numbers).as("n").where(nLessThanFour);
        Assert.assertEquals(1, (int) firstNum);
    }
    @Test
    public void canUsePredicateWithIndexerToRestrictSelection() {
        Queryable<Integer> numbers = new QueryableIterable<Integer>(Arrays.asList(0, 1, 5, 4, 3, 9, 8, 6, 7, 2, 0));
        ElementOperatorBuilder elementOperatorBuilder = new ElementOperatorBuilderImpl("first");
        GreaterThanOperator nGreaterThanIndex = new GreaterThanOperator (
                new Statement(
                        Arrays.<Expression>asList(
                                new Identifier("n")
                        )
                ),
                new Statement(
                        Arrays.<Expression>asList(
                                new Identifier("index")
                        )
                )
        );
        Integer firstNum = elementOperatorBuilder.in(numbers).as("n").withIndexer("index").where(nGreaterThanIndex);
        Assert.assertEquals(5, (int) firstNum);
    }
}
