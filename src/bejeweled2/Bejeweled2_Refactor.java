/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bejeweled2;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
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

import java.util.Random;


/**
 *
 * @author chuon
 */
public class Bejeweled2_Refactor extends Application {
    int gameWidth = 1280, gameHeight = 720, gemSize = 64;
    int row = 10, column = 14;
    Group root = new Group();
    private ImageView cursor, background;
    private BejeweledTileMap bejTileMap;
    int cX = 0, cY = 0, tX = 0, tY = 0;
    Label label;
    @Override    
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("INF122 Team 6 - Bejeweled Fruits");
        
        setup();
        draw();

        Scene scene = new Scene(root, gameWidth, gameHeight, Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void setup() {
        // Set Background
        //background = new ImageView(new Image("images/border.png"));
        background = new ImageView(new Image("images/bejeweled2/images/border.png"));
        background.setFitWidth(gameWidth);
        background.setFitHeight(gameHeight);
        
        // Set Cursor
        //cursor = new ImageView(new Image("images/cursor.png"));
        cursor = new ImageView(new Image("images/bejeweled2/images/cursor.png"));
        cursor.setFitWidth(gemSize);
        cursor.setFitHeight(gemSize);
        
        bejTileMap = new BejeweledTileMap(row, column);

        label = new Label();
        label.setTranslateX(gameWidth - 330);
        label.setTranslateY(200);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

    }
    private void draw()
    {
        root.getChildren().clear();
        root.getChildren().add(background);
        root.getChildren().add(label);
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < column; c++) {
                cells[r][c].setFitWidth(gemSize);
                cells[r][c].setFitHeight(gemSize);
                cells[r][c].setTranslateX(c * gemSize + 20);
                cells[r][c].setTranslateY(r * gemSize + 30);
                if(c == tX && r == tY)
                {
                    cursor.setTranslateX(c * gemSize + 20);
                    cursor.setTranslateY(r * gemSize + 30);
                    root.getChildren().add(cursor);
                }
                root.getChildren().add(cells[r][c]);
                cells[r][c].setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        cX = tX = (int)((event.getSceneX() - 20) / gemSize);
                        cY = tY = (int)((event.getSceneY() - 30) / gemSize);
                        draw();
                    }
                });
                cells[r][c].setOnMouseReleased(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        tX = (int)((event.getSceneX() - 20) / gemSize);
                        tY = (int)((event.getSceneY() - 30) / gemSize);
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
        transition.setToX(X * gemSize + 20);
        transition.setToY(Y * gemSize + 30);
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
            if(y + 1 >= row - 1)down = false;
            if(y - 1 <= 0)up = false;
            if(x + 1 >= column - 1)right = false;
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
    public static void main(String[] args) {
        launch(args);
    }
}
