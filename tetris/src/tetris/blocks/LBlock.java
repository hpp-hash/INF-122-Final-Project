package tetris.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.TetrisGameLogic;

public class LBlock extends AbstractBlock {
    public LBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {
        super(a, b, c, d, color);
    }

    @Override
    public void rotateBlock(TetrisGameLogic gameLogic) {
        if (form == 1 && cB(a, 1, -1, gameLogic) && cB(c, 1, 1, gameLogic) && cB(b, 2, 2, gameLogic)) {
            MoveRectangleRight(a);
            MoveRectangleDown(a);
            MoveRectangleUp(c);
            MoveRectangleRight(c);
            MoveRectangleUp(b);
            MoveRectangleUp(b);
            MoveRectangleRight(b);
            MoveRectangleRight(b);
            changeBlock();
            return;
        }
        if (form == 2 && cB(a, -1, -1, gameLogic) && cB(b, 2, -2, gameLogic) && cB(c, 1, -1, gameLogic)) {
            MoveRectangleDown(a);
            MoveRectangleLeft(a);
            MoveRectangleRight(b);
            MoveRectangleRight(b);
            MoveRectangleDown(b);
            MoveRectangleDown(b);
            MoveRectangleRight(c);
            MoveRectangleDown(c);
            changeBlock();
            return;
        }
        if (form == 3 && cB(a, -1, 1, gameLogic) && cB(c, -1, -1, gameLogic) && cB(b, -2, -2, gameLogic)) {
            MoveRectangleLeft(a);
            MoveRectangleUp(a);
            MoveRectangleDown(c);
            MoveRectangleLeft(c);
            MoveRectangleDown(b);
            MoveRectangleDown(b);
            MoveRectangleLeft(b);
            MoveRectangleLeft(b);
            changeBlock();
            return;
        }
        if (form == 4 && cB(a, 1, 1, gameLogic) && cB(b, -2, 2, gameLogic) && cB(c, -1, 1, gameLogic)) {
            MoveRectangleUp(a);
            MoveRectangleRight(a);
            MoveRectangleLeft(b);
            MoveRectangleLeft(b);
            MoveRectangleUp(b);
            MoveRectangleUp(b);
            MoveRectangleLeft(c);
            MoveRectangleUp(c);
            changeBlock();
            return;
        }
    }
}
