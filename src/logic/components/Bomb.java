package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Bomb class that represents a bomb object in the game.
 * It manages the bomb's image, position, and collision detection with a Croissant object.
 */
public class Bomb {
    // Constants for the bomb's dimensions
    private static final double WIDTH = 40;
    private static final double HEIGHT = 40;
    private static final int HEIGHT_OUT_OVEN_AREA = 60;
    private static final int GRID_SIZE = 440;

    // Position of the bomb on the grid
    private int x, y;

    // Image of the bomb
    private Image bombImage;
    private ImageView imageView;

    /**
     * Constructor to initialize the bomb with a random position and image.
     */
    public Bomb() {
        bombImage = new Image(ClassLoader.getSystemResource("images/tomatoExplosion.gif").toString());
        imageView = new ImageView(bombImage);
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        spawnRandom();
    }

    /**
     * Spawns the bomb at a random position within the grid.
     */
    public void spawnRandom() {
        Random rand = new Random();
        this.x = rand.nextInt(GRID_SIZE - (int) WIDTH);
        this.y = rand.nextInt(HEIGHT_OUT_OVEN_AREA + 1, GRID_SIZE - (int) HEIGHT);
        updateImageView();
    }

    /**
     * Renders the bomb image at the current position on the screen.
     * @param gc The GraphicsContext used to draw the image
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(bombImage, x, y, WIDTH, HEIGHT);
    }

    /**
     * Checks if the bomb collides with a given Croissant.
     * @param croissant The Croissant object to check for collision with
     * @return True if the bomb collides with the Croissant, false otherwise
     */
    public boolean checkCollision(Croissant croissant) {
        return Math.abs(croissant.getX() - x) < WIDTH && Math.abs(croissant.getY() - y) < HEIGHT;
    }

    /**
     * Updates the position of the bomb's image view.
     */
    private void updateImageView() {
        imageView.setX(x);
        imageView.setY(y);
    }

    /**
     * Returns the bomb's image.
     * @return The image representing the bomb
     */
    public Image getImage() {
        return bombImage;
    }

    /**
     * Returns the x position of the bomb.
     * @return The x coordinate of the bomb
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y position of the bomb.
     * @return The y coordinate of the bomb
     */
    public int getY() {
        return y;
    }
}
