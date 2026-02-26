/*Class to manage all the Commands and operations done on List*/

package baymax.function;

import baymax.*;
import baymax.task.*;
import baymax.data.*;
import baymax.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Commands {

    //Adds new Task to the List
    public static void addTask(Task currTask) {
        TaskData.addTask(currTask);
        /*
        * Any change in list, rewrite the list
        * */
        Ui.addedInputMessage(currTask);
        StoreData.writeToFile();
    }

    //Deletes a task based on number given
    public static void deleteTask(int index) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no deletion that can be done");
        }

        Task currTask = TaskData.getTask(index);
        TaskData.deleteTask(index);

        Ui.printDeletedTask(currTask);

        /*
         * Any change in list, rewrite the list
         * */
        StoreData.writeToFile();
    }

    //List all tasks
    public static void listTasks() {
        Ui.printTasks();
    }

    //Marks Task as Done
    public static void markTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no task that can be marked");
        }
        //If index to be marked is greater than Array List size
        else if (TaskData.getTotalTasks() < num) {
            throw new BaymaxException("There is no Task with number you mentioned. \n" +
                    "Please enter a smaller and valid Task number");
        }

        Task currTask = TaskData.getTask(num - 1);
        currTask.markDone();
        Ui.printMarked(currTask);

        /*
         * Any change in list, rewrite the list
         * */
        StoreData.writeToFile();
    }

    //Unmarks a task previously marked as done
    public static void unmarkTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no task that can be unmarked");
        }
        //If index to be marked is greater than Array List size
        else if (TaskData.getTotalTasks() < num) {
            throw new BaymaxException("There is no Task with number you mentioned. \n" +
                    "Please enter a smaller and valid Task number");
        }

        Task currTask = TaskData.getTask(num - 1);
        currTask.unmarkDone();
        Ui.printUnmarked(currTask);

        /*
         * Any change in list, rewrite the list
         * */
        StoreData.writeToFile();
    }

    //Command to stop running the program
    public static boolean closeProgram() {
        StoreData.writeToFile();
        return true;
    }

    //print tasks on a given date
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
