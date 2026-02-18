/*Handles Printing on CLI and related Logic*/

public class Ui {

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

    //Confirms the Input message is added
    public static String addedInputMessage(String message) {
        return Ui.horizontalLine + "added : " + message + "\n" + Ui.horizontalLine + "\n";
    }

    //Prints the Input Array
    public static void listInput() {
        int index = 1;
        System.out.print(Ui.horizontalLine);
        System.out.println("Here are the tasks in your list :\n");
        for (Task currTask : Baymax.inputList) {
            System.out.println(index + ". [" + currTask.getStatusIcon() +"] "+ currTask.description);
            index++;
        }
        System.out.print(Ui.horizontalLine + "\n");
    }

    public static String closingMessage() {
        return """
                ____________________________________________________________
                Bye. Hope to see you soon again!
                I must recharge now.
                ____________________________________________________________
                """;
    }
}
