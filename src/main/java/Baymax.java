import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static ArrayList<Task> inputList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(Ui.openingMessage());

        try {
            Parser.handleInput();
        } catch (BaymaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Ui.closingMessage());
    }
}
