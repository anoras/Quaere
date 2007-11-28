package org.quaere.quaere4objects;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.quaere.expressions.*;

public class Quaere4ObjectsQueryEngine_UnaryExpressionsTest {
    final Mockery context = new Mockery();
    private Quaere4ObjectsQueryEngine queryEngine;
    @Before
    public void setup() {
        queryEngine =new Quaere4ObjectsQueryEngine();
    }
    @Test
    public void canApplyNotOperator()
    {
        NotOperator notOperator = new NotOperator(new Constant(true));
        queryEngine.visit(notOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void canNegateIntegerValue()
    {
        NegateExpression negateExpression=new NegateExpression(new Constant(5));
        queryEngine.visit(negateExpression);
        Assert.assertEquals(-5,queryEngine.result);
    }
    @Test
    public void canNegateFloatValue()
    {
        NegateExpression negateExpression=new NegateExpression(new Constant(5.5f));
        queryEngine.visit(negateExpression);
        Assert.assertEquals(-5.5f,queryEngine.result);
        Assert.assertTrue(queryEngine.result instanceof Float);
    }
}
