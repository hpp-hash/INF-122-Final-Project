package tetris.src.sample.UI;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tetris.src.sample.Form;
import tetris.src.sample.TetrisGameLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;


public class TetrisUI {

    private static TetrisUI instance = null;

    private Pane group;

    private Scene scene;

    private Line line;

    private Text playerText;

    private Text scoreText;

    private Text gameOverText;

    Stage stage;

    private TetrisUI() throws FileNotFoundException, URISyntaxException {
        group = new Pane();

        group.setId("pane");

        scene = new Scene(group, TetrisGameLogic.XMAX + 150 , TetrisGameLogic.YMAX);
        scene.getStylesheets().addAll(this.getClass().getResource("/resources/style.css").toExternalForm());

        stage = new Stage();

        FileInputStream inputStream;
        Image image;
        ImageView imageView;

        ClassLoader resource = this.getClass().getClassLoader();
        URL path = this.getClass().getResource("/resources/logo.png");
        inputStream = new FileInputStream(new File(path.toURI()));
        image = new Image(inputStream);
        imageView = new ImageView(image);

        imageView.setX(TetrisGameLogic.XMAX + 15);
        imageView.setY(130);

        imageView.setFitWidth(119);
        imageView.setFitHeight(95.4444);


        line = new Line(TetrisGameLogic.XMAX, 0, TetrisGameLogic.XMAX, TetrisGameLogic.YMAX);

        playerText = new Text("Player 1 (left)");
        playerText.setStyle("-fx-font: 15 arial;");
        playerText.setY(260);
        playerText.setX(TetrisGameLogic.XMAX + 5);

        Region rectangle = new Region();
        rectangle.setPrefSize(140, 40);
        rectangle.setStyle("-fx-background-color: white; -fx-background-radius: 10 10 10 10");
        rectangle.relocate(TetrisGameLogic.XMAX + 5, 264);

        Region rectangle1 = new Region();
        rectangle1.setPrefSize(140, 40);
        rectangle1.setStyle("-fx-background-color: white; -fx-background-radius: 10 10 10 10");
        rectangle1.relocate(TetrisGameLogic.XMAX + 5, 334);

        scoreText = new Text();
        scoreText.setStyle("-fx-font: 15 arial;");
        scoreText.setY(290);
        scoreText.setX(TetrisGameLogic.XMAX + 15);


        gameOverText = new Text();
        gameOverText.setFill(Color.BLUE);
        gameOverText.setStyle("-fx-font: 30 arial;");
        gameOverText.setY(TetrisGameLogic.YMAX / 2);
        gameOverText.setX((50));

        group.getChildren().addAll(line, gameOverText, rectangle, imageView, scoreText, rectangle1, playerText);

        stage.setScene(scene);
        stage.setTitle("INF 122 - Tetris");
        stage.show();
    }

    public static TetrisUI getInstance() throws FileNotFoundException, URISyntaxException {
        if(instance == null){
            instance = new TetrisUI();
        }
        return instance;
    }

    public void setScore(int score){
        scoreText.setText("Score: " + score);
    }

    public void setGameOverText(boolean isOver){
        if(isOver){
            gameOverText.setText("GAME OVER");
            gameOverText.toFront();
        }
        else {
            gameOverText.setText("");
        }
    }

    public void addBlock(Form block) {
        group.getChildren().addAll(block.getA(), block.getB(), block.getC(), block.getD());
    }

    public Scene getScene(){
        return scene;
    }

    public Pane getPane() {
        return group;
    }
}
