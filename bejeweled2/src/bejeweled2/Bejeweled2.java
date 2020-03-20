package bejeweled2;

import javafx.application.Application;
import javafx.stage.Stage;

public class Bejeweled2 extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BejeweledGameLogic.getInstance().startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start() {
        BejeweledGameLogic.getInstance().startGame();
    }
}
