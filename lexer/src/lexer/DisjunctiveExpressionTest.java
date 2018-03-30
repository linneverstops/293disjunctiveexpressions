package lexer;

/**
 * TungHo Lin
 * txl429
 * PA5
 * Test DisjunctiveExpressionTest
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DisjunctiveExpressionTest {

    @Test
    public void conjunctiveRepresentation() throws Exception {
        DisjunctiveLexer lexer = new DisjunctiveLexer("(a and b)");
        DisjunctiveExpression exp = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals(exp.conjunctiveRepresentation(), "not (not a or not b)");
        lexer = new DisjunctiveLexer("((a and b) and c)");
        exp = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals(exp.conjunctiveRepresentation(), "not ((not a or not b) or not c)");
    }

}