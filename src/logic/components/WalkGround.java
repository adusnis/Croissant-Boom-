package logic.components;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.game.SceneController;
import logic.game.GameLogic;
import logic.game.KeyboardController;

/**
 * The WalkGround class represents the area where the croissant moves
 * and interacts with various game elements like the oven, trash, and bombs.
 * It handles movement, serving, and bomb interactions for the croissant.
 */
public class WalkGround extends Canvas {

    private final Image walkingArea;
    private Croissant croissant;
    private OvenArea oven;
    private ArrayList<Bomb> bombs;
    private Trash bin;
    private Table serveTable;
    private int bombNum;
    private int bombSpawnRate;
    private AudioClip bombSound;
    private KeyboardController keyboardController;
    private final static double SIZE = 440; // Size of the walking area
    private static final double SERVE_AREA_X_MIN = 39;
    private static final double SERVE_AREA_X_MAX = 340;
    private static final double SERVE_AREA_Y_MIN = 350;
    private static final double TRASH_AREA_X_MIN = 350;
    private static final double TRASH_AREA_Y_MIN = 110;
    private static final double TRASH_AREA_Y_MAX = 300;

    /**
     * Constructor initializes the walking area with specific game components.
     * It sets up the croissant, oven, bomb list, trash bin, and the serve table.
     * It also manages bomb spawning and movement handling.
     *
     * @param keyboardController The controller that listens to keyboard input.
     */
    public WalkGround(KeyboardController keyboardController) {
        super(SIZE, SIZE);
        this.keyboardController = keyboardController;
        walkingArea = new Image(ClassLoader.getSystemResource("images/walkingArea.png").toString());
        bombs = new ArrayList<>();
        croissant = CroissantFactory.createRandomCroissant();
        oven = GameCanvas.getOvenArea();
        bin = GameCanvas.getBin();
        serveTable = GameCanvas.getTable();
        bombSound = new AudioClip(ClassLoader.getSystemResource("sounds/tomatoSquish.mp3").toString());

        this.setFocusTraversable(true);
        this.requestFocus();
        setUpMove();
        goToTrash();
        serveToCustomer();
        updateCanvas(getGraphicsContext2D());
        
        // Set bomb number based on difficulty
        bombNum = ControlPane.isHard ? 5 : 2;
        bombSpawnRate = ControlPane.isHard ? 2000 : 4000;
        spawnBombPeriodically();
    }

    /**
     * This method handles serving the croissant to the customer.
     * The croissant is served when it is in the serving area and the E key is pressed.
     * If the croissant's state is perfect, it adds to the score; if burned or raw, it reduces the score.
     */
    public void serveToCustomer() {
        new Thread(() -> {
            while (!SceneController.isGameEnded()) {
                try {
                    if (this.croissant.getY() >= SERVE_AREA_Y_MIN  && this.croissant.getX() >= SERVE_AREA_X_MIN
                            && this.croissant.getX() <= SERVE_AREA_X_MAX  && keyboardController.isRPressed()) {
                        serveTable.serve();
                        
                        // Update score based on croissant state
                        if (this.croissant.getState() == State.PERFECT) {
                            GameLogic.setScore(new Score(GameLogic.getScore().getScore() + this.croissant.getBaseScore()));
                        } else if (this.croissant.getState() == State.BURN) {
                            GameLogic.setScore(new Score(GameLogic.getScore().getScore() - 150));
                        } else {
                            GameLogic.setScore(new Score(GameLogic.getScore().getScore() - 100));
                        }

                        GameLogic.getScore().toString();
                        this.croissant = CroissantFactory.createRandomCroissant();
                    }

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    /**
     * This method handles the action of throwing the croissant in the trash.
     * The croissant is discarded when it reaches the trash area and the E key is pressed.
     */
    public void goToTrash() {
        new Thread(() -> {
            while (!SceneController.isGameEnded()) {
                try {
                    if (this.croissant.getX() >= TRASH_AREA_X_MIN && this.croissant.getY() >= TRASH_AREA_Y_MIN
                            && this.croissant.getY() <= TRASH_AREA_Y_MAX && keyboardController.isFPressed()) {
                        this.croissant = CroissantFactory.createRandomCroissant();
                    }

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    /**
     * This method sets up the movement of the croissant using keyboard inputs.
     * The croissant moves based on the arrow keys and interacts with the oven area and bombs.
     */
    public void setUpMove() {
        new Thread(() -> {
            int dirUD, dirLR;
            while (!SceneController.isGameEnded()) {
                try {
                    dirLR = 0;
                    dirUD = 0;

                    // Movement based on keyboard input
                    if (keyboardController.isUpPressed()) {
                        dirUD = -1;
                    } else if (keyboardController.isDownPressed()) {
                        dirUD = 1;
                    }
                    if (keyboardController.isLeftPressed()) {
                        dirLR = -1;
                    } else if (keyboardController.isRightPressed()) {
                        dirLR = 1;
                    }

                    final int dirlr = dirLR;
                    final int dirud = dirUD;
                    Platform.runLater(() -> {
                        // Check for collision with bombs
                        for (int i = 0; i < bombs.size(); i++) {
                            if (bombs.get(i) != null && bombs.get(i).checkCollision(croissant)) {
                                croissant.setState(State.BURN); // Make croissant burn
                                bombSound.play();
                                bombs.remove(i); // Remove bomb
                            }
                        }

                        croissant.move(dirlr, dirud);

                        // Handle interactions with the oven
                        if (keyboardController.isQPressed() && croissant.isInOvenArea() && oven.isOccupied()) {
                            oven.removeCroissant();
                            croissant.setInOvenArea(false);
                        }

                        if (keyboardController.isEPressed() && !oven.isOccupied() && croissant.checkInOvenArea()
                                && croissant.getState() == State.RAW) {
                            oven.addCroissant(croissant);
                        }
                    });

                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * This method updates the canvas by drawing the background, croissant, and bombs.
     * It runs in a separate thread and refreshes the display every 30 milliseconds.
     *
     * @param gc The GraphicsContext to draw on.
     */
    public void updateCanvas(GraphicsContext gc) {
        new Thread(() -> {
            while (!SceneController.isGameEnded()) {
                try {
                    Platform.runLater(() -> {
                        gc.clearRect(0, 0, SIZE, SIZE);
                        gc.drawImage(walkingArea, 0, 0, SIZE, SIZE);
                        if (!oven.isOccupied()) {
                            croissant.render(gc);
                        }
                        // Render bombs if they haven't exploded
                        for (int i = 0; i < bombs.size(); i++) {
                            bombs.get(i).render(gc);
                        }
                    });

                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * This method spawns bombs at a periodic interval.
     * The bomb spawn rate is faster in hard mode.
     */
    private void spawnBombPeriodically() {
        new Thread(() -> {
            while (!SceneController.isGameEnded()) {
                try {
                    Platform.runLater(() -> {
                        bombs.add(new Bomb());
                    });

                    // Bomb spawn rate based on difficulty
                        Thread.sleep(bombSpawnRate);

                    // Remove bombs if the number exceeds the limit
                    if (bombs.size() >= bombNum) {
                        Platform.runLater(() -> {
                            bombs.remove(0);
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
