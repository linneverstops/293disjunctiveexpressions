package lexer;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * TungHo Lin
 * txl429
 * PA5
 * Class DisjunctiveExpression
 */

public final class DisjunctiveExpression {

    private final Factor factor;

    private final boolean positive;

    private DisjunctiveExpression(Factor factor, boolean positive) {
        this.factor = factor;
        this.positive = positive;
    }

    public static void main(String[] args) throws ParserException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi! Please input a Boolean expression");
        if(!sc.hasNextLine())
            throw new NoSuchElementException("No input is detected");
        DisjunctiveLexer lexer = new DisjunctiveLexer(sc.nextLine());
        try {
            DisjunctiveExpression exp = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
            StringBuilder builder = new StringBuilder();
            builder.append("The Conjunctive Expression is: ");
            builder.append(exp.conjunctiveRepresentation());
            System.out.println(builder.toString());
        }
        catch(ParserException pe) {
            /*will print a message showing that the input expression is
            incorrect; and then throw the caught exception to show the error code
            and stack trace
             */
            System.err.println("Invalid expression! Please check the input!");
            throw pe;
        }
    }

    public final String conjunctiveRepresentation() {
        StringBuilder builder = new StringBuilder();
        if(isOverallNegative())
            builder.append("not ");
        builder.append(factor.conjunctiveRepresentation().getRepresentation());
        return builder.toString();
    }

    /*private helper method that returns true if the truth values of the
    representation's negate field and the expression's positive
    field have the same truth values (e.g. F F or T T)
     */
    private boolean isOverallNegative() {
        //XNOR gate operations, only return true if the truth values are the same
        return factor.conjunctiveRepresentation().getNegation() == positive;
    }

    @Override
    public String toString() {
        if(positive)
            return factor.toString();
        return "not " + factor.toString();
    }

    public final DisjunctiveExpression negate() {
        return new DisjunctiveExpression(this.factor, !this.positive);
    }

    public static final class Builder {

        public static final DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            if(isPositive(token)) {
                return buildExpression(token, lexer, true);
            }
            LocationalToken firstToken = lexer.nextValid().get();
            return buildExpression(firstToken, lexer, false);
        }

        private static boolean isPositive(LocationalToken token) {
            return !(token.getType() == Token.Type.NOT);
        }

        private static DisjunctiveExpression buildExpression(LocationalToken token, DisjunctiveLexer lexer, boolean positive) throws ParserException {
            if(token.getType() == Token.Type.ID)
                return new DisjunctiveExpression(Identifier.Builder.build(token), positive);
            else
                return new DisjunctiveExpression(CompoundFactor.Builder.build(token, lexer), positive);
        }
    }
}
