package bejeweled2;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Timer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import java.awt.event.ActionListener;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Bejeweled2_Refactor_Derek extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BejeweledGameLogic.getInstance().startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
