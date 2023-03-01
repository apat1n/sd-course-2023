package sd.course;

import java.util.ArrayList;
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

    private void populateMap(List<Token> input){
        int n = input.size();
        for(int i = 0; i<n; ++i){
            Token currentToken = input.get(i);
            if (currentToken.type() == Token.Type.ASSIGNMENT){
                substituteMap.put(input.get(i - 1).value(), input.get(i + 1).value());
            }
        }
    }

    public List<Token> apply(List<Token> input) {
        populateMap(input);
        List<Token> returnTokens = new ArrayList<>();
        for (Token currentToken : input) {
            if (currentToken.type() == Token.Type.SINGLE_QUOTED) {
                returnTokens.add(new Token(Token.Type.WORD, currentToken.value()));
                continue;
            }
            if (currentToken.type() == Token.Type.DOUBLE_QUOTED) {
                String val = currentToken.value();
                returnTokens.add(new Token(Token.Type.WORD, substitution(val)));
                continue;
            }
            if (currentToken.type() == Token.Type.SUBSTITUTION) {
                returnTokens.add(new Token(Token.Type.WORD, substitution(currentToken.value())));
                continue;
            }
            returnTokens.add(currentToken);
        }
        return returnTokens;
    }

    private String substitution(String value) {
        int len = value.length();
        String res = "";
        for (int i = 0; i < len; ) {
            if (value.charAt(i) != '$') {
                res += value.charAt(i);
                i++;
                continue;
            }
            int j = i + 1;
            String tmp = "";
            while (j < len && Character.isLetterOrDigit(value.charAt(j))) {
                tmp += value.charAt(j);
                j++;
            }
            if (tmp.length() > 0) {
                res += substituteMap.getOrDefault(tmp, "");
            } else {
                res += value.charAt(i);
            }
            i = j;
        }
        return res;
    }
}
