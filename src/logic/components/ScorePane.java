package logic.components;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.game.GameLogic;
import logic.game.Timer;

/**
 * A class representing the score pane, which displays the score text and score count.
 */
public class ScorePane extends Pane {

    private Text scoreText; // Text for "SCORE" label
    private Text scoreCount; // Text for the score count

    /**
     * Constructor to initialize the score pane layout and display the initial score.
     */
    public ScorePane() {
        this.setPrefWidth(200);
        this.setPrefHeight(80);

        // Initialize score text and score count
        scoreText = new Text("SCORE");
        scoreCount = new Text("0");

        // Set font styles
        scoreText.setFont(new Font("Comic Sans MS", 15));
        scoreCount.setFont(new Font("Comic Sans MS", 55));

        // Set position for the score text
        scoreText.setX(15);
        scoreText.setY(20);
        scoreText.setTextAlignment(TextAlignment.CENTER);

        // Set position for the score count
        scoreCount.setX(10);
        scoreCount.setY(70);
        scoreCount.setTextAlignment(TextAlignment.CENTER);

        // Set text color
        scoreText.setFill(Color.BLACK);
        scoreCount.setFill(Color.BLACK);

        // Update the score display by calling GameLogic
        GameLogic.scoreBoard();

        // Add the score texts to the pane
        this.getChildren().addAll(scoreText, scoreCount);
    }

    /**
     * Updates the score count display with the new score.
     * @param score The new score to display.
     */
    public void setScoreText(Score score) {
        scoreCount.setText(score.toString());
    }
}
