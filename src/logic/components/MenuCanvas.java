package logic.components;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import logic.components.ControlPane;

/**
 * The MenuCanvas class sets up the menu screen for the game.
 * It initializes the background image and places the ControlPane 
 * at the bottom of the screen.
 */
public class MenuCanvas extends AnchorPane {

    private static final String BACKGROUND_IMAGE_PATH = "images/background.gif";  // Constant for background image path
    private static final double BACKGROUND_WIDTH = 1000;
    private static final double BACKGROUND_HEIGHT = 720;
    private static final double CONTROL_PANE_TOP_ANCHOR = 150;

    /**
     * Constructor to initialize the MenuCanvas layout and content.
     * It sets the background image and adds the control pane.
     */
    public MenuCanvas() {
        super();
        initializeBackground();  // Set the background image
        initializeControlPane();  // Add the control pane to the layout
        initializeTutorialButton(); // Add the tutorial button
    }

    /**
     * Initializes and sets the background image for the menu screen.
     */
    private void initializeBackground() {
        Image bgImage = new Image(ClassLoader.getSystemResource(BACKGROUND_IMAGE_PATH).toString());

        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.CENTER,
                new BackgroundSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, false, false, false, false)
        );
        setBackground(new Background(backgroundImage));  // Apply the background
    }

    /**
     * Initializes the ControlPane and sets its position at the bottom of the screen.
     */
    private void initializeControlPane() {
        ControlPane controlPane = new ControlPane();

        // Set the position of ControlPane at the bottom center
        AnchorPane.setLeftAnchor(controlPane, 0.0);
        AnchorPane.setRightAnchor(controlPane, 0.0);
        AnchorPane.setTopAnchor(controlPane, CONTROL_PANE_TOP_ANCHOR);

        // Add the ControlPane to the layout
        getChildren().add(controlPane);
    }
    
    /**
     * Initializes the Tutorial button and places it at the bottom right corner.
     */
    private void initializeTutorialButton() {
        TutorialButton tutorialButton = new TutorialButton(); // Assuming Tutorial extends some kind of Button or Node
        
        // Set size of the tutorial button (square shape)
        tutorialButton.setPrefSize(100, 100); // Adjust size as needed
        
        // Anchor it to the bottom-right corner
        AnchorPane.setBottomAnchor(tutorialButton, 20.0); // 20px from the bottom
        AnchorPane.setRightAnchor(tutorialButton, 20.0);  // 20px from the right

        // Add the tutorial button to the layout
        getChildren().add(tutorialButton);
    }

}
