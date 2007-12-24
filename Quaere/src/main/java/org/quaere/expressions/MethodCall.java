package org.quaere.expressions;

import java.util.Arrays;
import java.util.List;

public class MethodCall extends Expression {
    private final Identifier identifier;
    private final List<Expression> parameters;
    private Identifier anonymousIdentifier;
    private Identifier indexedIdentifier;
    private Expression lambdaExpression;


    public MethodCall(Identifier identifier, List<Expression> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    public MethodCall(Identifier identifier, Expression...parameters) {
        this.identifier = identifier;
        this.parameters = Arrays.asList(parameters);
    }

    public MethodCall(Identifier identifier, List<Expression> parameters, Identifier anonymousIdentifier, Identifier indexedIdentifier, Expression lambdaExpression) {
        this.identifier = identifier;
        this.parameters = parameters;
        this.anonymousIdentifier = anonymousIdentifier;
        this.indexedIdentifier = indexedIdentifier;
        this.lambdaExpression = lambdaExpression;
    }

    public Identifier getAnonymousIdentifier() {
        return anonymousIdentifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Identifier getIndexedIdentifier() {
        return indexedIdentifier;
    }

    public Expression getLambdaExpression() {
        return lambdaExpression;
    }

    public List<Expression> getParameters() {
        return parameters;
    }

    public String toString() {
        String parameterList = "";
        for (Expression e : parameters) {
            parameterList += e.toString() + ',';
        }
        if (parameterList.length() > 0) {
            parameterList = parameterList.substring(0, parameterList.length() - 1);
        }
        return identifier.toString()+'(' + parameterList + ')';
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isNoLambda() {
        return getLambdaExpression() == null;
    }

    public boolean hasAnonymousIdentifier() {
        return getAnonymousIdentifier() != null;
    }

    public boolean hasIndexedIdentifier() {
        return getIndexedIdentifier() != null;
    }

    public int nextIdentifierIndex(int i) {
        return hasIndexedIdentifier() ? i + 1 : i;
    }
}
