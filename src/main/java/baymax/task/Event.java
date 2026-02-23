package baymax.task;

import baymax.*;
import baymax.ui.Ui;

public class Event extends Task{

    public Event(String description, String time1, String time2) throws BaymaxException {
        super(description);

        //Changing the Task description
        /*Since super() has to be the first line, I am making this change here*/
        String time = "(from : " + time1 + "to : " + time2 + ")";
        this.setDescription(this.getDescription() + " " + time);

        //Prints Message
        Ui.addedInputMessage(this.getDescription(), this);
    }
}
