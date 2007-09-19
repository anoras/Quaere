package org.quaere.expressions;

public class WhereClause extends QueryBodyClause {
    private final Expression expression;

    public WhereClause(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
