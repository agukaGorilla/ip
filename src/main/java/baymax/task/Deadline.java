package baymax.task;

import baymax.ui.Ui;

public class Deadline extends Task{

    public String date;

    public Deadline(String description, String date) {
        super(description);

        //Changing the description
        /*Since super() has to be the first line, I am making this change here*/
        this.date = "(by : " + date + ")";
        this.description = description + " " + this.date;

        //Prints Message
        Ui.addedInputMessage(this.description, this);
    }
}
