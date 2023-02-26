package sd.course;

import java.util.List;

public interface Lexer {
    /**
     * Lexes the input string and returns a list of tokens.
     * @param input the input string
     * @return a list of tokens
     */
    List<Token> lex(String input);
}
