package org.quaere.expressions;

public class DeclareClause extends QueryBodyClause {
    private final Identifier left;
    private final Expression right;

    public DeclareClause(Identifier left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Identifier getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
