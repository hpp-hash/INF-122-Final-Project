package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.blocks.AbstractBlock;

public class ZBlock extends AbstractBlock {
    public ZBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {
        super(a, b, c, d, color);
    }

    @Override
    public void rotateBlock(TetrisGameLogic gameLogic) {

    }
}
