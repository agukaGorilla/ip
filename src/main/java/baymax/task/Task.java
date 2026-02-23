package baymax.task;

/*
* The Tasks to be done by user are instances of this Class
* */

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Getter for description
    public String getDescription() {
        return this.description;
    }

    //Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    //getter for isDone
    public boolean getIsDone() {
        return this.isDone;
    }

    //setters for isDone : mark as done
    public void markDone() {
        this.isDone = true;
    }

    //setter for isDone : mark as undone
    public void unmarkDone() {
        this.isDone = false;
    }

    //Returns the status of checkbox
    public String getStatusIcon() {
        String mark = (isDone ? "X" : " ");

        if (this instanceof ToDo) {
            return "[T][" + mark + "] ";
        }
        else if (this instanceof Deadline) {
            return "[D][" + mark + "] ";
        }
        else if(this instanceof Event) {
            return "[E][" + mark + "] ";
        }
        else {
            return "[ ][ ] ";
        }
    }
}
