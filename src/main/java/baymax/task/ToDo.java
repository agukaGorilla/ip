package baymax.task;

import baymax.ui.Ui;

public class ToDo extends Task{

    public ToDo (String description) {
        super(description);

        //Prints Message
        Ui.addedInputMessage(description, this);
    }
}
