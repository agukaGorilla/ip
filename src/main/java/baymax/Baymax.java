package baymax;

import baymax.ui.Ui;

/**
* Represents main entry point for Baymax application
* */
public class Baymax {
    
    /**
    * Runs main entry function for Baymax
    * Loads data from table, reads user input and closes the application
    * */
    public static void main(String[] args){

        //Opening Message
        Ui.openingMessage();

        /*
        * Loads messages previously stored (and)
        * Handles adding of new commands
        * */
        Ui.readInput();

        //Closing
        Ui.closingMessage();
    }
}
