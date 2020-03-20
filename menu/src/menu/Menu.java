package menu;

import bejeweled2.Bejeweled2;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tetris.Main;

import java.net.URL;

public class Menu extends Application {//The main menu where you choose between Tetris and Bejeweled
    int gameWidth = 730, gameHeight = 470;
    Group root = new Group();
    ImageView bejeweled, tetris;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("INF122 Team 6 - Menu Game");

        setup();

        Scene scene = new Scene(root, gameWidth, gameHeight, Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void setup() {
        URL bejeweledURL = this.getClass().getResource("/games/bejeweled.jpg");

        bejeweled = new ImageView(new Image(String.valueOf(bejeweledURL)));
        bejeweled.setFitWidth(300);
        bejeweled.setFitHeight(400);
        bejeweled.setTranslateX(30);
        bejeweled.setTranslateY(30);
        bejeweled.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event)
            {
                Bejeweled2 bejeweled = new Bejeweled2();
                bejeweled.start();
            }
        });

        URL tetrisURL = this.getClass().getResource("/games/tetris.jpg");
        tetris = new ImageView(new Image(String.valueOf(tetrisURL)));
        tetris.setFitWidth(300);
        tetris.setFitHeight(400);
        tetris.setTranslateX(400);
        tetris.setTranslateY(30);
        tetris.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event)
            {
                Main otherClass = new Main();
                otherClass.start();
            }
        });

        root.getChildren().clear();
        root.getChildren().addAll(bejeweled, tetris);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
