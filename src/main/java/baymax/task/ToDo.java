package baymax.task;

/**
 * Represents a todo task type.
 * Only contains the task description and no other details
 */
public class ToDo extends Task{
    
    /**
     * Constructs a todo task type with the description provided.
     * Sets as incomplete by default.
     * This is used when a task is provided by user.
     *
     * @param description The description of the task.
     */
    public ToDo (String description) {
        super(description);
        
    }
    
    /**
     * Constructs a todo task type with the description and completion status.
     * This is used when reading tasks from storage files.
     *
     * @param description The description of the task.
     * @param isDone True if task is completed, false otherwise.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

}
