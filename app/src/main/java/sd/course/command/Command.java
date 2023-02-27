package sd.course.command;

import java.io.InputStream;
import java.util.function.UnaryOperator;

public interface Command extends UnaryOperator<InputStream> {
    /**
     * Executes the command.
     * @param input the input stream of command
     * @return the result of the command
     */
    InputStream apply(InputStream input);

    static Command identity() {
        return t -> t;
    }
}
