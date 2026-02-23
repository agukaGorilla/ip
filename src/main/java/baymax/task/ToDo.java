package baymax.task;

import baymax.ui.Ui;

public class ToDo extends Task{

    public ToDo (String description) {
        super(description);

        //Prints Message
        Ui.addedInputMessage(description, this);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
}
