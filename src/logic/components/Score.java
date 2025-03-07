package logic.components;

import logic.game.ResetGameAble;

/**
 * Class representing a score in the game.
 * Implements the ResetGameAble interface to allow resetting the score.
 */
public class Score implements ResetGameAble {

    // Instance variable to hold the score value
    private int score;

    /**
     * Default constructor that initializes the score to 0.
     */
    public Score() {
        this.setScore(0);
    }

    /**
     * Constructor that initializes the score with a specified value.
     * @param score The initial score value.
     */
    public Score(int score) {
        this.setScore(score);
    }

    /**
     * Gets the current score.
     * @return The current score value.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score value. If the provided score is negative, it will be set to 0.
     * @param score The score value to set.
     */
    public void setScore(int score) {
        this.score = Math.max(score, 0);  // Ensures that the score is never negative
    }

    /**
     * Returns the score as a string representation.
     * @return The score as a string.
     */
    @Override
    public String toString() {
        return String.valueOf(this.getScore());  // Converts score to string
    }

    /**
     * Resets the score to 0.
     */
    @Override
    public void reset() {
        this.setScore(0);  // Resets score to 0
    }
}
