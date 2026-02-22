/*Class for creating Tasks as Objects*/

package baymax.task;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
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
