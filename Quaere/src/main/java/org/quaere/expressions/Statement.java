package org.quaere.expressions;

import java.util.Arrays;
import java.util.List;

public class Statement extends Expression {
    private final List<Expression> expressions;

    public Statement(List<Expression> expressions) {
        this.expressions = expressions;
    }
    public Statement(Expression...expressions) {
        this.expressions = Arrays.asList(expressions);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public String toString() {
        String result = "";
        for (Expression e : expressions) {
            result += e.toString() + '.';
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Identifier getFirstIdentifier() {
        return (Identifier) expressions.get(0);
    }

    public Identifier getLastIdentifier() {
        return (Identifier) expressions.get(expressions.size() - 1);
    }
}
