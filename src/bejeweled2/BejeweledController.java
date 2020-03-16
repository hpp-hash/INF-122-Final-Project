package bejeweled2;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import tmge.NextTileEntity;
import tmge.TileEntity;
import tmge.UserInputController;

import java.util.Random;

public class BejeweledController{

    int counter = 0;
    private final int GAME_WIDTH = 1280, GAME_HEIGHT = 720, GEM_SIZE = 64;
    private final int ROW = 10, COLUMN = 14;
    private Group root = new Group();
    private ImageView cursor, background;
    BejeweledTileMap map = BejeweledTileMap.getInstance(ROW, COLUMN);
    private int cX = 0, cY = 0, tX = 0, tY = 0;
    private Label label;

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

    public void setup() {
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
    public void draw()
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
        //handleUserInput();
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
        //transition.setNode(cells[Y][X]);
        transition.play();
    }
    // private boolean eatable(int y, int x) {
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

        if(maxCount > 2)
        {
            for(int i = 0; i < maxCount; i++){
                int randomIndex = random.nextInt(tileEntityNames.length);
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
