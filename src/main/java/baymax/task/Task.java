/*Class for creating Tasks as Objects*/

package baymax.task;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Getter for description
    public String getDescription() {
        return this.description;
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
    public void markUndone() {
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
