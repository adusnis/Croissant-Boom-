package logic.game;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.components.GameCanvas;
import logic.components.OvenArea;
import logic.components.Score;
import logic.components.ScorePane;
import logic.components.TimerPane;

public class GameLogic {
    private static Timer timer;
    private static TimerPane timerPane;
    private static Score score;
    private static ScorePane scorePane;
    public static Timer plTimer; // timer for countdown timer method

	// Update the score
    public static void scoreBoard() {

        // Thread that runs in the background to update the score
        Thread scoreThread = new Thread(() -> {
            try {
                while (!SceneController.isGameEnded()) {
                    Thread.sleep(100);  // Sleep for 100ms to reduce CPU usage
                    Platform.runLater(() -> {
                        scorePane.setScoreText(score); // Update the score on the UI
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted state
            }
        });

        scoreThread.setDaemon(true); // Ensure the thread stops when the app closes
        scoreThread.start();
    }

    // Start the countdown timer
    public static void startCountDownTimer() {
        Thread tt = new Thread(() -> {
            try {
                runCountDownTimer();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        tt.start();
    }

    // Run the countdown timer
    public static void runCountDownTimer() throws InterruptedException {
    	plTimer = timer;
        plTimer.setStop(false);
        new Thread(() -> {
            while (!plTimer.isTimerEmpty()) {
                try {
                    Thread.sleep(20);  // Sleep for 20ms to allow UI updates
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    timerPane.setTimer(plTimer);  // Update the timer on the UI
                });

                plTimer.decrementTimer(2);  // Decrease the timer
            }
            timerPane.setTimer(plTimer);  // Update the timer after time runs out
            Platform.runLater(() -> {
                SceneController.createEndScene();  // Show end scene when time is up
            });
        }).start();
        plTimer.setStop(true);
    }

    // Set the TimerPane
    public static void setTimerPane(TimerPane timerPane) {
        GameLogic.timerPane = timerPane;
    }

    // Set the ScorePane
    public static void setScoreBoard(ScorePane scorePane) {
        GameLogic.scorePane = scorePane;
    }

    // Get the score
    public static Score getScore() {
        return score;
    }

    // Set the score
    public static void setScore(Score score) {
        GameLogic.score = score;
    }
    
    // Get the plTimer
    public static Timer getPlTimer() {
    	if(plTimer == null) {
    		plTimer = timer;
    	}
    	return plTimer;
    }
    
    // Set the plTimer
    public static void setPlTimer(Timer plTimer) {
    	GameLogic.plTimer = plTimer;
    }

    // Reset the game
    public static void resetGame() {
        if (score != null) {
            score.reset();  // Reset the score
        } else {
            score = new Score();
        }
        if (timer != null) {
            timer.reset();  // Reset the timer
        } else {
            timer = new Timer();
        }
        SceneController.setIsGameEnded(false);  // Set the game as not ended
        GameCanvas.getOvenArea().reset();  // Reset the OvenArea
    }
}
