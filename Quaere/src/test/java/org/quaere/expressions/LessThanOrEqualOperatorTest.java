package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class LessThanOrEqualOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.LESS_THAN_OR_EQUAL;
    }
    Expression createInstance() {
        return BinaryExpression.lessThanOrEqual(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        BinaryExpression lteq = BinaryExpression.lessThanOrEqual(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 <= 2)", lteq.toString());
    }
}
