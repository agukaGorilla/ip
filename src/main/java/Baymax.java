import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static ArrayList<String> inputList = new ArrayList<>();

    public static void readInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {

            String currInput = sc.nextLine();

            if (Objects.equals(currInput, "bye")) {
                break;
            } else if (Objects.equals(currInput, "list")) {
                //Lists all Elements in inputList
                Ui.listInput();
            } else {
                //Stores Input in Array and echoes confirmation Message
                inputList.add(currInput);
                System.out.println(Ui.echo(currInput));
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(Ui.openingMessage());

        Baymax.readInput();

        System.out.println(Ui.closingMessage());
    }
}
