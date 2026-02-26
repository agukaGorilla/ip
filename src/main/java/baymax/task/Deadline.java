package baymax.task;

import baymax.BaymaxException;

import java.time.LocalDateTime;

/**
 * Represents 'Deadline' type of task.
 * Contains description of the task and a deadline date/time to be finished by
 */
public class Deadline extends Task{

    private LocalDateTime dateTime;
    
    /**
     * Constructs a Deadline task based on task description, completion status, deadline date/time
     *
     * @param description The description of the task
     * @param isDone True if completed, false otherwise
     * @param dateTime LocalDateTime variable by which the task is to be completed
     */
    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }
    
    /**
     * Constructor used to create a Deadline task based on task description and deadline date/time.
     * Marked as incomplete by default.
     * This is used when new Deadline tasks are entered by user.
     *
     * @param description The description of the task.
     * @param dateTime LocalDateTime variable by which the task is to be completed.
     */
    public Deadline(String description, LocalDateTime dateTime){
        super(description);
        this.dateTime = dateTime;
        
    }
    
    /**
     * Returns the deadline dat/time by which the task is to be completed.
     *
     * @return LocalDateTime variable deadline of the task.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

}
