package baymax;

import java.io.IOException;
import baymax.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Baymax using FXML.
 */
public class Main extends Application {
    
    private static final String FXML_PATH = "/view/MainWindow.fxml";
    
    private Baymax baymax = new Baymax();
    
    /**
     * Starts the JavaFX application and sets up the primary stage.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBaymax(baymax);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}