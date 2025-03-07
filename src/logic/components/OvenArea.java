package logic.components;

import java.io.File;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import logic.game.ResetGameAble;

/**
 * Represents an oven area where croissants can be baked.
 * It handles baking, removing croissants, and updating their states.
 */
public class OvenArea extends Pane implements ResetGameAble {
    
    private Croissant croissantInOven = null;
    protected boolean isBaking = false;
    protected boolean isCooked = false;
    private Thread bakingThread;
    
    private AudioClip takeOutSound;
    private final Background defaultBackground;
    private final Background bakingBackground;

    /**
     * Constructor to initialize the OvenArea with given width and height.
     * It loads images and sets up the background for the oven.
     *
     * @param width  Width of the oven area
     * @param height Height of the oven area
     */
    public OvenArea(int width, int height) {
        setPrefSize(width, height);

        // Load images
        Image defaultImage = new Image(ClassLoader.getSystemResource("images/oven.png").toString());
        Image bakingImage = new Image(ClassLoader.getSystemResource("images/ovenCooking.png").toString());

        // Create backgrounds
        bakingBackground = new Background(new BackgroundImage(
                bakingImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        ));

        defaultBackground = new Background(new BackgroundImage(
                defaultImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        ));

        setBackground(defaultBackground);

        // Load sound for take out
        takeOutSound = new AudioClip(ClassLoader.getSystemResource("sounds/bellDing.mp3").toString());
    }

    /**
     * Returns whether the oven is currently baking.
     *
     * @return true if the oven is baking, false otherwise
     */
    public boolean isBaking() {
        return isBaking;
    }

    /**
     * Sets the baking status of the oven and updates the background.
     *
     * @param isBaking true if the oven is baking, false otherwise
     */
    public void setBaking(boolean isBaking) {
        this.isBaking = isBaking;
        setBackground(isBaking ? bakingBackground : defaultBackground);
    }

    /**
     * Returns whether the croissant in the oven is cooked.
     *
     * @return true if the croissant is cooked, false otherwise
     */
    public boolean isCooked() {
        return isCooked;
    }

    /**
     * Sets the cooked status of the croissant.
     *
     * @param isCooked true if the croissant is cooked, false otherwise
     */
    public void setCooked(boolean isCooked) {
        this.isCooked = isCooked;
    }

    /**
     * Adds a croissant to the oven and starts the baking process.
     *
     * @param croissant The croissant to be added to the oven
     */
    public void addCroissant(Croissant croissant) {
        if (!isOccupied()) {
            croissantInOven = croissant;
            startBaking(croissant);
            croissant.setInOvenArea(true);
        }
    }

    /**
     * Starts the baking process for a croissant.
     * The baking time is based on the croissant's bake time.
     *
     * @param croissant The croissant to be baked
     */
    private void startBaking(Croissant croissant) {
        setBaking(true);

        bakingThread = new Thread(() -> {
            try {
                Thread.sleep(croissant.getBakeTime() * 1000); // Wait for the bake time
                if (isBaking) {
                    Platform.runLater(() -> {
                        setCooked(true);
                        croissant.updateImage(croissant.getImageView(), false);
                        croissant.setState(State.PERFECT);
                    });
                }
                Thread.sleep(2000); // Wait a bit before burning it
                if (isBaking) {
                    Platform.runLater(() -> {
                        croissant.updateImage(croissant.getImageView(), true);
                        croissant.setState(State.BURN);
                    });
                }
            } catch (InterruptedException e) {
            }
        });
        bakingThread.start();
    }

    /**
     * Removes the croissant from the oven and plays the sound.
     */
    public void removeCroissant() {
        if (isOccupied() && croissantInOven != null) {
            setBaking(false);
            if (bakingThread != null && bakingThread.isAlive()) {
                bakingThread.interrupt();
            }

            takeOutSound.play();

            croissantInOven.setPosition(180, 0);
            croissantInOven.setInOvenArea(false);
            croissantInOven = null;
        }
    }

    /**
     * Checks if the oven is currently occupied by a croissant.
     *
     * @return true if the oven is occupied, false otherwise
     */
    public boolean isOccupied() {
        return croissantInOven != null;
    }

    /**
     * Returns the croissant currently in the oven.
     *
     * @return the croissant in the oven
     */
    public Croissant getCroissantInOven() {
        return croissantInOven;
    }

    /**
     * Sets the croissant to be in the oven.
     *
     * @param croissantInOven The croissant to be placed in the oven
     */
    public void setCroissantInOven(Croissant croissantInOven) {
        this.croissantInOven = croissantInOven;
    }

    /**
     * Resets the oven to its default state.
     * This includes clearing the croissant, stopping the baking process, and resetting the background.
     */
    @Override
    public void reset() {
        setCroissantInOven(null);
        setBackground(defaultBackground);
        setBaking(false);
    }
}
