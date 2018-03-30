/**
 * TungHo Lin
 * txl429
 * PA2
 * Class Token
 */
package lexer;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public final class Token {

    private final Type type;

    private final Optional<String> data;

    //static Map field
    private static Map<Builder, Token> map = new HashMap<Builder, Token>();

    private Token(Type type, Optional<String> data) {
        this.type = type;
        this.data = data;
    }

    public static void main(String[] args) {
        Token t1 = Token.of(Token.Type.NOT, "not");
        Token t2 = Token.of(Token.Type.ID, "president");
        Token t3 = Token.of(Token.Type.WHITESPACE, " ");
        Token t4 = Token.of(Token.Type.NOT, "not");
        System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println(t3.toString());
        Token t5 = Token.of(Token.Type.OPEN, "(");
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getData() {
        return data;
    }



    //helper method checkPattern
    //check if the input String data has the same pattern as the type
    public static boolean checkPattern(Type type, String data) {
        return Pattern.matches(type.getPattern(), data);
    }

    //"of" method didn't check for the need for ancillary data because Builder method "build" will do the check
    public static Token of(Type type, String data) {
        if(!Token.checkPattern(type, data)) {//checks if input matches the type
            //throw InvalidParameterException if input does not match the type
            throw new InvalidParameterException("Data does not match the type");
        }
        Builder builder = new Builder(type, Optional.ofNullable(data));
        if(map.containsKey(builder)) {  //if token already exists
            return map.get(builder);
        }
        else {
            Token newtoken = builder.build();
            map.put(builder, newtoken);
            return newtoken;
        }
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (getType() != token.getType()) return false;
        return getData() != null ? getData().equals(token.getData()) : token.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }

    //enum Type
    public enum Type {
        NOT("not", false, false, Optional.empty()),
        AND("and", false, true, Optional.of(ParserException.ErrorCode.AND_EXPECTED)),
        OR("or", false, true, Optional.empty()),
        OPEN("\\(", false, false, Optional.of(ParserException.ErrorCode.OPEN_EXPECTED)),
        CLOSE("\\)", false, false, Optional.of(ParserException.ErrorCode.CLOSE_EXPECTED)),
        ID("[a-z]+", true, false, Optional.of(ParserException.ErrorCode.ID_EXPECTED)),
        NUMBER("-?[1-9]\\d*", true, false, Optional.empty()),
        BINARYOP("\\+|-|\\*|/", true, false, Optional.empty()),
        WHITESPACE("\\s+", false, false, Optional.empty());

        private final String pattern;

        private final boolean hasData;

        private final boolean isComplex;

        private Optional<ParserException.ErrorCode> errorCode;

        Type(String pattern, boolean hasData, boolean isComplex, Optional<ParserException.ErrorCode> errorCode) {
            this.pattern = pattern;
            this.hasData = hasData;
            this.isComplex = isComplex;
            this.errorCode = errorCode;
        }

        public String getPattern() {
            return pattern;
        }

        public boolean hasData() {
            return hasData;
        }

        public boolean isComplex() {
            return isComplex;
        }

        public String namedCapturingGroup() {
            return String.format("(?<%s>%s)", name(), pattern);
        }

        public Optional<ParserException.ErrorCode> getErrorCode() {
            return errorCode;
        }

    }

    //Builder static nested class
    static class Builder {

        private final Type type;

        private final Optional<String> builderdata;

        private Builder(Type type, Optional<String> builderdata) {
            this.type = type;
            this.builderdata = builderdata;
        }

        public Type getBuilderType() {
            return type;
        }

        public Optional<String> getBuilderData() {
            return builderdata;
        }

        //return a new Token, the need for ancillary data will be evaluated here
        private Token build() {
            if(type.hasData())
                return new Token(type, builderdata);
            else
                return new Token(type, Optional.empty());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            if (type != builder.type) return false;
            return builderdata != null ? builderdata.equals(builder.builderdata) : builder.builderdata == null;
        }

        @Override
        public int hashCode() {
            int result = type.hashCode();
            result = 31 * result + (builderdata != null ? builderdata.hashCode() : 0);
            return result;
        }
    }
}
  
  
  
    
    