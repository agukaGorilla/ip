public class ToDo extends Task{

    public ToDo (String description) {
        super(description);

        System.out.println(Ui.addedInputMessage(description, this));
    }
}
