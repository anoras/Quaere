package org.quaere.operations;

import org.quaere.dsl.*;
import org.quaere.QueryableIterable;
import org.quaere.Queryable;
import org.quaere.QueryEngine;
import org.quaere.operations.DefaultOperations;
import org.quaere.objects.Quaere4ObjectsQueryEngine;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.Statement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 02:31:19 (c) 2007 jexp.de
 */
public class FirstOperationImpl implements FirstOperation {
    public static final FirstOperation FIRST = new FirstOperationImpl();

    public <T> ElementOperatorArgumentDefinitionBuilder in(T[] source) {
        return in(Arrays.asList(source));
    }

    public <T> ElementOperatorArgumentDefinitionBuilder in(Iterable<T> source) {
        return in(new QueryableIterable<T>(source));
    }

    public <T> ElementOperatorArgumentDefinitionBuilder in(Queryable<T> source) {
        ElementOperatorBuilder builder = new ElementOperatorBuilderImpl("first");
        return builder.in(source);
    }

    public static <R> R firstInIterable(Iterable<R> source) {
        Iterator<R> iter = source.iterator();
        if (iter.hasNext()) {
            return iter.next();
        } else {
            return null;
        }
    }

    public static <R> R firstInQueryable(Queryable<R> source) {
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = DefaultOperations.createMethodCallStatement(sourceIdentifier, "first");
        QueryEngine queryEngine = DefaultOperations.createEngineAndAddSource(sourceIdentifier, source);
        //noinspection RedundantTypeArguments
        return queryEngine.<R>evaluate(query); // <-- IntelliJ suggests that <R> can be inferred, but this leads to a compilation error.
    }

    public static <R> R firstInQueryBodyBuilder(QueryBodyBuilder<?> query) {
        QueryExpressionBuilderImpl<R> impl = (QueryExpressionBuilderImpl<R>) query;
        Statement statement = DefaultOperations.createMethodCallStatement(query.getQueryExpression(), "first");
        Quaere4ObjectsQueryEngine engine = new Quaere4ObjectsQueryEngine();
        for (Map.Entry<Identifier, Queryable> sourceEntry : impl.getSources().entrySet()) {
            engine.addSource(sourceEntry.getValue().getSourceIdentifier(sourceEntry.getKey()), sourceEntry.getValue());
        }
        //noinspection RedundantTypeArguments
        return engine.<R>evaluate(statement); // <-- IntelliJ suggests that <R> can be inferred, but we get a compilation error if its not specified.
    }

    public static <R> R first(final Object source) {
        // NOTE: Explicit type argument is required in the following section support Eclipse.
        if (source instanceof QueryBodyBuilder<?>) {
            return FirstOperationImpl.<R>firstInQueryBodyBuilder((QueryBodyBuilder) source);
        } else if (source instanceof Queryable<?>) {
            // NOTE: The unchecked casts is required for IDEA to build the module while the call is redirected to
            // an explicitly typed method on the DSL class.
            return firstInQueryable((Queryable<R>) source);
        } else if (source instanceof Iterable<?>) {
            return firstInIterable((Iterable<R>) source);
        }
        throw new IllegalArgumentException("Cannot retrieve first element from " + source.getClass().getName());
    }

    public static <R> R firstFromArray(final R... source) {
        if (source.length > 0) {
            return source[0];
        } else {
            return null;
        }
    }
}
