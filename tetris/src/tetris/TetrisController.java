package tetris;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import tetris.blocks.AbstractBlock;

public class TetrisController {//controls the game and handle events
    private Scene scene;
    private TetrisGameLogic gameLogic;

    public TetrisController(Scene scene, TetrisGameLogic gameLogic){
        this.scene = scene;
        this.gameLogic = gameLogic;
    }

    public void moveOnKeyPress(AbstractBlock block) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (gameLogic.tetrisMultiplayer.isPlayer1GameActive() ^ gameLogic.tetrisMultiplayer.isPlayer2GameActive()) {
                    switch (event.getCode()) {
                        case RIGHT:
                            block.moveBlockRight(gameLogic);
                            break;
                        case DOWN:
                            gameLogic.fall(block);
                            gameLogic.incrementScore();
                            break;
                        case LEFT:
                            block.moveBlockLeft(gameLogic);
                            break;
                        case UP:
                            // rotate block
                            block.rotateBlock(gameLogic);
                            break;
                    }
                }
            }
        });
    }
}
