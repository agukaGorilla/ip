/*lass to manage all the Commands and operations done on List*/

public class Commands {

    public static void markTask(int num) {
        Baymax.inputList.get(num - 1).isDone = true;
    }

    public static void unmarkTask(int num) {
        Baymax.inputList.get(num - 1).isDone = false;
    }
}
