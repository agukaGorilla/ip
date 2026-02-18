import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static ArrayList<Task> inputList = new ArrayList<>();

    public static void readInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {

            Task currTask = new Task(sc.nextLine());
            String[] currCommand = currTask.description.split(" ");

            if (Objects.equals(currTask.description, "bye")) {
                break;
            } else if (Objects.equals(currTask.description, "list")) {
                //Lists all Elements in inputList
                Ui.listInput();
            } else if (currCommand.length > 1) {

                //It is a Mark or Unmark Activity
                if (Objects.equals(currCommand[0], "mark")) {
                    Commands.markTask(Integer.parseInt(currCommand[1]));
                } else if(Objects.equals(currCommand[0], "unmark")) {
                    Commands.unmarkTask(Integer.parseInt(currCommand[1]));
                }
            } else {
                //Stores Input in Array and echoes confirmation Message
                inputList.add(currTask);
                System.out.println(Ui.addedInputMessage(currTask.description));
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(Ui.openingMessage());

        Baymax.readInput();

        System.out.println(Ui.closingMessage());
    }
}
