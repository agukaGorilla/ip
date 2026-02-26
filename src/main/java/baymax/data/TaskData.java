package baymax.data;

import baymax.task.Task;

import java.util.ArrayList;

/**
 * Manages in-memory list of tasks.
 * Provides operation to manage input task list.
 */
public class TaskData {
    
    private static final ArrayList<Task> inputList = new ArrayList<>();
    
    /**
     * Loads tasks from the drive into the internal list.
     */
    public static void loadTasks() {
        StoreData.readFromFile();
    }
    
    /**
     * Adds a task to the task list.
     *
     * @param currTask Task to be added to the list.
     */
    public static void addTask(Task currTask) {
        TaskData.inputList.add(currTask);
    }
    
    /**
     * Removes a task from the task list based on index.
     *
     * @param index 0-based index of task to be deleted from list.
     */
    public static void deleteTask(int index) {
        TaskData.inputList.remove(index);
    }
    
    /**
     * Retrieves a task from list based on index.
     *
     * @param index 0-based index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public static Task getTask(int index) {
        return TaskData.inputList.get(index);
    }
    
    /**
     * Returns the total number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public static int getTotalTasks() {
        return TaskData.inputList.size();
    }
    
    /**
     * Checks if the task is currently empty.
     *
     * @return True if list has no tasks, false otherwise.
     */
    public static boolean hasNoTasks() {
        return TaskData.inputList.isEmpty();
    }
    
}
