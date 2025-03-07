package logic.components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.game.GameLogic;
import logic.game.Timer;

/**
 * Class representing the timer display pane.
 * It displays a countdown timer on the screen.
 */
public class TimerPane extends Pane {
    
    private Text header;
    private Text timer;

    /**
     * Constructor to initialize the TimerPane.
     * Sets the layout and default texts for the timer and header.
     */
    public TimerPane() {
        super();

        // Set preferred size for the TimerPane
        this.setPrefWidth(200);
        this.setPrefHeight(80);

        // Create and style header and timer text
        header = new Text(" Timer");
        timer = new Text("00:00:00");

        header.setFont(new Font("Comic Sans MS", 25));
        timer.setFont(new Font("Comic Sans MS", 40));
        header.setFill(Color.BLACK);
        timer.setFill(Color.BLACK);

        // Position the text on the pane
        header.setX(80);
        header.setY(30);

        timer.setX(30);
        timer.setY(65);

        // Start the countdown timer from GameLogic
        GameLogic.startCountDownTimer();

        // Add the text elements to the pane
        this.getChildren().addAll(header, timer);
    }

    /**
     * Updates the timer text based on the given Timer object.
     *
     * @param t The Timer object whose string representation will be shown.
     */
    public void setTimer(Timer t) {
        timer.setText(t.toString());
    }
}
