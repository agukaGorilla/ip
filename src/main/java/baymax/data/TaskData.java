/*A class just to handle Input Output Data*/

import java.util.ArrayList;

public class TaskData {

    private static final ArrayList<Task> inputList = new ArrayList<>();

    //Adds task to the list
    public static void addTask(Task currTask) {
        TaskData.inputList.add(currTask);
    }

    // Returns the total number of tasks
    public static int getTotalTasks() {
        return TaskData.inputList.size();
    }

    //Boolean whether there are any tasks currently or not
    public static boolean hasNoTasks() {
        return TaskData.inputList.isEmpty();
    }

    //Gets a task with index number
    public static Task getTask(int index) {
        return TaskData.inputList.get(index);
    }

    //Removes a task with Index Number
    public static void removeTask(int index) {
        TaskData.inputList.remove(index);
    }

}
