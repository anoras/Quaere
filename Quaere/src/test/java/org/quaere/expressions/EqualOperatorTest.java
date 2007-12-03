package org.quaere.expressions;

import junit.framework.Assert;
import org.junit.Test;

public class EqualOperatorTest extends BinaryExpressionTest {
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.EQUAL;
    }
    Expression createInstance() {
        return BinaryExpression.equal(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void toStringReturnsStringRepresentation() {
        BinaryExpression eq = BinaryExpression.equal(new Constant(4), new Constant(2));
        Assert.assertEquals("(4 == 2)", eq.toString());
    }

}
