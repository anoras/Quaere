package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class LessThanOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.LESS_THAN;
    }
    Expression createInstance() {
        return new LessThanOperator(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        LessThanOperator lt = new LessThanOperator(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 < 2)", lt.toString());
    }

}
