package org.quaere.dsl;


public interface GroupableQueryBodyBuilder extends QueryBodyBuilder {
    GroupClauseBuilder group(String identifier);
    FromClauseBuilder from(String identifier);
}
