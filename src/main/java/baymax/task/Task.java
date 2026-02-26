package baymax.task;

/**
 * Represents generic type of tasks in task tracker.
 * Acts as a base class for specific task types.
 */
public class Task {
    private String description;
    private boolean isDone;
    
    /**
     * Constructs a generic task with description and completion status.
     * This is used by subclasses when reading from storage files.
     *
     * @param description The description of the task.
     * @param isDone True is completed, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    /**
     * Constructs a task with the task description provided.
     * Marks as incomplete by default.
     * This is used when new tasks are entered by user.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * Returns the description of the task.
     *
     * @return The String describing the task.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Sets the task description to the new description provided.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Returns the completion status of the task.
     *
     * @return True is task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }
    
    /**
     * Marks the referenced task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }
    
    /**
     * Marks the referenced task as incomplete.
     */
    public void unmarkDone() {
        this.isDone = false;
    }
    
    /**
     * Returns the Status icon of the task.
     * The status icon shows the task type as letter, and completion status in user readable form.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        String mark = (isDone ? "X" : " ");

        if (this instanceof ToDo) {
            return "[T][" + mark + "] ";
        } else if (this instanceof Deadline) {
            return "[D][" + mark + "] ";
        } else if(this instanceof Event) {
            return "[E][" + mark + "] ";
        } else {
            return "[ ][ ] ";
        }
    }
    
    /**
     * Returns the subtype of the referenced task.
     *
     * @return The TaskType enum representing the type of the referenced task.
     */
    public TaskType getTaskType() {

        if (this instanceof ToDo) {
            return TaskType.TODO;
        } else if (this instanceof Deadline) {
            return TaskType.DEADLINE;
        } else if(this instanceof Event) {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }
}
