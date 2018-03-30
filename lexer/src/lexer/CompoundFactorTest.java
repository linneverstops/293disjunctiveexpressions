package lexer;

/**
 * TungHo Lin
 * txl429
 * PA5
 * Test CompoundFactorTest
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CompoundFactorTest {

    @Test
    public void conjunctiveRepresentation() throws Exception {
        DisjunctiveLexer lexer = new DisjunctiveLexer("(a and b)");
        CompoundFactor factor = CompoundFactor.Builder.build(lexer.nextValid().get(), lexer);
        assertEquals(factor.conjunctiveRepresentation().getNegation(), true);
        assertEquals(factor.conjunctiveRepresentation().getRepresentation(), "(not a or not b)");
    }

}