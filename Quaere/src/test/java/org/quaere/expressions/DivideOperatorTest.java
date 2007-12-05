package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class DivideOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.DIVISION;
    }
    Expression createInstance() {
        return BinaryExpression.divide(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        BinaryExpression divide = BinaryExpression.divide(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 / 2)", divide.toString());
    }
}
