package org.quaere;

public class IncompleteQueryException extends RuntimeException {
    public IncompleteQueryException(String message) {
        super(message);
    }
}
