package sd.course;

import sd.course.command.Command;

import java.util.List;
import java.util.function.Function;

public class Parser implements Function<List<Token>, List<Command>> {
    /**
     * Parses the tokens and returns a list of commands.
     * @param tokens the tokens
     * @return a list of commands
     */
    public List<Command> apply(List<Token> tokens) {
        return null;
    }
}
