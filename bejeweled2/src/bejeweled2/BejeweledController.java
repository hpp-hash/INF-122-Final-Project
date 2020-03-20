package bejeweled2;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import network.Network;
import player.Multiplayer;
import player.Player;
import tileEntityFactory.NextTileEntity;
import tilemap.TileEntity;

import java.net.*;
import java.util.*;

public class BejeweledController{

    private static int DEFAULT_GAMELENGTH = 30;
    int counter = 0;
    private final int GAME_WIDTH = 1280, GAME_HEIGHT = 720, GEM_SIZE = 64;
    private final int ROW = 10, COLUMN = 14;
    private Group root = new Group();
    private ImageView cursor, background;
    BejeweledTileMap map = BejeweledTileMap.getInstance(ROW, COLUMN);
    private int cX = 0, cY = 0, tX = 0, tY = 0;
    private Label label;
    private Label gameoverLabel;
    private static Label showTime;
    private static int gameLength = DEFAULT_GAMELENGTH; // in seconds
    static Timer timer;

    private Label player1Label;
    private Label player2Label;
    private Region region1;
    private Region region2;
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
    private Label currentScoreLabel;
    private Button player2Button;
    private Button playAgainButton;
    private Button exitButton;

    private Multiplayer bejMultiplayer;

    // User Network
    private Label userLabel;
    private TextField userField;
    private Button userLogin;
    private static int currentUser;
    protected static LinkedList<Integer> GEM_SET;

    private Random rand = new Random();
    private String[] dialogue = {"Somethin's Cookin'", "You're On Fire!", "Indiana Jones\nin the House",
        "That's What's Up", "Nice!", "Jewel THIEF!", "BIG BRAIN PLAYS", "Your IQ > Einstein"};
    private int score = 0;

    private static BejeweledController controller;

    private BejeweledController() {}

    public static BejeweledController getInstance(){
        if (controller == null){
            controller = new BejeweledController();
        }
        return controller;
    }

    public void moveOnMouseDrag() {
        for(int r = 0; r < ROW; r++) {
            for(int c = 0; c < COLUMN; c++) {
                ImageView currentTileImageView = map.getTile(r,c).getTileEntity().getImgV();
                currentTileImageView.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        cX = tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                        cY = tY = (int)((event.getSceneY() - 30) / GEM_SIZE);
                        draw();
                        counter++;
                    }
                });

                currentTileImageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                        tY = (int)((event.getSceneY() - 30) / GEM_SIZE);

                        TileEntity temp = map.getTile(tY, tX).getTileEntity();
                        map.getTile(tY, tX).addEntity(map.getTile(cY, cX).getTileEntity());
                        map.getTile(cY, cX).addEntity(temp);

