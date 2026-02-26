package baymax.task;

import baymax.*;

import java.time.LocalDateTime;

/**
 * Represents 'Event' type of task.
 * Consists of a task description, completion status and start and end time.
 */
public class Event extends Task{

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    /**
     * Constructs an Event task based on task description, start time and end time.
     * Marked as incomplete by default.
     * This is used when new event is entered by user.
     *
     * @param description The description of the task
     * @param time1 LocalDateTime representing the start time of the event.
     * @param time2 LocalDateTime representing the end time of the event.
     */
    public Event(String description, LocalDateTime time1, LocalDateTime time2) {
        super(description);
        this.startTime = time1;
        this.endTime = time2;
    }
    
    /**
     * Constructs an Event task based on task description, completion status, start time and end time.
     * This is used when tasks are read from storage files.
     *
     * @param description The description of the task
     * @param startTime LocalDateTime representing the start time of the event.
     * @param endTime LocalDateTime representing the end time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /**
     * Returns the start time of the event.
     *
     * @return LocalDateTime representing the start time.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }
    
    /**
     * Returns the end time of the event.
     *
     * @return LocalDateTime representing the end time.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

}
