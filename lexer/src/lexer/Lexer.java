package lexer;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TungHo Lin
 * txl429
 * PA3
 * Class Lexer
 */

public class Lexer {

    /* define the tokenPatterns to be the regex of all Types of tokens */
    private static Pattern tokenPatterns = Pattern.compile(tokenRegexes());

    private static String tokenRegexes() {
        return Stream.of(Token.Type.values())
                .map(Token.Type::namedCapturingGroup)
                .collect(Collectors.joining("|"));
    }

    private final Matcher matcher;

    /*will calculate the complexity after the tokens are generated*/
    private int complexity;

    public Lexer(String input) {
        this.matcher = tokenPatterns.matcher(input);
        this.complexity = 0; //McCabe's complexity starts at 0
    }

    public static void main(String[] args) throws ParserException {
        System.out.println(tokenPatterns.pattern());
    }

    /*public method that returns the complexity of the input String*/
    public int getComplexity() {
        return complexity;
    }

    public boolean hasNext() {
        if(matcher.find()) {
            matcher.region(matcher.start(), matcher.regionEnd());  //reset the matcher region
            return true;
        }
        return false;
    }

    public LocationalToken next() throws ParserException {
        if(!(hasNext()))
            throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
        else {
            Token nextToken = makeToken();
            int location = matcher.start();
            return new LocationalToken(nextToken, location);
        }
    }

    /* helper method that creates the next matched Token*/
    private Token makeToken() {
        matcher.find();
        String match = null;
        Token.Type currentType = null;
        for(Token.Type type: Token.Type.values()) {
            match = matcher.group(type.name());
            if(match != null) { //get the type of the matched String
                currentType = type;
                break;
            }
        }
        if(currentType.isComplex()) {  //check if it is AND OR
            this.complexity++;
        }
        Token token = Token.of(currentType, match);
        return token;
    }

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes) throws ParserException {
        while(hasNext()) {
            LocationalToken nextToken = next();
            Token.Type nextTokenType = nextToken.getType();
            if(validTypes.contains(nextTokenType))
                return Optional.of(nextToken);
            else if(invalidTypes.contains(nextTokenType))
                throw new ParserException(nextToken, ParserException.ErrorCode.INVALID_TOKEN);
            else
            /* a token that is neither valid nor invalid,
            the method will silently ignore and keep scanning
            the input for the next token
             */
                ;
        }
        return Optional.empty();
    }
}