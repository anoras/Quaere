package org.quaere.dsl;

import org.quaere.expressions.Expression;
import org.quaere.dsl.parser.QueryLexer;
import org.quaere.dsl.parser.QueryParser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class LiteralExpression {
    public static Expression parse(String literalExpression) {
        QueryLexer lexer = new QueryLexer(new ANTLRStringStream(literalExpression));
        QueryParser parser = new QueryParser(new CommonTokenStream(lexer));
        Expression expression;
        try {
            expression = parser.quaereExpression().value;
        } catch (RecognitionException e) {
            throw new RuntimeException(e);
        }
        return expression;
    }
}
