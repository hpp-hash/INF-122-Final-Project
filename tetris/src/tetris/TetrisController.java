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
                if (gameLogic.getGameStatus() ^ gameLogic.getGameStatus1()) {
                    switch (event.getCode()) {
                        case RIGHT:
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            block.moveBlockRight(gameLogic);
                            break;
                        case DOWN:
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            gameLogic.fall(block);
                            gameLogic.incrementScore();
                            break;
                        case LEFT:
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            block.moveBlockLeft(gameLogic);
                            break;
                        case UP:
                            // rotate block
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            block.rotateBlock(gameLogic);
                            break;
                    }
                }
            }
        });
    }
}
