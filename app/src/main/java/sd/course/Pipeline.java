package sd.course;

import java.io.InputStream;
import java.util.function.Function;

public class Pipeline implements Function<String, InputStream> {
    Lexer lexer;
    Substitutor substitutor;
    Parser parser;
    Executor executor;

    Pipeline() {
        lexer = new Lexer();
        substitutor = new Substitutor();
        parser = new Parser();
        executor = new Executor();
    }

    @Override
    public InputStream apply(String input) {
        return lexer.andThen(substitutor).andThen(parser).andThen(executor).apply(input);
    }
}