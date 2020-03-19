package tetris.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.TetrisGameLogic;

public class SBlock extends AbstractBlock {
    public SBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {

        super(a, b, c, d, color);
    }

    @Override
    public void rotateBlock(TetrisGameLogic gameLogic) {
        if (form == 1 && cB(a, -1, -1, gameLogic) && cB(c, -1, 1, gameLogic) && cB(d, 0, 2, gameLogic)) {
            MoveRectangleDown(a);
            MoveRectangleLeft(a);
            MoveRectangleLeft(c);
            MoveRectangleUp(c);
            MoveRectangleUp(d);
            MoveRectangleUp(d);
            changeBlock();
            return;
        }
        if (form == 2 && cB(a, 1, 1, gameLogic) && cB(c, 1, -1, gameLogic) && cB(d, 0, -2, gameLogic)) {
            MoveRectangleUp(a);
            MoveRectangleRight(a);
            MoveRectangleRight(c);
            MoveRectangleDown(c);
            MoveRectangleDown(d);
            MoveRectangleDown(d);
            changeBlock();
            return;
        }
        if (form == 3 && cB(a, -1, -1, gameLogic) && cB(c, -1, 1, gameLogic) && cB(d, 0, 2, gameLogic)) {
            MoveRectangleDown(a);
            MoveRectangleLeft(a);
            MoveRectangleLeft(c);
            MoveRectangleUp(c);
            MoveRectangleUp(d);
            MoveRectangleUp(d);
            changeBlock();
            return;
        }
        if (form == 4 && cB(a, 1, 1, gameLogic) && cB(c, 1, -1, gameLogic) && cB(d, 0, -2, gameLogic)) {
            MoveRectangleUp(a);
            MoveRectangleRight(a);
            MoveRectangleRight(c);
            MoveRectangleDown(c);
            MoveRectangleDown(d);
            MoveRectangleDown(d);
            changeBlock();
            return;
        }
    }
}
