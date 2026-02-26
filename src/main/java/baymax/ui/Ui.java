package baymax.ui;

/*
* Handles everything related to interaction with user
* */

import baymax.BaymaxException;
import baymax.function.Commands;
import baymax.function.Parser;
import baymax.task.*;
import baymax.data.TaskData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {

    private static final String horizontalLine =
            "____________________________________________________________________\n";

    private static final DateTimeFormatter deadlineDateFormat =
            DateTimeFormatter.ofPattern("'[Due on ' MMM dd yy ', at ' h:mm a']'");

    private static final DateTimeFormatter eventTimeFormat =
            DateTimeFormatter.ofPattern(" 'on' MMM dd yy 'at' h:mm a");

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yy");

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
    public static void addedInputMessage(Task currTask) {
        System.out.println(Ui.horizontalLine + "Fire!! I have added this task : \n"
                + Ui.getTaskUserFormat(currTask)+ "\n"
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
            System.out.println(index + ". " + Ui.getTaskUserFormat(currTask));
            index++;
        }
        System.out.print(Ui.horizontalLine + "\n");
    }

    //Prints task on given date
    public static void printOnDate(LocalDate date) {
        int index = 1;
        System.out.println(Ui.horizontalLine + "Here are the tasks on " + date.format(dateFormat) + " :\n");
        for (int i = 0; i < TaskData.getTotalTasks(); i++) {
            Task currTask = TaskData.getTask(i);
            TaskType type = currTask.getTaskType();

            switch (type) {
                case DEADLINE:
                    Deadline d = (Deadline) currTask;
                    if (d.getDateTime().toLocalDate().equals(date)) {
                        System.out.println(index + ". " + Ui.getTaskUserFormat(currTask));
                        index++;
                    }
                    break;
                case EVENT:
                    Event e = (Event) currTask;
                    if (e.getStartTime().toLocalDate().equals(date)) {
                        System.out.println(index + ". " + Ui.getTaskUserFormat(currTask));
                        index++;
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println(Ui.horizontalLine + "\n");
    }
    
    /**
     * Prints tasks which contain given woerd/phrase in task description.
     * Searches the task list for tasks which contain the given phrase in description.
     *
     * @param searchWord The search word.
     */
    public static void printSearchTasks(String searchWord) {
        int index = 1;
        System.out.print(Ui.horizontalLine);
        System.out.printf("Here are the tasks which contain the phrase '%s' :\n%n", searchWord);
        for (int i = 0; i < TaskData.getTotalTasks(); i++) {
            Task currTask = TaskData.getTask(i);
            if (Commands.hasPhrase(currTask, searchWord)) {
                System.out.println(index + ". " + Ui.getTaskUserFormat(currTask));
            }
        }
        System.out.print(Ui.horizontalLine + "\n");
    }

    //Prints the task to user in the form readable
    public static String getTaskUserFormat(Task currTask){
        TaskType currType = currTask.getTaskType();
        String userString = "";

        switch (currType) {
            case TODO :
                userString = currTask.getStatusIcon() + currTask.getDescription() + "\n";
                break;
            case DEADLINE:
                Deadline d = (Deadline) currTask;
                userString = currTask.getStatusIcon() + currTask.getDescription() +
                        "\n" + d.getDateTime().format(deadlineDateFormat) + "\n";
                break;
            case EVENT:
                Event e = (Event) currTask;
                userString = currTask.getStatusIcon() + currTask.getDescription() + " \n[Starts" +
                        e.getStartTime().format(eventTimeFormat) + " and ends" +
                        e.getEndTime().format(eventTimeFormat) + "]\n";
                break;
        }

        return userString;
    }

    //Print the Marked Message
    public static void printMarked(Task currTask) {
        System.out.println( Ui.horizontalLine + "Gotcha! You have finished the following task!");
        System.out.println(Ui.getTaskUserFormat(currTask) + "\n" + Ui.horizontalLine + "\n");

    }

    //Print the Unmarked Message
    public static void printUnmarked(Task currTask) {
        System.out.println( Ui.horizontalLine + "Aight. I have unmarked the task. Get on it soon...");
        System.out.println(Ui.getTaskUserFormat(currTask) + "\n" + Ui.horizontalLine + "\n");
    }

    //Print the exception error message
    public static void showError(String errorMessage) {
        System.out.print(Ui.horizontalLine);
        System.out.println("Ohh NOO!! " + errorMessage);
        System.out.println(Ui.horizontalLine);
    }

    //Print the task Deleted message
    public static void printDeletedTask(Task currTask) {
        System.out.println( Ui.horizontalLine + "As you wish!! I have deleted this task from inputList \n"
                + Ui.getTaskUserFormat(currTask) + "\n"
                + "Now you have " + TaskData.getTotalTasks() + " tasks in the list.\n"
                + Ui.horizontalLine + "\n");
    }
}
