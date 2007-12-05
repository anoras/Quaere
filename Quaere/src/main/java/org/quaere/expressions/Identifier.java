package org.quaere.expressions;

import java.util.UUID;

/**
 * Represents a textual identifier used for variables and other named references.
 */
public class Identifier extends Expression {
    /**
     * Gets the textual identifier.
     */
    public final String name;
    /**
     * Creates a new @see Identifier.
     *
     * @param identifier The name of the identifier.
     * @throws IllegalArgumentException The identifier is not a valid Java identifier.
     */
    public Identifier(String identifier) throws IllegalArgumentException {
        validateIdentifier(identifier);
        this.name = identifier;
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
    /**
     * Gets a textual representation of the @see Identifier.
     *
     * @return A textual representation of the @see Identifier.
     */
    @Override
    public String toString() {
        return name;
    }
    /**
     * Creates a new, uniquely named @see identifier.
     *
     * @return A new, uniquely named @see identifier.
     */
    public static Identifier createUniqueIdentfier() {
        return new Identifier("Id_" + UUID.randomUUID().toString().replace("-", ""));
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }

}
