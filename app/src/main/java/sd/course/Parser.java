package sd.course;

import sd.course.command.Command;

import java.util.List;

public interface Parser {
    /**
     * Parses the tokens and returns a list of commands.
     * @param tokens the tokens
     * @return a list of commands
     */
    List<Command> parse(List<Token> tokens);
}
