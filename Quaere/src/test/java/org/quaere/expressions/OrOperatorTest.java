package org.quaere.expressions;

import junit.framework.Assert;
import org.jmock.Mockery;
import org.junit.Test;

public class OrOperatorTest extends BinaryExpressionTest {
    public final Mockery context = new Mockery();
    Expression createInstance() {
        return new OrOperator(new UnimplementExpression(), new UnimplementExpression());
    }
    public BinaryExpression.OperatorType expectedOperator() {
        return BinaryExpression.OperatorType.OR;
    }
    @Test
    public void toStringContainsLogicalAndOperator() {
        Assert.assertTrue(createInstance().toString().contains("||"));
    }
}
