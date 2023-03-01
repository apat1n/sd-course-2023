package sd.course.command;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class CommandEcho implements Command {
    private final List<String> args;

    public CommandEcho(List<String> args) {
        this.args = args;
    }

    @Override
    public InputStream apply(InputStream input) {
        return new ByteArrayInputStream((String.join(" ", args)).getBytes());
    }
}
