package lexer;

import java.util.Optional;

/**
 * TungHo Lin
 * txl429
 * PA5
 * Class CompoundFactor
 */

public class CompoundFactor implements Factor {

    private final DisjunctiveExpression leftExpression;

    private final DisjunctiveExpression rightExpression;

    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(negateToString(leftExpression));
        builder.append(" or ");
        builder.append(negateToString(rightExpression));
        builder.append(")");
        return new ConjunctiveRepresentation(builder.toString(), true);
    }

    private String negateToString(DisjunctiveExpression exp) {
        return exp.negate().conjunctiveRepresentation();
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() +
                " and " + rightExpression.toString() +
                ')';
    }

    public static final class Builder {

        //have to write 2 helper methods and check if the OptionalToken is empty
        public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            ParserException.verify(Token.Type.OPEN, token);
            DisjunctiveExpression leftExp = CompoundFactor.Builder.buildDisjunctiveExpression(lexer);
            checkToken(Token.Type.AND, lexer.nextValid());
            DisjunctiveExpression rightExp = CompoundFactor.Builder.buildDisjunctiveExpression(lexer);
            checkToken(Token.Type.CLOSE, lexer.nextValid());
            return new CompoundFactor(leftExp, rightExp);
        }

        private static DisjunctiveExpression buildDisjunctiveExpression(DisjunctiveLexer lexer) throws ParserException {
            Optional<LocationalToken> firstToken = lexer.nextValid();
            if(!firstToken.isPresent()) {
                throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
            }
            return DisjunctiveExpression.Builder.build(firstToken.get(), lexer);
        }

        private static void checkToken(Token.Type expectedType, Optional<LocationalToken> token) throws ParserException{
            if(!token.isPresent())
                throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
            ParserException.verify(expectedType, token.get());
        }
    }
}
