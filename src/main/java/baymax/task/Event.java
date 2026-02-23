package baymax.task;

import baymax.*;
import baymax.ui.Ui;

import java.time.LocalDateTime;

public class Event extends Task{

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime time1, LocalDateTime time2) throws BaymaxException {
        super(description);

        //Changing the Task description
        /*Since super() has to be the first line, I am making this change here*/
        String time = "(from : " + time1 + " to : " + time2 + ")";
        this.setDescription(this.getDescription() + " " + time);

        //Prints Message
        Ui.addedInputMessage(this);
    }

    //Constructor used while reading or writing files
    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

}
