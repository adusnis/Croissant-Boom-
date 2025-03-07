package logic.components;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 * Class representing a table with a default and a served state.
 * The table background can change when it is served.
 */
public class Table extends Pane {
    private Background defaultBackground;
    private Image defaultImage;
    private Background serveBackground;
    private Image serveImage;

    /**
     * Constructor to initialize the table with a specified width and height.
     *
     * @param width  The width of the table.
     * @param height The height of the table.
     */
    public Table(int width, int height) {
        setPrefSize(width, height);

        // Load images for different table states
        defaultImage = new Image(ClassLoader.getSystemResource("images/table.png").toString());
        serveImage = new Image(ClassLoader.getSystemResource("images/tableWithServe.png").toString());

        // Create background for default table state
        defaultBackground = new Background(new BackgroundImage(
            defaultImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, true, false)
        ));

        // Create background for served table state
        serveBackground = new Background(new BackgroundImage(
            serveImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, true, false)
        ));

        // Set the default background initially
        setBackground(defaultBackground);
    }

    /**
     * Changes the table background to the served state and resets after a short delay.
     */
    public void serve() {
        new Thread(() -> {
            setBackground(serveBackground); // Change to served state
            try {
                Thread.sleep(2000); // Wait for 2 seconds to simulate serving process
            } catch (Exception e) {
                setBackground(serveBackground); // Keep served state if interrupted
            } finally {
                setBackground(defaultBackground); // Reset to default background after serving
            }
        }).start();
    }
}
