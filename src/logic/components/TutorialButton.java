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
 * Represents the TutorialButton.
 */
public class TutorialButton extends VBox {

    // Constants for button size
    private static final double BUTTON_WIDTH = 50.0;
    private static final double BUTTON_HEIGHT = 50.0;
    private static final double BUTTON_FONT_SIZE = 30.0;

    // Constants for layout
    private static final double PREF_WIDTH = 400.0;
    private static final double PREF_HEIGHT = 400.0;
    private static final double SPACING = 30.0;
    private static final Insets PADDING = new Insets(10.0, 0.0, 10.0, 0.0);

    // Constants for colors
    private static final Color BUTTON_COLOR = Color.rgb(87, 87, 82);
    private static final Color HOVER_COLOR = Color.rgb(222, 139, 100);

    // Corner radius for button
    private static final double BUTTON_CORNER_RADIUS = 10.0;

    private Button selectRestart;

    /**
     * Initializes the tutorial button.
     */
    public TutorialButton() {
        this.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(SPACING);
        this.setPadding(PADDING);
        
        initializeTutorialButton();
        this.getChildren().add(selectRestart);
    }

    /**
     * Creates and configures the tutorial button.
     */
    private void initializeTutorialButton() {
        selectRestart = new Button("?");
        selectRestart.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, BUTTON_FONT_SIZE));
        selectRestart.setTextFill(Color.WHITE);
        selectRestart.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        selectRestart.setBackground(new Background(new BackgroundFill(BUTTON_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));

        // Change button appearance on hover
        selectRestart.setOnMouseEntered(e -> {
            selectRestart.setBackground(new Background(new BackgroundFill(HOVER_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));
            selectRestart.setText("¿");
        });

        selectRestart.setOnMouseExited(e -> {
            selectRestart.setBackground(new Background(new BackgroundFill(BUTTON_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));
            selectRestart.setText("?");
        });

        // Set click action to change the scene
        selectRestart.setOnMouseClicked(e -> SceneController.showTutorialScene());
    }
} 
