package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class GreaterThanOrEqualOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.GREATER_THAN_OR_EQUAL;
    }
    Expression createInstance() {
        return new GreaterThanOrEqualOperator(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        GreaterThanOrEqualOperator gteq = new GreaterThanOrEqualOperator(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 >= 2)", gteq.toString());
    }
}
