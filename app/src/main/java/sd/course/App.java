package sd.course;

import sd.course.command.Command;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Lexer lexer = new LexerImpl();
        Substitutor substitutor = new SubstitutorImpl();
        Parser parser = new ParserImpl();
        Executor executor = new ExecutorImpl();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            List<Token> tokens = lexer.lex(input);
            tokens = substitutor.substitute(tokens);
            List<Command> commands = parser.parse(tokens);
            String output = executor.execute(commands);
            System.out.println(output + "\n");
        }
    }
}
