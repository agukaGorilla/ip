package baymax.task;

public class ToDo extends Task{

    public ToDo (String description) {
        super(description);

        //Prints Message
        Ui.addedInputMessage(description, this);
    }
}
