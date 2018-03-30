package lexer;

/**
 * TungHo Lin
 * txl429
 * PA5
 * Class ConjunctiveRepresentation
 */

public final class ConjunctiveRepresentation {

    private final String representation;

    private final boolean negation;

    public ConjunctiveRepresentation(String representation, boolean negation) {
        this.representation = representation;
        this.negation = negation;
    }

    public final String getRepresentation() {
        return representation;
    }

    public final boolean getNegation() {
        return negation;
    }
}
