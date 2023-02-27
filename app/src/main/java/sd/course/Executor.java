package sd.course;

import sd.course.command.Command;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

public class Executor implements Function<List<Command>, InputStream> {
    /**
     * Executes the commands.
     * @param commands the list of commands
     * @return the result of the commands
     */
    public InputStream apply(List<Command> commands) {
        return combine(commands).apply(System.in);
    }

    private static Command combine(List<Command> commands) {
        return commands.stream().reduce(
                Command.identity(),
                (Command before, Command after) -> (Command)(before.andThen(after))
        );
    }
}
