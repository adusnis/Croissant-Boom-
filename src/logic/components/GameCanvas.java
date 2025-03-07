package logic.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.game.GameLogic;
import logic.game.KeyboardController;

/**
 * The GameCanvas class represents the game screen, including the game grid,
 * score, timer, recipe, and various game components like the oven and trash.
 */
public class GameCanvas extends StackPane {
    private Text scoreText;
    private static WalkGround walkGround;
    private int score = 0;
    private KeyboardController keyboardController;
    private Text gameOverText;
    private static TimerPane timerPane;
    private ImageView recipeImg;
    private static Pane recipe;
    private static ScorePane scorePane;
    private static OvenArea ovenArea;
    private static Trash bin;
    private static Table serveTable;
    private static ExitButton exitButton;
    private GridPane game = new GridPane();

    // Constants for grid dimensions and padding
    private static final int GRID_COLUMNS = 20;
    private static final int GRID_ROWS = 16;
    private static final double COLUMN_WIDTH = 4.166; // 100% / 20 columns
    private static final double ROW_HEIGHT = 5.88; // 100% / 16 rows
    private static final int CANVAS_PADDING = 40;
    private static final int RECIPE_WIDTH = 200;
    private static final int RECIPE_HEIGHT = 360;
    private static final int IMAGE_WIDTH = 1000;
    private static final int IMAGE_HEIGHT = 720;

    /**
     * Constructor to initialize the GameCanvas layout and content.
     * Sets up grid properties and initializes components like the background,
     * game elements, and controllers.
     */
    public GameCanvas() {
        setGridProperties(); // Set up the grid properties (columns, rows)
        initializeComponents(); // Initialize the components inside the game
        
        // Setting up background image
        Image bgImage = new Image(ClassLoader.getSystemResource("images/background.gif").toString());
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(IMAGE_WIDTH);
        bgImageView.setFitHeight(IMAGE_HEIGHT);
        
        gameOverText = new Text();
        
        setPadding(new Insets(CANVAS_PADDING)); // Set padding for the canvas

        // Add background image and the game grid to the canvas
        getChildren().addAll(bgImageView, game);

        // Set the GameLogic to the timerPane and scorePane
        GameLogic.setTimerPane(timerPane);
        GameLogic.setScoreBoard(scorePane);
    }

    /**
     * Initializes the components of the game canvas.
     * Sets up various game elements like recipe, score pane, oven, etc.
     */
    private void initializeComponents() {
        // Initialize components if not already done
        if (ovenArea == null) {
            ovenArea = new OvenArea(360, 80);
        }
        if (bin == null) {
            bin = new Trash(360, 80);
        }
        if (serveTable == null) {
            serveTable = new Table(360, 80);
        }
        keyboardController = new KeyboardController(this);

        // Initialize the walk ground, score pane, and recipe
        
        walkGround = new WalkGround(keyboardController);
        scorePane = new ScorePane();
        recipe = createPane(RECIPE_WIDTH, RECIPE_HEIGHT);
        recipeImg = new ImageView(new Image(ClassLoader.getSystemResource("images/recipe.png").toString()));
        recipeImg.setFitWidth(RECIPE_WIDTH);
        recipeImg.setFitHeight(RECIPE_HEIGHT);
        recipe.getChildren().add(recipeImg);
        timerPane = new TimerPane();
        exitButton = new ExitButton();
        
        // Set Constraints to make exitButton doesn't effect servtable 
        ColumnConstraints exitButtonCol = new ColumnConstraints();
        exitButtonCol.setPercentWidth(6); // กำหนดเปอร์เซ็นต์ความกว้าง
        game.getColumnConstraints().add(exitButtonCol);

        RowConstraints exitButtonRow = new RowConstraints();
        exitButtonRow.setPercentHeight(6); // กำหนดเปอร์เซ็นต์ความสูง
        game.getRowConstraints().add(exitButtonRow);


        // Add components to the game grid
        game.add(scorePane, 0, 0, 5, 3);
        game.add(ovenArea, 7, 0, 9, 2);
        game.add(timerPane, 19, 0, 5, 2);
        game.add(bin, 18, 6, 2, 5);
        game.add(serveTable, 7, 15, 9, 2);
        game.add(recipe, 0, 5, 5, 9);
        game.add(walkGround, 6, 3, 11, 11);
        game.add(exitButton, 22, 16, 2, 2);

        game.setPrefSize(960, 680);
    }

    /**
     * Creates a Pane with the specified color and size.
     * 
     * @param color The color of the background.
     * @param width The width of the pane.
     * @param height The height of the pane.
     * @return A Pane with the given color and size.
     */
    private Pane createPane(int width, int height) {
        Pane pane = new Pane();
        pane.setPrefSize(width, height);
        return pane;
    }

    /**
     * Sets up the grid properties (columns and rows) for the game grid.
     */
    private void setGridProperties() {
        // Set up column constraints for the grid
        for (int i = 0; i < GRID_COLUMNS; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(COLUMN_WIDTH); // Split the grid into 20 equal columns
            game.getColumnConstraints().add(col);
        }

        // Set up row constraints for the grid
        for (int i = 0; i < GRID_ROWS; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(ROW_HEIGHT); // Split the grid into 16 equal rows
            game.getRowConstraints().add(row);
        }
    }

    /**
     * Sets the "GAME OVER" text.
     */
    public void setGameOverText() {
        gameOverText.setText("GAME OVER");
    }

    // Getter for OvenArea, initializes it if not created
    public static OvenArea getOvenArea() {
        if (ovenArea == null) {
            ovenArea = new OvenArea(360, 80); // Initialize OvenArea if it hasn't been created yet
        }
        return ovenArea;
    }

    // Getter for Table (ServeTable), initializes it if not created
    public static Table getTable() {
        if (serveTable == null) {
            serveTable = new Table(360, 80); // Initialize Table if it hasn't been created yet
        }
        return serveTable;
    }

    // Getter for Trash (Bin), initializes it if not created
    public static Trash getBin() {
        if (bin == null) {
            bin = new Trash(360, 80); // Initialize Trash if it hasn't been created yet
        }
        return bin;
    }

    // Getter for score
    public int getScore() {
        return score;
    }

    // Setter for score
    public void setScore(int score) {
        this.score = score;
    }
    
    // Getter for ScorePane
    public static ScorePane getScorePane() {
    	return scorePane;
    }
    
    // Getter for TimerPane
    public static TimerPane getTimerPane() {
    	return timerPane;
    }
    
    // Setter for TimerPane
    public static void setTimerPane(TimerPane timerPane) {
    	GameCanvas.timerPane = timerPane;
    }
    
    // Setter for ScorePane
    public static void setScorePane(ScorePane scorePane) {
    	GameCanvas.scorePane = scorePane;
    }
}
