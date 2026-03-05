package baymax;

import baymax.data.TaskData;
import baymax.ui.Ui;
import baymax.ui.UiBuffer;

/**
* Represents main entry point for Baymax application.
* */
public class Baymax {
    
    
    /**
    * Runs main entry function for Baymax.
    * Loads data from table, reads user input and closes the application.
    * */
    public static void main(String[] args){
        
        //Trigger to read input from user
        Ui.readInput();
        
    }
    
    public String getResponse(String input) {
        Ui.handleGuiInput(input);
        
        return UiBuffer.getAndClear();
    }
    
    
}
