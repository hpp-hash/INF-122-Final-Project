package tetris.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.TetrisGameLogic;

public class ZBlock extends AbstractBlock{
    public ZBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {
        super(a, b, c, d, color);
    }

    @Override
    public void rotateBlock(TetrisGameLogic gameLogic) {
        if (form == 1 && cB(b, 1, 1, gameLogic) && cB(c, -1, 1, gameLogic) && cB(d, -2, 0, gameLogic)) {
            MoveRectangleUp(b);
            MoveRectangleRight(b);
            MoveRectangleLeft(c);
            MoveRectangleUp(c);
            MoveRectangleLeft(d);
            MoveRectangleLeft(d);
            changeBlock();
            return;
        }
        if (form == 2 && cB(b, -1, -1, gameLogic) && cB(c, 1, -1, gameLogic) && cB(d, 2, 0, gameLogic)) {
            MoveRectangleDown(b);
            MoveRectangleLeft(b);
            MoveRectangleRight(c);
            MoveRectangleDown(c);
            MoveRectangleRight(d);
            MoveRectangleRight(d);
            changeBlock();
            return;
        }
        if (form == 3 && cB(b, 1, 1, gameLogic) && cB(c, -1, 1, gameLogic) && cB(d, -2, 0, gameLogic)) {
            MoveRectangleUp(b);
            MoveRectangleRight(b);
            MoveRectangleLeft(c);
            MoveRectangleUp(c);
            MoveRectangleLeft(d);
            MoveRectangleLeft(d);
            changeBlock();
            return;
        }
        if (form == 4 && cB(b, -1, -1, gameLogic) && cB(c, 1, -1, gameLogic) && cB(d, 2, 0, gameLogic)) {
            MoveRectangleDown(b);
            MoveRectangleLeft(b);
            MoveRectangleRight(c);
            MoveRectangleDown(c);
            MoveRectangleRight(d);
            MoveRectangleRight(d);
            changeBlock();
            return;
        }
    }
}
