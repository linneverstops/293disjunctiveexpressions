package lexer;

/**
 * TungHo Lin
 * txl429
 * PA5
 * Test IdentifierTest
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IdentifierTest {

    @Test
    public void conjunctiveRepresentation() throws Exception {
        DisjunctiveLexer lexer = new DisjunctiveLexer("a");
        Identifier id = Identifier.Builder.build(lexer.nextValid().get());
        assertEquals(id.conjunctiveRepresentation().getNegation(), false);
        assertEquals(id.conjunctiveRepresentation().getRepresentation(), "a");
    }

}