/*Handles Printing on CLI and related Logic*/

public class Ui {

    public static final String horizontalLine = "____________________________________________________________________\n";

    public static void openingMessage() {
        System.out.print(Ui.horizontalLine + """
                 Hello! I'm Baymax
                 It's been so long since I last saw you!!
                 What can I do for you? \n""" + Ui.horizontalLine);
    }
    public static void closingMessage() {
        System.out.print(Ui.horizontalLine + """
                Bye. Hope to see you soon again!
                I must recharge now. \n""" + Ui.horizontalLine);
    }

    //Confirms the Input message is added
    public static void addedInputMessage(String message, Task currTask) {
        System.out.println(Ui.horizontalLine + "Fire!! I have added this task : \n"
                + currTask.getStatusIcon() + message + "\n"
                + "Now you have " + Baymax.inputList.size() + " tasks in the list.\n"
                + Ui.horizontalLine);
    }

    //Prints the Input Array
    public static void listInput() {
        int index = 1;
        System.out.print(Ui.horizontalLine);
        System.out.println("Here are the tasks in your list :\n");
        for (Task currTask : Baymax.inputList) {
            System.out.println(index + ". " + currTask.getStatusIcon() + currTask.description);
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

    //Print the exception error message
    public static void showError(String errorMessage) {
        System.out.print(Ui.horizontalLine);
        System.out.println("Ohh NOO!! " + errorMessage);
        System.out.println(Ui.horizontalLine);
    }

    //Print the task Deleted message
    public static void printDeletedTask(Task currTask, String message) {
        System.out.println( Ui.horizontalLine + "As you wish!! I have deleted this task from inputList \n"
                + currTask.getStatusIcon() + message + "\n"
                + "Now you have " + Baymax.inputList.size() + " tasks in the list.\n"
                + Ui.horizontalLine + "\n");
    }
}
