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
    public static String closingMessage() {
        return """
                ____________________________________________________________
                Bye. Hope to see you soon again!
                I must recharge now.
                ____________________________________________________________
                """;
    }

    //Confirms the Input message is added
    public static String addedInputMessage(String message, String type) {
        return Ui.horizontalLine + "Fire!! I have added this task : \n"
                + type + "[ ] " + message + "\n"
                + "Now you have " + Baymax.inputList.size() + " tasks in the list.\n"
                + Ui.horizontalLine + "\n";
    }

    //Prints the Input Array
    public static void listInput() {
        int index = 1;
        System.out.print(Ui.horizontalLine);
        System.out.println("Here are the tasks in your list :\n");
        for (Task currTask : Baymax.inputList) {
            System.out.println(index + ". " + currTask.getStatusIcon(currTask) + currTask.description);
            index++;
        }
        System.out.print(Ui.horizontalLine + "\n");
    }

    //Print the Marked Message
    public static void printMarked(String taskDescription) {
        System.out.println( Ui.horizontalLine + "Gotcha! You have finished the following task!");
        System.out.println("[X] " + taskDescription + "\n" + Ui.horizontalLine + "\n");

    }

    //Print the Unmarked Message
    public static void printUnmarked(String taskDescription) {
        System.out.println( Ui.horizontalLine + "Aight. I have unmarked the task. Get on it soon...");
        System.out.println("[ ] " + taskDescription + "\n" + Ui.horizontalLine + "\n");
    }
}
