public class Event extends Task{

    public String time;

    public Event(String description, String time) {
        super(description);
        String[] times = time.split("/to");

        this.time = "(from : " + times[0] + "to : " + times[1] + ")";

        //Changing the Task description
        this.description = this.description + " " + this.time;

        System.out.println(Ui.addedInputMessage(this.description, this));
    }
}
