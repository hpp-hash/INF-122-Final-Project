package tmge;

public abstract class GameLogic {

    final void start() {
        initializeTileMap();
        do{
            generateTileEntity();
            handleUserInput();
            clearTiles();
            // TODO: abstract method for score calculation? or handle it in checkEndGame()?
        } while(checkEndGame());

        end();
    }

    abstract void initializeTileMap();

    abstract void generateTileEntity();

    abstract void handleUserInput();

    abstract void clearTiles();

    abstract boolean checkEndGame();

    final void end() {
        save();
        quit();
    }

    abstract void save();

    abstract void quit();
}
