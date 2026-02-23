/*A class just to handle Input Output Data*/

/*
* Only this class has access to the ArrayList<Task> inputList
* */

package baymax.data;

import baymax.task.*;
import java.util.ArrayList;

public class TaskData {

    private static final ArrayList<Task> inputList = new ArrayList<>();

    //Loads Tasks from Hard drive into inputList
    public static void loadTasks() {
        StoreData.readFromFile();
    }

    //Adds task to the list
    public static void addTask(Task currTask) {
        TaskData.inputList.add(currTask);
    }

    //Removes a task with Index Number
    public static void deleteTask(int index) {
        TaskData.inputList.remove(index);
    }

    //Gets a task with index number
    public static Task getTask(int index) {
        return TaskData.inputList.get(index);
    }

    // Returns the total number of tasks
    public static int getTotalTasks() {
        return TaskData.inputList.size();
    }

    //Boolean whether there are any tasks currently or not
    public static boolean hasNoTasks() {
        return TaskData.inputList.isEmpty();
    }

}
