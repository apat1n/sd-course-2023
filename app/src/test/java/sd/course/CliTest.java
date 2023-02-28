package sd.course;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CliTest {
    @Test public void testAllCli() {
        var pipeline = new Pipeline();
        String input = "echo hello world";
        try (var output = pipeline.apply(input)) {
            byte[] result = output.readAllBytes();
            Assert.assertArrayEquals("hello world".getBytes(), result);
        } catch (IOException e) {
            Assert.fail();
        }
    }
}
