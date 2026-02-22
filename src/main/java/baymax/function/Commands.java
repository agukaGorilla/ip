/*Class to manage all the Commands and operations done on List*/

public class Commands {

    //Marks Task as Done
    public static void markTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no task that can be marked");
        }
        //If index to be marked is greater than Array List size
        else if (TaskData.getTotalTasks() < num) {
            throw new BaymaxException("There is no Task with number you mentioned. \n" +
                    "Please enter a smaller and valid Task number");
        }

        Task currTask = TaskData.getTask(num - 1);
        currTask.isDone = true;
        Ui.printMarked(currTask.description);
    }

    //Unmarks a task previously marked as done
    public static void unmarkTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no task that can be unmarked");
        }
        //If index to be marked is greater than Array List size
        else if (TaskData.getTotalTasks() < num) {
            throw new BaymaxException("There is no Task with number you mentioned. \n" +
                    "Please enter a smaller and valid Task number");
        }

        Task currTask = TaskData.getTask(num - 1);
        TaskData.getTask(num - 1).isDone = false;
        Ui.printUnmarked(currTask.description);
    }

    //Deletes a task based on number given
    public static void deleteTask(int index) throws BaymaxException {

        //Throws exception if list is empty
        if (TaskData.hasNoTasks()) {
            throw new BaymaxException("The list is empty. There is no deletion that can be done");
        }

        Task currTask = TaskData.getTask(index);
        TaskData.removeTask(index);

        Ui.printDeletedTask(currTask, currTask.description);
    }
}
