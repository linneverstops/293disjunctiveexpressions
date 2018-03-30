package lexer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.HashSet;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TungHo Lin
 * txl429
 * PA3
 * Class LexerTest
 */

public class LexerTest {

    /* Lexer Objects needed for tests */
    private Lexer empty;
    private Lexer test1;
    private Lexer test2;
    private Lexer test3;
    private Lexer test4;
    private Lexer test5;

    @Before
    public void initialize() {
        empty = new Lexer("");
        test1 = new Lexer("(");
        test2 = new Lexer("123 abc and");
        test3 = new Lexer("(12 or");
        test4 = new Lexer("(a and b) or not c");
        test5 = new Lexer("a and b or c and not d or not e");
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getComplexity() throws ParserException{
        while(test4.hasNext()) /*basically calling generateTokens() on test4 but it is a private helper method*/
            test4.next();
        while(test5.hasNext())
            test5.next();
        assertEquals(test4.getComplexity(), 2);
        assertEquals(test5.getComplexity(), 4);
    }

    @Test
    public void hasNext() throws ParserException{
        assertFalse(empty.hasNext());
        assertTrue(test1.hasNext());
        test1.next();
        assertFalse(test1.hasNext());
    }

    @Test
    public void next() throws ParserException{
        assertEquals(test2.next().getType(), Token.Type.NUMBER);
        assertEquals(test2.next().getType(), Token.Type.WHITESPACE);
        assertEquals(test2.next().getType(), Token.Type.ID);
    }

    @Test
    public void nextValid() throws ParserException{
        exception.expect(ParserException.class);
        HashSet<Token.Type> testValidSet = new HashSet<Token.Type>();
        HashSet<Token.Type> testInvalidSet = new HashSet<Token.Type>();
        testValidSet.add(Token.Type.OPEN);
        testValidSet.add(Token.Type.OR);
        testInvalidSet.add(Token.Type.NUMBER);
        assertEquals(test3.nextValid(testValidSet, testInvalidSet).get().getType(), Token.Type.OPEN);
        assertEquals(test3.nextValid(testValidSet, testInvalidSet).get().getType(), Token.Type.OR); //will encounter ID as an invalid type and throws ParserException
    }
}