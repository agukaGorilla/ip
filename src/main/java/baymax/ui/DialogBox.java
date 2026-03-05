package baymax.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    
    private DialogBox(String text, Image img) {
        // ASSERTION (Precondition): Text and Image should exist before we try to build a UI component for them
        assert text != null : "Dialog text cannot be null";
        assert img != null : "Profile image cannot be null";
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // ASSERTION (Postcondition): FXML should have successfully injected these UI nodes
        assert dialog != null : "Dialog label was not initialized by FXML";
        assert displayPicture != null : "Display picture ImageView was not initialized by FXML";
        
        dialog.setText(text);
        displayPicture.setImage(img);
        
    }
    
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        // ASSERTION: We assume the HBox actually has children (the Label and ImageView) to flip
        assert !this.getChildren().isEmpty() : "DialogBox has no children to flip";
        
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
    
    /**
     * Creates a new DialogBox for the user.
     * The profile picture is positioned on the right side by default.
     *
     * @param text The message to be displayed in the dialog.
     * @param img The image representing the user's profile picture.
     * @return A DialogBox containing the user's message and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "User dialog text cannot be null";
        assert img != null : "User profile image cannot be null";
        return new DialogBox(text, img);
    }
    
    /**
     * Creates a new DialogBox for Baymax.
     * The profile picture is flipped to the left side to distinguish it from the user.
     *
     * @param text The message to be displayed in the dialog.
     * @param img The image representing Baymax's profile picture.
     * @return A DialogBox containing Baymax's message and image, flipped horizontally.
     */
    public static DialogBox getBaymaxDialog(String text, Image img) {
        assert text != null : "Baymax dialog text cannot be null";
        assert img != null : "Baymax profile image cannot be null";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}