package baymax.task;

public class ToDo extends Task{

    // Constructors used by users
    public ToDo (String description) {
        super(description);
        
    }

    //Constructors used while reading from file
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

}
