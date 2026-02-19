/*lass to manage all the Commands and operations done on List*/

public class Commands {

    //Marks Task as Done
    public static void markTask(int num) {
        Task currTask = Baymax.inputList.get(num - 1);
        currTask.isDone = true;
        Ui.printMarked(currTask.description);
    }

    //Unmarks a task previously marked as done
    public static void unmarkTask(int num) {
        Task currTask = Baymax.inputList.get(num - 1);
        Baymax.inputList.get(num - 1).isDone = false;
        Ui.printUnmarked(currTask.description);
    }

    //Deletes a task based on number given
    public static void deleteTask(int num) {
        int index = num - 1;
        Baymax.inputList.remove(index);
    }
}
