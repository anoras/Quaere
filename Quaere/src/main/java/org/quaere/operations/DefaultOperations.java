package org.quaere.operations;

import org.quaere.QueryEngine;
import org.quaere.Queryable;
import org.quaere.dsl.AggregateOperatorBuilder;
import org.quaere.dsl.AggregateOperatorBuilderImpl;
import org.quaere.expressions.*;
import org.quaere.objects.Quaere4ObjectsQueryEngine;

import java.util.Arrays;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 02:02:38 (c) 2007 jexp.de
 */
public class DefaultOperations {
    public static Statement createMethodCallStatement(final Identifier leftSourceIdentifier, final String operation, final Expression... parameters) {
        return new Statement(leftSourceIdentifier, new MethodCall(new Identifier(operation), parameters));
    }

    public static Statement createMethodCallStatement(final Expression leftExpression, final String operation, final Expression... parameters) {
        return new Statement(leftExpression, new MethodCall(new Identifier(operation), parameters));
    }

    public static Statement createMethodCallStatement(final Identifier leftSourceIdentifier, final DefaultOperation operation, final Expression... parameters) {
        return createMethodCallStatement(leftSourceIdentifier, operation.operationName(), parameters);
    }

    public static Statement createMethodCallStatement(final Expression leftExpression, final DefaultOperation operation, final Expression... parameters) {
        return createMethodCallStatement(leftExpression, operation.operationName(), parameters);
    }

    public static <R> QueryEngine createEngineAndAddSource(final Identifier sourceIdentifier, final Queryable<R> source) {
        QueryEngine queryEngine = source.createQueryEngine();
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
        return queryEngine;
    }

    protected static <T> QueryEngine createEngineAndSetSources(final Identifier leftSourceIdentifier, final Queryable<T> leftHandSide, final Identifier rightSourceIdentifier, final Queryable<T> rightHandSide) {
        QueryEngine queryEngine = leftHandSide.createQueryEngine();
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(leftSourceIdentifier, leftHandSide);
            asQuaere4ObjectsQueryEngine.addSource(rightSourceIdentifier, rightHandSide);
        }
        return queryEngine;
    }

    public static <T> Iterable<T> executeTwoParamOperation(final Queryable<T> leftHandSide, final DefaultOperation operation, final Queryable<T> rightHandSide) {
        return executeTwoParamOperation(leftHandSide, operation.operationName(), rightHandSide);
    }
    public static <T> Iterable<T> executeTwoParamOperation(final Queryable<T> leftHandSide, final String operation, final Queryable<T> rightHandSide) {
        Identifier leftSourceIdentifier = Identifier.createUniqueIdentfier();
        Identifier rightSourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = createMethodCallStatement(leftSourceIdentifier, operation, new Statement(rightSourceIdentifier));
        QueryEngine queryEngine = createEngineAndSetSources(leftSourceIdentifier, leftHandSide, rightSourceIdentifier, rightHandSide);
        return queryEngine.evaluate(query);
    }

    public static <T> Iterable<T> executeSingleParamOperation(final String operation, final Queryable<T> source) {
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = createMethodCallStatement(sourceIdentifier, operation);
        QueryEngine queryEngine = createEngineAndAddSource(sourceIdentifier, source);
        return queryEngine.evaluate(query);
    }

    public static <T> Iterable<T> executeSingleParamOperation(final DefaultOperation operation, final Queryable<T> source) {
        return executeSingleParamOperation(operation.operationName(), source);
    }

    public static NewExpression createDefaultNewExpression(final Property... properties) {
        return new NewExpression((Class<?>) null, Arrays.asList(properties));
    }
    public static NewExpression creatDefaultNewExpresion(Class<?> clazz, Property[] properties) {
        return new NewExpression(clazz, Arrays.asList(properties));
    }


    public static <R> AggregateOperatorBuilder<R> createAggregateOperationBuilder(String accumulationIdentifier, String anonymousIdentifier) {
        return new AggregateOperatorBuilderImpl<R>(new Identifier(accumulationIdentifier), new Identifier(anonymousIdentifier));
    }
}
