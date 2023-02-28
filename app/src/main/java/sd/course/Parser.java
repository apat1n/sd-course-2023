package sd.course;

import sd.course.command.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser implements Function<List<Token>, List<Command>> {
    private static final Map<String, Command> COMMANDS = new HashMap<>(Map.of(
            "cat", new CommandCat(new ArrayList<>()),
            "echo", new CommandEcho(new ArrayList<>()),
            "wc", new CommandWc(new ArrayList<>()),
            "pwd", new CommandPwd(new ArrayList<>()),
            "exit", new CommandExit()
    ));
    /**
     * Parses the tokens and returns a list of commands.
     * @param tokens the tokens
     * @return a list of commands
     */
    public List<Command> apply(List<Token> tokens) {
        List<List<Token>> splitTokens = splitCmds(tokens);
        List<Command> res = new ArrayList<>();
        for (List<Token> words : splitTokens) {
            Token firstToken = words.get(0);
            if (firstToken.type().equals(Token.Type.ASSIGNMENT)) {
                continue;
            }
            Command command;
            if (COMMANDS.containsKey(firstToken.value())) {
                switch (firstToken.value()){
                    case "cat":
                        command = new CommandCat(words.stream().skip(1).map(Token::value).collect(Collectors.toList()));
                        break;
                    case "echo":
                        command = new CommandEcho(words.stream().skip(1).map(Token::value).collect(Collectors.toList()));
                        break;
                    case "wc":
                        command = new CommandWc(words.stream().skip(1).map(Token::value).collect(Collectors.toList()));
                        break;
                    case "pwd":
                        command = new CommandPwd(words.stream().skip(1).map(Token::value).collect(Collectors.toList()));
                        break;
                    case "exit":
                        command = new CommandExit();
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown command");
                }
            } else {
                command = new CommandExternal(words.stream().map(Token::value).collect(Collectors.toList()));
            }
            res.add(command);
        }
        return res;
    }

    private static List<List<Token>> splitCmds(List<Token> lexemes) {
        List<List<Token>> res = new ArrayList<>();
        ArrayList<Token> tmp = new ArrayList<>();
        int i = 0;
        while (i < lexemes.size()) {
            if (lexemes.get(i).type().equals(Token.Type.PIPE)) {
                res.add(tmp);
                tmp = new ArrayList<>();
            } else {
                tmp.add(lexemes.get(i));
            }
            i++;
        }
        if (tmp.size() > 0) {
            res.add(tmp);
        }
        return res;
    }
}
