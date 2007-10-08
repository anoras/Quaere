package org.quaere.expressions;

import org.junit.Test;
import junit.framework.Assert;

public class IndexerTest extends ExpressionTest {
    Expression createInstance() {
        return new Indexer(UnimplementExpression.instance, UnimplementExpression.instance);
    }
    @Test
    public void stringRepresentationIsInnerExpressionIndexedByParameter() {
        Identifier id = new Identifier("indexable");
        Constant index = new Constant(4);
        Indexer indexer = new Indexer(id, index);
        Assert.assertEquals("indexable[4]", indexer.toString());
    }
}
