package sd.course;

import java.util.List;

public interface Substitutor {
    /**
     * Substitutes variables in the input string with their values.
     * @param input a list of tokens
     * @return a list of tokens
     */
    List<Token> substitute(List<Token> input);
}
