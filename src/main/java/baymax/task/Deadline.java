package baymax.task;

import baymax.BaymaxException;
import baymax.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime dateTime;

    // Constructor used when read from list file
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    // Constructor used while input entered by user
    public Deadline(String description, LocalDateTime dateTime) throws BaymaxException {
        super(description);
        this.dateTime = dateTime;

        //Prints Message
        Ui.addedInputMessage(this);
    }

    // Returns the date as a String
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

}
