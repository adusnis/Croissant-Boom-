package logic.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.game.GameLogic;
import logic.game.SceneController;

/**
 * A class that displays the score and provides a restart button.
 * Implements a singleton design pattern for a single instance.
 */
public class Scoreandrestart extends VBox {

    private static Scoreandrestart instance; // Singleton instance
    private Button selectRestart; // Button to restart the game
    private Text score; // Text to display the score

    /**
     * Constructor to initialize the layout, score, and restart button.
     */
    public Scoreandrestart() {
        this.setPrefWidth(300);
        this.setPrefHeight(600);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(30);
        this.setPadding(new Insets(10, 0, 10, 0));

        initializeRestartButton();
        initializeScoreDisplay();

        this.getChildren().addAll(score, selectRestart);
    }

    /**
     * Initializes the score display with the current score.
     */
    private void initializeScoreDisplay() {
        Text text = new Text("SCORE: " + GameLogic.getScore());
        text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 72));
        text.setFill(Color.rgb(87, 87, 82));
        text.setVisible(true);
        this.score = text;
    }

    /**
     * Initializes the restart button with hover effects and click action.
     */
    private void initializeRestartButton() {
        Button btn = new Button("Restart");
        btn.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(300);
        btn.setPrefHeight(50);
        btn.setBackground(new Background(new BackgroundFill(Color.rgb(87, 87, 82), new CornerRadii(10), null)));

        // Hover effects
        btn.setOnMouseEntered(e -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(222, 139, 100), new CornerRadii(10), null)));
            btn.setText("Restart ⟳");
        });
        btn.setOnMouseExited(e -> {
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(87, 87, 82), new CornerRadii(10), null)));
            btn.setText("Restart");
        });

        // Button click action
        btn.setOnMouseClicked(e -> SceneController.showPlayScene());
        this.selectRestart = btn;
    }

    /**
     * Updates the score displayed on the screen.
     * @param score The new score to display.
     */
    public void setScore(int score) {
        this.score.setText("SCORE: " + score);
    }
}
