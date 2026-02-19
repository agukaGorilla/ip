/*Class for creating Tasks as Objects*/

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(Task currTask) {
        String mark = (isDone ? "X" : " ");

        if (currTask instanceof ToDo) {
            return "[T][" + mark + "] ";
        }
        else if (currTask instanceof Deadline) {
            return "[D][" + mark + "] ";
        }
        else if(currTask instanceof Event) {
            return "[E][" + mark + "] ";
        }
        else {
            return "[ ][ ] ";
        }
    }
}
