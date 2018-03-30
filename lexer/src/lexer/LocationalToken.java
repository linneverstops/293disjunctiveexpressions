/**
 * TungHo Lin
 * txl429
 * PA2
 * Class LocationalToken
 */
package lexer;

import java.util.Optional;


public final class LocationalToken {
  
  private final Token token;
  
  private int location;
  
  public LocationalToken(Token token, int location) {
    this.token = token;
    this.location = location;
  }
  
  public Token getToken() {
    return token;
  }
  
  public int getLocation() {
    return location;
  }
  
  public Token.Type getType() {
    return token.getType();
  }
  
  public Optional<String> getData() {
    return token.getData();
  }

  @Override
  public String toString() {
    if (getType().hasData())
      return getType() + "(" + getData().get() + ") ";
    else
      return getType() + " ";
  }


}