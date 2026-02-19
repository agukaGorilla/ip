public class Event extends Task{

    public String time;

    public Event(String description, String time) throws BaymaxException {
        super(description);
        String[] times = time.split("/to");

        //Throws and exception if "/to" does not exist
        if (times.length < 2) {
            throw new BaymaxException("Enter a valid '/to' End time. ");
        }

        //Changing the Task description
        /*Since super() has to be the first line, I am making this change here*/
        this.time = "(from : " + times[0] + "to : " + times[1] + ")";
        this.description = this.description + " " + this.time;

        //Prints Message
        Ui.addedInputMessage(this.description, this);
    }
}
