package sd.course.command;

import java.io.InputStream;

public class CommandExit implements Command {
    @Override
    public InputStream execute(InputStream input) {
        System.exit(0);
        return null;
    }
}
