package org.quaere.expressions;

import org.junit.Test;
import org.jmock.Mockery;
import junit.framework.Assert;

public class AndOperatorTest extends BinaryExpressionTest {
    public final Mockery context = new Mockery();
    Expression createInstance() {
        return BinaryExpression.and(new UnimplementExpression(), new UnimplementExpression());
    }
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.AND;
    }
    @Test
    public void toStringContainsLogicalAndOperator() {
        Assert.assertTrue(createInstance().toString().contains("&&"));
    }
}
