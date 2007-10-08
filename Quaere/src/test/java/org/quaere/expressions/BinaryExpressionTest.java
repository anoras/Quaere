package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public abstract class BinaryExpressionTest extends ExpressionTest {
    public abstract BinaryExpression.OperatorType expectedOperator();
    @Test
    public void hasExpectedOperator() {
        BinaryExpression subject = (BinaryExpression) createInstance();
        Assert.assertEquals(expectedOperator(), subject.operator);
    }
    @Test
    public void doesNotUseDefaultOperatorTextRepresentation() {
        BinaryExpression subject = (BinaryExpression) createInstance();
        String leftString = subject.leftExpression.toString();
        String subjectString = subject.toString();
        Assert.assertFalse(subjectString.substring(subjectString.indexOf(leftString) + leftString.length()).trim().startsWith("default"));
    }
}
