package tetris.src.sample;

import javafx.scene.Scene;

public class TetrisController {
    private Scene scene;
    private TetrisGameLogic gameLogic;

    public TetrisController(Scene scene, TetrisGameLogic gameLogic){
        this.scene = scene;
        this.gameLogic = gameLogic;
    }
}
