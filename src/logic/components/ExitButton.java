package logic.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.game.GameLogic;
import logic.game.SceneController;

/**
 * Represents the bxit button.
 */
public class ExitButton extends VBox {

    // Constants for button size
    private static final double BUTTON_WIDTH = 40.0;
    private static final double BUTTON_HEIGHT = 40.0;
    private static final double BUTTON_FONT_SIZE = 25.0;



    // Constants for colors
    private static final Color BUTTON_COLOR = Color.rgb(222, 139, 100);
    private static final Color HOVER_COLOR = Color.rgb(220, 72, 72);

    // Corner radius for button
    private static final double BUTTON_CORNER_RADIUS = 10.0;

    private Button selectExitGame;

    /**
     * Initializes the exit button.
     */
    public ExitButton() {
        initializeExitButton();
        this.getChildren().add(selectExitGame);
    }

    /**
     * Creates and configures the exit button.
     */
    private void initializeExitButton() {
    	selectExitGame = new Button("X");
    	selectExitGame.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, BUTTON_FONT_SIZE));
        selectExitGame.setTextFill(Color.WHITE);
        selectExitGame.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        selectExitGame.setBackground(new Background(new BackgroundFill(BUTTON_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));

        // Change button appearance on hover
        selectExitGame.setOnMouseEntered(e -> {
        	selectExitGame.setBackground(new Background(new BackgroundFill(HOVER_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));
        	selectExitGame.setText("X");
        });

        selectExitGame.setOnMouseExited(e -> {
        	selectExitGame.setBackground(new Background(new BackgroundFill(BUTTON_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));
            selectExitGame.setText("X");
        });

        // Set click action to change the scene
        selectExitGame.setOnMouseClicked(e -> endTimer());
    }
    
    /**
     * Force timer to end the game.
     */
    private void endTimer() {
    	GameLogic.getPlTimer().setTimerEmpty();
    	GameLogic.getPlTimer().setStop(true);
    }
} 
