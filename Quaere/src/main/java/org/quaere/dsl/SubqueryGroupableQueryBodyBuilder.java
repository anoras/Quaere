package org.quaere.dsl;


public interface SubqueryGroupableQueryBodyBuilder extends SubqueryQueryBodyBuilder {
    SubqueryGroupClauseBuilder group(String identifier);
}
