package sd.course;

import sd.course.command.Command;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

public class Executor implements Function<List<Command>, InputStream> {
    /**
     * Executes the commands.
     *
     * @param commands the list of commands
     * @return the result of the commands
     */
    public InputStream apply(List<Command> commands) {
        var input = System.in;
        for (var command : commands){
            input = command.apply(input);
        }
        return input;
    }
}
