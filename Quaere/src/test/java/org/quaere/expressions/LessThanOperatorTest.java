package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class LessThanOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.LESS_THAN;
    }
    Expression createInstance() {
        return BinaryExpression.lessThan(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        BinaryExpression lt = BinaryExpression.lessThan(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 < 2)", lt.toString());
    }

}
