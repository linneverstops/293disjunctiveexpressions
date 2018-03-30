package lexer;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * TungHo Lin
 * txl429
 * PA4
 * Class DisjunctiveLexer
 */

public final class DisjunctiveLexer {

    public final static Set<Token.Type> validTypes;
    static {
        validTypes = new HashSet<>();
        validTypes.add(Token.Type.AND);
        validTypes.add(Token.Type.ID);
        validTypes.add(Token.Type.NOT);
        validTypes.add(Token.Type.OPEN);
        validTypes.add(Token.Type.CLOSE);
    }

    public final static Set<Token.Type> invalidTypes;
    static{
        HashSet<Token.Type> allInvalidTypes = new HashSet<>();
        allInvalidTypes.add(Token.Type.OR);
        allInvalidTypes.add(Token.Type.NUMBER);
        allInvalidTypes.add(Token.Type.BINARYOP);
        invalidTypes = allInvalidTypes;
    }

    private Lexer lexer;

    public DisjunctiveLexer(String input) {
        this.lexer = new Lexer(input);
    }

    public Optional<LocationalToken> nextValid() throws ParserException {
        return lexer.nextValid(validTypes, invalidTypes);
    }
}
