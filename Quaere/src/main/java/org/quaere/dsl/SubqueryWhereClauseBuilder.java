package org.quaere.dsl;


public interface SubqueryWhereClauseBuilder extends SubquerySelectable {
    SubqueryFromClauseBuilder from(String identifier);
}
