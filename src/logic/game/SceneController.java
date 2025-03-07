package logic.game;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import logic.components.GameCanvas;
import logic.components.MenuCanvas;
import logic.components.Tutorial;
import logic.components.EndCanvas;

/**
 * The SceneController is responsible for managing the scenes in the game.
 * It handles switching between the menu, play, and end scenes.
 */
public class SceneController {

    // Fields for managing game scenes and stage
    private static GameCanvas gameCanvas;
    private static boolean isGameEnded;
    private static Stage stage;
    private static Scene menuScene;
    private static Scene playScene;
    private static Scene endScene;
    private static Scene tutorialScene;
    private static Tutorial tutorial;
    

    /**
     * Set the primary stage for the application.
     * 
     * @param primaryStage the primary stage of the application
     */
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     * Setup the initial scene, which is the menu scene.
     */
    public static void setupScene() {
        createMenuScene();
        switchScene(menuScene);
    }
    
    
    /*
     * Pre create TutorialScene because video is very large
     */
    
    public static void createTutorialScene() {        
        tutorial = new Tutorial();
        tutorialScene = new Scene(tutorial, 1000, 720);
    }

    /**
     * Create the menu scene for the game.
     */
    private static void createMenuScene() {
        MenuCanvas menuCanvas = new MenuCanvas();
        menuScene = new Scene(menuCanvas, 1000, 720);
    }

    /**
     * Create the play scene and reset the game.
     */
    public static void showPlayScene() { 
	    GameLogic.resetGame();
	    gameCanvas = new GameCanvas();
	    playScene = new Scene(gameCanvas, 1000, 720);
    	isGameEnded = false;
        switchScene(playScene);
    }

    /**
     * Show the end scene and set the game as ended.
     */
    public static void createEndScene() {
        isGameEnded = true;
        gameCanvas.setGameOverText(); // Show game over text on the canvas
        EndCanvas endCanvas = new EndCanvas();
        endScene = new Scene(endCanvas, 1000, 720);
        switchScene(endScene);
    }
    
    /**
     * Show the tutorial scene.
     */
    public static void showTutorialScene() {
    		while(tutorial.getMediaPlayer() == null) {
    			try { 
    				Thread.sleep(200);
    			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    		tutorial.getMediaPlayer().stop();
			tutorial.getMediaPlayer().seek(javafx.util.Duration.ZERO); // play clip from the begining
			tutorial.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE); // loop
			tutorial.getMediaPlayer().play();
			switchScene(tutorialScene);
	
}

    /**
     * Switch to the given scene.
     * 
     * @param newScene the new scene to display
     */
    public static void switchScene(Scene newScene) {
        stage.setScene(newScene);
        stage.show();
    }

    /**
     * Get the current status of the game (whether it is ended).
     * 
     * @return true if the game has ended, otherwise false
     */
    public static boolean isGameEnded() {
        return isGameEnded;
    }

    /**
     * Set the game status as ended or not, and update the game canvas accordingly.
     * 
     * @param isGameEnded true if the game has ended, otherwise false
     */
    public static void setIsGameEnded(boolean isGameEnded) {
        SceneController.isGameEnded = isGameEnded;
        if (isGameEnded) {
            gameCanvas.setGameOverText(); // Update the game canvas with game over text
        }
    }

    /**
     * Get the current scene.
     * 
     * @return the current scene (either play or menu scene)
     */
    public static Scene getScene() {
        return (playScene != null) ? playScene : menuScene;
    }
}
