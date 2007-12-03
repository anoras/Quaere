package org.quaere;

import org.quaere.dsl.*;
import org.quaere.expressions.*;
import org.quaere.operations.*;
import static org.quaere.operations.DefaultOperations.*;

import java.util.Arrays;
import java.util.List;

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

    public static NewExpression create(Property... properties) {
        return createDefaultNewExpression(properties);
    }

    public static NewExpression create(Class<?> clazz, Property... properties) {
        return creatDefaultNewExpresion(clazz, properties);
    }

    public static Property property(String expression) {
        return PropertyOperation.createPropertyFromStringExpression(expression);
    }

    public static Property property(String propertyName, String expression) {
        return PropertyOperation.createProperty(propertyName, expression);
    }

    public static Property property(String propertyName, Expression expression) {
        return PropertyOperation.createProperty(propertyName, expression);
    }

    public static <R> Property property(String propertyName, QueryContinuationOrQueryBodyBuilder<R> subquery) {
        return PropertyOperation.createSubQueryProperty(propertyName, subquery);
    }

    // Aggregation operators
    public static Statement count(String expression) {
        return count(LiteralExpression.parse(expression));
    }

    public static Statement count(Expression expression) {
        return createMethodCallStatement(expression, DefaultOperation.COUNT);
    }

    public static InOperation count = AggregateOperation.COUNT;

    public static Statement sum(String expression) {
        return sum(LiteralExpression.parse(expression));
    }

    public static Statement sum(Expression expression) {
        return createMethodCallStatement(expression, DefaultOperation.SUM);
    }

    public static OfOperation sum = AggregateOperation.SUM;
    public static InOperation min = AggregateOperation.MIN;
    public static InOperation max = AggregateOperation.MAX;
    public static InOperation avg = AggregateOperation.AVG;

    public static <R> AggregateOperatorBuilder<R> aggregate(String accumulationIdentifier, String anonymousIdentifier) {
        return createAggregateOperationBuilder(accumulationIdentifier, anonymousIdentifier);
    }

    // Conversion operators
    public static <T> T[] asArray(T[] tArray, Iterable<T> source) {
        return CollectionOperations.asArray(tArray, source);
    }

    public static <T> T[] asArray(Iterable<T> source) {
        return CollectionOperations.asArray(source);
    }

    public static <T> List<T> asList(Iterable<?> iterable) {
        return CollectionOperations.asList(iterable);
    }

    public static <T> Iterable<T> ofClass(Class<T> clazz, Object... source) {
        return CollectionOperations.ofClass(clazz, Arrays.asList(source));
    }

    public static <T> Iterable<T> ofClass(Class<T> clazz, Iterable<?> source) {
        return CollectionOperations.ofClass(clazz, source);
    }

    // Ordering operators
    public static <T> Iterable<T> reverse(Iterable<T> sequence) {
        return CollectionOperations.reverse(sequence);
    }

    public static final AnyOperation any = new AnyOperation();

    // Partitioning operators
    public static PartitioningOperatorBuilder take(int count) {
        return new PartitioningOperatorBuilderImpl(PartitionOperator.take, count);
    }

    public static PartitionWhenOperatorBuilder take(String anonymousIdentifier) {
        return new PartitionWhenOperatorBuilderImpl(PartitionOperator.takeWhile, anonymousIdentifier);
    }

    public static PartitioningOperatorBuilder skip(int count) {
        return new PartitioningOperatorBuilderImpl(PartitionOperator.skip, count);
    }

    public static PartitionWhenOperatorBuilder skip(String anonymousIdentifier) {
        return new PartitionWhenOperatorBuilderImpl(PartitionOperator.skipWhile, anonymousIdentifier);
    }

    // Set operators
    public static <T> Iterable<T> distinct(T[] source) {
        return distinct(Arrays.asList(source));
    }

    public static <T> Iterable<T> distinct(Iterable<T> source) {
        return distinct(new QueryableIterable<T>(source));
    }

    public static <T> Iterable<T> distinct(Queryable<T> source) {
        return executeSingleParamOperation(DefaultOperation.DISTINCT, source);
    }

    public static <T> Iterable<T> union(T[] leftHandSide, T[] rightHandSide) {
        return union(Arrays.asList(leftHandSide), Arrays.asList(rightHandSide));
    }

    public static <T> Iterable<T> union(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        return union(new QueryableIterable<T>(leftHandSide), new QueryableIterable<T>(rightHandSide));
    }

    public static <T> Iterable<T> union(Queryable<T> leftHandSide, Queryable<T> rightHandSide) {
        return executeTwoParamOperation(leftHandSide, DefaultOperation.UNION, rightHandSide);
    }

    public static <T> Iterable<T> intersect(T[] leftHandSide, T[] rightHandSide) {
        return intersect(Arrays.asList(leftHandSide), Arrays.asList(rightHandSide));
    }

    public static <T> Iterable<T> intersect(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        return intersect(new QueryableIterable<T>(leftHandSide), new QueryableIterable<T>(rightHandSide));
    }

    public static <T> Iterable<T> intersect(Queryable<T> leftHandSide, Queryable<T> rightHandSide) {
        return executeTwoParamOperation(leftHandSide, DefaultOperation.INTERSECT, rightHandSide);
    }

    public static <T> Iterable<T> except(T[] leftHandSide, T[] rightHandSide) {
        return except(Arrays.asList(leftHandSide), Arrays.asList(rightHandSide));
    }

    public static <T> Iterable<T> except(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        return except(new QueryableIterable<T>(leftHandSide), new QueryableIterable<T>(rightHandSide));
    }

    public static <T> Iterable<T> except(Queryable<T> leftHandSide, Queryable<T> rightHandSide) {
        return executeTwoParamOperation(leftHandSide, DefaultOperation.EXCEPT, rightHandSide);
    }


    // Element operators
    public static <R> R first(R[] source) {
        return FirstOperationImpl.firstFromArray(source);
    }

    // NOTE: This method is a facade for the typed firstXxx methods to enable the module to be built with Eclipse.
    @SuppressWarnings({"RedundantTypeArguments", "unchecked"})
    public static <R> R first(Object source) {
        return FirstOperationImpl.<R>first(source);
    }

    public static FirstOperation first = FirstOperationImpl.FIRST;

    // Generators
    public static <T extends Number> Iterable<T> range(T from, T to) {
        return new NumberRange<T>(from, to);
    }

    public static Iterable<Character> range(char from, char to) {
        return new CharacterRange(from, to);
    }

    public static <T> Iterable<T> repeater(T value, int repititions) {
        return new Repeater<T>(value, repititions);
    }

}
