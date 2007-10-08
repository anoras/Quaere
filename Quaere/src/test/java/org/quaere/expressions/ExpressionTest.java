package org.quaere.expressions;

import org.junit.Test;
import org.jmock.Mockery;
import junit.framework.Assert;

public abstract class ExpressionTest {
    abstract Expression createInstance();
    @Test
    public void expressionBecomesLeftHandSideExpressionWhenApplyingAndOperator() {
        Expression subject = createInstance();
        AndOperator logicalAnd = subject.and(new UnimplementExpression());
        Assert.assertSame(subject, logicalAnd.leftExpression);
    }
    @Test
    public void expresionBecomesLeftHandSideExpressionWhenApplyingOrOperator() {
        Expression subject = createInstance();
        OrOperator logicalOr = subject.or(new UnimplementExpression());
        Assert.assertSame(subject, logicalOr.leftExpression);
    }
}
