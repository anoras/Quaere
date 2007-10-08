package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class DivideOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.DIVIDE;
    }
    Expression createInstance() {
        return new DivideOperator(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        DivideOperator divide = new DivideOperator(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 / 2)", divide.toString());
    }
}
