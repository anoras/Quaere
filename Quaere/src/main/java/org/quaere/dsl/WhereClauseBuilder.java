package org.quaere.dsl;


public interface WhereClauseBuilder extends Selectable {
    FromClauseBuilder from(String identifier);
}
