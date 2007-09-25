package org.quaere.dsl;

import org.quaere.expressions.Expression;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.Statement;
import org.quaere.expressions.MethodCall;

import java.util.ArrayList;
import java.util.Arrays;

public class QuantificationExpressionBuilderImpl implements
        QuantificationExpressionBuilder,
        QuantificationExpressionArgumentDefinitionBuilder,
        QuantificationExpressionWhereClauseOrIndexerArgumentBuilder,
        QuantificationExpressionWhereClauseBuilder {
    private String operator;
    private Expression referenceExpression;
    private Identifier anonymousIdentifier;
    private Identifier indexerIdentifier;
    public QuantificationExpressionBuilderImpl(String operator) {
        this.operator = operator;
    }

    public QuantificationExpressionArgumentDefinitionBuilder in(String expression) {
        referenceExpression = LiteralExpression.parse(expression);
        return this;
    }
    public QuantificationExpressionWhereClauseOrIndexerArgumentBuilder as(String anonymousIdentifier) {
        this.anonymousIdentifier = new Identifier(anonymousIdentifier);
        return this;
    }
    public QuantificationExpressionWhereClauseBuilder withIndexer(String indexerIdentifier) {
        this.indexerIdentifier = new Identifier(indexerIdentifier);
        return this;
    }
    public Expression where(String predicate) {
        return where(LiteralExpression.parse(predicate));
    }
    public Expression where(Expression predicate) {
        return new Statement(
                Arrays.<Expression>asList(
                        referenceExpression,
                        new MethodCall(
                                new Identifier(operator),
                                new ArrayList<Expression>(0),
                                anonymousIdentifier,
                                indexerIdentifier,
                                predicate
                        )
                )
        );
    }
}
