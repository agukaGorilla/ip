import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static ArrayList<Task> inputList = new ArrayList<>();

    public static void main(String[] args) {

        //Opening Message
        Ui.openingMessage();

        //Handles all th commands
        try {
            Parser.handleInput();
        } catch (BaymaxException e) {
            Ui.showError(e.getMessage());
        }

        //Closing
        Ui.closingMessage();
    }
}
