package tetris.src.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tmge.GameLogic;


public class TetrisGameLogic extends GameLogic {
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static int XMAX = SIZE * 12;
    public static int YMAX = SIZE * 24;
    public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private static Pane group = new Pane();
    private static Form object;
    private static Scene scene = new Scene(group, XMAX + 150, YMAX);
    public static int score = 0;
    private static int top = 0;
    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();
    private static int linesNo = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initializeTileMap(stage);
    }

    // initialize the background
    public void initializeTileMap(Stage stage) {
        for (int[] a : MESH) {
            Arrays.fill(a, 0);
        }

        Line line = new Line(XMAX, 0, XMAX, YMAX);
        Text scoretext = new Text("Score: ");
        scoretext.setStyle("-fx-font: 20 arial;");
        scoretext.setY(50);
        scoretext.setX(XMAX + 5);
//		Text level = new Text("Lines: ");
//		level.setStyle("-fx-font: 20 arial;");
//		level.setY(100);
//		level.setX(XMAX + 5);
//		level.setFill(Color.GREEN);
        group.getChildren().addAll(scoretext, line);

        Form a = nextObj;
        group.getChildren().addAll(a.a, a.b, a.c, a.d);
//        moveOnKeyPress(a);
        object = a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("INF 122 - Tetris");
        stage.show();

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0
                                || object.d.getY() == 0)
                            top++;
                        else
                            top = 0;

                        if (top == 2) {
                            // GAME OVER
                            Text over = new Text("GAME OVER");
                            over.setFill(Color.RED);
                            over.setStyle("-fx-font: 70 arial;");
                            over.setY(250);
                            over.setX(10);
                            group.getChildren().add(over);
                            game = false;
                        }
                        // Exit
                        if (top == 15) {
                            System.exit(0);
                        }

                        if (game) {
//                            MoveDown(object);
                            scoretext.setText("Score: " + Integer.toString(score));
//							level.setText("Lines: " + Integer.toString(linesNo));
                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 300);
    }

    @Override
    public void generateTileEntity() {

    }

    @Override
    public void handleUserInput() {

    }

    @Override
    public void clearTiles() {

    }

    @Override
    public boolean checkEndGame() {
        return false;
    }

    @Override
    public void save() {

    }

    @Override
    public void quit() {

    }
}
