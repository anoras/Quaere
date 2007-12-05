package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class ConstantTest extends ExpressionTest {
    Expression createInstance() {
        return new Constant(42);
    }
    @Test
    public void canInferType() {
        Constant constant = new Constant("String");
        Assert.assertSame(String.class, constant.clazz);
    }
    @Test(expected = IllegalArgumentException.class)
    public void cannotInferNullValueType() {
        new Constant(null);
    }
    @Test
    public void canSpecifyClass() {
        Constant constant = new Constant(42, Long.class);
        Assert.assertSame(Long.class, constant.clazz);
    }
    @Test
    public void toStringReturnsStringValue() {
        Constant constant = new Constant(3.14D);
        Assert.assertEquals(String.valueOf(3.14D), constant.toString());
    }
    @Test
    public void stringConstantIsEscapedWithDoubleQuotes() {
        Constant constant = new Constant("Escape this");
        Assert.assertEquals("\"Escape this\"", constant.toString());
    }
    @Test
    public void charConstantIsEscapedWithSingleQuotes() {
        Constant constant = new Constant('C');
        Assert.assertEquals("'C'", constant.toString());
    }
}
