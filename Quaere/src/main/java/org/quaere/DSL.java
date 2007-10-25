package org.quaere;

import org.quaere.dsl.*;
import org.quaere.expressions.*;
import org.quaere.quaere4objects.Quaere4ObjectsQueryEngine;

import java.util.*;

public class DSL {
    public static <R> FromClauseBuilder<R> from(final String identifier) {
        QueryExpressionBuilderImpl<R> queryExpressionBuilder = new QueryExpressionBuilderImpl<R>();
        return queryExpressionBuilder.from(identifier);
    }
    // Expressions
    // eq
    public static EqualOperator eq(String leftHandSide, String rightHandSide) {
        return new EqualOperator(LiteralExpression.parse(leftHandSide), LiteralExpression.parse(rightHandSide));
    }
    public static EqualOperator eq(String leftHandSide, Comparable<Integer> rightHandSide) {
        return new EqualOperator(LiteralExpression.parse(leftHandSide), new Constant(rightHandSide, rightHandSide.getClass()));
    }
    public static EqualOperator eq(Comparable leftHandSide, String rightHandSide) {
        return new EqualOperator(new Constant(leftHandSide, leftHandSide.getClass()), LiteralExpression.parse(rightHandSide));
    }
    // ne
    public static NotEqualOperator ne(String leftHandSide, String rightHandSide) {
        return new NotEqualOperator(LiteralExpression.parse(leftHandSide), LiteralExpression.parse(rightHandSide));
    }
    public static NotEqualOperator ne(String leftHandSide, Comparable<Integer> rightHandSide) {
        return new NotEqualOperator(LiteralExpression.parse(leftHandSide), new Constant(rightHandSide, rightHandSide.getClass()));
    }
    public static NotEqualOperator ne(Comparable leftHandSide, String rightHandSide) {
        return new NotEqualOperator(new Constant(leftHandSide, leftHandSide.getClass()), LiteralExpression.parse(rightHandSide));
    }
    // le
    public static LessThanOrEqualOperator le(String leftHandSide, String rightHandSide) {
        return new LessThanOrEqualOperator(LiteralExpression.parse(leftHandSide), LiteralExpression.parse(rightHandSide));
    }
    public static LessThanOrEqualOperator le(String leftHandSide, Comparable rightHandSide) {
        return new LessThanOrEqualOperator(LiteralExpression.parse(leftHandSide), new Constant(rightHandSide, rightHandSide.getClass()));
    }
    public static LessThanOrEqualOperator le(Comparable leftHandSide, String rightHandSide) {
        return new LessThanOrEqualOperator(new Constant(leftHandSide, leftHandSide.getClass()), LiteralExpression.parse(rightHandSide));
    }
    // lt
    public static LessThanOperator lt(String leftHandSide, String rightHandSide) {
        return new LessThanOperator(LiteralExpression.parse(leftHandSide), LiteralExpression.parse(rightHandSide));
    }
    public static LessThanOperator lt(String leftHandSide, Comparable rightHandSide) {
        return new LessThanOperator(LiteralExpression.parse(leftHandSide), new Constant(rightHandSide, rightHandSide.getClass()));
    }
    public static LessThanOperator lt(Comparable leftHandSide, String rightHandSide) {
        return new LessThanOperator(new Constant(leftHandSide, leftHandSide.getClass()), LiteralExpression.parse(rightHandSide));
    }
    // ge
    public static GreaterThanOrEqualOperator ge(String leftHandSide, String rightHandSide) {
        return new GreaterThanOrEqualOperator(LiteralExpression.parse(leftHandSide), LiteralExpression.parse(rightHandSide));
    }
    public static GreaterThanOrEqualOperator ge(String leftHandSide, Comparable rightHandSide) {
        return new GreaterThanOrEqualOperator(LiteralExpression.parse(leftHandSide), new Constant(rightHandSide, rightHandSide.getClass()));
    }
    public static GreaterThanOrEqualOperator ge(Comparable leftHandSide, String rightHandSide) {
        return new GreaterThanOrEqualOperator(new Constant(leftHandSide, leftHandSide.getClass()), LiteralExpression.parse(rightHandSide));
    }
    // gt
    public static GreaterThanOperator gt(String leftHandSide, String rightHandSide) {
        return new GreaterThanOperator(LiteralExpression.parse(leftHandSide), LiteralExpression.parse(rightHandSide));
    }
    public static GreaterThanOperator gt(String leftHandSide, Comparable rightHandSide) {
        return new GreaterThanOperator(LiteralExpression.parse(leftHandSide), new Constant(rightHandSide, rightHandSide.getClass()));
    }
    public static GreaterThanOperator gt(Comparable leftHandSide, String rightHandSide) {
        return new GreaterThanOperator(new Constant(leftHandSide, leftHandSide.getClass()), LiteralExpression.parse(rightHandSide));
    }
    // anonymous classes
    public static NewExpression create(Property... properties) {
        return new NewExpression(null, Arrays.asList(properties));
    }
    public static Property property(String expression) {
        Statement statment = (Statement) LiteralExpression.parse(expression);
        if (statment.getExpressions().size() > 1) {
            // We're looking for a method call
            for (Expression statementExpression : statment.getExpressions()) {
                if (statementExpression instanceof MethodCall) {
                    MethodCall methodCall = (MethodCall) statementExpression;
                    String propertyName = methodCall.getIdentifier().getText();
                    if (propertyName.startsWith("get")) {
                        propertyName = propertyName.substring("get".length());
                    }
                    else if (propertyName.startsWith("is")) {
                        propertyName = propertyName.substring("is".length());
                    }
                    propertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
                    return property(propertyName, expression);
                }
            }
        }
        else {
            // We're looking for an identifier
            Identifier identifier = (Identifier) statment.getExpressions().get(0);
            return property(identifier.getText(), expression);
        }
        throw new IllegalArgumentException(String.format("\"%s\" is not a vaild property expression.", expression));
    }
    public static Property property(String propertyName, String expression) {
        return property(propertyName, LiteralExpression.parse(expression));
    }
    public static Property property(String propertyName, Expression expression) {
        return new Property(new Identifier(propertyName), expression);
    }
    public static <R> Property property(String propertyName, QueryContinuationOrQueryBodyBuilder<R> subquery) {
        QueryExpressionBuilderImpl subqueryBuilder = (QueryExpressionBuilderImpl) subquery;
        QueryExpression queryExpression=subquery.getQueryExpression();
        SubqueryExpression subqueryExpression=new SubqueryExpression(queryExpression.getFrom(),queryExpression.getQueryBody());
        return property(propertyName, subqueryExpression);
    }
    public static class min {
        // Aggregation operators
        public static <R,T> R in(Class<R> rClass, T[] source) {
            return min.<R,T>in(rClass, Arrays.asList(source));
        }
        public static <R,T> R in(Class<R> rClass, Iterable<T> source) {
            return min.<R,T>in(rClass,new QueryableIterable<T>(source));
        }
        public static <R,T> R in(Class<R> rClass, Queryable<T> source) {
            QueryEngine queryEngine = source.createQueryEngine();
            Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
            Statement query = new Statement(
                    Arrays.<Expression>asList(
                            sourceIdentifier,
                            new MethodCall(
                                    new Identifier("min"),
                                    new ArrayList<Expression>(0)
                            )
                    )
            );
            if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
                Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
                asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
            }
            return (R) Convert.coerce(queryEngine.evaluate(query),rClass);
        }
        public static <R> AggregationClauseBuilder<R> qualify(String anonymousIdentifier) {
            return new AggregationClauseBuilderImpl<R>("min",new Identifier(anonymousIdentifier));
        }
    }

    // Conversion operators
    public static <T> T[] asArray(T[] tArray, Iterable<T> source) {
        List<T> asList = new ArrayList<T>();
        for (T elm : source) {
            asList.add(elm);
        }
        return asList.toArray(tArray);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> List<T> asList(Iterable<? extends Object> iterable) {
        List<T> asList = new ArrayList<T>();
        for (Object elm : iterable) {
            asList.add((T) elm);
        }
        return asList;
    }
    public static <T> Iterable<T> ofClass(Class<T> clazz, Object... source) {
        return ofClass(clazz, Arrays.asList(source));
    }
    @SuppressWarnings({"unchecked"})
    public static <T> Iterable<T> ofClass(Class<T> clazz, Iterable<? extends Object> source) {
        List<T> result = new ArrayList<T>();
        for (Object obj : source) {
            if (obj != null && obj.getClass().equals(clazz)) {
                result.add((T) obj);
            }
        }
        return result;
    }

    // Ordering operators
    public static <T> Iterable<T> reverse(Iterable<T> sequence) {
        List<T> asList = new ArrayList<T>();
        for (T elm : sequence) {
            asList.add(elm);
        }
        Collections.reverse(asList);
        return asList;
    }
    // Quantifier operators
    public static class any {
        public static QuantificationExpressionArgumentDefinitionBuilder in(String expression) {
            QuantificationExpressionBuilder quantificationBuilder = new QuantificationExpressionBuilderImpl("any");
            return quantificationBuilder.in(expression);
        }
        public static <T> QuantificationOperatorArgumentDefinitionBuilder in(T[] source) {
            return in(Arrays.asList(source));
        }
        public static <T> QuantificationOperatorArgumentDefinitionBuilder in(Iterable<T> source) {
            return in(new QueryableIterable<T>(source));
        }
        public static <T> QuantificationOperatorArgumentDefinitionBuilder in(Queryable<T> source) {
            QuantificationOperatorBuilder quantificationBuilder = new QuantificationOperatorBuilderImpl("any");
            return quantificationBuilder.in(source);
        }
    }


    // Partitioning operators
    public static PartitioningOperatorBuilder take(int count) {
        return new PartitioningOperatorBuilderImpl("take", count);
    }
    public static PartitionWhenOperatorBuilder take(String anonymousIdentifier) {
        return new PartitionWhenOperatorBuilderImpl("takeWhile", anonymousIdentifier);
    }
    public static PartitioningOperatorBuilder skip(int count) {
        return new PartitioningOperatorBuilderImpl("skip", count);
    }
    public static PartitionWhenOperatorBuilder skip(String anonymousIdentifier) {
        return new PartitionWhenOperatorBuilderImpl("skipWhile", anonymousIdentifier);
    }
    // Set operators
    public static <T> Iterable<T> distinct(T[] source) {
        return distinct(Arrays.asList(source));
    }
    public static <T> Iterable<T> distinct(Iterable<T> source) {
        return distinct(new QueryableIterable<T>(source));
    }

    public static <T> Iterable<T> distinct(Queryable<T> source) {
        QueryEngine queryEngine = source.createQueryEngine();
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        sourceIdentifier,
                        new MethodCall(
                                new Identifier("distinct"),
                                new ArrayList<Expression>(0)
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
        return queryEngine.evaluate(query);
    }
    public static <T> Iterable<T> union(T[] leftHandSide, T[] rightHandSide) {
        return union(Arrays.asList(leftHandSide), Arrays.asList(rightHandSide));
    }
    public static <T> Iterable<T> union(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        return union(new QueryableIterable<T>(leftHandSide), new QueryableIterable<T>(rightHandSide));
    }

    public static <T> Iterable<T> union(Queryable<T> leftHandSide, Queryable<T> rightHandSide) {
        QueryEngine queryEngine = leftHandSide.createQueryEngine();
        Identifier leftSourceIdentifier = Identifier.createUniqueIdentfier();
        Identifier rightSourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        leftSourceIdentifier,
                        new MethodCall(
                                new Identifier("union"),
                                Arrays.<Expression>asList(
                                        new Statement(
                                                Arrays.<Expression>asList(
                                                        rightSourceIdentifier
                                                )
                                        )
                                )
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(leftSourceIdentifier, leftHandSide);
            asQuaere4ObjectsQueryEngine.addSource(rightSourceIdentifier, rightHandSide);
        }
        return queryEngine.evaluate(query);
    }
    public static <T> Iterable<T> intersect(T[] leftHandSide, T[] rightHandSide) {
        return intersect(Arrays.asList(leftHandSide), Arrays.asList(rightHandSide));
    }
    public static <T> Iterable<T> intersect(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        return intersect(new QueryableIterable<T>(leftHandSide), new QueryableIterable<T>(rightHandSide));
    }

    public static <T> Iterable<T> intersect(Queryable<T> leftHandSide, Queryable<T> rightHandSide) {
        QueryEngine queryEngine = leftHandSide.createQueryEngine();
        Identifier leftSourceIdentifier = Identifier.createUniqueIdentfier();
        Identifier rightSourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        leftSourceIdentifier,
                        new MethodCall(
                                new Identifier("intersect"),
                                Arrays.<Expression>asList(
                                        new Statement(
                                                Arrays.<Expression>asList(
                                                        rightSourceIdentifier
                                                )
                                        )
                                )
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(leftSourceIdentifier, leftHandSide);
            asQuaere4ObjectsQueryEngine.addSource(rightSourceIdentifier, rightHandSide);
        }
        return queryEngine.evaluate(query);
    }
    public static <T> Iterable<T> except(T[] leftHandSide, T[] rightHandSide) {
        return except(Arrays.asList(leftHandSide), Arrays.asList(rightHandSide));
    }
    public static <T> Iterable<T> except(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        return except(new QueryableIterable<T>(leftHandSide), new QueryableIterable<T>(rightHandSide));
    }

    public static <T> Iterable<T> except(Queryable<T> leftHandSide, Queryable<T> rightHandSide) {
        QueryEngine queryEngine = leftHandSide.createQueryEngine();
        Identifier leftSourceIdentifier = Identifier.createUniqueIdentfier();
        Identifier rightSourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        leftSourceIdentifier,
                        new MethodCall(
                                new Identifier("except"),
                                Arrays.<Expression>asList(
                                        new Statement(
                                                Arrays.<Expression>asList(
                                                        rightSourceIdentifier
                                                )
                                        )
                                )
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(leftSourceIdentifier, leftHandSide);
            asQuaere4ObjectsQueryEngine.addSource(rightSourceIdentifier, rightHandSide);
        }
        return queryEngine.evaluate(query);
    }
    // Element operators
    public static <R> R first(R[] source) {
        if (source.length > 0) {
            return source[0];
        }
        else {
            return null;
        }
    }

    // NOTE: This method is a facade for the typed firstXxx methods to enable the module to be built with Eclipse.
    @SuppressWarnings({"RedundantTypeArguments", "unchecked"})
    public static <R> R first(Object source) {
        // NOTE: Explicit type argument is required in the following section support Eclipse.
        if (source instanceof QueryBodyBuilder<?>) {
            return DSL.<R>firstInQueryBodyBuilder((QueryBodyBuilder) source);
        }
        else if (source instanceof Queryable<?>) {
            // NOTE: The unchecked casts is required for IDEA to build the module while the call is redirected to
            // an explicitly typed method on the DSL class.
            return DSL.<R>firstInQueryable((Queryable<R>) source);
        }
        else if (source instanceof Iterable<?>) {
            return DSL.<R>firstInIterable((Iterable<R>) source);
        }
        throw new IllegalArgumentException("Cannot retrieve first element from " + source.getClass().getName());
    }

    private static <R> R firstInIterable(Iterable<R> source) {
        Iterator<R> iter = source.iterator();
        if (iter.hasNext()) {
            return iter.next();
        }
        else {
            return null;
        }
    }
    private static <R> R firstInQueryable(Queryable<R> source) {
        QueryEngine queryEngine = source.createQueryEngine();
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        sourceIdentifier,
                        new MethodCall(
                                new Identifier("first"),
                                new ArrayList<Expression>(0)
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
        //noinspection RedundantTypeArguments
        return queryEngine.<R>evaluate(query); // <-- IntelliJ suggests that <R> can be inferred, but this leads to a compilation error.
    }
    private static <R> R firstInQueryBodyBuilder(QueryBodyBuilder<?> query) {
        QueryExpressionBuilderImpl<R> impl = (QueryExpressionBuilderImpl<R>) query;
        Statement statement = new Statement(Arrays.<Expression>asList(
                query.getQueryExpression(),
                new MethodCall(new Identifier("first"), new ArrayList<Expression>(0)))

        );
        Quaere4ObjectsQueryEngine engine = new Quaere4ObjectsQueryEngine();
        for (Map.Entry<Identifier, Queryable> sourceEntry : impl.getSources().entrySet()) {
            engine.addSource(sourceEntry.getValue().getSourceIdentifier(sourceEntry.getKey()), sourceEntry.getValue());
        }
        //noinspection RedundantTypeArguments
        return engine.<R>evaluate(statement); // <-- IntelliJ suggests that <R> can be inferred, but we get a compilation error if its not specified.
    }
    public static class first {
        public static <T> ElementOperatorArgumentDefinitionBuilder in(T[] source) {
            return in(Arrays.asList(source));
        }
        public static <T> ElementOperatorArgumentDefinitionBuilder in(Iterable<T> source) {
            return in(new QueryableIterable<T>(source));
        }
        public static <T> ElementOperatorArgumentDefinitionBuilder in(Queryable<T> source) {
            ElementOperatorBuilder builder = new ElementOperatorBuilderImpl("first");
            return builder.in(source);
        }
    }
    // Generators
    public static <T extends Number> Iterable<T> range(T from, T to) {
        return new NumberRange<T>(from, to);
    }
    public static Iterable<Character> range(char from, char to) {
        return new CharacterRange(from,to);
    }
    public static <T> Iterable<T> repeater(T value, int repititions) {
        return new Repeater<T>(value, repititions);
    }

}
