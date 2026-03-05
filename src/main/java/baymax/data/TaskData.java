package baymax.data;

import baymax.task.Task;

import java.util.ArrayList;

/**
 * Manages in-memory list of tasks.
 * Provides operation to manage input task list.
 */
public class TaskData {
    
    private static final ArrayList<Task> allTasks = new ArrayList<>();
    
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
        // ASSERTION (Precondition): We should never attempt to add a non-existent task
        assert currTask != null : "Task to be added cannot be null";
        
        int initialSize = allTasks.size();
        TaskData.allTasks.add(currTask);
        
        // ASSERTION (Postcondition): The list size must increase by exactly 1
        assert allTasks.size() == initialSize + 1 : "Task list size should increase by 1 after adding";
    }
    
    /**
     * Removes a task from the task list based on index.
     *
     * @param index 0-based index of task to be deleted from list.
     */
    public static void deleteTask(int index) {
        // ASSERTION (Precondition): The index must be within the valid bounds of the list
        assert index >= 0 && index < allTasks.size() : "Index out of bounds for deletion: " + index;
        
        int initialSize = allTasks.size();
        TaskData.allTasks.remove(index);
        
        // ASSERTION (Postcondition): The list size must decrease by exactly 1
        assert allTasks.size() == initialSize - 1 : "Task list size should decrease by 1 after deletion";
    }
    
    /**
     * Retrieves a task from list based on index.
     *
     * @param index 0-based index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public static Task getTask(int index) {
        // ASSERTION (Precondition): The index must be within the valid bounds of the list
        assert index >= 0 && index < allTasks.size() : "Index out of bounds for retrieval: " + index;
        
        Task retrievedTask = TaskData.allTasks.get(index);
        
        // ASSERTION (Postcondition): The retrieved task should not be null
        assert retrievedTask != null : "Retrieved task should not be null";
        
        return retrievedTask;
    }
    
    /**
     * Returns the total number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public static int getTotalTasks() {
        // ASSERTION (Class Invariant): The size of the task list should never be negative
        assert allTasks.size() >= 0 : "Task list size cannot be negative";
        return TaskData.allTasks.size();
    }
    
    /**
     * Checks if the task is currently empty.
     *
     * @return True if list has no tasks, false otherwise.
     */
    public static boolean hasNoTasks() {
        return TaskData.allTasks.isEmpty();
    }
}