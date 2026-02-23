package baymax.ui;

/*
* Handles everything related to interaction with user
* */

import baymax.BaymaxException;
import baymax.function.Parser;
import baymax.task.Task;
import baymax.data.TaskData;
import baymax.task.ToDo;


import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {

    private static final String horizontalLine =
            "____________________________________________________________________\n";

    private DateTimeFormatter deadlineDateFormat = DateTimeFormatter.ofPattern(" '[Due on ' MMM dd yy ', at ' h:mm a']'");

    //Read Input from user
    public static void readInput() {

        //First Load previous messages
        TaskData.loadTasks();

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit && sc.hasNextLine()) {
            String currInput = sc.nextLine();
            try {
                isExit = Parser.handleInput(currInput);
            } catch (BaymaxException e) {
                Ui.showError(e.getMessage());
            }
        }
    }

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
                + "Now you have " + TaskData.getTotalTasks() + " tasks in the list.\n"
                + Ui.horizontalLine);
    }

    //Prints the Input Array
    public static void printTasks() {
        int index = 1;
        System.out.print(Ui.horizontalLine);
        System.out.println("Here are the tasks in your list :\n");
        for (int i = 0; i < TaskData.getTotalTasks(); i++) {
            Task currTask = TaskData.getTask(i);
            System.out.println(index + ". " + currTask.getStatusIcon() + currTask.getDescription());
            index++;
        }
        System.out.print(Ui.horizontalLine + "\n");
    }

    //Prints the task to user in the form readable
    public static void printTaskUser(Task currTask) {
        if (currTask instanceof ToDo) {
            
        } else if (currTask instanceof ) {
            
        }
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
                + "Now you have " + TaskData.getTotalTasks() + " tasks in the list.\n"
                + Ui.horizontalLine + "\n");
    }
}
