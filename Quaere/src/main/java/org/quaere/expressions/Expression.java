package org.quaere.expressions;

public abstract class Expression implements ExpressionTreeNode {
// --------------------- Interface ExpressionTreeNode ---------------------


    public abstract void accept(ExpressionTreeVisitor visitor);
}
