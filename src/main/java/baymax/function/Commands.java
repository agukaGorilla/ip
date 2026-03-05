package baymax.function;

import baymax.BaymaxException;
import baymax.task.Task;
import baymax.data.StoreData;
import baymax.data.TaskData;
import baymax.ui.Ui;

import java.time.LocalDate;

/**
 * Manages all commands and operations.
 */
public class Commands {
    
    /**
     * Adds a new task to the list, displays a confirmation, and saves the updated list.
     *
     * @param currTask The task to be added.
     */
    public static void addTask(Task currTask) {
        // ASSERTION (Precondition): The task passed from the parser should never be null
        assert currTask != null : "Cannot add a null task to the task list";
        
        TaskData.addTask(currTask);
        Ui.printAddedMessage(currTask);
        StoreData.writeToFile();
    }
    
    /**
     * Deletes a task from the list based on the provided index.
     *
     * @param index 0-based index of the task to be deleted.
     * @throws BaymaxException If the list is empty or the index is out of bounds.
     */
    public static void deleteTask(int index) throws BaymaxException {
        // ASSERTION (Precondition): Index should not be negative
        assert index >= 0 : "Task index to delete cannot be negative";
        
        checkListNotEmpty("The list is empty. There is no deletion that can be done");
        checkIndexBounds(index);
        
        Task currTask = TaskData.getTask(index);
        
        // ASSERTION: Ensure the task we are about to delete actually exists
        assert currTask != null : "Task to be deleted should not be null";
        
        TaskData.deleteTask(index);
        
        Ui.printDeletedMessage(currTask);
        StoreData.writeToFile();
    }
    
    /**
     * Prompts the UI to print all tasks currently in the list.
     */
    public static void listTasks() {
        Ui.printTasks();
    }
    
    /**
     * Marks a specific task as completed based on the task number provided.
     *
     * @param num 1-based task number as provided by the user.
     * @throws BaymaxException If the list is empty or the task number is invalid.
     */
    public static void markTask(int num) throws BaymaxException {
        // ASSERTION (Precondition): User's 1-based number should be strictly positive
        assert num > 0 : "Task number to mark must be greater than 0";
        
        checkListNotEmpty("The list is empty. There is no task that can be marked");
        checkIndexBounds(num - 1);
        
        Task currTask = TaskData.getTask(num - 1);
        
        // ASSERTION: Ensure the task we are about to mark actually exists
        assert currTask != null : "Task to be marked should not be null";
        
        currTask.markDone();
        Ui.printMarked(currTask);
        StoreData.writeToFile();
    }
    
    /**
     * Unmarks a task as undone based on the task number provided.
     *
     * @param num 1-based index of the task as viewed by the user.
     * @throws BaymaxException If the list is empty or the task number is invalid.
     */
    public static void unmarkTask(int num) throws BaymaxException {
        // ASSERTION (Precondition): User's 1-based number should be strictly positive
        assert num > 0 : "Task number to unmark must be greater than 0";
        
        checkListNotEmpty("The list is empty. There is no task that can be unmarked");
        checkIndexBounds(num - 1);
        
        Task currTask = TaskData.getTask(num - 1);
        
        // ASSERTION: Ensure the task we are about to unmark actually exists
        assert currTask != null : "Task to be unmarked should not be null";
        
        currTask.unmarkDone();
        Ui.printUnmarked(currTask);
        StoreData.writeToFile();
    }
    
    /**
     * Saves data to the hard disk and signals the application to close.
     *
     * @return True to indicate the program should exit.
     */
    public static boolean canCloseProgram() {
        StoreData.writeToFile();
        Ui.printClosingMessage();
        return true;
    }
    
    /**
     * Prompts the UI to print the tasks that occur on the given date.
     *
     * @param date The date to filter tasks by.
     */
    public static void listTasksDate(LocalDate date) {
        // ASSERTION (Precondition): Date object should be valid
        assert date != null : "Date provided for searching tasks cannot be null";
        Ui.printOnDate(date);
    }
    
    /**
     * Searches through the list of tasks and prints those containing the given keyword.
     *
     * @param searchWord The word or phrase to search for.
     */
    public static void searchTasks(String searchWord) {
        // ASSERTION (Precondition): Search string should not be null
        assert searchWord != null : "Search word cannot be null";
        Ui.printSearchTasks(searchWord);
    }
    
    /**
     * Checks if a specific task's description contains the provided search phrase.
     *
     * @param currTask The task being evaluated.
     * @param searchPhrase The phrase being searched for.
     * @return True if the task description contains the phrase, false otherwise.
     */
    public static boolean hasPhrase(Task currTask, String searchPhrase) {
        assert currTask != null : "Task to search within cannot be null";
        assert searchPhrase != null : "Phrase to search for cannot be null";
        
        String currDesc = currTask.getDescription();
        return currDesc.contains(searchPhrase);
    }
    
    /**
     * Verifies that the task list is not empty.
     * Acts as a guard clause to prevent operations on an empty list.
     *
     * @param errorMessage The specific error message to display if the list is empty.
     * @throws BaymaxException If the task list is currently empty.
     */
    private static void checkListNotEmpty(String errorMessage) throws BaymaxException {
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException(errorMessage);
        }
    }
    
    /**
     * Verifies that the provided 0-based index is within the bounds of the task list.
     * Acts as a guard clause to prevent IndexOutOfBounds exceptions.
     *
     * @param index The 0-based index to validate.
     * @throws BaymaxException If the index is less than 0 or greater than the total number of tasks.
     */
    private static void checkIndexBounds(int index) throws BaymaxException {
        if (index < 0 || index >= TaskData.getTotalTasks()) {
            throw new BaymaxException("There is no Task with the number you mentioned. \n" +
                    "Please enter a valid Task number.");
        }
    }
}