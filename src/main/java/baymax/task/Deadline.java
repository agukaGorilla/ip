package baymax.task;

import java.time.LocalDateTime;

/**
 * Represents 'Deadline' type of task.
 * Contains description of the task and a deadline date/time to be finished by.
 */
public class Deadline extends Task {
    
    private LocalDateTime dateTime;
    
    /**
     * Constructs a Deadline task based on task description, completion status, and deadline date/time.
     *
     * @param description The description of the task.
     * @param isDone True if completed, false otherwise.
     * @param dateTime LocalDateTime variable by which the task is to be completed.
     */
    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        assert dateTime != null : "Deadline time cannot be null";
        this.dateTime = dateTime;
    }
    
    /**
     * Constructs a Deadline task based on task description and deadline date/time.
     * Marked as incomplete by default.
     * This is used when new Deadline tasks are entered by user.
     *
     * @param description The description of the task.
     * @param dateTime LocalDateTime variable by which the task is to be completed.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        // Constructor chaining avoids code duplication
        this(description, false, dateTime);
    }
    
    /**
     * Returns the deadline date/time by which the task is to be completed.
     *
     * @return LocalDateTime variable representing the deadline of the task.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}