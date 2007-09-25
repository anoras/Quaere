package org.quaere.dsl;

import org.w3c.dom.html.HTMLInputElement;

import java.util.Iterator;

public interface QueryContinuationOrQueryBodyBuilder<R> extends Iterable<R>, QueryBodyBuilder<R> {
    QueryBodyBuilder<R> into(String identifier);

    Iterator<R> iterator();
}