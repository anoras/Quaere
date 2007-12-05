package org.quaere.quaere4objects;

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
        sourceNames.add(expression.identifier.name);
        if (sourceNames.size() == 1) {
            // First iterable
            expression.sourceExpression.accept(this);
            for (Object item : (Iterable) result) {
                List<Object> row = new ArrayList<Object>();
                row.add(item);
                tuples.add(row);
            }
        }
        else {
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
            expression.getExpression().accept(this);
            Object key = result;
            boolean containsKey = false;
            if (expression.getComparator() != null) {
                for (Object currentKey : groups.keySet()) {
                    if (expression.getComparator().compare(currentKey, key) == 0) {
                        containsKey = true;
                        key = currentKey;
                        break;
                    }
                }
            }
            else {
                containsKey = groups.containsKey(key);
            }
            if (!containsKey) {
                groups.put(key, new Group(key));
            }
            Statement i = new Statement(Arrays.<Expression>asList(expression.getIdentifier()));
            i.accept(this);
            groups.get(key).getGroup().add(result);
        }
        tuples.clear();
        for (Group g : groups.values()) {
            List<Object> row = new ArrayList<Object>();
            row.add(g);
            tuples.add(row);
        }
    }

    public void visit(JoinClause expression) {
        sourceNames.add(expression.identifier.name);
        for (int i = tuples.size() - 1; i >= 0; i--) {
            List<Object> tuple = tuples.get(i);
            currentTuple = tuple;
            expression.inIndentifier.accept(this);
            for (Object item : (Iterable) result) {
                List<Object> newTuple = new ArrayList<Object>();
                newTuple.addAll(tuple);
                newTuple.add(item);
                currentTuple = newTuple;
                expression.onExpression.accept(this);
                Object left = result;
                expression.keyEqualityExpression.accept(this);
                Object right = result;
                if (Comparer.compare(left, right) == 0) {
                    tuples.add(newTuple);
                }
                tuples.remove(tuple);
            }
        }
        if (expression.intoIdentifier != null) {
            sourceNames.add(expression.intoIdentifier.name);
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
                        }
                        else {
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
        sourceNames.add(expression.variableIdentifier.name);
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
        sourceNames.add(expression.getIdentifier().name);
        expression.getQueryBody().accept(this);
    }

    public void visit(QueryExpression expression) {
        if (expression instanceof SubqueryExpression) {
            visit((SubqueryExpression) expression);
        }
        else {
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
                }
                else if (left == null) {
                    result = false;
                }
                else {
                    result = left.equals(right);
                }
                break;
            case NOT_EQUAL:
                if (left == null && right == null) {
                    result = false;
                }
                else if (left == null) {
                    result = true;
                }
                else {
                    result = !left.equals(right);
                }
                break;
            case GREATER_THAN:
                if (left == null && right == null) {
                    result = false;
                }
                else if (left == null) {
                    result = false;
                }
                else {
                    result = ((Comparable) left).compareTo(right) > 0;
                }
                break;
            case GREATER_THAN_OR_EQUAL:
                if (left == null && right == null) {
                    result = false;
                }
                else if (left == null) {
                    result = false;
                }
                else {
                    result = ((Comparable) left).compareTo(right) >= 0;
                }
                break;
            case LESS_THAN:
                if (left == null && right == null) {
                    result = false;
                }
                else if (left == null) {
                    result = false;
                }
                else {
                    result = ((Comparable) left).compareTo(right) < 0;
                }
                break;
            case LESS_THAN_OR_EQUAL:
                if (left == null && right == null) {
                    result = false;
                }
                else if (left == null) {
                    result = false;
                }
                else {
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
                }
                else {
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
        }
        else {
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
            }
            else if (index == -1) {
                // Coerce the non-exisiting Identifier to a Constant.
                Constant asConstant = new Constant(expression.name, String.class);
                this.visit(asConstant);
            }
            else {
                result = null;
            }
        }
        else {
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
        }
        else if (result != null) {
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
        }
        else if (indexed instanceof CharSequence) {
            result = ((CharSequence) indexed).charAt((Integer) result);
        }
        else {
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
        }
        else {
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
        }
        else if (methodName.equals("where")) {
            return where(methodCall);
        }
        else if (methodName.equals("take")) {
            return take(methodCall);
        }
        else if (methodName.equals("skip")) {
            return skip(methodCall);
        }
        else if (methodName.equals("takeWhile")) {
            return takeWhile(methodCall);
        }
        else if (methodName.equals("skipWhile")) {
            return skipWhile(methodCall);
        }
        else if (methodName.equals("select")) {
            return select(methodCall);
        }
        else if (methodName.equals("reverse")) {
            return reverse(methodCall);
        }
        else if (methodName.equals("distinct")) {
            return distinct(methodCall);
        }
        else if (methodName.equals("union")) {
            return union(methodCall);
        }
        else if (methodName.equals("intersect")) {
            return intersect(methodCall);
        }
        else if (methodName.equals("except")) {
            return except(methodCall);
        }
        else if (methodName.equals("first")) {
            return first(methodCall);
        }
        else if (methodName.equals("elementAt")) {
            return elementAt(methodCall);
        }
        else if (methodName.equals("any")) {
            return any(methodCall);
        }
        else if (methodName.equals("all")) {
            return all(methodCall);
        }
        else if (methodName.equals("sum")) {
            return sum(methodCall);
        }
        else if (methodName.equals("min")) {
            return min(methodCall);
        }
        else if (methodName.equals("max")) {
            return max(methodCall);
        }
        else if (methodName.equals("average")) {
            return average(methodCall);
        }
        else if (methodName.equals("aggregate")) {
            return aggregate(methodCall);
        }
        else {
            return null;
        }
    }

    private int count(MethodCall methodCall) {
        if (methodCall.getLambdaExpression() == null) {
            return ((List) result).size();
        }

        return where(methodCall).size();
    }

    private List<Object> where(MethodCall methodCall) {
        if (methodCall.getLambdaExpression() == null) {
            throw new IllegalArgumentException("Method calls to the where operator must have a lambda sourceExpression.");
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            if ((Boolean) result) {
                evaluation.add(item);
            }
        }
        sourceNames = oldSourceNames;
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
        if (methodCall.getLambdaExpression() == null) {
            throw new IllegalArgumentException("Expected lambda");
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            if ((Boolean) result) {
                evaluation.add(item);
            }
            else {
                break;
            }
        }
        sourceNames = oldSourceNames;
        return evaluation;
    }

    private List<Object> skipWhile(MethodCall methodCall) {
        if (methodCall.getLambdaExpression() == null) {
            throw new IllegalArgumentException("Expected lambda");
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        boolean go = false;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            if (!go) {
                methodCall.getLambdaExpression().accept(this);
                if ((Boolean) result) {
                    continue;
                }
                else {
                    go = true;
                }
            }
            evaluation.add(item);
        }
        sourceNames = oldSourceNames;
        return evaluation;
    }

    private List<Object> select(MethodCall methodCall) {
        if (methodCall.getLambdaExpression() == null) {
            throw new IllegalArgumentException("Expected lambda");
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        List<Object> evaluation = new ArrayList<Object>();
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            evaluation.add(result);
        }
        sourceNames = oldSourceNames;
        return evaluation;
    }

    private List<Object> reverse(MethodCall methodCall) {
        List<Object> evaluation = new ArrayList<Object>();
        if (result instanceof List) {
            for (int i = ((List) result).size() - 1; i >= 0; i++) {
                evaluation.add(((List) result).get(i));
            }
        }
        else {
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
        if (methodCall.getLambdaExpression() == null) {
            if (((List) result).size() > 0) {
                return ((List) result).get(0);
            }
            else {
                return null;
            }
        }

        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        Object evaluation = null;
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            if ((Boolean) result) {
                evaluation = item;
                break;
            }
        }
        sourceNames = oldSourceNames;
        return evaluation;
    }

    private Object elementAt(MethodCall methodCall) {
        Object o = result;
        methodCall.getParameters().get(0).accept(this);
        if (((List) o).size() > (Integer) result) {
            return ((List) o).get((Integer) result);
        }
        else {
            return null;
        }
    }

    private boolean any(MethodCall methodCall) {
        if (methodCall.getLambdaExpression() == null) {
            throw new IllegalArgumentException("Lam");
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            if ((Boolean) result) {
                sourceNames = oldSourceNames;
                return true;
            }
        }
        sourceNames = oldSourceNames;
        return false;
    }

    private boolean all(MethodCall methodCall) {
        if (methodCall.getLambdaExpression() == null) {
            throw new IllegalArgumentException("Lam");
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            if (!(Boolean) result) {
                sourceNames = oldSourceNames;
                return false;
            }
        }
        sourceNames = oldSourceNames;
        return true;
    }

    private double min(MethodCall methodCall) {
        Double min = Double.MAX_VALUE;
        if (methodCall.getLambdaExpression() == null) {
            for (Object value : (Iterable) result) {
                min = Math.min(min, (Integer) value);
            }
            return min;
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            min = Math.min((Double) min, (Double) Convert.toType(result, Double.class));
        }
        sourceNames = oldSourceNames;
        return min;
    }

    private double max(MethodCall methodCall) {
        Double max = Double.MIN_VALUE;
        if (methodCall.getLambdaExpression() == null) {
            for (Object value : (Iterable) result) {
                max = Math.max(max, (Integer) value);
            }
            return max;
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            max = Math.max((Double) max, (Double) Convert.toType(result, Double.class));
        }
        sourceNames = oldSourceNames;
        return max;
    }

    private double average(MethodCall methodCall) {
        int count = ((List) result).size();
        double sum = sum(methodCall);
        return sum / count;
    }

    private double sum(MethodCall methodCall) {
        double sum = 0D;
        if (methodCall.getLambdaExpression() == null) {
            for (Object value : (Iterable) result) {
                sum += (Double) Convert.toType(value, Double.class);
            }
            return sum;
        }
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }
        int i = 0;
        for (Object item : (Iterable) result) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(item);
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(i++);
            }
            methodCall.getLambdaExpression().accept(this);
            sum += (Double) Convert.toType(result, Double.class);
        }
        sourceNames = oldSourceNames;
        return sum;
    }
    public Object aggregate(MethodCall methodCall) {
        List<String> oldSourceNames = sourceNames;
        sourceNames = new ArrayList<String>();
        if (methodCall.getAnonymousIdentifier() != null) {
            sourceNames.add(methodCall.getAnonymousIdentifier().name);
        }
        // NOTE: Index identigier doubles for accumulate identifier...
        if (methodCall.getIndexedIdentifier() != null) {
            sourceNames.add(methodCall.getIndexedIdentifier().name);
        }

        Iterator resultIter = ((Iterable) result).iterator();
        Object accumulation = resultIter.next();
        while (resultIter.hasNext()) {
            currentTuple = new ArrayList<Object>();
            if (methodCall.getAnonymousIdentifier() != null) {
                currentTuple.add(resultIter.next());
            }
            if (methodCall.getIndexedIdentifier() != null) {
                currentTuple.add(accumulation);
            }
            methodCall.getLambdaExpression().accept(this);
            accumulation = result;
        }
        sourceNames = oldSourceNames;
        return accumulation;
    }

    static String getSourceName(Identifier identifier) {
        if (!identifier.name.startsWith("__src_")) {
            return "__src_" + identifier.name;
        }
        else {
            return identifier.name;
        }
    }
}
