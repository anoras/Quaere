package org.quaere.expressions;

public class FromClause extends QueryBodyClause {
    private final String className;
    private final Identifier identifier;
    private final Expression expression;


    public FromClause(String className, Identifier identifier, Expression expression) {
        this.className = className;
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getClassName() {
        return className;
    }

    public Expression getExpression() {
        return expression;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
