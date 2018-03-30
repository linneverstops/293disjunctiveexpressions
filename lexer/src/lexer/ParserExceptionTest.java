package lexer;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * TungHo Lin
 * txl429
 * PA4
 * Test ParserExceptionTest
 */

public class ParserExceptionTest {

    private Lexer lexer;


    @Before
    public void setUp() {
        lexer = new Lexer("or and b)");
    }


    @Test
    public void verify() throws ParserException {
        try {
            ParserException.verify(Token.Type.OPEN, lexer.next());
        }
        catch(ParserException p) {
            assertThat(p.toString(), is("ParserException{serialVersionUID=293, errorCode=OPEN_EXPECTED, location=-1}"));
        }
        try {
            ParserException.verify(Token.Type.ID, lexer.next());
        }
        catch(ParserException p) {
            assertThat(p.toString(), is("ParserException{serialVersionUID=293, errorCode=ID_EXPECTED, location=-1}"));
        }
    }
}