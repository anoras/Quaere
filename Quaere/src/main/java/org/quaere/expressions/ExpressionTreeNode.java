package org.quaere.expressions;


public interface ExpressionTreeNode {
    void accept(ExpressionTreeVisitor visitor);
}
