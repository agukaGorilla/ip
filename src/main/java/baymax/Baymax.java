package baymax;

import baymax.ui.Ui;
import baymax.function.Parser;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static void main(String[] args){

        //Opening Message
        Ui.openingMessage();

        //Handles all the commands
        Ui.readInput();

        //Closing
        Ui.closingMessage();
    }
}
