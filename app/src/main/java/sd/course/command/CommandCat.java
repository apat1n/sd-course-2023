package sd.course.command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CommandCat implements Command {
    private final List<String> args;

    public CommandCat(List<String> args) {
        this.args = args;
    }

    @Override
    public InputStream execute(InputStream input) {
        StringBuilder result = new StringBuilder();
        for (String arg : args) {
            try {
                result.append(new String(Files.readAllBytes(Paths.get(arg))));
            } catch (IOException e) {
                result.append("cat: ").append(arg).append(": No such file or directory");
            }
        }
        return new ByteArrayInputStream(result.toString().getBytes());
    }
}
