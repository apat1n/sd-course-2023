package sd.course.command;

import java.io.InputStream;

public class CommandExit implements Command {
    @Override
    public InputStream apply(InputStream input) {
        System.exit(0);
        return null;
    }
}
