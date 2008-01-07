package org.quaere.operations;

import org.quaere.Convert;
import org.quaere.QueryEngine;
import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.dsl.AggregationOperatorBuilder;
import org.quaere.dsl.AggregationOperatorBuilderImpl;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.Statement;
import org.quaere.objects.Quaere4ObjectsQueryEngine;

import java.util.Arrays;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 01:08:49 (c) 2007 jexp.de
 */
public class AggregateOperation implements InOperation, OfOperation {
    private final DefaultOperation operation;
    public static final OfOperation SUM = new AggregateOperation(DefaultOperation.SUM);
    public static final InOperation MIN = new AggregateOperation(DefaultOperation.MIN);
    public static final InOperation COUNT = new AggregateOperation(DefaultOperation.COUNT);
    public static final InOperation MAX = new AggregateOperation(DefaultOperation.MAX);
    public static final InOperation AVG = new AggregateOperation(DefaultOperation.AVERAGE);

    public AggregateOperation(final DefaultOperation operation) {
        this.operation = operation;
    }

    public <T> Double of(T[] source) {
        return of(Double.class, source);
    }

    public <R, T> R of(Class<R> toClass, T[] source) {
        return of(toClass, Arrays.asList(source));
    }

    public <T> Double of(Iterable<T> source) {
        return of(Double.class, source);
    }

    public <R, T> R of(Class<R> toClass, Iterable<T> source) {
        return of(toClass, new QueryableIterable<T>(source));
    }

    public <T> Double of(Queryable<T> source) {
        return of(Double.class, source);
    }

    public <R, T> R of(Class<R> toClass, Queryable<T> source) {
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = DefaultOperations.createMethodCallStatement(sourceIdentifier, operationName());
        QueryEngine queryEngine = source.createQueryEngine();
        handleObjectsQuerySource(queryEngine, sourceIdentifier, source);
        return (R) Convert.toType(queryEngine.evaluate(query), toClass);
    }


    protected <T> void handleObjectsQuerySource(final QueryEngine queryEngine, final Identifier sourceIdentifier, final Queryable<T> source) {
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
    }

    protected String operationName() {
        return operation.operationName();
    }

    public String toString() {
        return operationName();
    }

    public <R> AggregationOperatorBuilder<R> qualify(String anonymousIdentifier) {
        return new AggregationOperatorBuilderImpl<R>(operationName(), new Identifier(anonymousIdentifier));
    }

    // Delegation for InInterface

    public <T> Double in(T[] source) {
        return of(source);
    }

    public <R, T> R in(Class<R> toClass, T[] source) {
        return of(toClass, source);
    }

    public <T> Double in(Iterable<T> source) {
        return of(source);
    }

    public <R, T> R in(Class<R> toClass, Iterable<T> source) {
        return of(toClass, source);
    }

    public <T> Double in(Queryable<T> source) {
        return of(source);
    }

    public <R, T> R in(Class<R> toClass, Queryable<T> source) {
        return of(toClass, source);
    }
}