package sd.course.command;

import java.io.InputStream;
import java.util.List;

public class CommandEcho implements Command {
    private final List<String> args;

    public CommandEcho(List<String> args) {
        this.args = args;
        assert this.args.size() == 1: "Echo command takes exactly one argument";
    }

    @Override
    public String execute(InputStream input) {
        return this.args.get(0);
    }
}
