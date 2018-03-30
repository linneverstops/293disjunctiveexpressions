package lexer;

/**
 * TungHo Lin
 * txl429
 * PA5
 * Class Identifier
 */

public final class Identifier implements Factor {

    private final String id;

    private Identifier(String id){
        this.id = id;
    }

    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation() {
        return new ConjunctiveRepresentation(id, false);
    }

    @Override
    public String toString() {
        return id;
    }

    /* inner class Builder*/
    public static final class Builder {

        public static final Identifier build(LocationalToken token) throws ParserException {
            ParserException.verify(Token.Type.ID, token);
            return new Identifier(token.getData().get());
        }
    }
}
