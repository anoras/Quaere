package org.quaere.objects;

import org.quaere.*;
import org.quaere.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Quaere4ObjectsQueryEngine implements ExpressionTreeVisitor, QueryEngine {
    List<String> sourceNames = new ArrayList<String>();
    Map<String, Queryable> rawSources = new HashMap<String, Queryable>();
    Map<String, List<Object>> namedSources = new HashMap<String, List<Object>>();
    List<List<Object>> tuples = new ArrayList<List<Object>>();
    List<Object> currentTuple = null;
    Object result;

    public Quaere4ObjectsQueryEngine() {

    }

    public void addSource(Identifier identifier, Queryable<?> source) {
        rawSources.put(identifier.name, source);
    }

    // --------------------- Interface ExpressionTreeVisitor ---------------------

    public void visit(FromClause expression) {
        addToSourceNames(expression.identifier);
        if (sourceNames.size() == 1) {
            // First iterable
            expression.sourceExpression.accept(this);
            for (Object item : (Iterable) result) {
                List<Object> row = new ArrayList<Object>();
                row.add(item);
                tuples.add(row);
            }
        } else {
            // Create a scalar product
            for (int i = tuples.size() - 1; i >= 0; i--) {
                List<Object> tuple = tuples.get(i);
                currentTuple = tuple;
                expression.sourceExpression.accept(this);
                for (Object item : (Iterable) result) {
                    List<Object> newTuple = new ArrayList<Object>();
                    newTuple.addAll(tuple);
                    newTuple.add(item);
                    tuples.add(newTuple);
                    tuples.remove(tuple);
                }
            }
        }
    }

    public void visit(GroupClause expression) {
        Map<Object, Group> groups = new HashMap<Object, Group>();
        for (List<Object> tuple : tuples) {
            currentTuple = tuple;
            expression.keySelectionExpression.accept(this);
            Object key = result;
            boolean containsKey = false;
            if (expression.keyComparator != null) {
                for (Object currentKey : groups.keySet()) {
                    if (expression.keyComparator.compare(currentKey, key) == 0) {
                        containsKey = true;
                        key = currentKey;
                        break;
                    }
                }
            } else {
                containsKey = groups.containsKey(key);
            }
            if (!containsKey) {
                groups.put(key, new Group(key));
            }
            Statement i = new Statement(Arrays.<Expression>asList(expression.identifier));
            i.accept(this);
            groups.get(key).getGroup().add(result);
        }
        tuples.clear();
        for (Group g : groups.values()) {
            List<Object> row = new ArrayList<Object>();
            row.add(g);
            tuples.add(row);
        }
        result = groups.values();
    }

    public void visit(JoinClause expression) {
        addToSourceNames(expression.identifier);
        Map<Object, List<Object>> interimGroups = new HashMap<Object, List<Object>>();
        for (int i = tuples.size() - 1; i >= 0; i--) {
            List<Object> tuple = tuples.get(i);
            currentTuple = tuple;
            expression.inIndentifier.accept(this);
            // result contains all products.
            for (Object item : (Iterable) result) {
                List<Object> newTuple = new ArrayList<Object>();
                newTuple.addAll(tuple);
                newTuple.add(item);
                currentTuple = newTuple;
                expression.onExpression.accept(this);
                Object left = result; // Left has grouping key
                expression.keyEqualityExpression.accept(this);
                Object right = result;
                if (Comparer.compare(left, right) == 0) {
                    tuples.add(newTuple);
                    if (expression.intoIdentifier != null) {
                        if (!interimGroups.containsKey(left)) {
                            interimGroups.put(left, new ArrayList<Object>());
                        }
                        interimGroups.get(left).add(item);
                    }
                }
                tuples.remove(tuple);
            }
        }
        if (expression.intoIdentifier != null) {
            // TODO: This is broken - FIX IT ANDERS!
            List<Group> groups = Group.fromMap(interimGroups);
            tuples.clear();
            for (Group g : groups) {
                List<Object> row = new ArrayList<Object>();
                row.add(g.getKey());
                row.add(g.getGroup());
                tuples.add(row);
            }
            result = groups;
            addToSourceNames(expression.intoIdentifier);
            this.sourceNames.remove(expression.identifier.toString());
        }
    }

    public void visit(final OrderByClause expression) {
        final Quaere4ObjectsQueryEngine thisRef = this;
        Collections.sort(tuples, new Comparator<List<Object>>() {
            public int compare(List<Object> a, List<Object> b) {
                for (OrderByCriteria criteria : expression.getCriterias()) {
                    currentTuple = a;
                    criteria.getExpression().accept(thisRef);
                    Object xa = result;
                    currentTuple = b;
                    criteria.getExpression().accept(thisRef);
                    Object xb = result;
                    int v = Comparer.compare(xa, xb, criteria.getComparator());
                    if (v != 0) {
                        if (!criteria.getDirection().equals(OrderByCriteria.Direction.ASCENDING)) {
                            return -1 * v;
                        } else {
                            return v;
                        }
                    }
                }
                return 0;
            }
        }
        );
    }

    public void visit(DeclareClause expression) {
        addToSourceNames(expression.variableIdentifier);
        for (int i = tuples.size() - 1; i >= 0; i--) {
            List<Object> tuple = tuples.get(i);
            currentTuple = tuple;
            expression.assignedExpression.accept(this);
            tuple.add(result);
        }
    }

    public void visit(WhereClause expression) {
        List<List<Object>> newTuples = new ArrayList<List<Object>>();
        for (List<Object> tuple : tuples) {
            currentTuple = tuple;
            expression.getExpression().accept(this);
            if ((Boolean) result) {
                newTuples.add(tuple);
            }
        }
        tuples.clear();
        tuples.addAll(newTuples);
    }

    public void visit(SelectClause expression) {
        List<Object> selectedItems = new ArrayList<Object>();
        for (List<Object> tuple : tuples) {
            currentTuple = tuple;
            expression.getExpression().accept(this);
            selectedItems.add(result);
        }
        result = selectedItems;
    }

    public void visit(QueryBody expression) {
        for (QueryBodyClause clause : expression.getClauses()) {
            clause.accept(this);
        }
        if (expression.hasSelectOrGroupClause()) {
            expression.getSelectOrGroupClause().accept(this);
        }
        if (expression.hasContinuation()) {
            expression.getContinuation().accept(this);
        }
    }

    public void visit(QueryContinuation expression) {
        sourceNames.clear();
        addToSourceNames(expression.getIdentifier());
        expression.getQueryBody().accept(this);
    }

    public void visit(QueryExpression expression) {
        if (expression instanceof SubqueryExpression) {
            visit((SubqueryExpression) expression);
        } else {
            expression.getFrom().accept(this);
            expression.getQueryBody().accept(this);
        }
    }
    public void visit(SubqueryExpression expression) {
        Quaere4ObjectsQueryEngine subqueryEngine = new Quaere4ObjectsQueryEngine();
        List<Object> passonTuple = new ArrayList<Object>();
        for (String sourceName : sourceNames) {
            result = null;
            Identifier identifier = new Identifier(sourceName);
            visit(identifier);
            subqueryEngine.sourceNames.add(sourceName);
            passonTuple.add(result);
        }
        subqueryEngine.currentTuple = passonTuple;
        subqueryEngine.tuples.add(passonTuple);

        QueryExpression liftedExpression = new QueryExpression(expression.getFrom(), expression.getQueryBody());
        result = subqueryEngine.evaluate(liftedExpression);
    }

    @SuppressWarnings({"unchecked"})
    public void visit(BinaryExpression expression) {
        Object old = result;
        expression.leftExpression.accept(this);
        Object left = result;
        result = null;
        expression.rightExpression.accept(this);
        Object right = result;
        result = old;
        // TODO: Replace usage of deprecated coerce method with toType.
        switch (expression.operator) {
            case AND:
                result = (Boolean) left && (Boolean) right;
                break;
            case OR:
                result = (Boolean) left || (Boolean) right;
                break;
            case EQUAL:
                if (left == null && right == null) {
                    result = true;
                } else if (left == null) {
                    result = false;
                } else {
                    result = left.equals(right);
                }
                break;
            case NOT_EQUAL:
                if (left == null && right == null) {
                    result = false;
                } else if (left == null) {
                    result = true;
                } else {
                    result = !left.equals(right);
                }
                break;
            case GREATER_THAN:
                if (left == null && right == null) {
                    result = false;
                } else if (left == null) {
                    result = false;
                } else {
                    result = ((Comparable) left).compareTo(right) > 0;
                }
                break;
            case GREATER_THAN_OR_EQUAL:
                if (left == null && right == null) {
                    result = false;
                } else if (left == null) {
                    result = false;
                } else {
                    result = ((Comparable) left).compareTo(right) >= 0;
                }
                break;
            case LESS_THAN:
                if (left == null && right == null) {
                    result = false;
                } else if (left == null) {
                    result = false;
                } else {
                    result = ((Comparable) left).compareTo(right) < 0;
                }
                break;
            case LESS_THAN_OR_EQUAL:
                if (left == null && right == null) {
                    result = false;
                } else if (left == null) {
                    result = false;
                } else {
                    result = ((Comparable) left).compareTo(right) <= 0;
                }
                break;
            case SUBTRACTION:
                result = Convert.toType(
                        Convert.toDouble(left) - Convert.toDouble(right),
                        left.getClass()
                );
                break;
            case ADDITION:
                if ((left instanceof String) || (right instanceof String)) {
                    result = String.valueOf(left) + String.valueOf(right);
                } else {
                    result = Convert.toType(
                            Convert.toDouble(left) + Convert.toDouble(right),
                            left.getClass()
                    );
                }
                break;
            case MULTIPLICATION:
                result = Convert.toType(
                        Convert.toDouble(left) * Convert.toDouble(right),
                        left.getClass());
                break;
            case DIVISION:
                result = Convert.toType(Convert.toDouble(left) / Convert.toDouble(right), left.getClass());
                break;
            case MODULO:
                result = Convert.toType(
                        Convert.toDouble(left) % Convert.toDouble(right),
                        left.getClass()
                );
                break;
            case POW:
                result = Math.pow(Convert.toDouble(left), Convert.toDouble(right));
                break;
        }
    }

    public void visit(TernaryExpression expression) {
        result = null;
        expression.getLeftExpression().accept(this);
        Object left = result;
        if ((Boolean) left) {
            expression.getMiddleExpression().accept(this);
        } else {
            expression.getRightExpression().accept(this);
        }
    }

    public void visit(UnaryExpression expression) {
        expression.expression.accept(this);

        switch (expression.operator) {
            case NOT:
                result = !((Boolean) result);
                break;

            case NEGATE:
                result = Convert.toType(-Convert.toDouble(result), result.getClass());
                break;
        }
    }

    public void visit(Constant expression) {
        result = expression.value;
    }
    public void visit(Identifier expression) {
        if (namedSources.containsKey(expression.name)) {
            result = namedSources.get(expression.name);
            return;
        }
        if (result == null) {
            int index = sourceNames.indexOf(expression.name);
            if (index > -1 && index < currentTuple.size()) {
                result = currentTuple.get(index);
            } else if (index == -1) {
                // Coerce the non-exisiting Identifier to a Constant.
                Constant asConstant = new Constant(expression.name, String.class);
                this.visit(asConstant);
            } else {
                result = null;
            }
        } else {
            Class clazz = result.getClass();
            try {
                Field f = clazz.getDeclaredField(expression.name);
                f.setAccessible(true);
                result = f.get(result);
            }
            catch (NoSuchFieldException e) {
                throw new RuntimeException(String.format("Field %s was not found on %s", e.getMessage(), result.getClass().getName()), e);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void visit(MethodCall expression) {
        if (expression.getLambdaExpression() != null) {
            result = invokeOperator(expression);
            if (result == null) {
                throw new RuntimeException(String.format("Unknown operator: %s", expression.getIdentifier().name));
            }
        } else if (result != null) {
            Object[] parameters = new Object[expression.getParameters().size()];
            Class[] argumentClasses = new Class[parameters.length];
            if (parameters.length > 0) {
                Object oldResult = result;
                for (int j = 0; j < parameters.length; j++) {
                    expression.getParameters().get(j).accept(this);
                    parameters[j] = result;
                }
                result = oldResult;
                for (int j = 0; j < parameters.length; j++) {
                    argumentClasses[j] = parameters[j].getClass();
                }
            }
            Class<? extends Object> clazz = null;
            try {
                clazz = result.getClass();
                String methodName = expression.getIdentifier().name;
                Method method = findMethod(clazz, methodName, argumentClasses);
                result = method.invoke(result, parameters);
            }
            catch (NoSuchMethodException e) {
                result = invokeOperator(expression);
                if (result == null) {
                    throw new RuntimeException(String.format("Method '%s' not found on class '%s'", expression.getIdentifier().name, clazz.getName()));
                }
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Method findMethod(Class<? extends Object> clazz, String methodName, Class[] argumentClasses) throws NoSuchMethodException {
        try {
            return clazz.getMethod(methodName, argumentClasses);
        }
        catch (NoSuchMethodException e) {
            // TODO: Make this work when argumentClasses.length > 1
            if (argumentClasses.length == 1) {
                for (Class i : argumentClasses[0].getInterfaces()) {
                    try {
                        return findMethod(clazz, methodName, new Class[]{i});
                    }
                    catch (Throwable e1) {
                    }
                }
            }
            throw e;
        }

    }

    public void visit(Indexer expression) {
        Object old = result;    // <-- Gets the collection

        expression.getInnerExpression().accept(this);
        if (result != null) {                            // <-- result is element
            if (result.getClass().isArray()) {
                result = Arrays.asList((Object[]) result);
            }
        }
        Object indexed = result;
        result = null;
        expression.getParameter().accept(this);
        if (indexed instanceof List) {
            result = ((List) indexed).get((Integer) result);
        } else if (indexed instanceof CharSequence) {
            result = ((CharSequence) indexed).charAt((Integer) result);
        } else {
            throw new IllegalArgumentException(String.format("Cannot apply indexer to '%s'.", result.getClass().getName()));
        }
    }

    public void visit(Statement expression) {
        result = null;
        for (Expression e : expression.getExpressions()) {
            e.accept(this);
        }
    }

    public void visit(Parameter expression) {
        throw new RuntimeException("Quaere4ObjectsQueryEngine.visit is not implemented");
    }

    public void visit(NewExpression expression) {
        if (expression.getClazz() == null) {
            Variant v = new Variant();
            for (Property p : expression.getProperties()) {
                p.getExpression().accept(this);
                v.add(p.getPropertyName(), result);
            }
            result = v;
        } else {
            try {
                // TODO: Support setter (and ctor) injection
                Class<?> clazz = expression.getClazz();
                Object instance = clazz.newInstance();
                for (Property p : expression.getProperties()) {
                    p.getExpression().accept(this);
                    Field field = clazz.getDeclaredField(p.getPropertyName());
                    field.setAccessible(true);
                    field.set(instance, result);
                }
                result = instance;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // --------------------- Interface QueryEngine ---------------------
    public <T> T evaluate(Expression expression) {
        for (Map.Entry<String, Queryable> sourceEntry : rawSources.entrySet()) {
            List<Object> s = new ArrayList<Object>();
            for (Object elm : sourceEntry.getValue()) {
                s.add(elm);
            }
            namedSources.put(sourceEntry.getKey(), s);
        }
        expression.accept(this);
        return (T) result;
    }

    private Object invokeOperator(MethodCall methodCall) {
        String methodName = methodCall.getIdentifier().name;
        if (methodName.equals("count")) {
            return count(methodCall);
        } else if (methodName.equals("where")) {
            return where(methodCall);
        } else if (methodName.equals("take")) {
            return take(methodCall);
        } else if (methodName.equals("skip")) {
            return skip(methodCall);
        } else if (methodName.equals("takeWhile")) {
            return takeWhile(methodCall);
        } else if (methodName.equals("skipWhile")) {
            return skipWhile(methodCall);
        } else if (methodName.equals("select")) {
            return select(methodCall);
        } else if (methodName.equals("reverse")) {
            return reverse(methodCall);
        } else if (methodName.equals("distinct")) {
            return distinct(methodCall);
        } else if (methodName.equals("union")) {
            return union(methodCall);
        } else if (methodName.equals("intersect")) {
            return intersect(methodCall);
        } else if (methodName.equals("except")) {
            return except(methodCall);
        } else if (methodName.equals("first")) {
            return first(methodCall);
        } else if (methodName.equals("elementAt")) {
            return elementAt(methodCall);
        } else if (methodName.equals("any")) {
            return any(methodCall);
        } else if (methodName.equals("all")) {
            return all(methodCall);
        } else if (methodName.equals("sum")) {
            return sum(methodCall);
        } else if (methodName.equals("min")) {
            return min(methodCall);
        } else if (methodName.equals("max")) {
            return max(methodCall);
        } else if (methodName.equals("average")) {
            return average(methodCall);
        } else if (methodName.equals("aggregate")) {
            return aggregate(methodCall);
        } else {
            return null;
        }
    }

    private int count(MethodCall methodCall) {
        if (methodCall.isNoLambda()) {
            return ((List) result).size();
        }

        return where(methodCall).size();
    }

    private List<Object> where(MethodCall methodCall) {
        if (methodCall.isNoLambda()) {
            throw new IllegalArgumentException("Method calls to the where operator must have a lambda sourceExpression.");
        }
        List<String> oldSourceNames = createTemporarySourceNames(methodCall);
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        for (Object item : (Iterable) result) {
            final Boolean lambdaResult = evaluateLambda(methodCall, item, i, Boolean.class);
            if (lambdaResult) {
                evaluation.add(item);
            }
            i = methodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return evaluation;
    }

    private List<Object> take(MethodCall methodCall) {
        Iterable items = (Iterable) result;
        methodCall.getParameters().get(0).accept(this);
        Integer max = (Integer) result;
        List<Object> selected = new ArrayList<Object>();
        int i = 0;
        for (Object item : items) {
            if (i++ >= max) {
                break;
            }
            selected.add(item);
        }
        return selected;
    }

    private List<Object> skip(MethodCall methodCall) {
        Iterable items = (Iterable) result;
        methodCall.getParameters().get(0).accept(this);
        Integer max = (Integer) result;
        List<Object> selected = new ArrayList<Object>();
        int i = 0;
        for (Object item : items) {
            if (i++ < max) {
                continue;
            }
            selected.add(item);
        }
        return selected;
    }

    private List<Object> takeWhile(MethodCall methodCall) {
        if (methodCall.isNoLambda()) {
            throw new IllegalArgumentException("Expected lambda");
        }
        List<String> oldSourceNames = createTemporarySourceNames(methodCall);
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        for (Object item : (Iterable) result) {
            final Boolean lambdaResult = evaluateLambda(methodCall, item, i, Boolean.class);
            if (lambdaResult) {
                evaluation.add(item);
            } else {
                break;
            }
            i = methodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return evaluation;
    }

    private List<Object> skipWhile(MethodCall methodCall) {
        if (methodCall.isNoLambda()) {
            throw new IllegalArgumentException("Expected lambda");
        }
        List<String> oldSourceNames = createTemporarySourceNames(methodCall);
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        boolean go = false;
        for (Object item : (Iterable) result) {
            if (go) {
                evaluation.add(item);
            } else {
                final Boolean noGo = evaluateLambda(methodCall, item, i, Boolean.class);
                i = methodCall.nextIdentifierIndex(i);
                if (!noGo) {
                    go = true;
                    evaluation.add(item);
                }
            }
        }
        restoreSourceNames(oldSourceNames);
        return evaluation;
    }

    private List<Object> select(MethodCall methodCall) {
        if (methodCall.isNoLambda()) {
            throw new IllegalArgumentException("Expected lambda");
        }
        List<String> oldSourceNames = createTemporarySourceNames(methodCall);
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        for (Object item : (Iterable) result) {
            evaluation.add(evaluateLambda(methodCall, item, i, Object.class));
            i = methodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return evaluation;
    }

    private List<Object> createCurrentTuple(final MethodCall methodCall, int i, final Object item) {
        List<Object> currentTuple = new ArrayList<Object>();
        if (methodCall.hasAnonymousIdentifier()) {
            currentTuple.add(item);
        }
        if (methodCall.hasIndexedIdentifier()) {
            currentTuple.add(i);
        }
        return currentTuple;
    }

    private List<Object> reverse(MethodCall methodCall) {
        List<Object> evaluation = new ArrayList<Object>();
        if (result instanceof List) {
            for (int i = ((List) result).size() - 1; i >= 0; i++) {
                evaluation.add(((List) result).get(i));
            }
        } else {
            throw new RuntimeException("Cannot reverse...");
        }
        return evaluation;
    }

    private List<Object> distinct(MethodCall methodCall) {
        Iterable items = (Iterable) result;
        List<Object> selected = new ArrayList<Object>();
        for (Object item : items) {
            if (!selected.contains(item)) {
                selected.add(item);
            }
        }
        return selected;
    }

    private List<Object> union(MethodCall methodCall) {
        Iterable aIter = (Iterable) result;
        methodCall.getParameters().get(0).accept(this);
        Iterable bIter = (Iterable) result;
        List<Object> selected = new ArrayList<Object>();
        for (Object item : aIter) {
            if (!selected.contains(item)) {
                selected.add(item);
            }
        }
        for (Object item : bIter) {
            if (!selected.contains(item)) {
                selected.add(item);
            }
        }
        return selected;
    }

    private List<Object> intersect(MethodCall methodCall) {
        Iterable aIter = (Iterable) result;
        methodCall.getParameters().get(0).accept(this);
        List bList = (List) result;
        List<Object> selected = new ArrayList<Object>();
        for (Object item : aIter) {
            if (bList.contains(item)) {
                selected.add(item);
            }
        }
        return selected;
    }

    private List<Object> except(MethodCall methodCall) {
        Iterable aIter = (Iterable) result;
        methodCall.getParameters().get(0).accept(this);
        List bList = (List) result;
        List<Object> selected = new ArrayList<Object>();
        for (Object item : aIter) {
            if (!bList.contains(item)) {
                selected.add(item);
            }
        }
        return selected;
    }

    private Object first(MethodCall methodCall) {
        if (methodCall.isNoLambda()) {
            if (((List) result).size() > 0) {
                return ((List) result).get(0);
            } else {
                return null;
            }
        }

        List<String> oldSourceNames = createTemporarySourceNames(methodCall);
        Object evaluation = null;
        int i = 0;
        for (Object item : (Iterable) result) {
            final Boolean lambdaResult = evaluateLambda(methodCall, item, i, Boolean.class);
            if (lambdaResult) {
                evaluation = item;
                break;
            }
            i = methodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return evaluation;
    }

    private Object elementAt(MethodCall methodCall) {
        List listResult = (List) result;
        methodCall.getParameters().get(0).accept(this);
        final Integer index = (Integer) result;
        if (listResult.size() > index) {
            return listResult.get(index);
        } else {
            return null;
        }
    }

    private boolean any(MethodCall anyMethodCall) {
        if (anyMethodCall.isNoLambda()) {
            throw new IllegalArgumentException("Lam");
        }
        List<String> oldSourceNames = createTemporarySourceNames(anyMethodCall);
        int i = 0;
        for (Object item : (Iterable) result) {
            if (evaluateLambda(anyMethodCall, item, i, Boolean.class)) {
                restoreSourceNames(oldSourceNames);
                return true;
            }
            i = anyMethodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return false;
    }

    private boolean all(MethodCall allMethodCall) {
        if (allMethodCall.isNoLambda()) {
            throw new IllegalArgumentException("Lam");
        }
        List<String> oldSourceNames = createTemporarySourceNames(allMethodCall);
        int i = 0;
        for (Object item : (Iterable) result) {
            final Boolean lambdaResult = evaluateLambda(allMethodCall, item, i, Boolean.class);
            if (!lambdaResult) {
                restoreSourceNames(oldSourceNames);
                return false;
            }
            i = allMethodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return true;
    }


    private double min(MethodCall minMethodCall) {
        Double min = Double.MAX_VALUE;
        if (minMethodCall.isNoLambda()) {
            for (Object value : (Iterable) result) {
                min = Math.min(min, (Integer) value);
            }
            return min;
        }
        List<String> oldSourceNames = createTemporarySourceNames(minMethodCall);
        int i = 0;
        for (Object item : (Iterable) result) {
            min = Math.min(min, evaluateLambda(minMethodCall, item, i, Double.class));
            i = minMethodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return min;
    }


    private double max(MethodCall maxMethodCall) {
        Double max = Double.MIN_VALUE;
        if (maxMethodCall.isNoLambda()) {
            for (Object value : (Iterable) result) {
                max = Math.max(max, (Integer) value);
            }
            return max;
        }
        List<String> oldSourceNames = createTemporarySourceNames(maxMethodCall);
        int i = 0;
        for (Object item : (Iterable) result) {
            max = Math.max((Double) max, evaluateLambda(maxMethodCall, item, i, Double.class));
            i = maxMethodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return max;
    }

    private double average(MethodCall avgCall) {
        int count = ((List) result).size();
        double sum = sum(avgCall);
        return sum / count;
    }

    private double sum(MethodCall sumMethodCall) {
        double sum = 0D;
        if (sumMethodCall.isNoLambda()) {
            for (Object value : (Iterable) result) {
                sum += (Double) Convert.toType(value, Double.class);
            }
            return sum;
        }
        List<String> oldSourceNames = createTemporarySourceNames(sumMethodCall);
        int i = 0;
        for (Object item : (Iterable) result) {
            sum += evaluateLambda(sumMethodCall, item, i, Double.class);
            i = sumMethodCall.nextIdentifierIndex(i);
        }
        restoreSourceNames(oldSourceNames);
        return sum;
    }

    public Object aggregate(MethodCall aggregateMethodCall) {
        // NOTE: Index identigier doubles for accumulate identifier...
        List<String> oldSourceNames = createTemporarySourceNames(aggregateMethodCall);

        Iterator resultIter = ((Iterable) result).iterator();
        Object accumulation = resultIter.next();
        while (resultIter.hasNext()) {
            currentTuple = new ArrayList<Object>();
            if (aggregateMethodCall.hasAnonymousIdentifier()) {
                currentTuple.add(resultIter.next());
            }
            if (aggregateMethodCall.hasIndexedIdentifier()) {
                currentTuple.add(accumulation);
            }
            aggregateMethodCall.getLambdaExpression().accept(this);
            final Object lambdaResult = result;
            accumulation = lambdaResult;
        }
        restoreSourceNames(oldSourceNames);
        return accumulation;
    }

    protected void restoreSourceNames(final List<String> oldSourceNames) {
        sourceNames = oldSourceNames;
    }

    private List<String> createTemporarySourceNames(final MethodCall methodCall) {
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>(2);
        if (methodCall.hasAnonymousIdentifier()) {
            addToSourceNames(methodCall.getAnonymousIdentifier());
        }
        if (methodCall.hasIndexedIdentifier()) {
            addToSourceNames(methodCall.getIndexedIdentifier());
        }
        return oldSourceNames;
    }

    protected void addToSourceNames(final Identifier id) {
        sourceNames.add(id.name);
    }

    static String getSourceName(Identifier identifier) {
        if (!identifier.name.startsWith("__src_")) {
            return "__src_" + identifier.name;
        } else {
            return identifier.name;
        }
    }
    protected <T> T evaluateLambda(final MethodCall methodCall, final Object item, final int index, final Class<T> resultType) {
        currentTuple = createCurrentTuple(methodCall, index, item);
        methodCall.getLambdaExpression().accept(this);
        return (T) Convert.toType(result, resultType);
    }
}
