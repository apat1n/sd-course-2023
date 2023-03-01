package sd.course.command;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class CommandWc implements Command {
    private final List<String> args;

    public CommandWc(List<String> args) {
        this.args = args;
    }

    @Override
    public InputStream apply(InputStream input) {
        StringBuilder result = new StringBuilder();
        int curLinesCount, curWordsCount, curBytesCount;

        if (args.isEmpty()) {
            String text;
            try {
                text = IOUtils.toString(input, StandardCharsets.UTF_8);
            } catch (java.io.IOException e) {
                return new ByteArrayInputStream(new byte[0]);
            }
            curLinesCount = text.split("\n").length;
            curWordsCount = text.trim().split("\\s+").length;
            curBytesCount = text.length();
            result
                    .append(curLinesCount).append(" ")
                    .append(curWordsCount).append(" ")
                    .append(curBytesCount).append("\n");
        } else {
            int totalLinesCount = 0;
            int totalWordsCount = 0;
            int totalBytesCount = 0;
            for (String arg : args) {
                try {
                    var text = new String(Files.readAllBytes(Paths.get(arg)));
                    curLinesCount = text.split("\n").length;
                    curWordsCount = text.trim().split("\\s+").length;
                    curBytesCount = text.length();

                    result
                            .append(curLinesCount).append(" ")
                            .append(curWordsCount).append(" ")
                            .append(curBytesCount).append(" ")
                            .append(arg).append("\n");

                    totalLinesCount += curLinesCount;
                    totalWordsCount += curWordsCount;
                    totalBytesCount += curBytesCount;
                } catch (NoSuchFileException e) {
                    result.append("wc: ").append(arg).append(": open: No such file or directory");
                } catch (IOException ignored) {
                }
            }
            if (args.size() > 1) {
                result
                        .append(totalLinesCount).append(" ")
                        .append(totalWordsCount).append(" ")
                        .append(totalBytesCount).append(" total\n");
            }
        }
        return new ByteArrayInputStream(result.toString().getBytes());
    }
}
