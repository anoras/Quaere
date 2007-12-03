package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class GreaterThanOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.GREATER_THAN;
    }
    Expression createInstance() {
        return BinaryExpression.greaterThan(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        BinaryExpression gt = BinaryExpression.greaterThan(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 > 2)", gt.toString());
    }
}
