/**
 * TungHo Lin
 * txl429
 * PA2
 * Token JUnit Tester
 */
package lexer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TungHo Lin
 * txl429
 * PA2
 * Test TokenTest
 */

//Testing the Token class
public class TokenTester {
  
  //checkPattern test
  @Test
  public void testcheckPattern() {
    assertTrue(Token.checkPattern(Token.Type.NOT, "not"));
    assertTrue(Token.checkPattern(Token.Type.AND, "and"));
    assertTrue(Token.checkPattern(Token.Type.OR, "or"));
    assertTrue(Token.checkPattern(Token.Type.OPEN, "("));
    assertTrue(Token.checkPattern(Token.Type.CLOSE, ")"));
    assertTrue(Token.checkPattern(Token.Type.ID, "president"));
    assertTrue(Token.checkPattern(Token.Type.NUMBER, "3346718"));
    assertTrue(Token.checkPattern(Token.Type.NUMBER, "-3346718"));
    assertFalse(Token.checkPattern(Token.Type.NUMBER, "-09"));
    assertTrue(Token.checkPattern(Token.Type.BINARYOP, "+"));
    assertTrue(Token.checkPattern(Token.Type.WHITESPACE, " "));
  }
  
  //equals test
  @Test
  public void testequals() {
    assertTrue(Token.of(Token.Type.AND, "and").equals(Token.of(Token.Type.AND, "and")));
    assertFalse(Token.of(Token.Type.OPEN, "(").equals(Token.of(Token.Type.CLOSE, ")")));
  }

  //toString test 
  @Test
  public void testtoString() {
    assertEquals(Token.of(Token.Type.AND, "and").toString(), "and");
    assertEquals(Token.of(Token.Type.ID, "president").toString(), "president");
    assertEquals(Token.of(Token.Type.WHITESPACE, " ").toString(), " ");
  }
}

