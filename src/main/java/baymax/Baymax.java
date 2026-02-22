import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static void main(String[] args) {

        //Opening Message
        Ui.openingMessage();

        //Handles all the commands
        try {
            Parser.handleInput();
        } catch (BaymaxException e) {
            Ui.showError(e.getMessage());
        }

        //Closing
        Ui.closingMessage();
    }
}
