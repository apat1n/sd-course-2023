package sd.course;

import sd.course.command.Command;

import java.util.List;

public interface Executor {
    /**
     * Executes the commands.
     * @param commands the list of commands
     * @return the result of the commands
     */
    String execute(List<Command> commands);
}
