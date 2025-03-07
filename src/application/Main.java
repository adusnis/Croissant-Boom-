package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import logic.components.ControlPane;
import logic.game.SceneController;

/**
 * Main application class for launching the game.
 * Initializes the game window, background music, and scene.
 */
public class Main extends Application {
    // Constants for sound file, icon file, window title, and media settings
    private static final String SOUND_FILE = "sounds/backgroundMusic.mp3";
    private static final String ICON_FILE = "images/classicCroissant.png";
    private static final String WINDOW_TITLE = "CROISSANT BOOM!!! 💣💣💣";
    private static final boolean RESIZABLE = false;
    private static final int MEDIA_LOOP = MediaPlayer.INDEFINITE;

    // MediaPlayer to keep playing background music across instances
    private static MediaPlayer mediaPlayer;

    /**
     * The start method is the main entry point for the application.
     * It sets up the primary stage, scene, and background music.
     *
     * @param primaryStage The main stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
    	
    	// Load tutorial video before start
    	SceneController.createTutorialScene();
    	// Set up Stage and Scene through the SceneController
        SceneController.setStage(primaryStage);
        SceneController.setupScene();

        // Initialize and play background music
        Media sound = new Media(ClassLoader.getSystemResource(SOUND_FILE).toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MEDIA_LOOP); // Set the music to loop indefinitely
        mediaPlayer.play();

        // Set window icon and title
        Image icon = new Image(ClassLoader.getSystemResource(ICON_FILE).toString());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle(WINDOW_TITLE);

        // Disable window resizing
        primaryStage.setResizable(RESIZABLE);
        primaryStage.show();

        // Handle the close request to stop the music and exit the application
        primaryStage.setOnCloseRequest(event -> closeGame());
    }

    /**
     * Handles game closing operations.
     * Stops the media player and exits the application.
     */
    private void closeGame() {
        mediaPlayer.stop(); // Stop background music
        Platform.exit(); // Exit JavaFX application
        System.exit(0); // Exit the JVM
    }

    /**
     * Main entry point for launching the application.
     * Calls launch() to start the JavaFX application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
