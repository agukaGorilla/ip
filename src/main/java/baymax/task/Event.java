package baymax.task;

public class Event extends Task{

    public String time;

    public Event(String description, String time1, String time2) throws BaymaxException {
        super(description);

        //Changing the Task description
        /*Since super() has to be the first line, I am making this change here*/
        this.time = "(from : " + time1 + "to : " + time2 + ")";
        this.description = this.description + " " + this.time;

        //Prints Message
        Ui.addedInputMessage(this.description, this);
    }
}
