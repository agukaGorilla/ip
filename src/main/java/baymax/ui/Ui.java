package baymax.ui;

import baymax.BaymaxException;
import baymax.function.Commands;
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
 * Handles all interaction between the user and the application.
 * Responsible for reading user input and formatting messages for the user.
 */
public class Ui {
    
    private static final DateTimeFormatter DEADLINE_DATE_FORMAT =
            DateTimeFormatter.ofPattern("'[Due on ' MMM dd yy ', at ' h:mm a']'");
    
    private static final DateTimeFormatter EVENT_TIME_FORMAT =
            DateTimeFormatter.ofPattern(" 'on' MMM dd yy 'at' h:mm a");
    
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yy");
    
    /**
     * Reads user inputs and triggers parsing functions for the CLI version.
     * Initially loads tasks to the internal list from the hard disk before starting the input loop.
     */
    public static void readInput() {
        TaskData.loadTasks();
        Ui.printOpeningMessage();
        
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
     * Reads user input from the GUI and triggers the parsing function.
     *
     * @param currInput The input string given by the user.
     * @return True if the user issued the exit command, false otherwise.
     */
    public static boolean handleGuiInput(String currInput) {
        try {
            return Parser.handleInput(currInput);
        } catch (BaymaxException e) {
            Ui.showError(e.getMessage());
            return false;
        }
    }
    
    /**
     * Appends the welcome message to the UI buffer.
     */
    public static void printOpeningMessage() {
        UiBuffer.append("""
                 Hello! I'm Baymax
                 It's been so long since I last saw you!!
                 What can I do for you? \n""");
    }
    
    /**
     * Appends the closing message to the UI buffer.
     */
    public static void printClosingMessage() {
        UiBuffer.append("""
                Bye. Hope to see you soon again!
                I must recharge now. \n""");
    }
    
    /**
     * Appends a confirmation message when a task is successfully added.
     *
     * @param currTask The task that was added.
     */
    public static void printAddedMessage(Task currTask) {
        UiBuffer.append("Fire!! I have added this task : \n"
                + Ui.getTaskUserFormat(currTask)+ "\n"
                + "Now you have " + TaskData.getTotalTasks() + " tasks in the list.\n");
    }
    
    /**
     * Iterates through the task list and appends all tasks to the UI buffer.
     */
    public static void printTasks() {
        int index = 1;
        UiBuffer.append("Here are the tasks in your list :\n");
        for (int i = 0; i < TaskData.getTotalTasks(); i++) {
            Task currTask = TaskData.getTask(i);
            UiBuffer.append(index + ". " + Ui.getTaskUserFormat(currTask));
            index++;
        }
    }
    
    /**
     * Prints all tasks that occur on a specific date.
     *
     * @param date The date to filter tasks by.
     */
    public static void printOnDate(LocalDate date) {
        int index = 1;
        UiBuffer.append("Here are the tasks on " + date.format(DATE_FORMAT) + " :\n");
        for (int i = 0; i < TaskData.getTotalTasks(); i++) {
            Task currTask = TaskData.getTask(i);
            if (isTaskOnDate(currTask, date)) {
                UiBuffer.append(index + ". " + Ui.getTaskUserFormat(currTask));
                index++;
            }
        }
    }
    
    /**
     * Helper method to determine if a specific task falls on a given date.
     *
     * @param currTask The task to evaluate.
     * @param date The date to check against.
     * @return True if the task has a date that matches the target date, false otherwise.
     */
    private static boolean isTaskOnDate(Task currTask, LocalDate date) {
        TaskType type = currTask.getTaskType();
        switch (type) {
        case DEADLINE:
            Deadline d = (Deadline) currTask;
            return d.getDateTime().toLocalDate().equals(date);
        case EVENT:
            Event e = (Event) currTask;
            return e.getStartTime().toLocalDate().equals(date);
        default:
            return false;
        }
    }
    
    /**
     * Searches the task list for tasks which contain the given phrase in their description.
     * Appends matching tasks to the UI buffer.
     *
     * @param searchWord The phrase or word to search for.
     */
    public static void printSearchTasks(String searchWord) {
        int index = 1;
        UiBuffer.append(String.format("Here are the tasks which contain the phrase '%s' :\n", searchWord));
        for (int i = 0; i < TaskData.getTotalTasks(); i++) {
            Task currTask = TaskData.getTask(i);
            if (Commands.hasPhrase(currTask, searchWord)) {
                UiBuffer.append(index + ". " + Ui.getTaskUserFormat(currTask));
                index++;
            }
        }
    }
    
    /**
     * Formats a task into a human-readable string based on its specific subclass properties.
     *
     * @param currTask The task to format.
     * @return A formatted string representing the task's status and date details.
     */
    public static String getTaskUserFormat(Task currTask) {
        TaskType currType = currTask.getTaskType();
        
        switch (currType) {
        case TODO:
            return currTask.getStatusIcon() + currTask.getDescription() + "\n";
        case DEADLINE:
            Deadline d = (Deadline) currTask;
            return currTask.getStatusIcon() + currTask.getDescription() +
                    "\n" + d.getDateTime().format(DEADLINE_DATE_FORMAT) + "\n";
        case EVENT:
            Event e = (Event) currTask;
            return currTask.getStatusIcon() + currTask.getDescription() + " \n[Starts" +
                    e.getStartTime().format(EVENT_TIME_FORMAT) + " and ends" +
                    e.getEndTime().format(EVENT_TIME_FORMAT) + "]\n";
        default:
            return "";
        }
    }
    
    /**
     * Displays a message confirming a task has been marked as completed.
     *
     * @param currTask The task that was marked completed.
     */
    public static void printMarked(Task currTask) {
        UiBuffer.append("Gotcha! You have finished the following task!\n");
        UiBuffer.append(Ui.getTaskUserFormat(currTask) + "\n");
    }
    
    /**
     * Displays a message confirming a task has been unmarked (as incomplete).
     *
     * @param currTask The task that was unmarked.
     */
    public static void printUnmarked(Task currTask) {
        UiBuffer.append("Aight. I have unmarked the task. Get on it soon...\n");
        UiBuffer.append(Ui.getTaskUserFormat(currTask) + "\n");
    }
    
    /**
     * Formats and displays standard error messages to the user.
     *
     * @param errorMessage The details of the error message.
     */
    public static void showError(String errorMessage) {
        UiBuffer.append("Ohh NOO!! " + errorMessage + "\n");
    }
    
    /**
     * Displays a confirmation message when a task is successfully deleted.
     *
     * @param currTask The task that was deleted.
     */
    public static void printDeletedMessage(Task currTask) {
        UiBuffer.append("As you wish!! I have deleted this task from inputList \n"
                + Ui.getTaskUserFormat(currTask) + "\n"
                + "Now you have " + TaskData.getTotalTasks() + " tasks in the list.\n");
    }
}