package org.quaere.expressions;

public class SelectClause extends SelectOrGroupClause {
    private final Expression expression;

    public SelectClause(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
    public String toString() {
        return String.format("select(%s)",expression.toString());
    }
    // --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
