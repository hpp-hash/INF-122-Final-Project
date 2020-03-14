package tmge;

import javafx.application.Application;

import javafx.stage.Stage;

public abstract class GameLogic extends Application {

    // final means no one can change the method
    public final void startGame(Stage stage) {
        initializeTileMap(stage);
        do{
            generateTileEntity();
            handleUserInput();
            clearTiles();
            // TODO: abstract method for score calculation? or handle it in checkEndGame()?
        } while(checkEndGame());

        end();
    }

    public abstract void initializeTileMap(Stage stage);

    public abstract void generateTileEntity();

    public abstract void handleUserInput();

    public abstract void clearTiles();

    public abstract boolean checkEndGame();

    public final void end() {
        save();
        quit();
    }

    public abstract void save();

    public abstract void quit();
}
