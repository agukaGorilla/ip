package baymax.task;

import baymax.*;
import baymax.ui.Ui;

import java.time.LocalDateTime;

public class Event extends Task{

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime time1, LocalDateTime time2) throws BaymaxException {
        super(description);
        this.startTime = time1;
        this.endTime = time2;

        //Prints Message
        Ui.addedInputMessage(this);
    }

    //Constructor used while reading or writing files
    public Event(String description, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //Getters
    public LocalDateTime getStartTime() {
        return this.startTime;
    }
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

}
