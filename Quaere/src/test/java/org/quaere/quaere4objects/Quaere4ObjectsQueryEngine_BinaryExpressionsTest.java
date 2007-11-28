package org.quaere.quaere4objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.quaere.expressions.*;

public class Quaere4ObjectsQueryEngine_BinaryExpressionsTest {
    private Quaere4ObjectsQueryEngine queryEngine;
    @Before
    public void setup() {
        queryEngine = new Quaere4ObjectsQueryEngine();
    }
    @Test
    public void canPerformLogicalAndOperations()
    {
        AndOperator andOperator;
        andOperator = new AndOperator(new Constant(true),new Constant(true));
        queryEngine.visit(andOperator);
        Assert.assertEquals(true, queryEngine.result);
        andOperator = new AndOperator(new Constant(false),new Constant(true));
        queryEngine.visit(andOperator);
        Assert.assertEquals(false, queryEngine.result);
        andOperator = new AndOperator(new Constant(false),new Constant(false));
        queryEngine.visit(andOperator);
        Assert.assertEquals(false, queryEngine.result);
        andOperator = new AndOperator(new Constant(true),new Constant(false));
        queryEngine.visit(andOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void canPerformLogicalOrOperations()
    {
        OrOperator orOperator;
        orOperator = new OrOperator(new Constant(true),new Constant(false));
        queryEngine.visit(orOperator);
        Assert.assertEquals(true, queryEngine.result);
        orOperator = new OrOperator(new Constant(false),new Constant(false));
        queryEngine.visit(orOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void sameValuesAreEqualWhenApplyingEqualityOperator()
    {
        EqualOperator equalOperator = new EqualOperator(new Constant("Hello World"), new Constant("Hello World"));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void differentValuesAreNotEqualWhenApplyingEqualityOperator()
    {
        EqualOperator equalOperator = new EqualOperator(new Constant("Hello"), new Constant("World"));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nullIsEqualToNullWhenApplyingEqualityOperator()
    {
        EqualOperator equalOperator = new EqualOperator(new Constant(null,String.class), new Constant(null,String.class));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nullIsNotEqualToNonNullValueWhenApplyingEqualityOperator()
    {
        EqualOperator equalOperator = new EqualOperator(new Constant(null,String.class),new Constant("Not null"));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nonNullValueIsNotEqualToNullWhenApplyingEqualityOperator()
    {
        EqualOperator equalOperator = new EqualOperator(new Constant("Not null"), new Constant(null,String.class));
        queryEngine.visit(equalOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void sameValuesAreNotEqualWhenApplyingInEqualityOperator()
    {
        NotEqualOperator notEqualOperator = new NotEqualOperator(new Constant("Hello World"), new Constant("Hello World"));
        queryEngine.visit(notEqualOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void differentValuesEvaluateToTrueWhenApplyingEqualityOperator()
    {
        NotEqualOperator notEqualOperator = new NotEqualOperator(new Constant("Hello"), new Constant("World"));
        queryEngine.visit(notEqualOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nullIsNotEqualToNullWhenApplyingInequalityOperator()
    {
        NotEqualOperator notEqualOperator = new NotEqualOperator(new Constant(null,String.class), new Constant(null,String.class));
        queryEngine.visit(notEqualOperator);
        Assert.assertEquals(false, queryEngine.result);
    }
    @Test
    public void nullComparedToNonNullValueEvaluatesToTrueWhenApplyingEqualityOperator()
    {
        NotEqualOperator notEqualOperator = new NotEqualOperator(new Constant(null,String.class),new Constant("Not null"));
        queryEngine.visit(notEqualOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void nonNullValueComparedToNullEvaluatesToTrueWhenApplyingEqualityOperator()
    {
        NotEqualOperator notEqualOperator = new NotEqualOperator(new Constant("Not null"), new Constant(null,String.class));
        queryEngine.visit(notEqualOperator);
        Assert.assertEquals(true, queryEngine.result);
    }
    @Test
    public void applyingGreaterThanToGreaterValueEvaluatesToTrue()
    {
        GreaterThanOperator greaterThanOperator = new GreaterThanOperator(new Constant(4),new Constant(2));
        queryEngine.visit(greaterThanOperator);
        Assert.assertEquals(true,queryEngine.result);
    }
    @Test
    public void applyingGreaterThanToSameValuesEvaluatesToFalse()
    {
        GreaterThanOperator greaterThanOperator = new GreaterThanOperator(new Constant(2), new Constant(2));
        queryEngine.visit(greaterThanOperator);
        Assert.assertEquals(false,queryEngine.result);
    }
    @Test
    public void nullIsNotGreaterThanNull()
    {
        GreaterThanOperator greaterThanOperator = new GreaterThanOperator(new Constant(null,Integer.class), new Constant(null,Integer.class));
        queryEngine.visit(greaterThanOperator);
        Assert.assertEquals(false,queryEngine.result);
    }
    @Test
    public void applyingGreaterThanOrEqualToGreaterValueEvaluatesToTrue()
    {
        GreaterThanOrEqualOperator greaterThanOrEqualOperator = new GreaterThanOrEqualOperator(new Constant(4),new Constant(2));
        queryEngine.visit(greaterThanOrEqualOperator);
        Assert.assertEquals(true,queryEngine.result);
    }
    @Test
    public void applyingGreaterOrEqualThanToSameValuesEvaluatesToTrue()
    {
        GreaterThanOrEqualOperator greaterThanOrEqualOperator = new GreaterThanOrEqualOperator(new Constant(2), new Constant(2));
        queryEngine.visit(greaterThanOrEqualOperator);
        Assert.assertEquals(true,queryEngine.result);
    }
    @Test
    public void nullIsNotGreaterThanOrEqualToNull()
    {
        GreaterThanOrEqualOperator greaterThanOrEqualOperator = new GreaterThanOrEqualOperator(new Constant(null,Integer.class), new Constant(null,Integer.class));
        queryEngine.visit(greaterThanOrEqualOperator);
        Assert.assertEquals(false,queryEngine.result);
    }
    @Test
    public void applyingLessThanToLesserValueEvaluatesToTrue()
    {
        LessThanOperator lessThanOperator = new LessThanOperator(new Constant(2),new Constant(4));
        queryEngine.visit(lessThanOperator);
        Assert.assertEquals(true,queryEngine.result);
    }
    @Test
    public void applyingLessThanToSameValuesEvaluatesToFalse()
    {
        LessThanOperator lessThanOperator = new LessThanOperator(new Constant(2), new Constant(2));
        queryEngine.visit(lessThanOperator);
        Assert.assertEquals(false,queryEngine.result);
    }
    @Test
    public void nullIsNotLessThanNull()
    {
        LessThanOperator lessThanOperator = new LessThanOperator(new Constant(null,Integer.class), new Constant(null,Integer.class));
        queryEngine.visit(lessThanOperator);
        Assert.assertEquals(false,queryEngine.result);
    }
    @Test
    public void applyingLessThanOrEqualToLesserValueEvaluatesToTrue()
    {
        LessThanOrEqualOperator lessThanOrEqualOperator = new LessThanOrEqualOperator(new Constant(2),new Constant(4));
        queryEngine.visit(lessThanOrEqualOperator);
        Assert.assertEquals(true,queryEngine.result);
    }
    @Test
    public void applyingLessThanOrEqualThanToSameValuesEvaluatesToTrue()
    {
        LessThanOrEqualOperator lessThanOrEqualOperator = new LessThanOrEqualOperator(new Constant(2), new Constant(2));
        queryEngine.visit(lessThanOrEqualOperator);
        Assert.assertEquals(true,queryEngine.result);
    }
    @Test
    public void nullIsNotLessThanOrEqualToNull()
    {
        LessThanOrEqualOperator lessThanOrEqualOperator = new LessThanOrEqualOperator(new Constant(null,Integer.class), new Constant(null,Integer.class));
        queryEngine.visit(lessThanOrEqualOperator);
        Assert.assertEquals(false,queryEngine.result);
    }
    @Test
    public void canSubtractAnIntegerFromAnotherInteger()
    {
        MinusOperator minusOperator = new MinusOperator(new Constant(10),new Constant(5));
        queryEngine.visit(minusOperator);
        Assert.assertEquals(5,queryEngine.result);
    }
    @Test
    public void subtractionWithDifferentTypesAreCoercedToLeftHandSideType()
    {
        MinusOperator minusOperator = new MinusOperator(new Constant(10f),new Constant(5d));
        queryEngine.visit(minusOperator);
        Assert.assertEquals(5f,queryEngine.result);
        Assert.assertEquals(Float.class,queryEngine.result.getClass());
    }
    @Test
    public void canAddAnIntegerWithAnotherIntger()
    {
        PlusOperator plusOperator = new PlusOperator(new Constant(2), new Constant(2));
        queryEngine.visit(plusOperator);
        Assert.assertEquals(4,queryEngine.result);
    }
    @Test
    public void additionWithDifferentTypesAreCoercedToLeftHandSideType()
    {
        PlusOperator plusOperator = new PlusOperator(new Constant(2.5f), new Constant(2));
        queryEngine.visit(plusOperator);
        Assert.assertEquals(4.5f,queryEngine.result);
        Assert.assertEquals(Float.class,queryEngine.result.getClass());
    }
    @Test
    public void addingTwoStringsConcatinatesTheStrings()
    {
        PlusOperator plusOperator = new PlusOperator(new Constant("Hello "), new Constant("world"));
        queryEngine.visit(plusOperator);
        Assert.assertEquals("Hello world",queryEngine.result);
    }
    @Test
    public void addingStringToNumberConcatinatesTheResult()
    {
        PlusOperator plusOperator = new PlusOperator(new Constant(5), new Constant(" times"));
        queryEngine.visit(plusOperator);
        Assert.assertEquals("5 times",queryEngine.result);
    }
    @Test
    public void addingNumberToStringConcatinatesTheResult()
    {
        PlusOperator plusOperator = new PlusOperator(new Constant("Price: "),new Constant(43.5));
        queryEngine.visit(plusOperator);
        Assert.assertEquals("Price: 43.5",queryEngine.result);
    }
    @Test
    public void canMultiplyAnIntegerByAnotherInteger()
    {
        MultiplyOperator multiplyOperator = new MultiplyOperator(new Constant(3),new Constant(10));
        queryEngine.visit(multiplyOperator);
        Assert.assertEquals(30,queryEngine.result);
    }
    // TODO: Need to figure out whether values should be up-casted or coerced to lhs type when results from maths operations exceed a types boundry values...
    @Test
    public void canDivideAnIntegerByAnotherInteger()
    {
        DivideOperator divideOperator = new DivideOperator(new Constant(10),new Constant(5));
        queryEngine.visit(divideOperator);
        Assert.assertEquals(2, queryEngine.result);
    }
    @Test
    public void divisionWithDifferentTypesAreCoercedToLeftHandSidesType()
    {
        DivideOperator divideOperator = new DivideOperator(new Constant(10.0f),new Constant(5));
        queryEngine.visit(divideOperator);
        Assert.assertEquals(2f, queryEngine.result);
        Assert.assertEquals(Float.class,queryEngine.result.getClass());
    }
}
