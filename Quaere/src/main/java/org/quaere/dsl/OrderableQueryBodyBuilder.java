package org.quaere.dsl;

import org.quaere.Query;
import org.w3c.dom.html.HTMLInputElement;

import java.util.Comparator;


public interface OrderableQueryBodyBuilder extends QueryBodyBuilder {
    OrderByBuilderContinuation orderBy(String identifier);
    OrderByBuilderContinuation orderBy(String expression, Comparator comparator);
    OrderByBuilderContinuation orderByDescending(String expression);
    OrderByBuilderContinuation orderByDescending(String expression, Comparator comparator);

}
