package org.quaere.objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.quaere.expressions.*;

public class ObjectsQueryEngine_BinaryExpressionsTest {
    private ObjectQueryEngine queryEngine;
    @Before
    public void setup() {
        queryEngine = new ObjectQueryEngine();
    }
    @Test
    public void canPerformLogicalAndOperations() {
        BinaryExpression andOperator;
        andOperator = BinaryExpression.and(new Constant(true), new Constant(true));
        queryEngine.visit(andOperator);
        Assert.assertEquals(true, queryEngine.result);
        andOperator = BinaryExpression.and(new Constant(false), new Constant(true));
        queryEngine.visit(andOperator);
        Assert.assertEquals(false, queryEngine.result);
        andOperator = BinaryExpression.and(new Constant(false), new Constant(false));
        queryEngine.visit(andOperator);
        Assert.assertEquals(false, queryEngine.result);
        andOperator = BinaryExpression.and(new Constant(true), new Constant(false));
        queryEngine.visit(andOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void canPerformLogicalOrOperations() {
        BinaryExpression orOperator;
        orOperator = BinaryExpression.or(new Constant(true), new Constant(false));
        queryEngine.visit(orOperator);
        Assert.assertEquals(true, queryEngine.result);
        orOperator = BinaryExpression.or(new Constant(false), new Constant(false));
        queryEngine.visit(orOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void sameValuesAreEqualWhenApplyingEqualityOperator() {
        BinaryExpression equalOperator = BinaryExpression.equal(new Constant("Hello World"), new Constant("Hello World"));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void differentValuesAreNotEqualWhenApplyingEqualityOperator() {
        BinaryExpression equalOperator = BinaryExpression.equal(new Constant("Hello"), new Constant("World"));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nullIsEqualToNullWhenApplyingEqualityOperator() {
        BinaryExpression equalOperator = BinaryExpression.equal(new Constant(null, String.class), new Constant(null, String.class));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nullIsNotEqualToNonNullValueWhenApplyingEqualityOperator() {
        BinaryExpression equalOperator = BinaryExpression.equal(new Constant(null, String.class), new Constant("Not null"));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nonNullValueIsNotEqualToNullWhenApplyingEqualityOperator() {
        BinaryExpression equalOperator = BinaryExpression.equal(new Constant("Not null"), new Constant(null, String.class));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void sameValuesAreNotEqualWhenApplyingInEqualityOperator() {
        BinaryExpression notBinaryExpression = BinaryExpression.notEqual(new Constant("Hello World"), new Constant("Hello World"));
        queryEngine.visit(notBinaryExpression);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void differentValuesEvaluateToTrueWhenApplyingEqualityOperator() {
        BinaryExpression notBinaryExpression = BinaryExpression.notEqual(new Constant("Hello"), new Constant("World"));
        queryEngine.visit(notBinaryExpression);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nullIsNotEqualToNullWhenApplyingInequalityOperator() {
        BinaryExpression notBinaryExpression = BinaryExpression.notEqual(new Constant(null, String.class), new Constant(null, String.class));
        queryEngine.visit(notBinaryExpression);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nullComparedToNonNullValueEvaluatesToTrueWhenApplyingEqualityOperator() {
        BinaryExpression notBinaryExpression = BinaryExpression.notEqual(new Constant(null, String.class), new Constant("Not null"));
        queryEngine.visit(notBinaryExpression);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nonNullValueComparedToNullEvaluatesToTrueWhenApplyingEqualityOperator() {
        BinaryExpression notBinaryExpression = BinaryExpression.notEqual(new Constant("Not null"), new Constant(null, String.class));
        queryEngine.visit(notBinaryExpression);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void applyingGreaterThanToGreaterValueEvaluatesToTrue() {
        BinaryExpression greaterThanOperator = BinaryExpression.greaterThan(new Constant(4), new Constant(2));
        queryEngine.visit(greaterThanOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void applyingGreaterThanToSameValuesEvaluatesToFalse() {
        BinaryExpression greaterThanOperator = BinaryExpression.greaterThan(new Constant(2), new Constant(2));
        queryEngine.visit(greaterThanOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nullIsNotGreaterThanNull() {
        BinaryExpression greaterThanOperator = BinaryExpression.greaterThan(new Constant(null, Integer.class), new Constant(null, Integer.class));
        queryEngine.visit(greaterThanOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void applyingGreaterThanOrEqualToGreaterValueEvaluatesToTrue() {
        BinaryExpression greaterThanOrBinaryExpression = BinaryExpression.greaterThanOrEqual(new Constant(4), new Constant(2));
        queryEngine.visit(greaterThanOrBinaryExpression);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void applyingGreaterOrEqualThanToSameValuesEvaluatesToTrue() {
        BinaryExpression greaterThanOrBinaryExpression = BinaryExpression.greaterThanOrEqual(new Constant(2), new Constant(2));
        queryEngine.visit(greaterThanOrBinaryExpression);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nullIsNotGreaterThanOrEqualToNull() {
        BinaryExpression greaterThanOrBinaryExpression = BinaryExpression.greaterThanOrEqual(new Constant(null, Integer.class), new Constant(null, Integer.class));
        queryEngine.visit(greaterThanOrBinaryExpression);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void applyingLessThanToLesserValueEvaluatesToTrue() {
        BinaryExpression lessThanOperator = BinaryExpression.lessThan(new Constant(2), new Constant(4));
        queryEngine.visit(lessThanOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void applyingLessThanToSameValuesEvaluatesToFalse() {
        BinaryExpression lessThanOperator = BinaryExpression.lessThan(new Constant(2), new Constant(2));
        queryEngine.visit(lessThanOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nullIsNotLessThanNull() {
        BinaryExpression lessThanOperator = BinaryExpression.lessThan(new Constant(null, Integer.class), new Constant(null, Integer.class));
        queryEngine.visit(lessThanOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void applyingLessThanOrEqualToLesserValueEvaluatesToTrue() {
        BinaryExpression lessThanOrBinaryExpression = BinaryExpression.lessThanOrEqual(new Constant(2), new Constant(4));
        queryEngine.visit(lessThanOrBinaryExpression);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void applyingLessThanOrEqualThanToSameValuesEvaluatesToTrue() {
        BinaryExpression lessThanOrBinaryExpression = BinaryExpression.lessThanOrEqual(new Constant(2), new Constant(2));
        queryEngine.visit(lessThanOrBinaryExpression);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nullIsNotLessThanOrEqualToNull() {
        BinaryExpression lessThanOrBinaryExpression = BinaryExpression.lessThanOrEqual(new Constant(null, Integer.class), new Constant(null, Integer.class));
        queryEngine.visit(lessThanOrBinaryExpression);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void canSubtractAnIntegerFromAnotherInteger() {
        BinaryExpression minusOperator = BinaryExpression.subtract(new Constant(10), new Constant(5));
        queryEngine.visit(minusOperator);
        Assert.assertEquals(5, queryEngine.result);
    }
    @Test
    public void subtractionWithDifferentTypesAreCoercedToLeftHandSideType() {
        BinaryExpression minusOperator = BinaryExpression.subtract(new Constant(10f), new Constant(5d));
        queryEngine.visit(minusOperator);
        Assert.assertEquals(5f, queryEngine.result);
        Assert.assertEquals(Float.class, queryEngine.result.getClass());
    }
    @Test
    public void canAddAnIntegerWithAnotherIntger() {
        BinaryExpression plusOperator = BinaryExpression.add(new Constant(2), new Constant(2));
        queryEngine.visit(plusOperator);
        Assert.assertEquals(4, queryEngine.result);
    }
    @Test
    public void additionWithDifferentTypesAreCoercedToLeftHandSideType() {
        BinaryExpression plusOperator = BinaryExpression.add(new Constant(2.5f), new Constant(2));
        queryEngine.visit(plusOperator);
        Assert.assertEquals(4.5f, queryEngine.result);
        Assert.assertEquals(Float.class, queryEngine.result.getClass());
    }
    @Test
    public void addingTwoStringsConcatinatesTheStrings() {
        BinaryExpression plusOperator = BinaryExpression.add(new Constant("Hello "), new Constant("world"));
        queryEngine.visit(plusOperator);
        Assert.assertEquals("Hello world", queryEngine.result);
    }
    @Test
    public void addingStringToNumberConcatinatesTheResult() {
        BinaryExpression plusOperator = BinaryExpression.add(new Constant(5), new Constant(" times"));
        queryEngine.visit(plusOperator);
        Assert.assertEquals("5 times", queryEngine.result);
    }
    @Test
    public void addingNumberToStringConcatinatesTheResult() {
        BinaryExpression plusOperator = BinaryExpression.add(new Constant("Price: "), new Constant(43.5));
        queryEngine.visit(plusOperator);
        Assert.assertEquals("Price: 43.5", queryEngine.result);
    }
    @Test
    public void canMultiplyAnIntegerByAnotherInteger() {
        BinaryExpression multiplyOperator = BinaryExpression.multiply(new Constant(3), new Constant(10));
        queryEngine.visit(multiplyOperator);
        Assert.assertEquals(30, queryEngine.result);
    }
    // TODO: Need to figure out whether values should be up-casted or coerced to lhs type when results from maths operations exceed a types boundry values...
    @Test
    public void canDivideAnIntegerByAnotherInteger() {
        BinaryExpression divideOperator = BinaryExpression.divide(new Constant(10), new Constant(5));
        queryEngine.visit(divideOperator);
        Assert.assertEquals(2, queryEngine.result);
    }
    @Test
    public void divisionWithDifferentTypesAreCoercedToLeftHandSidesType() {
        BinaryExpression divideOperator = BinaryExpression.divide(new Constant(10.0f), new Constant(5));
        queryEngine.visit(divideOperator);
        Assert.assertEquals(2f, queryEngine.result);
        Assert.assertEquals(Float.class, queryEngine.result.getClass());
    }
}
