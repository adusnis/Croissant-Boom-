package logic.components;

import java.util.Random;

/**
 * CroissantFactory class that creates random Croissant objects.
 * It provides a method to randomly generate a Classic, Salad, or Rainbow Croissant.
 */
public class CroissantFactory {

    // Constants for random croissant types
    private static final int CLASSIC_CROISSANT_TYPE = 0;
    private static final int SALAD_CROISSANT_TYPE = 1;
    private static final int RAINBOW_CROISSANT_TYPE = 2;
    
    /**
     * Creates a random Croissant object (Classic, Salad, or Rainbow).
     * 
     * @return A random Croissant object.
     */
    public static Croissant createRandomCroissant() {
        Random rand = new Random();
        int type = rand.nextInt(3); // Randomly selects between 0, 1, or 2
        
        // Return a new Croissant based on the randomly selected type
        return createCroissantByType(type);
    }

    /**
     * Creates a Croissant object based on the given type.
     * 
     * @param type The type of Croissant to create (0: Classic, 1: Salad, 2: Rainbow).
     * @return A new Croissant object corresponding to the type.
     */
    private static Croissant createCroissantByType(int type) {
        switch (type) {
            case CLASSIC_CROISSANT_TYPE:
                return new ClassicCroissant();
            case SALAD_CROISSANT_TYPE:
                return new SaladCroissant();
            case RAINBOW_CROISSANT_TYPE:
                return new RainbowCroissant();
            default:
                return new ClassicCroissant(); // Default case to return Classic Croissant
        }
    }
}
