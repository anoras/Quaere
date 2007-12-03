package org.quaere.operations;

import org.quaere.expressions.*;
import org.quaere.dsl.LiteralExpression;
import org.quaere.dsl.QueryContinuationOrQueryBodyBuilder;
import org.quaere.dsl.QueryExpressionBuilderImpl;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 02:48:06 (c) 2007 jexp.de
 */
public class PropertyOperation {
    public static Property createProperty(final String propertyName, final Expression expression) {
        return new Property(new Identifier(propertyName), expression);
    }

    public static Property createPropertyFromStringExpression(final String expression) {
        Statement statment = (Statement) LiteralExpression.parse(expression);
        if (statment.getExpressions().size() > 1) {
            // We're looking for a method call
            for (Expression statementExpression : statment.getExpressions()) {
                if (statementExpression instanceof MethodCall) {
                    MethodCall methodCall = (MethodCall) statementExpression;
                    String propertyName = methodCall.getIdentifier().getText();
                    if (propertyName.startsWith("get")) {
                        propertyName = propertyName.substring("get".length());
                    } else if (propertyName.startsWith("is")) {
                        propertyName = propertyName.substring("is".length());
                    }
                    propertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
                    return createProperty(propertyName, expression);
                }
            }
        } else {
            // We're looking for an identifier
            Identifier identifier = (Identifier) statment.getExpressions().get(0);
            return createProperty(identifier.getText(), expression);
        }
        throw new IllegalArgumentException(String.format("\"%s\" is not a vaild property expression.", expression));
    }

    public static <R> Property createSubQueryProperty(final String propertyName, final QueryContinuationOrQueryBodyBuilder<R> subquery) {
        QueryExpressionBuilderImpl subqueryBuilder = (QueryExpressionBuilderImpl) subquery;
        QueryExpression queryExpression = subquery.getQueryExpression();
        SubqueryExpression subqueryExpression = new SubqueryExpression(queryExpression.getFrom(), queryExpression.getQueryBody());
        return createProperty(propertyName, subqueryExpression);
    }

    public static Property createProperty(final String propertyName, final String expression) {
        return createProperty(propertyName, LiteralExpression.parse(expression));
    }
}
