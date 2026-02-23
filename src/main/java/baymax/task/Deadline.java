package baymax.task;

import baymax.ui.Ui;

public class Deadline extends Task{

    public Deadline(String description, String date) {
        super(description);

        //Changing the description
        /*Since super() has to be the first line, I am making this change here*/
        String date1 = "(by : " + date + ")";
        this.description = description + " " + date1;

        //Prints Message
        Ui.addedInputMessage(this.description, this);
    }
}
