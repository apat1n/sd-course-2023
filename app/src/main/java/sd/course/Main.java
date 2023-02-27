package sd.course;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var pipeline = new Pipeline();
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            var output = pipeline.apply(input);
            System.out.println(output);
        }
    }
}
