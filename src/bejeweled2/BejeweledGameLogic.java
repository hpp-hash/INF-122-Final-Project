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
import tmge.NextTileEntity;
import tmge.TileEntity;
import tmge.UserInputController;

import java.util.Random;

public class BejeweledGameLogic extends GameLogic {
    private final int GAME_WIDTH = 1280, GAME_HEIGHT = 720, GEM_SIZE = 64;
    private final int ROW = 10, COLUMN = 14;
    Group root = new Group();
    private ImageView cursor, background;
    BejeweledTileMap map = new BejeweledTileMap(ROW, COLUMN);
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
        draw();
    }

    @Override
    public void handleUserInput() {
        for(int r = 0; r < ROW; r++) {
            for(int c = 0; c < COLUMN; c++) {
                map.getTile(r,c).getTileEntity().getImgV().setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        cX = tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                        cY = tY = (int)((event.getSceneY() - 30) / GEM_SIZE);
                        draw();
                    }
                });
                map.getTile(r,c).getTileEntity().getImgV().setOnMouseReleased(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event)
                    {
                        tX = (int)((event.getSceneX() - 20) / GEM_SIZE);
                        tY = (int)((event.getSceneY() - 30) / GEM_SIZE);

                        TileEntity temp = map.getTile(tY, tX).getTileEntity();
                        map.getTile(tY, tX).addEntity(map.getTile(cY, cX).getTileEntity());
                        map.getTile(cY, cX).addEntity(temp);

                        if(eatable(tY, tX) || eatable(cY, cX))
                        {
                            label.setText("EatAble");
                        }
                        else
                        {
                            label.setText("Swap Back");
                            temp = map.getTile(tY, tX).getTileEntity();
                            map.getTile(tY, tX).addEntity(map.getTile(cY, cX).getTileEntity());
                            map.getTile(cY, cX).addEntity(temp);

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

        label = new Label();
        label.setTranslateX(GAME_WIDTH - 330);
        label.setTranslateY(200);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        map.fillMap();
    }
    private void draw()
    {
        root.getChildren().clear();
        root.getChildren().add(background);
        root.getChildren().add(label);
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
        handleUserInput();
    }
    private void moveAnimation(int X, int Y)
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setToX(X * GEM_SIZE + 20);
        transition.setToY(Y * GEM_SIZE + 30);
        //transition.setNode(cells[Y][X]);
        transition.setNode(map.getTile(Y,X).getTileEntity().getImgV());
        transition.play();
    }
    private boolean eatable(int y, int x) {
        int count = 1;
        boolean up = true, down = true, left = true, right = true;
        NextTileEntity nextTileEntity = new NextTileEntity(BejeweledGemFactory.getInstance());
        String[] tileEntityNames = {"blue", "green", "orange", "purple", "red", "white", "yellow"};
        Random random = new Random();
        for(int i = 1; i < 3; i++)
        {
            if(y + 1 >= ROW - 1)down = false;
            if(y - 1 <= 0)up = false;
            if(x + 1 >= COLUMN - 1)right = false;
            if(x - 1 <= 0)left = false;
        }
        if(up == true && down == true)
        {
            if(map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y + 1,x).getTileEntity().getIconSrc()
                && map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y - 1,x).getTileEntity().getIconSrc()){
                count = 3;
            }
            if(count == 3)
            {
                for(int i = -1; i < 2; i++){
                    int randomIndex = random.nextInt(tileEntityNames.length);
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(y - i,x));
                }
                return true;
            }
        }
        if(left == true && right == true)
        {
            if(map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y,x-1).getTileEntity().getIconSrc()
                    && map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y,x+1).getTileEntity().getIconSrc()){
                count = 3;
            }
            if(count == 3)
            {
                for(int i = -1; i < 2; i++){
                    int randomIndex = random.nextInt(tileEntityNames.length);
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(y,x-i));
                }
                return true;
            }
        }
        if(up == true)
        {
            for(int i = 1; i < 3; i++){
                if(map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y-i,x).getTileEntity().getIconSrc()){
                    count++;
                }
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randomIndex = random.nextInt(tileEntityNames.length);
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(y-i,x));
                }
                return true;
            }
        }
        if(down == true)
        {
            for(int i = 1; i < 3; i++){
                if(map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y+i,x).getTileEntity().getIconSrc()){
                    count++;
                }
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randomIndex = random.nextInt(tileEntityNames.length);
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(y+i,x));
                }
                return true;
            }
        }
        if(left == true)
        {
            for(int i = 1; i < 3; i++){
                if(map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y,x-i).getTileEntity().getIconSrc()){
                    count++;
                }
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randomIndex = random.nextInt(tileEntityNames.length);
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(y,x-i));
                }
                return true;
            }
        }
        if(right == true)
        {
            for(int i = 1; i < 3; i++){
                if(map.getTile(y,x).getTileEntity().getIconSrc() == map.getTile(y,x+i).getTileEntity().getIconSrc()){
                    count++;
                }
                else count = 1;
            }
            if(count == 3)
            {
                for(int i = 0; i < 3; i++){
                    int randomIndex = random.nextInt(tileEntityNames.length);
                    nextTileEntity.addNewTileEntity(tileEntityNames[randomIndex], map.getTile(y,x+i));
                }
                return true;
            }
        }
        return false;
    }
}
