package sd.course;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lexer implements Function<String, List<Token>> {
    /**
     * Lexes the input string and returns a list of result.
     * @param input the input string
     * @return a list of result
     */
    @Override
    public List<Token> apply(String input) {
        List<Token> result = new ArrayList<>();
        for (int i = 0; i < input.length(); ++i) {
            switch (input.charAt(i)) {
                case ' ':
                    break;
                case '\'':
                    var end2 = findFirstOf(input, c -> c == '\'', i + 1);
                    if (end2 == -1) {
                        throw new IllegalArgumentException("can't find closing single quote");
                    }
                    result.add(new Token(Token.Type.SINGLE_QUOTED, input.substring(i + 1, end2)));
                    i = end2;
                    break;
                case '\"':
                    var end3 = findFirstOf(input, c -> c == '\"', i + 1);
                    if (end3 == -1) {
                        throw new IllegalArgumentException("can't find closing double quote");
                    }
                    result.add(new Token(Token.Type.DOUBLE_QUOTED, input.substring(i + 1, end3)));
                    i = end3;
                    break;
                case '|':
                    result.add(new Token(Token.Type.PIPE, "|"));
                    break;
                case '=':
                    result.add(new Token(Token.Type.ASSIGNMENT, "="));
                    break;
                case '$':
                    var end6 = findFirstOf(input, c -> !Character.isLetterOrDigit(c), i + 1);
                    if (i == end6) {
                        throw new IllegalArgumentException("Empty variable name");
                    }
                    result.add(new Token(Token.Type.SUBSTITUTION, input.substring(i + 1, end6)));
                    i = end6 - 1;
                    break;
                default:
                    var end7 = findFirstOf(input, c -> !Character.isLetterOrDigit(c), i);
                    if (i == end7) {
                        throw new IllegalArgumentException("Empty variable name");
                    }
                    result.add(new Token(Token.Type.WORD, input.substring(i, end7)));
                    i = end7 - 1;
                    break;
            }
        }
        return result;
    }

    private static int findFirstOf(String input, Predicate<Character> predicate, int start) {
        for (int i = start; i < input.length(); ++i) {
            if (predicate.test(input.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
