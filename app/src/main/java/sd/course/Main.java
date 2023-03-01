package sd.course;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var pipeline = new Pipeline();
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            InputStream output = pipeline.apply(input);
            try {
                String text = IOUtils.toString(output, StandardCharsets.UTF_8);
                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
