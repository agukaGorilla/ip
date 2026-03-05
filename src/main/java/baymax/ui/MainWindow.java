package baymax.ui;

import baymax.Baymax;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI. Provides the layout and interaction logic
 * for the main window of the Baymax application.
 */
public class MainWindow extends AnchorPane {
    
    private static final String USER_IMAGE_PATH = "/images/UserImage.png";
    private static final String BAYMAX_IMAGE_PATH = "/images/BaymaxImage.png";
    private static final String EXIT_COMMAND = "bye";
    private static final double EXIT_DELAY_SECONDS = 1.8;
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    
    private Baymax baymax;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
    private Image baymaxImage = new Image(this.getClass().getResourceAsStream(BAYMAX_IMAGE_PATH));
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. Binds the scroll pane to the dialog container.
     */
    @FXML
    public void initialize() {
        // ASSERTION: Ensure all FXML elements were successfully injected
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file.";
        assert dialogContainer != null : "fx:id=\"dialogContainer\" was not injected: check your FXML file.";
        assert userInput != null : "fx:id=\"userInput\" was not injected: check your FXML file.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file.";
        
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    
    /**
     * Injects the Baymax instance into the controller and displays the opening message.
     *
     * @param d The Baymax instance to be used by the application.
     */
    public void setBaymax(Baymax d) {
        // ASSERTION: Ensure the injected logic instance is valid
        assert d != null : "Baymax instance injected into MainWindow cannot be null";
        baymax = d;
        
        Ui.printOpeningMessage();
        dialogContainer.getChildren().add(
                DialogBox.getBaymaxDialog(UiBuffer.getAndClear(), baymaxImage)
        );
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Baymax's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     * Handles the termination of the application if the exit command is given.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input string cannot be null";
        
        String response = baymax.getResponse(input);
        assert response != null : "Baymax response string cannot be null";
        
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaymaxDialog(response, baymaxImage)
        );
        userInput.clear();
        
        checkAndExit(input);
    }
    
    /**
     * Checks if the user provided the exit command, and if so, triggers a delayed application closure.
     *
     * @param input The raw string input from the user.
     */
    private void checkAndExit(String input) {
        if (input.trim().equalsIgnoreCase(EXIT_COMMAND)) {
            javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(EXIT_DELAY_SECONDS));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}