package sd.course;

import sd.course.command.Command;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lexer lexer = new Lexer();
        Substitutor substitutor = new Substitutor();
        Parser parser = new Parser();
        Executor executor = new Executor();

        var pipeline = lexer.andThen(substitutor).andThen(parser).andThen(executor);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            var output = pipeline.apply(input);
            System.out.println(output);
        }
    }
}
