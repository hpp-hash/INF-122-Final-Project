package bejeweled2;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import tmge.GameLogic;
import tmge.UserInputController;

import java.util.Random;

public class BejeweledGameLogic extends GameLogic {
    private final int GAME_WIDTH = 1280, GAME_HEIGHT = 720, GEM_SIZE = 64;
    private final int ROW = 10, COLUMN = 14;
    Group root = new Group();
    private ImageView cursor, background;
    private Image[] images;
    private ImageView[][] cells;
    int[][] board;
    private int cX = 0, cY = 0, tX = 0, tY = 0;
    Label label;

    private static BejeweledGameLogic gameLogic;

    public static BejeweledGameLogic getInstance(){
        if (gameLogic == null){
            gameLogic = new BejeweledGameLogic();
        }
        return gameLogic;
    }

    @Override
    public void initializeTileMap() {
        Stage gameStage = new Stage();
        gameStage.setTitle("INF122 Team 6 - Bejeweled Fruits");

        setup();
        draw();

        Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT, Color.TRANSPARENT);
        gameStage.setScene(scene);
        gameStage.show();
    }

    @Override
    public void generateTileEntity() {

    }

    @Override
    public void handleUserInput() {
        for(int r = 0; r < ROW; r++) {
            for(int c = 0; c < COLUMN; c++) {
                cells[r][c].setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        cX = tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                        cY = tY = (int)((event.getSceneY() - 30) / GEM_SIZE);
                        draw();
                    }
                });
                cells[r][c].setOnMouseReleased(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                        tY = (int)((event.getSceneY() - 30) / GEM_SIZE);
                        int temp = board[tY][tX];
                        board[tY][tX] = board[cY][cX];
                        board[cY][cX] = temp;

                        ImageView tempI = cells[tY][tX];
                        cells[tY][tX] = cells[cY][cX];
                        cells[cY][cX] = tempI;

                        moveAnimation(tX, tY);
                        moveAnimation(cX, cY);

                        if(eatable(tY, tX) || eatable(cY, cX))
                        {
                            label.setText("EatAble");
                        }
                        else
                        {
                            label.setText("Swap Back");
                            temp = board[tY][tX];
                            board[tY][tX] = board[cY][cX];
                            board[cY][cX] = temp;


                            tempI = cells[tY][tX];
                            cells[tY][tX] = cells[cY][cX];
                            cells[cY][cX] = tempI;
                        }
                        draw();
                    }
                });
            }
        }
        BejeweledController controller = new BejeweledController();
        BejeweledInputAdapter inputAdapter = new BejeweledInputAdapter(controller);
        UserInputController.getInstance(inputAdapter).onInput();
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

    private void setup() {
        // Set Background
        background = new ImageView(new Image("images/bejeweled2/images/border.png"));
        background.setFitWidth(GAME_WIDTH);
        background.setFitHeight(GAME_HEIGHT);

        // Set Cursor
        cursor = new ImageView(new Image("images/bejeweled2/images/cursor.png"));
        cursor.setFitWidth(GEM_SIZE);
        cursor.setFitHeight(GEM_SIZE);

        images = new Image[7];
        images[0] = new Image("images/bejeweled2/fruits/blue.png");
        images[1] = new Image("images/bejeweled2/fruits/green.png");
        images[2] = new Image("images/bejeweled2/fruits/orange.png");
        images[3] = new Image("images/bejeweled2/fruits/purple.png");
        images[4] = new Image("images/bejeweled2/fruits/red.png");
        images[5] = new Image("images/bejeweled2/fruits/white.png");
        images[6] = new Image("images/bejeweled2/fruits/yellow.png");

        cells = new ImageView[ROW][COLUMN];
        board = new int[ROW][COLUMN];

        label = new Label();
        label.setTranslateX(GAME_WIDTH - 330);
        label.setTranslateY(200);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        Random random = new Random();
        ImageView tempImage;
        for(int r = 0; r < ROW; r++) {
            for(int c = 0; c < COLUMN; c++) {
                int randImageIndex = random.nextInt(images.length);
                tempImage = new ImageView(images[randImageIndex]);
                cells[r][c] = tempImage;
                board[r][c] = randImageIndex;
            }
        }
    }
    private void draw()
    {
        root.getChildren().clear();
        root.getChildren().add(background);
        root.getChildren().add(label);
        for(int r = 0; r < ROW; r++) {
            for(int c = 0; c < COLUMN; c++) {
                cells[r][c].setFitWidth(GEM_SIZE);
                cells[r][c].setFitHeight(GEM_SIZE);
                cells[r][c].setTranslateX(c * GEM_SIZE + 20);
                cells[r][c].setTranslateY(r * GEM_SIZE + 30);
                if(c == tX && r == tY)
                {
                    cursor.setTranslateX(c * GEM_SIZE + 20);
                    cursor.setTranslateY(r * GEM_SIZE + 30);
                    root.getChildren().add(cursor);
                }
                root.getChildren().add(cells[r][c]);
            }
        }
    }
    private void swapInt(int a, int b)
    {
        int temp = a;
        a = b;
        b = temp;
    }
    private void moveAnimation(int X, int Y)
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setToX(X * GEM_SIZE + 20);
        transition.setToY(Y * GEM_SIZE + 30);
        transition.setNode(cells[Y][X]);
        transition.play();
    }
    private boolean eatable(int y, int x) {
        int count = 1;
        boolean up = true, down = true, left = true, right = true;
        Random random = new Random();
        ImageView tempImage;
        for(int i = 1; i < 3; i++)
        {
            if(y + 1 >= ROW - 1)down = false;
            if(y - 1 <= 0)up = false;
            if(x + 1 >= COLUMN - 1)right = false;
            if(x - 1 <= 0)left = false;
        }
        if(up == true && down == true)
        {
            if(board[y][x] == board[y + 1][x] && board[y][x] == board[y - 1][x])count = 3;
            if(count == 3)
            {
                for(int i = -1; i < 2; i++){
                    int randImageIndex = random.nextInt(images.length);
                    tempImage = new ImageView(images[randImageIndex]);
                    cells[y - i][x] = tempImage;
                    board[y - i][x] = randImageIndex;
                }
                return true;
            }
        }
        if(left == true && right == true)
        {
            if(board[y][x] == board[y][x - 1] && board[y][x] == board[y][x + 1])count = 3;
            if(count == 3)
            {
                for(int i = -1; i < 2; i++){
                    int randImageIndex = random.nextInt(images.length);
                    tempImage = new ImageView(images[randImageIndex]);
                    cells[y][x - i] = tempImage;
                    board[y][x - i] = randImageIndex;
                }
                return true;
            }
        }
        if(up == true)
        {
            for(int i = 1; i < 3; i++){
                if(board[y][x] == board[y - i][x])count++;
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randImageIndex = random.nextInt(images.length);
                    tempImage = new ImageView(images[randImageIndex]);
                    cells[y - i][x] = tempImage;
                    board[y - i][x] = randImageIndex;
                }
                return true;
            }
        }
        if(down == true)
        {
            for(int i = 1; i < 3; i++){
                if(board[y][x] == board[y + i][x])count++;
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randImageIndex = random.nextInt(images.length);
                    tempImage = new ImageView(images[randImageIndex]);
                    cells[y + i][x] = tempImage;
                    board[y + i][x] = randImageIndex;
                }
                return true;
            }
        }
        if(left == true)
        {
            for(int i = 1; i < 3; i++){
                if(board[y][x] == board[y][x - i])count++;
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randImageIndex = random.nextInt(images.length);
                    tempImage = new ImageView(images[randImageIndex]);
                    cells[y][x - i] = tempImage;
                    board[y][x - i] = randImageIndex;
                }
                return true;
            }
        }
        if(right == true)
        {
            for(int i = 1; i < 3; i++){
                if(board[y][x] == board[y][x + i])count++;
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randImageIndex = random.nextInt(images.length);
                    tempImage = new ImageView(images[randImageIndex]);
                    cells[y][x + i] = tempImage;
                    board[y][x + i] = randImageIndex;
                }
                return true;
            }
        }
        return false;
    }
}
