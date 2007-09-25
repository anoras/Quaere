package org.quaere.expressions;

import java.util.UUID;

public class Identifier extends Expression {
    private final String text;

    public Identifier(String text) {
        this.text = text;
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
