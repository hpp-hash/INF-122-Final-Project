package bejeweled2;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import gameLogic.GameLogic;
import userinput.UserInputController;

public class BejeweledGameLogic extends GameLogic {
    private BejeweledController controller = BejeweledController.getInstance();

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

        controller.setup();
        controller.draw();

        Scene scene = new Scene(controller.getRoot(), controller.getGAME_WIDTH(), controller.getGAME_HEIGHT(), Color.TRANSPARENT);
        scene.getStylesheets().addAll(getClass().getResource("/style/style.css").toExternalForm());
        gameStage.setScene(scene);
        gameStage.show();
    }
    
    @Override
    public void generateTileEntity() {
        controller.draw();
    }
    
    @Override
    public void handleUserInput() {
        BejeweledInputAdapter inputAdapter = new BejeweledInputAdapter(controller);
        UserInputController.getInstance(inputAdapter).onInput();
    }

    @Override
    public void clearTiles() {
        for (int r = 0; r < controller.getROW(); r++){
            for (int c = 0; c < controller.getCOLUMN(); c++){
                int temp = controller.getScore();
                controller.eatable(r, c);
                controller.setScore(temp);
            }
        }
        controller.draw();
    }

    @Override
    public boolean checkEndGame() {
        System.out.println(controller.getCounter());
        if(controller.getCounter() == 2)
        {
            controller.setCounter(0);
            return true;
        }
        return false;
    }

    @Override
    public void save() {

    }

    @Override
    public void quit() {

    }
}
