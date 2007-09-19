package org.quaere;


public interface Query {
    @SuppressWarnings({"unchecked"})
            <T> T evaluate();
}
