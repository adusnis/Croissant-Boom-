package logic.components;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 * Class representing the trash bin display pane.
 * It displays an image of a trash bin.
 */
public class Trash extends Pane {
    
    private Background defaultBackground;

    /**
     * Constructor to initialize the Trash pane with the given width and height.
     * Sets the background image of the trash bin.
     * 
     * @param width The width of the trash pane.
     * @param height The height of the trash pane.
     */
    public Trash(int width, int height) {
        setPrefSize(width, height);
        
        // Load the image for the trash bin
        Image defaultImage = new Image(ClassLoader.getSystemResource("images/trashBin.png").toString());

        // Create a background using the trash bin image
        defaultBackground = new Background(new BackgroundImage(
                defaultImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        ));

        // Set the trash bin image as the background
        setBackground(defaultBackground);
    }
}
