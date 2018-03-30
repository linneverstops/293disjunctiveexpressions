package lexer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * TungHo Lin
 * txl429
 * PA4
 * Test DisjunctiveLexerTest
 */

public class DisjunctiveLexerTest {

    private DisjunctiveLexer lexer;

    @Before
    public void setUp() throws Exception {
        lexer = new DisjunctiveLexer("");
    }

    @Test
    public void nextValid() throws Exception {
        lexer = new DisjunctiveLexer("(a and b)");
        assertEquals(lexer.nextValid().get().getType(), Token.Type.OPEN);
        assertEquals(lexer.nextValid().get().getType(), Token.Type.ID);
        assertEquals(lexer.nextValid().get().getType(), Token.Type.AND);
        assertEquals(lexer.nextValid().get().getType(), Token.Type.ID);
        assertEquals(lexer.nextValid().get().getType(), Token.Type.CLOSE);
        assertFalse(lexer.nextValid().isPresent()); //If there are no more valid tokens, nextValid will return an empty Optional
    }
}