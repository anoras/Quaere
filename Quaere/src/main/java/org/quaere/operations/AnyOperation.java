package org.quaere.operations;

import org.quaere.dsl.*;
import org.quaere.QueryableIterable;
import org.quaere.Queryable;

import java.util.Arrays;

/**
 * @author mh14 @ jexp.de
* @since 11.11.2007 10:25:20 (c) 2007 jexp.de
*/ // Quantifier operators
public class AnyOperation {
    public static QuantificationExpressionArgumentDefinitionBuilder in(String expression) {
        QuantificationExpressionBuilder quantificationBuilder = new QuantificationExpressionBuilderImpl("any");
        return quantificationBuilder.in(expression);
    }
    public static <T> QuantificationOperatorArgumentDefinitionBuilder in(T[] source) {
        return in(Arrays.asList(source));
    }
    public static <T> QuantificationOperatorArgumentDefinitionBuilder in(Iterable<T> source) {
        return in(new QueryableIterable<T>(source));
    }
    public static <T> QuantificationOperatorArgumentDefinitionBuilder in(Queryable<T> source) {
        QuantificationOperatorBuilder quantificationBuilder = new QuantificationOperatorBuilderImpl("any");
        return quantificationBuilder.in(source);
    }
}
