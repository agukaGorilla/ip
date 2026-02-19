public class Deadline extends Task{

    public String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = "(by : " + date + ")";

        //Changing the description
        this.description = description + " " + this.date;

        System.out.println(Ui.addedInputMessage(this.description, this));
    }
}
