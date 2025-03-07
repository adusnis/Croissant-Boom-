package logic.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.game.SceneController;

/**
 * Represents the Home screen component with a restart button.
 */
public class Home extends VBox {

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

    private Button selectHome;

    /**
     * Initializes the Home screen layout and components.
     */
    public Home() {
        this.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(SPACING);
        this.setPadding(PADDING);
        
        initializeHomeButton();
        this.getChildren().add(selectHome);
    }

    /**
     * Creates and configures the restart button.
     */
    private void initializeHomeButton() {
        selectHome = new Button("🏠");
        selectHome.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, BUTTON_FONT_SIZE));
        selectHome.setTextFill(Color.WHITE);
        selectHome.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        selectHome.setBackground(new Background(new BackgroundFill(BUTTON_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));

        // Change button appearance on hover
        selectHome.setOnMouseEntered(e -> {
            selectHome.setBackground(new Background(new BackgroundFill(HOVER_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));
            selectHome.setText("<3");
        });

        selectHome.setOnMouseExited(e -> {
            selectHome.setBackground(new Background(new BackgroundFill(BUTTON_COLOR, new CornerRadii(BUTTON_CORNER_RADIUS), null)));
            selectHome.setText("🏠");
        });

        // Set click action to change the scene
        selectHome.setOnMouseClicked(e -> SceneController.setupScene());
    }
} 
