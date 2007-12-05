package org.quaere.expressions;

public class Property {
    private final Identifier identifier;
    private final Expression expression;

    public Property(Identifier identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getPropertyName() {
        if (identifier != null) {
            return identifier.name;
        }
        if (expression instanceof Statement) {
            Statement s = (Statement) expression;
            return s.getLastIdentifier().name;
        }
        else {
            throw new RuntimeException("Property name must be set...");
        }
    }
    public String toString() {
        return String.format("%s: %s", identifier.toString(), expression.toString());
    }
}
