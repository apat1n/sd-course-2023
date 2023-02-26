package sd.course.command;

import java.io.InputStream;

public interface Command {
    /**
     * Executes the command.
     * @param input the input stream of command
     * @return the result of the command
     */
    String execute(InputStream input);
}
