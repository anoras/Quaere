package org.quaere.expressions;

import org.junit.Test;

import java.util.ArrayList;

import junit.framework.Assert;

public class MethodCallTest extends ExpressionTest {
    Expression createInstance() {
        return new MethodCall(new Identifier("methodName"), new ArrayList<Expression>());
    }
    @Test
    public void methodNameIsAssigned() {
        Identifier methodName = new Identifier("methodName");
        MethodCall methodCall = new MethodCall(methodName, new ArrayList<Expression>());
        Assert.assertSame(methodName, methodCall.getIdentifier());
    }

}
