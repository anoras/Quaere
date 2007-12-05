package org.quaere.expressions;


/**
 * Represents a node within an sourceExpression tree.
 */
public interface ExpressionTreeNode {
    void accept(ExpressionTreeVisitor visitor);
}
