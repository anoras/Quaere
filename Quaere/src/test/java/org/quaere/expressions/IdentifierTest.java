package org.quaere.expressions;

import junit.framework.Assert;
import org.junit.Test;

public class IdentifierTest extends ExpressionTest {
    Expression createInstance() {
        return new Identifier("id");
    }
    @Test
    public void canGetIdentifierString() {
        Identifier identifer = new Identifier("id");
        Assert.assertEquals("id", identifer.name);
    }
    @Test(expected = IllegalArgumentException.class)
    public void identifierMustStartWithAJavaIdentifierCharacter() {
        new Identifier("&id");
    }
    @Test(expected = IllegalArgumentException.class)
    public void identifierCanOnlyContainValidJavaIdentifierCharacters() {
        new Identifier("id%entifer");
    }
    @Test
    public void toStringReturnsIdentifier() {
        Identifier identifer = new Identifier("id");
        Assert.assertEquals("id", identifer.toString());
    }
}
