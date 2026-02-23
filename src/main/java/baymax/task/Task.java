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

    /*
    * Getters and Setters for Task Description
    * */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    * Getters and Setters for isDone
    * */
    public boolean getIsDone() {
        return this.isDone;
    }
    public void markDone() {
        this.isDone = true;
    }
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

    //Returns the status of checkbox
    public TaskType taskType() {

        if (this instanceof ToDo) {
            return TaskType.TODO;
        }
        else if (this instanceof Deadline) {
            return TaskType.DEADLINE;
        }
        else if(this instanceof Event) {
            return TaskType.EVENT;
        }
        else {
            return TaskType.UNKNOWN;
        }
    }
}
