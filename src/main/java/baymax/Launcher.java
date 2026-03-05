package baymax;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Acts as the primary entry point for starting the JavaFX application.
 */
public class Launcher {
    
    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // ASSERTION (Precondition): The arguments array provided by the system should not be null
        assert args != null : "Command line arguments array should not be null";
        
        Application.launch(Main.class, args);
    }
}