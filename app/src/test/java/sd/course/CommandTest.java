package sd.course;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import sd.course.command.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CommandTest {
    @Test public void testCommandEchoEmpty() {
        Command commandEcho = new CommandEcho(Collections.emptyList());
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        InputStream output = commandEcho.execute(inputStream);
        try {
            Assert.assertEquals("", IOUtils.toString(output, StandardCharsets.UTF_8));
        } catch (IOException ex) {
            Assert.assertNull(ex);
        }
    }

    @Test public void testCommandEcho() {
        Command commandEcho = new CommandEcho(List.of("hello", "world"));
        InputStream output = commandEcho.execute(new ByteArrayInputStream("".getBytes()));
        try {
            Assert.assertEquals("hello world", IOUtils.toString(output, StandardCharsets.UTF_8));
        } catch (IOException ex) {
            Assert.assertNull(ex);
        }
    }

    @Test public void testCommandPwd() {
        Command commandEcho = new CommandPwd(Collections.emptyList());
        InputStream output = commandEcho.execute(new ByteArrayInputStream("".getBytes()));
        try {
            String expected = Paths.get(".").toAbsolutePath().normalize() + "\n";
            Assert.assertEquals(expected, IOUtils.toString(output, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void testCommandCat() {
        String text = "Hello World!";
        byte[] buf = text.getBytes();

        Path path;
        try {
            path = Files.createTempFile("temp", ".txt");
            Files.write(path, buf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Command command = new CommandCat(List.of(path.toAbsolutePath().toString()));
        InputStream output = command.execute(new ByteArrayInputStream("".getBytes()));
        try {
            Assert.assertEquals(text, IOUtils.toString(output, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void testCommandWc() {
        String text = "Hello World!\n Hello World!";
        byte[] buf = text.getBytes();

        Path path;
        try {
            path = Files.createTempFile("temp", ".txt");
            Files.write(path, buf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Command command = new CommandWc(List.of(path.toAbsolutePath().toString()));
        InputStream output = command.execute(new ByteArrayInputStream("".getBytes()));
        try {
            String expected = "2 4 26 " + path.toAbsolutePath() + "\n";
            Assert.assertEquals(expected, IOUtils.toString(output, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
