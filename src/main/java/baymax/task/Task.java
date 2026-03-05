package baymax.task;

/**
 * Represents a generic type of task in the task tracker.
 * Acts as a base class for specific task types like ToDo, Deadline, and Event.
 */
public class Task {
    
    private static final String DONE_MARKER = "X";
    private static final String UNDONE_MARKER = " ";
    
    private String description;
    private boolean isDone;
    
    /**
     * Constructs a generic task with a description and completion status.
     * This is used by subclasses when reading tasks from storage files.
     *
     * @param description The description of the task.
     * @param isDone True if completed, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    /**
     * Constructs a task with the task description provided.
     * Marks the task as incomplete by default.
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
     * @return True if the task is completed, false otherwise.
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
     * Returns the status icon of the task.
     * Combines the specific task type letter and completion status in a readable format.
     *
     * @return The formatted status icon string.
     */
    public String getStatusIcon() {
        String completionStatus = isDone ? DONE_MARKER : UNDONE_MARKER;
        String typeLetter = getTypeLetter();
        
        return String.format("[%s][%s] ", typeLetter, completionStatus);
    }
    
    /**
     * Helper method to determine the single-letter identifier for the task type.
     * * @return A one-character string representing the task type.
     */
    private String getTypeLetter() {
        TaskType currentType = getTaskType();
        
        switch (currentType) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return " ";
        }
    }
    
    /**
     * Evaluates the subclass of the referenced task to return its enum representation.
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