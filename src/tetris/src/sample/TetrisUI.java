package tetris.src.sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TetrisUI {
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XMAX = SIZE * 12;
    public static final int YMAX = SIZE * 24;

    private Pane group;

    private Scene scene;

    private Line line;

    private Text scoreText;
    private Text gameOverText;

    Stage stage;

    public TetrisUI(){
        group = new Pane();

        scene = new Scene(group, XMAX + 150, YMAX);

        stage = new Stage();

        line = new Line(XMAX, 0, XMAX, YMAX);

        scoreText = new Text();
        scoreText.setStyle("-fx-font: 20 arial;");
        scoreText.setY(50);
        scoreText.setX(XMAX + 5);

        gameOverText = new Text();
        gameOverText.setFill(Color.BLUE);
        gameOverText.setStyle("-fx-font: 70 arial;");
        gameOverText.setY(250);
        gameOverText.setX(10);

        group.getChildren().addAll(scoreText, line, gameOverText);

        stage.setScene(scene);
        stage.setTitle("INF 122 - Tetris");
        stage.show();
    }

    public void setScore(int score){
        scoreText.setText("Score: " + score);
    }

    public void setGameOverText(boolean isOver){
        if(isOver){
            gameOverText.setText("GAME OVER!");
        } else {
            gameOverText.setText("");
        }
    }
}
