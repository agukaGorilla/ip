/*lass to manage all the Commands and operations done on List*/

public class Commands {

    public static void markTask(int num) {
        Task currTask = Baymax.inputList.get(num - 1);
        currTask.isDone = true;
        Ui.printMarked(currTask.description);
    }

    public static void unmarkTask(int num) {
        Task currTask = Baymax.inputList.get(num - 1);
        Baymax.inputList.get(num - 1).isDone = false;
        Ui.printUnmarked(currTask.description);
    }
}
