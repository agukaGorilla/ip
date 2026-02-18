import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static ArrayList<Task> inputList = new ArrayList<>();

    public static void readInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {

            Task currTask = new Task(sc.nextLine());

            if (Objects.equals(currTask.description, "bye")) {
                break;
            } else if (Objects.equals(currTask.description, "list")) {
                //Lists all Elements in inputList
                Ui.listInput();
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
