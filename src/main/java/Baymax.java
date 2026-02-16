import java.util.Objects;
import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String  openingMessage = " ____________________________________________________________\n" +
                " Hello! I'm Baymax\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(openingMessage);

        String horizontalLine = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        while (true) {
            String currInput = sc.nextLine();
            if (Objects.equals(currInput, "bye")) {
                break;
            }

            System.out.println(horizontalLine);
            System.out.println(currInput);
            System.out.println(horizontalLine);
        }

        String closingMessage = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
        System.out.println(closingMessage);
    }
}
