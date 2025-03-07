package logic.components;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import logic.game.GameLogic;
import logic.game.SceneController;
import logic.components.*;

/**
 * EndCanvas class represents the layout shown at the end of the game.
 * It displays the final score and provides options to restart or go home.
 */
public class EndCanvas extends VBox {

    // Constants for layout and button positions
    private static final int PADDING = 150;
    private static final int BUTTON_TRANSLATE_X = 130;

    /**
     * Constructor to initialize the EndCanvas layout and content.
     */
    public EndCanvas() {
        super(); // Calls the parent constructor

        // Create HBox containers for restart and home buttons
        HBox restartBox = createRestartBox();
        HBox homeBox = createHomeBox();

        // Set the background image for the EndCanvas
        setBackground(createBackgroundImage());

        // Add restart and home boxes to the EndCanvas layout
        getChildren().add(restartBox);
        getChildren().add(homeBox);
    }

    /**
     * Creates the background image for the canvas.
     * 
     * @return A Background object with the background image.
     */
    private Background createBackgroundImage() {
        Image bgImage = new Image(ClassLoader.getSystemResource("images/background.gif").toString());
        BackgroundImage backgroundImage = new BackgroundImage(
            bgImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1000, 720, false, false, false, false)
        );
        return new Background(backgroundImage); // Return the background object
    }

    /**
     * Creates the HBox for the restart button and score display.
     * 
     * @return An HBox containing the restart button and score display.
     */
    private HBox createRestartBox() {
        HBox restartBox = new HBox();
        Scoreandrestart scoreAndRestart = new Scoreandrestart();

        restartBox.setPadding(new Insets(PADDING)); // Set padding for the restart box
        restartBox.setAlignment(Pos.BASELINE_CENTER); // Align restart box to the bottom center
        restartBox.getChildren().add(scoreAndRestart); // Add score and restart to the box

        return restartBox; // Return the configured restart box
    }

    /**
     * Creates the HBox for the home button.
     * 
     * @return An HBox containing the home button.
     */
    private HBox createHomeBox() {
        HBox homeBox = new HBox();
        Home homeButton = new Home();

        homeBox.setAlignment(Pos.BOTTOM_RIGHT); // Align home button to the bottom right
        homeBox.setTranslateX(BUTTON_TRANSLATE_X); // Set horizontal translation for home button
        homeBox.getChildren().add(homeButton); // Add the home button to the box

        return homeBox; // Return the configured home box
    }
}
