package baymax.task;

import baymax.BaymaxException;

import java.time.LocalDateTime;

public class Deadline extends Task{

    private LocalDateTime dateTime;

    // Constructor used when read from list file
    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    // Constructor used while input entered by user
    public Deadline(String description, LocalDateTime dateTime) throws BaymaxException {
        super(description);
        this.dateTime = dateTime;
        
    }

    // Returns the date as a String
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

}
