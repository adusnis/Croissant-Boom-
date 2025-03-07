package logic.components;

import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Tutorial extends AnchorPane {

    private final String TUTORIAL_VIDEO_PATH = "images/tutorial.mp4";
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    private ProgressIndicator loadingIndicator;

    public Tutorial() {
        super();
        initializeLoadingIndicator(); // เพิ่มตัวหมุนรอ
        loadMedia(); // โหลดวิดีโอ
        initializeHomeButton(); // เพิ่มปุ่ม Home
    }

    private void initializeLoadingIndicator() {
        loadingIndicator = new ProgressIndicator();
        loadingIndicator.setPrefSize(100, 100);

        // ใช้ AnchorPane.setTopAnchor กับ AnchorPane.setLeftAnchor เพื่อให้อยู่กลางหน้าจอ
        AnchorPane.setTopAnchor(loadingIndicator, 310.0); 
        AnchorPane.setLeftAnchor(loadingIndicator, 450.0); 
        getChildren().add(loadingIndicator);
    }

    private void loadMedia() {
        // ใช้ Platform.runLater เพื่อให้ UI ปรากฏก่อน
        Platform.runLater(() -> {
            media = new Media(ClassLoader.getSystemResource(TUTORIAL_VIDEO_PATH).toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);

            mediaView.setFitWidth(1000);
            mediaView.setFitHeight(720);

            mediaPlayer.setOnReady(() -> {
                getChildren().add(mediaView);
                mediaView.toBack();
                getChildren().remove(loadingIndicator); // เอาตัวหมุนออกเมื่อโหลดเสร็จ
            });

            mediaPlayer.setOnError(() -> {
                loadMedia();
            });
        });
    }

    private void initializeHomeButton() {
        Home homeButton = new Home();

        homeButton.setPrefSize(100, 100);
        AnchorPane.setBottomAnchor(homeButton, 20.0);
        AnchorPane.setRightAnchor(homeButton, 20.0);

        getChildren().add(homeButton);
        homeButton.toFront();
    }

    public MediaPlayer getMediaPlayer() {
        if (mediaPlayer == null) {
            initializeLoadingIndicator(); // แสดงตัวหมุนระหว่างโหลด
            //loadMedia(); // โหลดใหม่
        }
        return mediaPlayer;
    }
}
