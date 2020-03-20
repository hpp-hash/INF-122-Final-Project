package tetris.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.TetrisGameLogic;

public class TBlock extends AbstractBlock {
    public TBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {
        super(a, b, c, d, color);
    }

    @Override
    public void rotateBlock(TetrisGameLogic gameLogic) {
        if (form == 1 && cB(a, 1, 1, gameLogic) && cB(d, -1, -1, gameLogic) && cB(c, -1, 1, gameLogic)) {
            MoveRectangleUp(a);
            MoveRectangleRight(a);
            MoveRectangleDown(d);
            MoveRectangleLeft(d);
            MoveRectangleLeft(c);
            MoveRectangleUp(c);
            changeBlock();
            return;
        }
        if (form == 2 && cB(a, 1, -1, gameLogic) && cB(d, -1, 1, gameLogic) && cB(c, 1, 1, gameLogic)) {
            MoveRectangleRight(a);
            MoveRectangleDown(a);
            MoveRectangleLeft(d);
            MoveRectangleUp(d);
            MoveRectangleUp(c);
            MoveRectangleRight(c);
            changeBlock();
            return;
        }
        if (form == 3 && cB(a, -1, -1, gameLogic) && cB(d, 1, 1, gameLogic) && cB(c, 1, -1, gameLogic)) {
            MoveRectangleDown(a);
            MoveRectangleLeft(a);
            MoveRectangleUp(d);
            MoveRectangleRight(d);
            MoveRectangleRight(c);
            MoveRectangleDown(c);
            changeBlock();
            return;
        }
        if (form == 4 && cB(a, -1, 1, gameLogic) && cB(d, 1, -1, gameLogic) && cB(c, -1, -1, gameLogic)) {
            MoveRectangleLeft(a);
            MoveRectangleUp(a);
            MoveRectangleRight(d);
            MoveRectangleDown(d);
            MoveRectangleDown(c);
            MoveRectangleLeft(c);
            changeBlock();
            return;
        }
    }
}
