package sd.course;

import org.junit.Assert;
import org.junit.Test;
import sd.course.command.Command;

import java.util.List;

public class CliTest {
    @Test public void testAllCli() {
        Lexer lexer = new LexerImpl();
        Substitutor substitutor = new SubstitutorImpl();
        Parser parser = new ParserImpl();
        Executor executor = new ExecutorImpl();

        String input = "echo hello world";
        List<Token> tokens = lexer.lex(input);
        tokens = substitutor.substitute(tokens);

        List<Command> commands = parser.parse(tokens);
        String output = executor.execute(commands);
        Assert.assertEquals("hello world", output);
    }
}
