package baymax.task;

import java.time.LocalDateTime;

/**
 * Represents 'Event' type of task.
 * Consists of a task description, completion status, and start and end time.
 */
public class Event extends Task {
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    /**
     * Constructs an Event task based on task description, completion status, start time, and end time.
     * This is used when tasks are read from storage files.
     *
     * @param description The description of the task.
     * @param isDone True if completed, false otherwise.
     * @param startTime LocalDateTime representing the start time of the event.
     * @param endTime LocalDateTime representing the end time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        assert startTime != null : "Event start time cannot be null";
        assert endTime != null : "Event end time cannot be null";
        assert !startTime.isAfter(endTime) : "Event start time cannot be after its end time";
        
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /**
     * Constructs an Event task based on task description, start time, and end time.
     * Marked as incomplete by default.
     * This is used when a new event is entered by the user.
     *
     * @param description The description of the task.
     * @param startTime LocalDateTime representing the start time of the event.
     * @param endTime LocalDateTime representing the end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        // Constructor chaining avoids code duplication and enforces the time logic assertions
        this(description, false, startTime, endTime);
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