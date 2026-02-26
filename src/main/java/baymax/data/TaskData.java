/*A class just to handle Input Output Data*/

/*
* Only this class has access to the ArrayList<Task> inputList
* */

package baymax.data;

import baymax.task.*;
import java.util.ArrayList;

public class TaskData {

    private static final ArrayList<Task> allTasks = new ArrayList<>();

    //Loads Tasks from Hard drive into inputList
    public static void loadTasks() {
        StoreData.readFromFile();
    }

    //Adds task to the list
    public static void addTask(Task currTask) {
        TaskData.allTasks.add(currTask);
    }

    //Removes a task with Index Number
    public static void deleteTask(int index) {
        TaskData.allTasks.remove(index);
    }

    //Gets a task with index number
    public static Task getTask(int index) {
        return TaskData.allTasks.get(index);
    }

    // Returns the total number of tasks
    public static int getTotalTasks() {
        return TaskData.allTasks.size();
    }

    //Boolean whether there are any tasks currently or not
    public static boolean hasNoTasks() {
        return TaskData.allTasks.isEmpty();
    }

}