                        if (eatable(tY, tX) || eatable(cY, cX)) {
                            label.setText("Your Score: " + score + "\n     --Cleared--\n\n" + dialogue[rand.nextInt(dialogue.length)]);
                            currentScoreLabel.setText("Score: " + score);

                        } else {
                            label.setText("Your Score: " + score + "\n--None Cleared--\nSwapping Back");
                            moveAnimation(tX, tY, cX, cY);
                            moveAnimation(cX, cY, tX, tY);
                            temp = map.getTile(tY, tX).getTileEntity();
                            map.getTile(tY, tX).addEntity(map.getTile(cY, cX).getTileEntity());
                            map.getTile(cY, cX).addEntity(temp);
                        }
                        draw();
                        counter++;
                        }
                    });
            }
        }
    }

    // placeholder for non-movement handling; can involve keyboard or mouse
    public void otherMove(){
        // TODO: implement other moves (if any)
    }

    public void setupPlayers() {
        bejMultiplayer = new Multiplayer(false);
        currentScoreLabel = player1ScoreLabel;
        bejMultiplayer.setPlayer1HighScore(score);
    }


    public void setup() {
        GEM_SET = new LinkedList<>();
        URL borderURL = this.getClass().getResource("/images/border.png");
        background = new ImageView(new Image(String.valueOf(borderURL)));
        background.setFitWidth(GAME_WIDTH);
        background.setFitHeight(GAME_HEIGHT);

        URL cursorURL = this.getClass().getResource("/images/cursor.png");
        cursor = new ImageView(new Image(String.valueOf(cursorURL)));
        cursor.setFitWidth(GEM_SIZE);
        cursor.setFitHeight(GEM_SIZE);

        label = new Label("Player 1 login!");
        label.setTranslateX(GAME_WIDTH - 330);
        label.setTranslateY(200);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        gameoverLabel = new Label();
        gameoverLabel.setTranslateX(320);
        gameoverLabel.setTranslateY(200);
        gameoverLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 35));

        showTime = new Label();
        showTime.setTranslateX(GAME_WIDTH - 150);
        showTime.setTranslateY(375);
        showTime.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        player1Label = new Label();
        makePlayer1LabelCurrent();
        player1Label.getStyleClass().add("outline");
        region1 = new Region();
        region1.setPrefSize(245, 50);
        region1.setStyle("-fx-background-color: white; -fx-background-radius: 10 10 10 10");
        region1.setTranslateX(GAME_WIDTH - 300);
        region1.setTranslateY(490);
        player1ScoreLabel = new Label("Score: 0");
        player1ScoreLabel.setTranslateX(GAME_WIDTH-230);
        player1ScoreLabel.setTranslateY(503);
        player1ScoreLabel.setFont(Font.font("Arial", FontWeight.BLACK, 20));

        player2Label= new Label();
        resetPlayer2Label();
        player2Label.getStyleClass().add("outline");
        region2 = new Region();
        region2.setPrefSize(245, 50);
        region2.setStyle("-fx-background-color: white; -fx-background-radius: 10 10 10 10");
        region2.setTranslateX(GAME_WIDTH - 300);
        region2.setTranslateY(615);
        player2ScoreLabel = new Label("Score: 0");
        player2ScoreLabel.setTranslateX(GAME_WIDTH-230);
        player2ScoreLabel.setTranslateY(628);
        player2ScoreLabel.setFont(Font.font("Arial", FontWeight.BLACK, 20));

        player2Button = new Button("Player 2's turn!");
        player2Button.setFont(Font.font("Arial", FontWeight.BLACK, 15));
        player2Button.setTranslateX(400);
        player2Button.setTranslateY(500);
        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                reset();
            }
        });

        playAgainButton = new Button(("Play Again!"));
        playAgainButton.setFont(Font.font("Arial", FontWeight.BLACK, 15));
        playAgainButton.setTranslateX(350);
        playAgainButton.setTranslateY(500);
        playAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                player1ScoreLabel.setText("Score: 0");
                player2ScoreLabel.setText("Score: 0");
                reset();
            }
        });

        exitButton = new Button(("Exit"));
        exitButton.setFont(Font.font("Arial", FontWeight.BLACK, 15));
        exitButton.setTranslateX(550);
        exitButton.setTranslateY(500);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        map.fillMap();
        setupPlayers();
        loginForm();
    }

    /* **************************************** */
    /*                 Network                  */
    /* **************************************** */
    private void loginForm()
    {
        userLabel = new Label("Username: ");
        userLabel.setTranslateX(GAME_WIDTH - 345);
        userLabel.setTranslateY(35);
        userLabel.getStyleClass().add("login");
        userField = new TextField();
        userField.setPrefHeight(30);
        userField.setTranslateX(GAME_WIDTH - 200);
        userField.setTranslateY(40);
        userLogin = new Button("Login");
        userLogin.setTranslateX(GAME_WIDTH - 200);
        userLogin.setTranslateY(100);
        userLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                userField.setDisable(true);
                userLogin.setDisable(true);
                userField.clear();
                String output = Network.postQuery("bejeweled", userField.getText());
                String[] arrOfStr = output.split(",");
                currentUser = Integer.parseInt(arrOfStr[0]);
                for(int i = 1; i < 1000; i++)
                    GEM_SET.push(Integer.parseInt(arrOfStr[i]));
                label.setText("Go!");
                gameLength = DEFAULT_GAMELENGTH;

                setTimmer();
            }
        });
    }

    /* **************************************** */
    /*                 Timmer                   */
    /* **************************************** */
    private void setTimmer()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    showTime.setText(S2H(setInterval()));
                });
            }
        }, 1000, 1000);
    }

    private int setInterval() {
        if (gameLength == 1)
        {
            timer.cancel();
            Network.checkResult("bejeweled", currentUser, score);
            gameOver();
            Platform.runLater(() -> {
                showTime.setText("Game Over!");
            });
        }
        return --gameLength;
    }

    private static String S2H(int secs)
    {
        int nDays = secs / 86400;
        int nHours = (secs % 86400 ) / 3600;
        int nMinutes = ((secs % 86400 ) % 3600 ) / 60;
        int nSeconds = ((secs % 86400 ) % 3600 ) % 60;
        return String.format("%02d", nHours) + ":" + String.format("%02d", nMinutes) + ":" + String.format("%02d", nSeconds);
    }
    // End Timmer

    private void reset() {
        showTime.setText("");
        bejMultiplayer.getCurrentPlayer().saveSession(score);

        if (!bejMultiplayer.isPlayer2GameActive()) {
            bejMultiplayer.switchPlayer();
            bejMultiplayer.setPlayer1HighScore(score);
            currentScoreLabel = player2ScoreLabel;
            makePlayer2LabelCurrent();
            resetPlayer1Label();
        }
        else {
            bejMultiplayer.switchPlayer();
            bejMultiplayer.setPlayer2HighScore(score);
            currentScoreLabel = player1ScoreLabel;
            makePlayer1LabelCurrent();
            resetPlayer2Label();
        }

        score = 0;
        bejMultiplayer.switchActiveGame();

        gameLength = DEFAULT_GAMELENGTH;


        root.getChildren().clear();
        map.fillMap();
        draw();
    }
    private void gameOver()
    {
        root.getChildren().clear();
        root.getChildren().add(background);

        gameoverLabel.setText(bejMultiplayer.getCurrentPlayer().getName() + " Score: " + score + "\n\n" + dialogue[rand.nextInt(dialogue.length)]);

        root.getChildren().add(gameoverLabel);
        root.getChildren().add(showTime);
        root.getChildren().addAll(player1Label, region1, player1ScoreLabel);
        root.getChildren().addAll(player2Label, region2, player2ScoreLabel);

        userField.setDisable(false);
        userLogin.setDisable(false);
        root.getChildren().addAll(userLogin, userLabel, userField);

        if(!bejMultiplayer.isPlayer2GameActive()) {
            label.setText("Player 2 login!");
            root.getChildren().add(player2Button);
        }
        else {
            label.setText("Player 1 login!");
            bejMultiplayer.getCurrentPlayer().saveSession(score);
            bejMultiplayer.setPlayer2HighScore(score);

            String winner;
            if(bejMultiplayer.getPlayer1HighScore() == bejMultiplayer.getPlayer2HighScore())
                winner = "IT'S A TIE!!\n\nPlay a tiebreaker!";
            else {
                winner = bejMultiplayer.getPlayer1HighScore() >= bejMultiplayer.getPlayer2HighScore()? "Player 1": "Player 2";
                winner = winner + " WINS!!\n\nCONGRATULATIONS!";
            }

            pauseBeforeWinnerAnnouance(winner);
            resetPlayer2Label();
        }
    }

    private void resetPlayer1Label() {
        player1Label.setText("Player 1");
        player1Label.setTranslateX(GAME_WIDTH - 240);
    }

    private void resetPlayer2Label() {
        player2Label.setText("Player 2");
        player2Label.setTranslateX(GAME_WIDTH - 240);
        player2Label.setTranslateY(575);
    }

    private void makePlayer1LabelCurrent() {
        player1Label.setText("Player 1 (current)");
        player1Label.setTranslateX(GAME_WIDTH - 300);
        player1Label.setTranslateY(450);
    }

    private void makePlayer2LabelCurrent() {
        player2Label.setText("Player 2 (current)");
        player2Label.setTranslateX(GAME_WIDTH - 300);
    }

    private void pauseBeforeWinnerAnnouance(String text) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                gameoverLabel.setText(text);
                root.getChildren().addAll(playAgainButton, exitButton);
            }
        });
        new Thread(sleeper).start();
    }

    public void draw()
    {
        root.getChildren().clear();
        root.getChildren().add(background);
        root.getChildren().add(label);
        root.getChildren().add(showTime);
        root.getChildren().addAll(player1Label, region1, player1ScoreLabel);
        root.getChildren().addAll(player2Label, region2, player2ScoreLabel);
        root.getChildren().addAll(userLabel, userField, userLogin);

        for(int r = 0; r < ROW; r++) {
            for(int c = 0; c < COLUMN; c++) {
                if(c == tX && r == tY)
                {
                    cursor.setTranslateX(c * GEM_SIZE + 20);
                    cursor.setTranslateY(r * GEM_SIZE + 30);
                    root.getChildren().add(cursor);
                }
                root.getChildren().add(map.getTile(r,c).getTileEntity().getImgV());
            }
        }
        moveOnMouseDrag();
        otherMove();
    }
    public void moveAnimation(int X, int Y, int dX, int dY)
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(map.getTile(Y,X).getTileEntity().getImgV());
        transition.setDuration(Duration.seconds(1));
        transition.setToX(dX * GEM_SIZE + 20);
        transition.setToY(dY * GEM_SIZE + 30);
        transition.play();
    }
    public boolean eatable(int rowIndex, int colIndex) {
        int count = 0, maxCount = 0;
        int startColIndex = colIndex, startRowIndex = rowIndex;
        int begin = -3, end = 3;
        boolean vertical = false;

        NextTileEntity nextTileEntity = new NextTileEntity(BejeweledGemFactory.getInstance());
        String[] tileEntityNames = {"blue", "green", "orange", "purple", "red", "white", "yellow"};
        Random random = new Random();

        // Case - Vertical
        while(rowIndex + begin < 0)begin++;
        while(rowIndex + end > ROW - 1)end--;
        for(int i = begin; i <= end; i++)
        {
            if(map.getTile(rowIndex,colIndex).getTileEntity().getIconSrc().equals(map.getTile(rowIndex + i,colIndex).getTileEntity().getIconSrc()))
            {
                count++;
                if(count >= 2)
                {
                    startRowIndex = rowIndex + i - count + 1;
                    if(startRowIndex < 0)
                    {
                        startRowIndex = 0;
                        count--;
                    }
                    maxCount = count;
                }
            }else count = 0;
        }
        if(maxCount > 2)vertical = true;

        // Case - Horizontal
        begin = -3;
        end = 3;
        if(maxCount <= 2)
        {
            while(colIndex + begin < 0)begin++;
            while(colIndex + end > COLUMN - 1)end--;
            for(int i = begin; i <= end; i++)
            {
                if(map.getTile(rowIndex,colIndex).getTileEntity().getIconSrc().equals(map.getTile(rowIndex,colIndex + i).getTileEntity().getIconSrc()))
                {
                    count++;
                    if(count >= 2)
                    {
                        startColIndex = colIndex + i - count + 1;
                        if(startColIndex < 0)
                        {
                            startColIndex = 0;
                            count--;
                        }
                        maxCount = count;
                    }
                }else count = 0;
            }
        }
        int randomIndex;
        if(maxCount > 2)
        {
            for(int i = 0; i < maxCount; i++){
                if(BejeweledController.GEM_SET.size() == 0)
                    randomIndex = random.nextInt(tileEntityNames.length);
                else
                    randomIndex = BejeweledController.GEM_SET.pop();
                if(vertical == true) {
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(startRowIndex + i, startColIndex));
                    int temp = score;
                    eatable(startRowIndex + i, startColIndex);
                    score = temp;
                }
                else {
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(startRowIndex, startColIndex + i));
                    int temp = score;
                    eatable(startRowIndex, startColIndex + i);
                    score = temp;
                }
            }
            score += maxCount * 10;
            return true;
        }

        return false;
    }

    public int getCounter(){
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getGAME_WIDTH() {
        return GAME_WIDTH;
    }

    public int getGAME_HEIGHT() {
        return GAME_HEIGHT;
    }

    public int getROW() {
        return ROW;
    }

    public int getCOLUMN() {
        return COLUMN;
    }

    public Group getRoot() {
        return root;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
