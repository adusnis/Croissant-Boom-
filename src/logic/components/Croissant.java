package logic.components;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

/**
 * The Croissant class represents a base croissant object in the game.
 * It contains properties like name, bake time, base score, and the croissant's image.
 * It also handles movement, random spawning, and rendering of the croissant.
 */
public abstract class Croissant {
    
    private String name;             // Name of the croissant
    private int bakeTime;            // Bake time in seconds
    private int baseScore;           // Base score for this croissant
    private final double WIDTH = 48, HEIGHT = 40;   // Width and height of the croissant
    private int x, y;                // Position of the croissant on the grid
    private int speed = 20;          // Movement speed of the croissant
    private static final int GRID_SIZE = 392;  // Grid size where croissants spawn
    private boolean inOvenArea = false;         // Whether the croissant is in the oven area
    private Image croissantImage;   // Image of the croissant
    private ImageView imageView;    // ImageView for displaying the croissant
    private State state;            // Current state of the croissant (e.g., RAW, BURNED)
    private AudioClip spawnSound;   // Sound played when croissant spawns

    /**
     * Constructor to initialize the croissant with the provided parameters.
     * 
     * @param name       The name of the croissant
     * @param bakeTime   The bake time in seconds
     * @param baseScore  The base score for the croissant
     * @param imagePath  The file path for the croissant's image
     */
    public Croissant(String name, int bakeTime, int baseScore, String imagePath) {
        setName(name);
        setBaseScore(baseScore);
        setBakeTime(bakeTime);
        croissantImage = new Image(ClassLoader.getSystemResource(imagePath).toString());
        imageView = new ImageView(croissantImage);
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        setState(state.RAW);  // Set initial state to RAW
        spawnSound = new AudioClip(ClassLoader.getSystemResource("sounds/spawnSound.mp3").toString());
        spawnRandom();  // Spawn the croissant at a random location
    }

    // Getter and setter methods for croissant properties
    public Image getCroissantImage() {
        return croissantImage;
    }

    public void setCroissantImage(String imagePath) {
        this.croissantImage = new Image(ClassLoader.getSystemResource(imagePath).toString());
    }

    public String getName() {
        return name;
    }

    public int getBakeTime() {
        return bakeTime;
    }

    public int getBaseScore() {
        return baseScore;
    }

    /**
     * Spawns the croissant at a random position on the grid and plays the spawn sound.
     */
    public void spawnRandom() {
        Random rand = new Random();
        this.x = rand.nextInt(GRID_SIZE);
        this.y = rand.nextInt(GRID_SIZE);
        updateImageView();
        spawnSound.play();
    }

    /**
     * Moves the croissant by the given deltas in the x and y direction.
     * Ensures that the croissant stays within the grid bounds.
     * 
     * @param dx The change in x position
     * @param dy The change in y position
     */
    public void move(int dx, int dy) {
        x = Math.max(0, Math.min(GRID_SIZE, x + dx * speed));
        y = Math.max(0, Math.min(GRID_SIZE + 8, y + dy * speed));  // Allow for slight overflow in y direction
        updateImageView();
    }

    /**
     * Checks if the croissant is within the oven area.
     * 
     * @return True if the croissant is in the oven area, false otherwise
     */
    public boolean checkInOvenArea() {
        return this.y <= 40;  // If the y position is within the oven area
    }

    /**
     * Checks if the croissant is perfectly baked based on the actual baking time.
     * 
     * @param actualTime The actual bake time of the croissant
     * @return True if the croissant is perfectly baked, false otherwise
     */
    public boolean isPerfectlyBaked(int actualTime) {
        return Math.abs(actualTime - bakeTime) <= 1;  // Allow a margin of 1 second
    }

    /**
     * Renders the croissant on the provided GraphicsContext.
     * 
     * @param gc The GraphicsContext used for rendering
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(croissantImage, getX(), getY(), WIDTH, HEIGHT);
    }

    // Updates the ImageView with the current position of the croissant
    private void updateImageView() {
        imageView.setX(x);
        imageView.setY(y);
    }

    // Getter and setter methods for position, speed, and other properties
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isInOvenArea() {
        return inOvenArea;
    }

    public void setInOvenArea(boolean inOvenArea) {
        this.inOvenArea = inOvenArea;
    }

    public static int getGridSize() {
        return GRID_SIZE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBakeTime(int bakeTime) {
        this.bakeTime = Math.max(bakeTime, 1);  // Ensure that bakeTime is at least 1
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = Math.max(baseScore, 0);  // Ensure baseScore is non-negative
    }

    /**
     * Sets the position of the croissant and updates the ImageView.
     * 
     * @param x The x position
     * @param y The y position
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        updateImageView();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public State getState() {
        return state;
    }

    /**
     * Sets the current state of the croissant and updates the image accordingly.
     * 
     * @param state The new state of the croissant
     */
    public void setState(State state) {
        this.state = state;
        switch (state) {
            case BURN:
                setCroissantImage("images/burnedCroissant.png");
                break;
            default:
                break;
        }
    }

    /**
     * Abstract method to update the image of the croissant, 
     * should be implemented by subclasses.
     * 
     * @param imageView The ImageView used to display the croissant
     * @param isBurnt   Boolean indicating if the croissant is burnt
     */
    public abstract void updateImage(ImageView imageView, boolean isBurnt);

}
