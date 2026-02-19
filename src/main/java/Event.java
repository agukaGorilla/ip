public class Event extends Task{

    public String time;

    public Event(String description, String time) throws BaymaxException {
        super(description);
        String[] times = time.split("/to");

        //Throws and exception if "/to" does not exist
        if (times.length < 2) {
            throw new BaymaxException("Enter a valid '/to' End time. ");
        }

        this.time = "(from : " + times[0] + "to : " + times[1] + ")";

        //Changing the Task description
        this.description = this.description + " " + this.time;

        System.out.println(Ui.addedInputMessage(this.description, this));
    }
}
