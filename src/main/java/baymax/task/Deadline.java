package baymax.task;

import baymax.BaymaxException;
import baymax.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime thisDate;

    // Constructor used when read from list file
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    // Constructor used while input entered by user
    public Deadline(String description, String date) throws BaymaxException {
        super(description);

        //Changing the description
        /*Since super() has to be the first line, I am making this change here*/
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" '[Due on ' MMM dd yy ', at ' h:mm a']'");

        try {
            this.thisDate = LocalDateTime.parse(date.trim(), inputFormat);
        } catch (DateTimeException e) {
            throw new BaymaxException(
                    "Please enter the due date in this exact format: yyyy-MM-dd HHmm (eg., 2026-02-22 0500)");
        }

        String date1 = this.thisDate.format(outputFormat);
        this.setDescription(description + " " + date1);

        //Prints Message
        Ui.addedInputMessage(this.getDescription(), this);
    }
}
