package org.quaere.expressions;

import java.util.UUID;

public class Identifier extends Expression {
    private final String text;

    public Identifier(String text) {
        validateIdentifier(text);
        this.text = text;
    }
    private void validateIdentifier(String text) throws IllegalArgumentException {
        if (text == null || text.equals("")) {
            throw new IllegalArgumentException("The identifier cannot be null or an empty string.");
        }
        if (!Character.isJavaIdentifierStart(text.charAt(0))) {
            throw new IllegalArgumentException("The identifier must start with a Java identifier start character.");
        }
        for (int i = 1; i < text.length(); i++) {
            if (!Character.isJavaIdentifierPart(text.charAt(i))) {
                throw new IllegalArgumentException("The identifier can only contain Java identifier part characters.");
            }
        }
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return text;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
    public static Identifier createUniqueIdentfier() {
        return new Identifier("Id_" + UUID.randomUUID().toString().replace("-", ""));
    }
}
