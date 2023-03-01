package sd.course;

public record Token(sd.course.Token.Type type, String value) {
    public enum Type {
        WORD,
        PIPE,
        SUBSTITUTION,
        SINGLE_QUOTED,
        DOUBLE_QUOTED,
        ASSIGNMENT,
    }
}
