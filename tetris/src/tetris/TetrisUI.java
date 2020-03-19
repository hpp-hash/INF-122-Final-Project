package tetris;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Stack;


public class TetrisUI {

    private static TetrisUI instance = null;

    private Pane group;

    private Scene scene;

    private Line line;

    private Text playerText;
    private Text player1Text;

    private Text scoreText;
    private Text scoreText1;

    private Text gameOverText;

    Stage stage;

    Button player2Btn;
    Button restartBtn;
    Button exitBtn;

    private Stack<Form> formStack;

    private TetrisUI() throws FileNotFoundException, URISyntaxException {

        formStack = new Stack<Form>();

        group = new Pane();

        group.setId("pane");

        scene = new Scene(group, TetrisGameLogic.XMAX + 150 , TetrisGameLogic.YMAX);
        scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());

        stage = new Stage();

        FileInputStream inputStream;
        Image image;
        ImageView imageView;

        ClassLoader resource = this.getClass().getClassLoader();
        URL path = this.getClass().getResource("/logo.png");
        System.out.println(path.getPath());
        inputStream = new FileInputStream(new File(path.getPath()));
        image = new Image(inputStream);
        imageView = new ImageView(image);

        imageView.setX(TetrisGameLogic.XMAX + 15);
        imageView.setY(130);

        imageView.setFitWidth(119);
        imageView.setFitHeight(95.4444);


        line = new Line(TetrisGameLogic.XMAX, 0, TetrisGameLogic.XMAX, TetrisGameLogic.YMAX);

        playerText = new Text("Player 1 (current)");
        playerText.setStyle("-fx-font: 15 arial;");
        playerText.setY(260);
        playerText.setX(TetrisGameLogic.XMAX + 5);

        player1Text = new Text("Player 2");
        player1Text.setStyle("-fx-font: 15 arial;");
        player1Text.setY(330);
        player1Text.setX(TetrisGameLogic.XMAX + 5);

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

        scoreText1 = new Text("Score: 0");
        scoreText1.setStyle("-fx-font: 15 arial;");
        scoreText1.setY(360);
        scoreText1.setX(TetrisGameLogic.XMAX + 15);


        gameOverText = new Text();
        gameOverText.setFill(Color.BLACK);
        gameOverText.setStyle("-fx-font: 40 arial;");
        gameOverText.setY(TetrisGameLogic.YMAX / 2);
        gameOverText.setX((35));

        group.getChildren().addAll(line, gameOverText, rectangle, imageView, scoreText, rectangle1, playerText, player1Text, scoreText1);

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

    public void setScore1(int score1){
        scoreText1.setText("Score: " + score1);
    }

    public void addPlayer2Btn() {
        if (!TetrisGameLogic.getInstance().getAlreadyAdded()) {
            player2Btn = new Button("Player 2's Turn");
            player2Btn.relocate(TetrisGameLogic.XMAX + 20, 400);
            player2Btn.setStyle("-fx-font-size: 15px;");
            player2Btn.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    TetrisGameLogic.getInstance().setRemovePlayer2Btn(true);
                }
            });

            getPane().getChildren().addAll(player2Btn);
        }

    }

    public void changePlayerText(String player, String player1) {
        playerText.setText(player);
        player1Text.setText(player1);
    }

    public void resetPlayer() {
        for (Form entry : formStack) {
            getPane().getChildren().removeAll(entry.getA(), entry.getB(), entry.getC(), entry.getD());
        }

        formStack.clear();
        setGameOverText(false);
        playerText.setText("Player 1");
        player1Text.setText("Player 2 (current)");
    }

    public void removePlayer2Btn() {
        System.out.println("hi");
        getPane().getChildren().remove(player2Btn);
    }

    public void exitBtn() {
        exitBtn = new Button("Exit");
        exitBtn.relocate(TetrisGameLogic.XMAX + 55, 400);
        exitBtn.setStyle("-fx-font-size: 15px;");
        exitBtn.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });

        getPane().getChildren().addAll(exitBtn);
    }

    public void restartBtn() {
        restartBtn = new Button("Restart");
        restartBtn.relocate(TetrisGameLogic.XMAX + 45, 450);
        restartBtn.setStyle("-fx-font-size: 15px;");
        restartBtn.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TetrisGameLogic.getInstance().setRestartStatus(true);
            }
        });

        getPane().getChildren().addAll(restartBtn);
    }

    public void restartGame() {
        for (Form entry : formStack) {
            getPane().getChildren().removeAll(entry.getA(), entry.getB(), entry.getC(), entry.getD());
        }

        formStack.clear();
        getPane().getChildren().removeAll(restartBtn, exitBtn);
        setGameOverText(false);
        playerText.setText("Player 1 (current)");
        player1Text.setText("Player 2");
        TetrisGameLogic.getInstance().score = 0;
        TetrisGameLogic.getInstance().score1 = 0;
        scoreText.setText("Score: 0");
        scoreText1.setText("Score: 0");
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
        formStack.push(block);
    }

    public Scene getScene(){
        return scene;
    }

    public Pane getPane() {
        return group;
    }
}
