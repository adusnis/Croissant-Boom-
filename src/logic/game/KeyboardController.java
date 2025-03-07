package logic.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.components.GameCanvas;

public class KeyboardController {
    // Boolean flags to track the key states (pressed or not)
    private boolean upPressed = false;   // 'W' key for up
    private boolean downPressed = false; // 'S' key for down
    private boolean leftPressed = false; // 'A' key for left
    private boolean rightPressed = false; // 'D' key for right
    private boolean ePressed = false;    // 'E' key for some action (example: use item)
    private boolean qPressed = false;    // 'Q' key for another action (example: quit)
    private boolean rPressed = false;    // 'R' key for reset
    private boolean fPressed = false;    // 'F' key for some action (example: fight)

    // Static reference to KeyboardController (can be shared across other components)
    public static KeyboardController keyboardController;

    /**
     * Constructor that takes a GameCanvas object and sets key event handlers
     * for key pressed and key released events.
     *
     * @param gameCanvas The GameCanvas object to register key events for
     */
    public KeyboardController(GameCanvas gameCanvas) {
        // Register the event handlers for key press and key release
        gameCanvas.setOnKeyPressed(this::handleKeyPress);  // When a key is pressed
        gameCanvas.setOnKeyReleased(this::handleKeyRelease); // When a key is released
    }

    /**
     * Handle key press events. Sets the appropriate boolean flags when specific keys are pressed.
     *
     * @param e The KeyEvent triggered when a key is pressed
     */
    public void handleKeyPress(KeyEvent e) {
        // Check which key was pressed and set the corresponding flag to true
        if (e.getCode() == KeyCode.W) {
            upPressed = true;
        }
        if (e.getCode() == KeyCode.S) {
            downPressed = true;
        }
        if (e.getCode() == KeyCode.A) {
            leftPressed = true;
        }
        if (e.getCode() == KeyCode.D) {
            rightPressed = true;
        }
        if (e.getCode() == KeyCode.E && !e.isConsumed()) {
            ePressed = true;  // Trigger some action when 'E' is pressed
            e.consume();  // Consume the event so it doesn't propagate
        }
        if (e.getCode() == KeyCode.Q && !e.isConsumed()) {
            qPressed = true;  // Trigger another action when 'Q' is pressed
        }
        if (e.getCode() == KeyCode.R && !e.isConsumed()) {
            rPressed = true;  // Trigger reset action when 'R' is pressed
        }
        if (e.getCode() == KeyCode.F && !e.isConsumed()) {
            fPressed = true;  // Trigger some action (e.g., fight) when 'F' is pressed
        }
    }

    /**
     * Handle key release events. Resets the corresponding boolean flags when specific keys are released.
     *
     * @param e The KeyEvent triggered when a key is released
     */
    public void handleKeyRelease(KeyEvent e) {
        // Check which key was released and reset the corresponding flag to false
        if (e.getCode() == KeyCode.W) {
            upPressed = false;
        }
        if (e.getCode() == KeyCode.S) {
            downPressed = false;
        }
        if (e.getCode() == KeyCode.A) {
            leftPressed = false;
        }
        if (e.getCode() == KeyCode.D) {
            rightPressed = false;
        }
        if (e.getCode() == KeyCode.E) {
            ePressed = false;  // Reset 'E' key state
        }
        if (e.getCode() == KeyCode.Q) {
            qPressed = false;  // Reset 'Q' key state
        }
        if (e.getCode() == KeyCode.R) {
            rPressed = false;  // Reset 'R' key state
        }
        if (e.getCode() == KeyCode.F) {
            fPressed = false;  // Reset 'F' key state
        }
    }

    // Getter methods for each key state

    /**
     * Returns true if the 'W' key (up) is pressed.
     *
     * @return boolean indicating whether the 'W' key is pressed
     */
    public boolean isUpPressed() {
        return upPressed;
    }

    /**
     * Returns true if the 'S' key (down) is pressed.
     *
     * @return boolean indicating whether the 'S' key is pressed
     */
    public boolean isDownPressed() {
        return downPressed;
    }

    /**
     * Returns true if the 'A' key (left) is pressed.
     *
     * @return boolean indicating whether the 'A' key is pressed
     */
    public boolean isLeftPressed() {
        return leftPressed;
    }

    /**
     * Returns true if the 'D' key (right) is pressed.
     *
     * @return boolean indicating whether the 'D' key is pressed
     */
    public boolean isRightPressed() {
        return rightPressed;
    }

    /**
     * Returns true if the 'E' key is pressed.
     *
     * @return boolean indicating whether the 'E' key is pressed
     */
    public boolean isEPressed() {
        return ePressed;
    }

    /**
     * Returns true if the 'Q' key is pressed.
     *
     * @return boolean indicating whether the 'Q' key is pressed
     */
    public boolean isQPressed() {
        return qPressed;
    }

    /**
     * Returns true if the 'R' key is pressed.
     *
     * @return boolean indicating whether the 'R' key is pressed
     */
    public boolean isRPressed() {
        return rPressed;
    }

    /**
     * Returns true if the 'F' key is pressed.
     *
     * @return boolean indicating whether the 'F' key is pressed
     */
    public boolean isFPressed() {
        return fPressed;
    }
}
