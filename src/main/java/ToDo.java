public class ToDo extends Task{

    public ToDo (String description) {
        super(description);

        Ui.addedInputMessage(description, this);
    }
}
