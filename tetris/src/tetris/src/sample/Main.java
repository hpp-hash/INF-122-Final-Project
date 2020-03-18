package tetris.src.sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        for(String str : args) {
            System.out.println(str);
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TetrisGameLogic hi = new TetrisGameLogic();
        hi.startGame();
    }
}
