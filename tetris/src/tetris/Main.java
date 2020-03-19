package tetris;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Alert;
import javafx.concurrent.Task;

import java.net.URL;

public class Main extends Application {

    private static MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setContentText("Please turn on volume.");
        alert.setHeaderText(null);
        alert.showAndWait();

        final Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                ClassLoader resource = this.getClass().getClassLoader();
                URL path = this.getClass().getResource("/music.mp3");
                System.out.println(path.toURI().toString());
                Media media = new Media(path.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        TetrisGameLogic.getInstance().startGame();

    }
}
