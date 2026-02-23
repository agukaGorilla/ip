package baymax.task;

import baymax.ui.Ui;

public class ToDo extends Task{

    // Constructors used by users
    public ToDo (String description) {
        super(description);

        //Prints Message
        Ui.addedInputMessage(description, this);
    }

    //Constructors used while reading from file
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

}
