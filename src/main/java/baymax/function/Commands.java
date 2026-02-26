package baymax.function;

import baymax.BaymaxException;
import baymax.task.Task;
import baymax.data.StoreData;
import baymax.data.TaskData;
import baymax.ui.Ui;

import java.time.LocalDate;

/**
* Manages all commands and operations.
* */
public class Commands {

    /**
    * Adds a new task to the list and updates the changes.
    *
    * @param currTask The task to be added.
    * */
    public static void addTask(Task currTask) {
        TaskData.addTask(currTask);
        
        // Any change in list, write to Hard Disk
        Ui.printAddedMessage(currTask);
        StoreData.writeToFile();
    }

    /**
    * Deletes a task from the list based on provided index.
    *
    * @param index 0-based index of the task to be deleted in the list.
    * @throws BaymaxException If the list is empty.
    * */
    public static void deleteTask(int index) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no deletion that can be done");
        }

        Task currTask = TaskData.getTask(index);
        TaskData.deleteTask(index);

        Ui.printDeletedMessage(currTask);
        
        // Any change in list, write to Hard Disk
        StoreData.writeToFile();
    }
    
    /**
     * Prompts Ui to print all tasks in the list.
     */
    public static void listTasks() {
        Ui.printTasks();
    }
    
    /**
     * Marks a specific task as completed based on index number provided.
     *
     * @param num 1-based task number as provided by user.
     * @throws BaymaxException If list is empty or index is invalid.
     */
    public static void markTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no task that can be marked");
        } else if (TaskData.getTotalTasks() < num) {
            //If index to be marked is greater than Array List size
            throw new BaymaxException("There is no Task with number you mentioned. \n" +
                    "Please enter a smaller and valid Task number");
        }

        Task currTask = TaskData.getTask(num - 1);
        currTask.markDone();
        Ui.printMarked(currTask);

        // Any change in list, write to Hard Disk
        StoreData.writeToFile();
    }
    
    /**
     * Unmarks a task as undone based on index number provided.
     *
     * @param num 1-based index of task as viewed by user.
     * @throws BaymaxException If list is empty or index is invalid.
     */
    public static void unmarkTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no task that can be unmarked");
        } else if (TaskData.getTotalTasks() < num) {
            //If index to be marked is greater than Array List size
            throw new BaymaxException("There is no Task with number you mentioned. \n" +
                    "Please enter a smaller and valid Task number");
        }

        Task currTask = TaskData.getTask(num - 1);
        currTask.unmarkDone();
        Ui.printUnmarked(currTask);
        
        // Any change in list, write to Hard Disk
        StoreData.writeToFile();
    }
    
    /**
     * Saves data to hard disk and signals the program to close.
     *
     * @return True to indicate program should exit.
     */
    public static boolean canCloseProgram() {
        StoreData.writeToFile();
        return true;
    }
    
    /**
     * Prompts Ui to print the tasks on the given Date.
     *
     * @param date The date to filter tasks by.
     */
    public static void listTasksDate(LocalDate date) {
        Ui.printOnDate(date);
    }
    
    /**
     * Triggers further actions to implement find command
     * 'Find' command searches through the list of tasks.
     * And it prints tasks which contain the given word/phrase in its description.
     *
     * @param searchWord The word to be searched.
     */
    public static void searchTasks(String searchWord) {
        Ui.printSearchTasks(searchWord);
    }
    
    /**
     * Returns boolean representing the status of task description matching the search keyword.
     * Tells whether the task contains the given keyword/phrase.
     *
     * @param currTask The task we are checking.
     * @param searchPhrase The phrase we are finding.
     * @return True if task description contain the phrase, false otherwise.
     */
    public static boolean hasPhrase(Task currTask, String searchPhrase) {
        String currDesc = currTask.getDescription();
        return currDesc.contains(searchPhrase);
    }

}
