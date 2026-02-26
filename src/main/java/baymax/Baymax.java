package baymax;

import baymax.ui.Ui;

public class Baymax {

    public static void main(String[] args){

        //Opening Message
        Ui.printOpeningMessage();

        /*
        * Loads messages previously stored (and)
        * Handles adding of new commands
        * */
        Ui.readInput();

        //Closing
        Ui.printClosingMessage();
    }
}
