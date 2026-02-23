/*Class to manage all the Commands and operations done on List*/

package baymax.function;

import baymax.*;
import baymax.task.*;
import baymax.data.*;
import baymax.ui.Ui;

public class Commands {

    //Adds new Task to the List
    public static void addTask(Task currTask) {
        TaskData.addTask(currTask);

        /*
        * Any change in list, rewrite the list
        * */
        
        StoreData.writeToFile();
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
        Ui.printMarked(currTask.getDescription());

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
        Ui.printUnmarked(currTask.getDescription());

        /*
         * Any change in list, rewrite the list
         * */
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

        Ui.printDeletedTask(currTask, currTask.getDescription());

        /*
         * Any change in list, rewrite the list
         * */
        StoreData.writeToFile();
    }

}
