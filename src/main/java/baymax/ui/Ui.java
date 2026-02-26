package baymax.ui;

import baymax.BaymaxException;
import baymax.function.Parser;
import baymax.task.Task;
import baymax.task.Deadline;
import baymax.task.Event;
import baymax.task.TaskType;
import baymax.data.TaskData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles all interaction between user and the application.
 * Responsible for reading user input and displaying all messages to user.
 */
public class Ui {

    private static final String horizontalLine =
            "____________________________________________________________________\n";

    private static final DateTimeFormatter deadlineDateFormat =
            DateTimeFormatter.ofPattern("'[Due on ' MMM dd yy ', at ' h:mm a']'");

    private static final DateTimeFormatter eventTimeFormat =
            DateTimeFormatter.ofPattern(" 'on' MMM dd yy 'at' h:mm a");

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yy");
    
    /**
     * Reads user inputs and triggers parsing functions.
     * Initially loads task to internal list from hard disk before starting input loop.
     */
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
    
    /**
     * Displays the welcome message to user.
     */
    public static void openingMessage() {
        System.out.print(Ui.horizontalLine + """
                 Hello! I'm Baymax
                 It's been so long since I last saw you!!
                 What can I do for you? \n""" + Ui.horizontalLine);
    }
    
    /**
     * Displays the closing message to user.
     */
    public static void closingMessage() {
        System.out.print(Ui.horizontalLine + """
                Bye. Hope to see you soon again!
                I must recharge now. \n""" + Ui.horizontalLine);
    }
    
    /**
     * Displays a confirmation message when a task is added.
     *
     * @param currTask The task that was added.
     */
    public static void addedInputMessage(Task currTask) {
        System.out.println(Ui.horizontalLine + "Fire!! I have added this task : \n"
                + Ui.getTaskUserFormat(currTask)+ "\n"
                + "Now you have " + TaskData.getTotalTasks() + " tasks in the list.\n"
                + Ui.horizontalLine);
    }
    
    /**
     * Prints all tasks in the list.
     */
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
    
    /**
     * Prints all tasks that occur on a specific date.
     *
     * @param date The date to filter tasks by.
     */
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
     * Formats a task to human-readable string.
     *
     * @param currTask The task to format.
     * @return A formatted string representing the task's status and details.
     */
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
    
    /**
     * Displays a message confirming a task has been marked as completed.
     *
     * @param currTask The task that was marked completed.
     */
    public static void printMarked(Task currTask) {
        System.out.println( Ui.horizontalLine + "Gotcha! You have finished the following task!");
        System.out.println(Ui.getTaskUserFormat(currTask) + "\n" + Ui.horizontalLine + "\n");

    }
    
    /**
     * Displays a message confirming a task has been unmarked (as incomplete).
     *
     * @param currTask The task that was unmarked.
     */
    public static void printUnmarked(Task currTask) {
        System.out.println( Ui.horizontalLine + "Aight. I have unmarked the task. Get on it soon...");
        System.out.println(Ui.getTaskUserFormat(currTask) + "\n" + Ui.horizontalLine + "\n");
    }
    
    /**
     * Displays error message to user in standard form.
     *
     * @param errorMessage The details of the error message.
     */
    public static void showError(String errorMessage) {
        System.out.print(Ui.horizontalLine);
        System.out.println("Ohh NOO!! " + errorMessage);
        System.out.println(Ui.horizontalLine);
    }
    
    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param currTask The task that was deleted.
     */
    public static void printDeletedTask(Task currTask) {
        System.out.println( Ui.horizontalLine + "As you wish!! I have deleted this task from inputList \n"
                + Ui.getTaskUserFormat(currTask) + "\n"
                + "Now you have " + TaskData.getTotalTasks() + " tasks in the list.\n"
                + Ui.horizontalLine + "\n");
    }
}
