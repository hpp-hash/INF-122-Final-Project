package tetris.src.sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
    Stage stage;

    public TetrisUI(){
        group = new Pane();

        scene = new Scene(group, XMAX + 150, YMAX);

        stage = new Stage();

        line = new Line(XMAX, 0, XMAX, YMAX);

        scoreText = new Text("Score: ");
        scoreText.setStyle("-fx-font: 20 arial;");
        scoreText.setY(50);
        scoreText.setX(XMAX + 5);

        group.getChildren().addAll(scoreText, line);

        stage.setScene(scene);
        stage.setTitle("INF 122 - Tetris");
        stage.show();
    }
}
