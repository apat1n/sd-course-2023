package sd.course.command;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

public class CommandPwd implements Command {
    public CommandPwd(List<String> args) {
        assert args.isEmpty() : "pwd: too many arguments";
    }
    @Override
    public InputStream apply(InputStream input) {
        String path = Paths.get(".").toAbsolutePath().normalize() + "\n";
        return new ByteArrayInputStream(path.getBytes());
    }
}
