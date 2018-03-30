package lexer;

import java.util.Optional;

/**
 * TungHo Lin
 * txl429
 * PA3, PA4
 * Class ParserException
 */

public final class ParserException extends Exception {

    private static long serialVersionUID = 293L;

    public enum ErrorCode {
        TOKEN_EXPECTED, INVALID_TOKEN, TRAILING_INPUT, AND_EXPECTED, OPEN_EXPECTED, CLOSE_EXPECTED, ID_EXPECTED;
    }

    private final ErrorCode errorCode;

    private final int location;

    public ParserException(LocationalToken token, ErrorCode errorCode) {
        this.location = token.getLocation();
        this.errorCode = errorCode;
    }

    public ParserException(ErrorCode errorCode) {
        this.location = -1;
        this.errorCode = errorCode;
    }

    //toString method override
    @Override
    public String toString() {
        return "ParserException{" +
                "serialVersionUID=" + serialVersionUID +
                ", errorCode=" + errorCode +
                ", location=" + location +
                '}';
    }

    public static void verify(Optional<LocationalToken> token) throws ParserException {
        if(!token.isPresent())
            throw new ParserException(ErrorCode.TOKEN_EXPECTED);
    }

    public static void verifyEnd(Optional<LocationalToken> token) throws ParserException {
        if (token.isPresent())
            throw new ParserException(ErrorCode.TRAILING_INPUT);
    }

    public final static void verify(Token.Type expectedType, LocationalToken token) throws ParserException{
        if(!expectedType.getErrorCode().isPresent())
            throw new ParserException(ErrorCode.INVALID_TOKEN);
        if(expectedType != token.getType())
            throw new ParserException(expectedType.getErrorCode().get());
    }
}
  
  
    