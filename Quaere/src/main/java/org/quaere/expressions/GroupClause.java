package org.quaere.expressions;

import java.util.Comparator;

public class GroupClause extends SelectOrGroupClause {
    private final Identifier identifier;
    private final Expression expression;
    private final Comparator comparator;

    public GroupClause(Identifier identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
        this.comparator = null;
    }

    public GroupClause(Identifier identifier, Expression expression, Comparator comparator) {
        this.identifier = identifier;
        this.expression = expression;
        this.comparator = comparator;
    }
    public Expression getExpression() {
        return expression;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
    public Comparator getComparator() {
        return comparator;
    }
    public String toString() {
        return String.format("group(%s).by(%s).",identifier.toString(),expression.toString());
    }
    // --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
