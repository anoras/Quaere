package org.quaere.objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.Variant;
import org.quaere.expressions.*;
import org.quaere.model.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quaere4ObjectsQueryEngineTest {
    private Quaere4ObjectsQueryEngine queryEngine;
    @Before
    public void setup() {
        queryEngine = new Quaere4ObjectsQueryEngine();
    }
    @Test
    public void canEvaluateConstants() {
        Constant constant = new Constant(42f);
        queryEngine.visit(constant);
        Assert.assertEquals(42f, queryEngine.result);
        Assert.assertTrue(queryEngine.result instanceof Float);
    }
    @Test
    public void canRetriveNamedSourceByIdentifier() {
        Identifier identifier = new Identifier("id");
        final List<Integer> values = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Queryable<Integer> source = new QueryableIterable<Integer>(
                values
        );
        queryEngine.addSource(identifier, source);
        queryEngine.evaluate(new Expression() {
            public void accept(ExpressionTreeVisitor visitor) {
            }
        });
        queryEngine.visit(identifier);
        Assert.assertEquals(values, queryEngine.result);
    }
    @Test
    public void canGetUnnamedSourceByIdentifier() {
        Identifier identifier = new Identifier("id");
        final List<Integer> values = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Queryable<Integer> source = new QueryableIterable<Integer>(
                values
        );
        FromClause fromClause = new FromClause(identifier, new Expression() {
            public void accept(ExpressionTreeVisitor visitor) {
                queryEngine.result = values;
            }
        });
        queryEngine.addSource(identifier, source);
        queryEngine.visit(fromClause);
        queryEngine.currentTuple = new ArrayList<Object>();
        queryEngine.currentTuple.add(values);
        queryEngine.result = null;
        queryEngine.visit(identifier);
        Assert.assertEquals(values, queryEngine.result);
    }
    @Test
    public void canGetFieldValueByIdentifier() {
        Customer customer = new Customer();
        customer.setCity("Gotham");
        Identifier city = new Identifier("city");
        queryEngine.result = customer;
        queryEngine.visit(city);
        Assert.assertEquals(customer.getCity(), queryEngine.result);
    }
    @Test
    public void canInvokeOperation() {
        MethodCall methodCall = new MethodCall(
                new Identifier("getInformation"),
                Arrays.<Expression>asList(
                        new Constant("Customer: "),
                        new Constant(true),
                        new Constant(false)
                ));
        Customer customer = new Customer();
        customer.setCompanyName("Big Company");
        customer.setCity("Gotham");

        queryEngine.result = customer;
        queryEngine.visit(methodCall);
        Assert.assertEquals("Customer: Big Company", queryEngine.result);
    }
    // TODO: Need test cases for method resolving...
    @Test
    public void canInvokeCountOperatorWithoutLambdaExpression() {
        MethodCall methodCall = new MethodCall(
                new Identifier("count"),
                Arrays.<Expression>asList(),
                null,
                null,
                null
        );
        queryEngine.result = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        queryEngine.visit(methodCall);
        Assert.assertEquals(10, queryEngine.result);
    }
    @Test
    public void canInvokeCountOperatorWithLamdaExpression() {
        final int[] lambdaExpressionInvoked = new int[]{0};
        MethodCall methodCall = new MethodCall(
                new Identifier("count"),
                Arrays.<Expression>asList(),
                null,
                null,
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        lambdaExpressionInvoked[0]++;
                        queryEngine.result = true;
                    }
                }
        );
        queryEngine.result = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        queryEngine.visit(methodCall);
        Assert.assertTrue(lambdaExpressionInvoked[0] > 0);
        Assert.assertEquals(10, queryEngine.result);
    }
    @Test
    public void canInvokeCountOperatorWithIndexer() {
        final Identifier indexedIdentifier = new Identifier("indexer");
        final int[] lastIndex = new int[]{0};
        MethodCall methodCall = new MethodCall(
                new Identifier("count"),
                Arrays.<Expression>asList(),
                null,
                indexedIdentifier,
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = null;
                        queryEngine.visit(indexedIdentifier);
                        Assert.assertEquals(lastIndex[0]++, queryEngine.result);
                        queryEngine.result = true;
                    }
                }
        );
        queryEngine.result = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        queryEngine.visit(methodCall);
        Assert.assertEquals(10, queryEngine.result);
    }
    @Test
    public void canInvokeCountOperatorWithAnonymousIndentifer() {
        final Identifier anonIdentifier = new Identifier("anonymous");
        final int[] lastIndex = new int[]{0};
        final List<Integer> values = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        MethodCall methodCall = new MethodCall(
                new Identifier("count"),
                Arrays.<Expression>asList(),
                anonIdentifier,
                null,
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = null;
                        queryEngine.visit(anonIdentifier);
                        Assert.assertEquals(values.get(lastIndex[0]++), queryEngine.result);
                        queryEngine.result = true;
                    }
                }
        );
        queryEngine.result = values;
        queryEngine.visit(methodCall);
        Assert.assertEquals(10, queryEngine.result);
    }
    // TODO: Need unit tests for other extension methods... where, take, skip etc.
    @Test
    public void canEvaluateStatements() {
        final int[] acceptInvocations = new int[]{0};
        Expression mockExpression = new Expression() {
            public void accept(ExpressionTreeVisitor visitor) {
                acceptInvocations[0]++;
            }
        };
        Statement statement = new Statement(
                Arrays.<Expression>asList(mockExpression, mockExpression, mockExpression)
        );
        queryEngine.visit(statement);
        Assert.assertEquals(3, acceptInvocations[0]);
    }
    @Test
    public void canCreateVariants() {
        NewExpression newExpression = new NewExpression((Class<?>) null, new Property(new Identifier("a"), new Constant("a")));
        queryEngine.visit(newExpression);
        Assert.assertTrue(queryEngine.result instanceof Variant);
        Variant v = (Variant) queryEngine.result;
        Assert.assertEquals("a", v.get("a"));
    }
    @Test
    public void canCreateDefinedType() {
        NewExpression newExpression = new NewExpression(
                Customer.class,
                new Property(new Identifier("customerID"), new Constant("ALFKI")),
                new Property(new Identifier("companyName"), new Constant("Alfred's Futterkiste"))
        );
        queryEngine.visit(newExpression);
        Assert.assertTrue(queryEngine.result instanceof Customer);
        Customer alfki = (Customer) queryEngine.result;
        Assert.assertEquals("ALFKI", alfki.getCustomerID());
        Assert.assertEquals("Alfred's Futterkiste", alfki.getCompanyName());
    }
    @Test
    public void canApplyIndexerToArray() {
        final Integer[] numbers = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Indexer indexer = new Indexer(
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = numbers;
                    }
                },
                new Constant(2)
        );
        queryEngine.visit(indexer);
        Assert.assertEquals(7, queryEngine.result);
    }
    @Test
    public void canApplyIndexerToList() {
        final List<Integer> numbers = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        Indexer indexer = new Indexer(
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = numbers;
                    }
                },
                new Constant(2)
        );
        queryEngine.visit(indexer);
        Assert.assertEquals(7, queryEngine.result);
    }
    @Test
    public void canApplyCharAtUsingIndexer() {
        Indexer indexer = new Indexer(
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = "9876543210";
                    }
                },
                new Constant(2)
        );
        queryEngine.visit(indexer);
        Assert.assertEquals('7', queryEngine.result);
    }
    @Test
    public void fromClauseAddsIdentifierToSourceNameRegistry() {
        FromClause fromClause = new FromClause(new Identifier("id"),
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = new ArrayList();
                    }
                }
        );
        queryEngine.visit(fromClause);
        Assert.assertTrue(queryEngine.sourceNames.contains("id"));
    }
    @Test
    public void fromClauseEvaluatesInnerExperession() {
        final boolean[] innerExpressionEvaluated = new boolean[]{false};
        FromClause fromClause = new FromClause(new Identifier("id"),
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        innerExpressionEvaluated[0] = true;
                        queryEngine.result = new ArrayList();
                    }
                }
        );
        queryEngine.visit(fromClause);
        Assert.assertTrue(innerExpressionEvaluated[0]);
    }
    @Test
    public void fromClauseCreatesSingleTupleForSingleSource() {

        FromClause fromClause = new FromClause(new Identifier("id"),
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = Arrays.asList(1, 2, 3, 4);
                    }
                }
        );
        queryEngine.visit(fromClause);
        Assert.assertEquals(4, queryEngine.tuples.size());
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(1)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(2)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(3)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(4)));
    }
    @Test
    public void fromClauseCreatesScalarProductForMultipleSources() {
        FromClause fromClause1 = new FromClause(new Identifier("id1"),
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = Arrays.asList(1, 2, 3, 4);
                    }
                }
        );
        FromClause fromClause2 = new FromClause(new Identifier("id2"),
                new Expression() {
                    public void accept(ExpressionTreeVisitor visitor) {
                        queryEngine.result = Arrays.asList(5, 6, 7, 8);
                    }
                }
        );
        queryEngine.visit(fromClause1);
        queryEngine.visit(fromClause2);
        Assert.assertEquals(16, queryEngine.tuples.size());
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(1, 5)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(2, 5)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(3, 5)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(4, 5)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(1, 6)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(2, 6)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(3, 6)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(4, 6)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(1, 7)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(2, 7)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(3, 7)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(4, 7)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(1, 8)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(2, 8)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(3, 8)));
        Assert.assertTrue(queryEngine.tuples.contains(Arrays.<Object>asList(4, 8)));
    }
}
