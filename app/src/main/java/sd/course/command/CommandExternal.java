package sd.course.command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CommandExternal implements Command {
    private final List<String> args;

    public CommandExternal(List<String> args) {
        this.args = args;
    }

    @Override
    public InputStream apply(InputStream input) {
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        processBuilder.redirectErrorStream(true);
        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            return new ByteArrayInputStream(("Command not found: " + this.args.get(0)).getBytes());
        }

        try {
            process.waitFor();
            return process.getInputStream();
        } catch (InterruptedException ignored) {
            return new ByteArrayInputStream(new byte[0]);
        }
    }
}
