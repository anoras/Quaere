package org.quaere.expressions;

public abstract class QueryBodyClause implements ExpressionTreeNode {
// --------------------- Interface ExpressionTreeNode ---------------------

    public abstract void accept(ExpressionTreeVisitor visitor);
}
