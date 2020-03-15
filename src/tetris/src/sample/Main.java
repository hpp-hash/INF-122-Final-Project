package tetris.src.sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static TetrisGameLogic hi = new TetrisGameLogic();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        hi.startGame();
    }
}
