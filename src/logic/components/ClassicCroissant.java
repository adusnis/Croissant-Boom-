package logic.components;

import javafx.scene.image.ImageView;

/**
 * ClassicCroissant class extends Croissant to represent a specific type of croissant.
 * It provides the properties and behavior of the Classic Croissant, including the image
 * update when it is baked or burnt.
 */
public class ClassicCroissant extends Croissant {

    // Constants for image paths
    private static final String RAW_IMAGE_PATH = "images/rawClassicCroissant.png";
    private static final String BURNED_IMAGE_PATH = "images/burnedCroissant.png";
    private static final String CLASSIC_IMAGE_PATH = "images/classicCroissant.png";
    private static final int CLASSIC_CROISSANT_BAKE_TIME = 5; // Bake time in seconds
    private static final int CLASSIC_CROISSANT_BASE_SCORE = 100; // Base score for the croissant


    /**
     * Constructor for the ClassicCroissant.
     * Sets the name, bake time, base score, and image for the Classic Croissant.
     */
    public ClassicCroissant() {
        // Calls the parent constructor with specific values for this croissant type
    	super("Classic Croissant", CLASSIC_CROISSANT_BAKE_TIME, CLASSIC_CROISSANT_BASE_SCORE, RAW_IMAGE_PATH);
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
            return CLASSIC_IMAGE_PATH;
        }
    }
}
