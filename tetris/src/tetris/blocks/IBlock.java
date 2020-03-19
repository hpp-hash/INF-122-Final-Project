package tetris.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.TetrisGameLogic;

public class IBlock extends AbstractBlock {
    public IBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {
        super(a, b, c, d, color);
    }

    @Override
    public void rotateBlock(TetrisGameLogic gameLogic) {
        if (form == 1 && cB(a, 2, 2, gameLogic) && cB(b, 1, 1, gameLogic) && cB(d, -1, -1, gameLogic)) {
            MoveRectangleUp(a);
            MoveRectangleUp(a);
            MoveRectangleRight(a);
            MoveRectangleRight(a);
            MoveRectangleUp(b);
            MoveRectangleRight(b);
            MoveRectangleDown(d);
            MoveRectangleLeft(d);
            changeBlock();
            return;
        }
        if (form == 2 && cB(a, -2, -2, gameLogic) && cB(b, -1, -1, gameLogic) && cB(d, 1, 1, gameLogic)) {
            MoveRectangleDown(a);
            MoveRectangleDown(a);
            MoveRectangleLeft(a);
            MoveRectangleLeft(a);
            MoveRectangleDown(b);
            MoveRectangleLeft(b);
            MoveRectangleUp(d);
            MoveRectangleRight(d);
            changeBlock();
            return;
        }
        if (form == 3 && cB(a, 2, 2, gameLogic) && cB(b, 1, 1, gameLogic) && cB(d, -1, -1, gameLogic)) {
            MoveRectangleUp(a);
            MoveRectangleUp(a);
            MoveRectangleRight(a);
            MoveRectangleRight(a);
            MoveRectangleUp(b);
            MoveRectangleRight(b);
            MoveRectangleDown(d);
            MoveRectangleLeft(d);
            changeBlock();
            return;
        }
        if (form == 4 && cB(a, -2, -2, gameLogic) && cB(b, -1, -1, gameLogic) && cB(d, 1, 1, gameLogic)) {
            MoveRectangleDown(a);
            MoveRectangleDown(a);
            MoveRectangleLeft(a);
            MoveRectangleLeft(a);
            MoveRectangleDown(b);
            MoveRectangleLeft(b);
            MoveRectangleUp(d);
            MoveRectangleRight(d);
            changeBlock();
            return;
        }
    }
}
