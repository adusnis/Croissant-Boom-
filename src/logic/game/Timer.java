package logic.game;

/**
 * The Timer class is responsible for managing the in-game timer.
 * It tracks time in minutes, seconds, and milliseconds, and allows for decrementing and resetting.
 */
public class Timer implements ResetGameAble {

    // Constants for the default initial values
    private static final int INITIAL_MINUTE = 1;
    private static final int INITIAL_SECONDS = 30;
    private static final int INITIAL_MS = 0;
    private static final int MS_DECREMENT = 100;
    
    // Fields for minute, seconds, and milliseconds
    private int minute;
    private int seconds;
    private int ms;
    private boolean isStop;

    /**
     * Constructor initializes the timer to 0 minutes, 40 seconds, and 0 milliseconds.
     */
    public Timer() {
        this.minute = INITIAL_MINUTE;
        this.seconds = INITIAL_SECONDS;
        this.ms = INITIAL_MS;
        this.isStop = true;
    }

    /**
     * Decrements the timer by a specified amount of milliseconds.
     * It handles underflowing values for seconds and minutes when milliseconds go below zero.
     * 
     * @param amount the amount to decrement (in milliseconds)
     */
    public void decrementTimer(int amount) {
        if (isTimerEmpty()) {
            return;
        }

        ms -= amount;

        while (ms < 0) {
            if (isTimerEmpty()) {
                ms = 0;
                return;
            }
            ms += MS_DECREMENT;
            seconds -= 1;
            handleSecondsUnderflow();
        }
    }

    /**
     * Handles the underflow of seconds (i.e., when seconds go below 0).
     * It adjusts the minute value accordingly.
     */
    private void handleSecondsUnderflow() {
        while (seconds < 0) {
            seconds += 60;
            minute -= 1;
        }
    }

    /**
     * Checks if the timer has reached zero (i.e., all time values are zero).
     * 
     * @return true if the timer is empty, otherwise false
     */
    public boolean isTimerEmpty() {
        return minute <= 0 && seconds <= 0 && ms <= 0;
    }
    
    /**
     * Set timer to end.
     */
    public void setTimerEmpty() {
    	this.minute = 0;
        this.seconds = 0;
        this.ms = 0;
    }

    /**
     * Returns the timer as a formatted string: "MM:SS:MS".
     * 
     * @return the string representation of the timer
     */
    public String toString() {
        return String.format("%02d:%02d:%02d", minute, seconds, ms);
    }

    /**
     * Returns the current status of the timer (whether it is stopped).
     * 
     * @return true if the timer is stopped, otherwise false
     */
    public boolean isStop() {
        return isStop;
    }

    /**
     * Sets the stop status of the timer.
     * 
     * @param isStop true to stop the timer, false to start it
     */
    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }

    /**
     * Resets the timer to the default values (0 minutes, 30 seconds, and 0 milliseconds).
     */
    @Override
    public void reset() {
        this.minute = INITIAL_MINUTE;
        this.seconds = INITIAL_SECONDS;
        this.ms = INITIAL_MS;
        this.isStop = true;
    }
}
