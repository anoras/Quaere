package org.quaere.quaere4objects;

import org.quaere.dsl.JoinClauseBuilder;
import org.quaere.dsl.JoinClauseOnConditionBuilder;
import org.quaere.dsl.*;
import org.quaere.expressions.*;

import java.util.Arrays;
import java.util.List;

public class Quaere4ObjectsQueryJoinClauseBuilder implements JoinClauseBuilder, JoinClauseOnConditionBuilder {
    private final Quaere4ObjectsQueryBuilder parentQueryBuilder;
    private final Identifier lastIdentifier;
    public Quaere4ObjectsQueryJoinClauseBuilder(Quaere4ObjectsQueryBuilder queryBuilder, Identifier identifer) {
        parentQueryBuilder=queryBuilder;
        lastIdentifier=identifer;
    }
    public <T> JoinClauseOnConditionBuilder in(T... source) {
        return in(Arrays.asList(source));
    }
    public <T> JoinClauseOnConditionBuilder in(List<T> source) {
        parentQueryBuilder.sources.put(lastIdentifier, source);
        return this;
    }
    public JoinClauseBuilderContinuation on(EqualOperator expression) {
        JoinClause joinClause=new JoinClause(
                null,
                lastIdentifier,
                new Statement(
                        Arrays.<Expression>asList(
                                new Identifier(Quaere4ObjectsQuery.getSourceName(lastIdentifier))
                        )
                ),
                expression.leftExpression,
                expression.rightExpression,
                null
                );
        parentQueryBuilder.queryBodyClauses.add(joinClause);
        return parentQueryBuilder;
    }
}
