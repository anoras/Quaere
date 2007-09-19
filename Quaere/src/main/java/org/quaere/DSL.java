package org.quaere;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.quaere.dsl.OrderableAndGroupableQueryBodyBuilder;
import org.quaere.dsl.SubqueryOrderableAndGroupableQueryBodyBuilder;
import org.quaere.dsl.parser.QuaereLexer;
import org.quaere.dsl.parser.QuaereParser;
import org.quaere.expressions.*;
import org.quaere.quaere4objects.Quaere4ObjectsQueryBuilder;
import org.quaere.quaere4objects.Quaere4ObjectsQuery;
import org.quaere.quaere4objects.Quaere4ObjectsSubqueryBuilder;

import java.util.*;

public class DSL {
    private static Expression parseLiteralExpression(String literalExpression) {
        QuaereLexer lexer = new QuaereLexer(new ANTLRStringStream(literalExpression));
        QuaereParser parser = new QuaereParser(new CommonTokenStream(lexer));
        Expression expression;
        try {
            expression = parser.quaereExpression().value;
        } catch (RecognitionException e) {
            throw new RuntimeException(e);
        }
        return expression;
    }

    public static FirstFromClause from(String identifier) {
        return new FirstFromClause(identifier);
    }


    public static <T> Iterable<T> select(Object[] source, String lambdaExpression){
        return select(Arrays.asList(source),lambdaExpression);
    }
    public static <T> Iterable<T> select(List<Object> source, String lambdaExpression){
        String sourceIdenetifier= "src_"+UUID.randomUUID().toString().replace("-","");
        String completeExpression=String.format("%s.Select(%s)",sourceIdenetifier,lambdaExpression);
        Expression queryExpression=parseLiteralExpression(completeExpression);
        Quaere4ObjectsQuery query=new Quaere4ObjectsQuery(queryExpression);
        query.addSource(sourceIdenetifier, source);
        return query.evaluate();
    }
    // Element operators
    public static <T> T first(Iterable<T> source) {
        Iterator<T> iter = source.iterator();
        if (!iter.hasNext()) {
            return null;
        } else {
            return iter.next();
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

    public static <T> List<T> asList(Iterable<T> source) {
        List<T> asList = new ArrayList<T>();
        for (T elm : source) {
            asList.add(elm);
        }
        return asList;
    }


    public static <V> Map<Object, V> asMap(String keyProperty, Iterable<V> source) {
        throw new RuntimeException("dsl.asMap is not implemented");
    }
    public static <T> Iterable<T> ofClass(Class<T> clazz, Object... source) {
        return ofClass(clazz, Arrays.asList(source));
    }
    @SuppressWarnings({"unchecked"})
    public static <T> Iterable<T> ofClass(Class<T> clazz, Iterable source) {
         List<T> result = new ArrayList<T>();
         for (Object obj : source) {
             if (obj != null && obj.getClass().equals(clazz)) {
                 result.add((T) obj);
             }
         }
         return result;
     }

    // Set operators
    public static <T> Iterable<T> distinct(T... source) {
         return distinct(Arrays.asList(source));
     }

     public static <T> Iterable<T> distinct(Iterable<T> source) {
         List<T> result = new ArrayList<T>();
         for (T elm : source) {
             if (!result.contains(elm)) result.add(elm);
         }
         return result;
     }
   public static <T> Iterable<T> union(T[] leftHandSide, T[] rightHandSide) {
        List<T> unionedList = new ArrayList<T>();
        for (T elm : leftHandSide) {
            if (!unionedList.contains(elm)) unionedList.add(elm);
        }
        for (T elm : rightHandSide) {
            if (!unionedList.contains(elm)) unionedList.add(elm);
        }
        return unionedList;
    }

    public static <T> Iterable<T> union(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        List<T> unionedList = new ArrayList<T>();
        for (T elm : leftHandSide) {
            if (!unionedList.contains(elm)) unionedList.add(elm);
        }
        for (T elm : rightHandSide) {
            if (!unionedList.contains(elm)) unionedList.add(elm);
        }
        return unionedList;
    }    
   public static <T> Iterable<T> intersect(T[] leftHandSide, T[] rightHandSide) {
        List<T> intersection = new ArrayList<T>();
        for (T elm : leftHandSide) {
            if (Arrays.binarySearch(rightHandSide, elm) >= 0 && !intersection.contains(elm)) {
                intersection.add(elm);
            }
        }
        return intersection;
    }

    public static <T> Iterable<T> intersect(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        List<T> rhsList = new ArrayList<T>();
        for (T rhsElm : rightHandSide) {
            rhsList.add(rhsElm);
        }
        List<T> intersection = new ArrayList<T>();
        for (T lhsElm : leftHandSide) {
            if (rhsList.contains(lhsElm) && !intersection.contains(lhsElm)) {
                intersection.add(lhsElm);
            }
        }
        return intersection;
    }
    public static <T> Iterable<T> except(T[] leftHandSide, T[] rightHandSide) {
        List<T> expections = new ArrayList<T>();
        for (T elm : leftHandSide) {
            if (Arrays.binarySearch(rightHandSide, elm) < 0 && !expections.contains(elm)) {
                expections.add(elm);
            }
        }
        return expections;
    }

    public static <T> Iterable<T> except(Iterable<T> leftHandSide, Iterable<T> rightHandSide) {
        List<T> rhsList = new ArrayList<T>();
        for (T rhsElm : rightHandSide) {
            rhsList.add(rhsElm);
        }
        List<T> exceptions = new ArrayList<T>();
        for (T lhsElm : leftHandSide) {
            if (!rhsList.contains(lhsElm) && !exceptions.contains(lhsElm)) {
                exceptions.add(lhsElm);
            }
        }
        return exceptions;
    }
    // Partitioning operators
    public static <T> Iterable<T> take(int count, T...source){
        return take(count, Arrays.asList(source));  
    }
    public static <T> Iterable<T> take(int count, Iterable<T> source)
    {
        List<T> result = new ArrayList<T>();
        Iterator<T> iter = source.iterator();
        while (result.size() < count && iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    public static <T> Iterable<T> takeWhile(String lambdaExpression, T...source)
    {
        return takeWhile(lambdaExpression, Arrays.asList(source));
    }
    public static <T> Iterable<T> takeWhile(String lambdaExpression, List<T> source)
    {
        String sourceIdenetifier= "src_"+UUID.randomUUID().toString().replace("-","");
        String completeExpression=String.format("%s.takeWhile(%s)",sourceIdenetifier,lambdaExpression);
        Expression queryExpression=parseLiteralExpression(completeExpression);
        Quaere4ObjectsQuery query=new Quaere4ObjectsQuery(queryExpression);
        query.addSource(sourceIdenetifier, source);
        return query.evaluate();
    }
    public static <T> Iterable<T> skip(int count, T... source) {
        return skip(count, Arrays.asList(source));
    }

    public static <T> Iterable<T> skip(int count, Iterable<T> source) {
        ArrayList<T> result = new ArrayList<T>();
        int skipped = 0;
        Iterator<T> iter = source.iterator();
        while (skipped < count && iter.hasNext()) {
            iter.next();
            skipped++;
        }
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }
    public static <T> Iterable<T> skipWhile(String lambdaExpression, T... source) {
        return skipWhile(lambdaExpression,Arrays.asList(source));
    }
    public static <T> Iterable<T> skipWhile(String lambdaExpression, List<T> source)
    {
        String sourceIdenetifier= "src_"+UUID.randomUUID().toString().replace("-","");
        String completeExpression=String.format("%s.skipWhile(%s)",sourceIdenetifier,lambdaExpression);
        Expression queryExpression=parseLiteralExpression(completeExpression);
        Quaere4ObjectsQuery query=new Quaere4ObjectsQuery(queryExpression);
        query.addSource(sourceIdenetifier, source);
        return query.evaluate();
    }
    // Quantifiers
    public static boolean any(Object[] source,String lambdaExpression) {
        return any(Arrays.asList(source), lambdaExpression);
    }
    public static boolean any(List<?> source, String lambdaExpression){
        String sourceIdenetifier= "src_"+UUID.randomUUID().toString().replace("-","");
        String completeExpression=String.format("%s.any(%s)",sourceIdenetifier,lambdaExpression);
        Expression queryExpression=parseLiteralExpression(completeExpression);
        Quaere4ObjectsQuery query=new Quaere4ObjectsQuery(queryExpression);
        query.addSource(sourceIdenetifier, source);
        return (Boolean) query.<Object>evaluate();
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
    // Expression factories
    public static NewExpression create(Property...properties)
    {
        return new NewExpression(null,Arrays.asList(properties));
    }

    public static Property property(String expression)
    {
        Statement statment= (Statement) parseLiteralExpression(expression);
        if (statment.getExpressions().size() >1) {
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
                    return property(propertyName, expression);
                }
            }
        } else
        {
            // We're looking for an identifier
            Identifier identifier=(Identifier) statment.getExpressions().get(0);
            return property(identifier.getText(),expression);
        }
        throw new IllegalArgumentException(String.format("\"%s\" is not a vaild property expression.",expression));
    }
    public static Property property(String propertyName, String expression)
    {
        return property(propertyName,parseLiteralExpression(expression));
    }
    public static Property property(String propertyName, Expression expression)
    {
        return new Property(new Identifier(propertyName),expression);
    }
    public static Expression any(String reference,String lambdaExpression)
    {
        String completeExpression=String.format("%s.any(%s)",reference,lambdaExpression);
        return parseLiteralExpression(completeExpression);

    }
    public static Expression avg(String reference,String lambdaExpression){
        String completeExpression=String.format("%s.average(%s)",reference,lambdaExpression);
         return parseLiteralExpression(completeExpression);
    }
    public static AndOperator and(Expression leftHandSide, Expression rightHandSide) {
        return new AndOperator(
                new Statement(
                        Arrays.<Expression>asList(leftHandSide)
                ),
                new Statement(
                        Arrays.<Expression>asList(rightHandSide)
                )
        );
    }

    public static EqualOperator eq(String leftHandSide, String rightHandSide) {
        Expression leftExpression = parseLiteralExpression(leftHandSide);
        Expression rightExpression = parseLiteralExpression(rightHandSide);
        return new EqualOperator(leftExpression, rightExpression);
    }
    public static EqualOperator eq(String leftHandSide, Comparable rightHandSide) {
        Expression leftExpression = parseLiteralExpression(leftHandSide);
        return new EqualOperator(
                leftExpression,
                new Statement(
                        Arrays.<Expression>asList(new Constant(rightHandSide, rightHandSide.getClass()))
                )
        );
    }
    public static LessThanOperator lt(String leftHandSide, String rightHandSide)
    {
        Expression leftExpression = parseLiteralExpression(leftHandSide);
        Expression rightExpression = parseLiteralExpression(rightHandSide);
        return new LessThanOperator(leftExpression,rightExpression);        
    }

    public static LessThanOperator lt(String leftHandSide, Comparable rightHandSide) {
        Expression leftExpression = parseLiteralExpression(leftHandSide);
        return new LessThanOperator(
                leftExpression,
                new Statement(
                        Arrays.<Expression>asList(new Constant(rightHandSide, rightHandSide.getClass()))
                )
        );
    }
    public static GreaterThanOperator gt(String leftHandSide, Comparable rightHandSide) {
        Expression leftExpression = parseLiteralExpression(leftHandSide);
        return new GreaterThanOperator(
                leftExpression,
                new Statement(
                        Arrays.<Expression>asList(new Constant(rightHandSide, rightHandSide.getClass()))
                )
        );
    }

    private DSL() {
    }
    public static class FirstFromClause {
        private final String identifier;

        public FirstFromClause(String identifier) {
            this.identifier = identifier;
        }
        public <T> OrderableAndGroupableQueryBodyBuilder in(T...source)
        {
            Quaere4ObjectsQueryBuilder builder = new Quaere4ObjectsQueryBuilder();
            return builder.from(identifier).in(source);
        }
        public <T> OrderableAndGroupableQueryBodyBuilder in(List<T> source)
        {
            Quaere4ObjectsQueryBuilder builder = new Quaere4ObjectsQueryBuilder();
            return builder.from(identifier).in(source);
        }
        public SubqueryOrderableAndGroupableQueryBodyBuilder in(String expression)
        {
            Quaere4ObjectsSubqueryBuilder builder=new Quaere4ObjectsSubqueryBuilder();
            return builder.from(identifier).in(expression);
        }
    }
}
