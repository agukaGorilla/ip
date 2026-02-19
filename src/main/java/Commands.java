/*lass to manage all the Commands and operations done on List*/

public class Commands {

    //Marks Task as Done
    public static void markTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (Baymax.inputList.isEmpty()) {
            throw new BaymaxException("The list is empty. There is no task that can be marked");
        }

        Task currTask = Baymax.inputList.get(num - 1);
        currTask.isDone = true;
        Ui.printMarked(currTask.description);
    }

    //Unmarks a task previously marked as done
    public static void unmarkTask(int num) throws BaymaxException {

        //Throws exception if list is empty
        if (Baymax.inputList.isEmpty()) {
            throw new BaymaxException("The list is empty. There is no task that can be unmarked");
        }

        Task currTask = Baymax.inputList.get(num - 1);
        Baymax.inputList.get(num - 1).isDone = false;
        Ui.printUnmarked(currTask.description);
    }

    //Deletes a task based on number given
    public static void deleteTask(int index) throws BaymaxException {

        //Throws exception if list is empty
        if (Baymax.inputList.isEmpty()) {
            throw new BaymaxException("The list is empty. There is no deletion that can be done");
        }

        Task currTask = Baymax.inputList.get(index);
        Baymax.inputList.remove(index);

        Ui.printDeletedTask(currTask, currTask.description);
    }
}
