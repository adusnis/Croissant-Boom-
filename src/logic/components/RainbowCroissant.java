package logic.components;

import javafx.scene.image.ImageView;

/**
 * RainbowCroissant class extends Croissant to represent a specific type of croissant.
 * It provides the properties and behavior of the Rainbow Croissant, including the image
 * update when it is baked or burnt.
 */
public class RainbowCroissant extends Croissant {

    // Constants for image paths
    private static final String RAW_IMAGE_PATH = "images/rawRainbowCroissant.png";
    private static final String BURNED_IMAGE_PATH = "images/burnedCroissant.png";
    private static final String RAINBOW_IMAGE_PATH = "images/rainbowCroissant.png";
    
    // Constants for croissant properties
    private static final int BAKE_TIME = 8; // Bake time in seconds
    private static final int BASE_SCORE = 250; // Base score for the croissant

    /**
     * Constructor for the RainbowCroissant.
     * Sets the name, bake time, base score, and image for the Rainbow Croissant.
     */
    public RainbowCroissant() {
        // Calls the parent constructor with specific values for this croissant type
        super("Rainbow Croissant", BAKE_TIME, BASE_SCORE, RAW_IMAGE_PATH); // Bake time 8s, Base Score 250
    }

    /**
     * Updates the image of the croissant based on whether it is burnt or not.
     * 
     * @param imageView The ImageView used to display the croissant image
     * @param isBurnt   A boolean indicating if the croissant is burnt
     */
    @Override
    public void updateImage(ImageView imageView, boolean isBurnt) {
        // Sets the image path depending on whether the croissant is burnt
        String imagePath = getImagePath(isBurnt);
        setCroissantImage(imagePath); // Update the image for the croissant
    }

    /**
     * Returns the correct image path based on whether the croissant is burnt.
     * 
     * @param isBurnt A boolean indicating if the croissant is burnt
     * @return The image path for the croissant
     */
    private String getImagePath(boolean isBurnt) {
        if (isBurnt) {
            return BURNED_IMAGE_PATH;
        } else {
            return RAINBOW_IMAGE_PATH;
        }
    }
}
