package sd.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public class Substitutor implements UnaryOperator<List<Token>> {
    private static final  Map<String, String> substituteMap = new HashMap<>();
    /**
     * Substitutes variables in the input string with their values.
     * @param input a list of tokens
     * @return a list of tokens
     */
    public List<Token> apply(List<Token> input) {
        return null;
    }
}
