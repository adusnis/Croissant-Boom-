package logic.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.game.SceneController;

/**
 * ControlPane class for managing the control buttons
 * (Easy/Hard mode) and displaying the game name.
 */
public class ControlPane extends VBox {
    
    // Buttons for selecting game modes
    private Button selectEasyMode;
    private Button selectHardMode;
    
    // Text for displaying the game name
    private Text gameName;
    
    // Static variable to store the game mode status
    public static boolean isHard;
    
    // Constants to replace magic numbers
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 50;
    private static final int FONT_SIZE_GAME_NAME = 80;
    private static final int FONT_SIZE_BUTTON = 28;
    private static final String FONT_FAMILY = "Comic Sans MS";
    private static final int PREF_WIDTH = 300; // for control pane width
    private static final int PREF_HEIGHT = 600; // for control pane height
    private static final int SPACING = 30; // for spacing between elements
    private static final int PADDING = 10; // for padding
    
    /**
     * Getter for isHard
     */
    public static boolean isHard() {
        return isHard;
    }

    /**
     * Setter for isHard
     */
    public static void setHard(boolean isHard) {
        ControlPane.isHard = isHard;
    }

    /**
     * Constructor for initializing the control pane layout and components
     */
    public ControlPane() {
        // Initialize with Easy mode as default
        setHard(false);
        
        // Set preferred size and layout for the control pane
        this.setPrefWidth(PREF_WIDTH);
        this.setPrefHeight(PREF_HEIGHT);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(SPACING);
        this.setPadding(new Insets(PADDING, 0, PADDING, 0));

        // Initialize buttons and game name text
        initializeEasyButton();  // Easy Mode button
        initializeHardButton();  // Hard Mode button
        initializeGameName();    // Game name text

        // Add components to the layout
        this.getChildren().addAll(gameName, selectEasyMode, selectHardMode);
    }

    /**
     * Initialize the game name text
     */
    private void initializeGameName() {
        // Create and configure the game name text
        Text text = new Text("CROISSANT\n   💣!BOOM!💣");
        text.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_GAME_NAME));
        text.setFill(Color.rgb(87, 87, 82));
        text.setStroke(Color.WHITE);
        text.setStrokeWidth(2);
        text.setVisible(true);
        this.gameName = text;
    }

    /**
     * Initialize the "Hard Mode" button
     */
    private void initializeHardButton() {
        Button btn = new Button("Hard Mode");
        
        // Set button properties
        btn.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_BUTTON));
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(BUTTON_WIDTH);
        btn.setPrefHeight(BUTTON_HEIGHT);
        btn.setBackground(new Background(new BackgroundFill(Color.rgb(87, 87, 82), new CornerRadii(10), null)));
        
        // Mouse hover effects
        btn.setOnMouseEntered(e -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(222, 139, 100), new CornerRadii(10), null)));
            btn.setText("🔥 Hard Mode 🔥"); // Change text when mouse hovers
        });

        // Mouse exit effects
        btn.setOnMouseExited(e -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(87, 87, 82), new CornerRadii(10), null)));
            btn.setText("Hard Mode"); // Revert text when mouse exits
        });
        
        // Button click action
        btn.setOnMouseClicked(e -> {
            setHard(true);             // Set to hard mode
            SceneController.showPlayScene(); // Switch to play scene
        });
        
        // Set the button for hard mode
        this.selectHardMode = btn;
    }

    /**
     * Initialize the "Easy Mode" button
     */
    private void initializeEasyButton() {
        Button btn = new Button("Easy Mode");
        
        // Set button properties
        btn.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_BUTTON));
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(BUTTON_WIDTH);
        btn.setPrefHeight(BUTTON_HEIGHT);
        btn.setBackground(new Background(new BackgroundFill(Color.rgb(87, 87, 82), new CornerRadii(10), null)));
        
        // Mouse hover effects
        btn.setOnMouseEntered(e -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(222, 197, 100), new CornerRadii(10), null)));
            btn.setText("🥐 Easy Mode 🥐"); // Change text when mouse hovers
        });

        // Mouse exit effects
        btn.setOnMouseExited(e -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(87, 87, 82), new CornerRadii(10), null)));
            btn.setText("Easy Mode"); // Revert text when mouse exits
        });

        // Button click action
        btn.setOnMouseClicked(e -> {
            setHard(false);            // Set to easy mode
            SceneController.showPlayScene(); // Switch to play scene
        });
        
        // Set the button for easy mode
        this.selectEasyMode = btn;
    }
}
