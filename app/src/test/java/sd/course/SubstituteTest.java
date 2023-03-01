package sd.course;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class SubstituteTest {
    @Test
    public void testSubstitution() {
        Substitutor substitutor = new Substitutor();
        List<Token> input = Arrays.asList(
                new Token(Token.Type.WORD, "x"),
                new Token(Token.Type.ASSIGNMENT, "="),
                new Token(Token.Type.WORD, "1"),
                new Token(Token.Type.SUBSTITUTION, "$x+2")
        );
        Assert.assertEquals(
                substitutor.apply(input),
                Arrays.asList(
                        new Token(Token.Type.WORD, "x"),
                        new Token(Token.Type.ASSIGNMENT, "="),
                        new Token(Token.Type.WORD, "1"),
                        new Token(Token.Type.WORD, "1+2")
                )
        );
    }
}
