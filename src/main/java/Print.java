/*Handles Printing and related Logic*/

public class Print {

    public static final String horizontalLine = "____________________________________________________________\n";

    public static String openingMessage() {
        return """
                 ____________________________________________________________
                 Hello! I'm Baymax
                 It's been so long since I last saw you!!
                 What can I do for you?
                ____________________________________________________________
                """;
    }

    public static String echo(String message) {
        return Print.horizontalLine + message + "\n" +Print.horizontalLine + "\n";
    }

    public static String closingMessage() {
        return """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                I must recharge now.
                ____________________________________________________________
                """;
    }
}
