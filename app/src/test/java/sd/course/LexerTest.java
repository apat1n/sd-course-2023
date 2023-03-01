package sd.course;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class LexerTest {
    @Test
    public void testCorrectness() {
        Lexer lexer = new Lexer();
        String input = " x = 'aba' echo \"$x\" | wc ";
        Assert.assertEquals(
                lexer.apply(input),
                Arrays.asList(
                    new Token(Token.Type.WORD, "x"),
                    new Token(Token.Type.ASSIGNMENT, "="),
                    new Token(Token.Type.SINGLE_QUOTED, "aba"),
                    new Token(Token.Type.WORD, "echo"),
                    new Token(Token.Type.DOUBLE_QUOTED, "$x"),
                    new Token(Token.Type.PIPE, "|"),
                    new Token(Token.Type.WORD, "wc")
                )
        );
    }
}