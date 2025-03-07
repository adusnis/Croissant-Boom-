package logic.game;

/**
 * Interface for components that can be reset in the game.
 * Any class that implements this interface must provide a reset method
 * to restore its state to the initial configuration.
 */
public interface ResetGameAble {

    /**
     * Resets the state of the component.
     * Implementations of this method should define how to reset the component
     * to its initial or default state.
     */
    void reset();
}
