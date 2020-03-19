package tetris.blocks;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris.TetrisGameLogic;


public abstract class AbstractBlock {
    protected Color color;
    protected Rectangle a;
    protected Rectangle b;
    protected Rectangle c;
    protected Rectangle d;
    protected int form = 1;

    public AbstractBlock(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color) {
        this.color = color;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public abstract void rotateBlock(TetrisGameLogic gameLogic);

    protected void changeBlock() {
        if (form != 4) {
            form++;
        } else {
            form = 1;
        }
    }

    protected void moveBlockLeft(TetrisGameLogic gameLogic) {
        if (a.getX() - TetrisGameLogic.MOVE >= 0 && b.getX() - TetrisGameLogic.MOVE >= 0 && c.getX() - TetrisGameLogic.MOVE >= 0
                && d.getX() - TetrisGameLogic.MOVE >= 0) {
            int movea = gameLogic.MESH[((int) a.getX() / TetrisGameLogic.SIZE) - 1][((int) a.getY() / TetrisGameLogic.SIZE)];
            int moveb = gameLogic.MESH[((int) b.getX() / TetrisGameLogic.SIZE) - 1][((int) b.getY() / TetrisGameLogic.SIZE)];
            int movec = gameLogic.MESH[((int) c.getX() / TetrisGameLogic.SIZE) - 1][((int) c.getY() / TetrisGameLogic.SIZE)];
            int moved = gameLogic.MESH[((int) d.getX() / TetrisGameLogic.SIZE) - 1][((int) d.getY() / TetrisGameLogic.SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                a.setX(a.getX() - TetrisGameLogic.MOVE);
                b.setX(b.getX() - TetrisGameLogic.MOVE);
                c.setX(c.getX() - TetrisGameLogic.MOVE);
                d.setX(d.getX() - TetrisGameLogic.MOVE);
            }
        }
    }

    protected void moveBlockRight(TetrisGameLogic gameLogic) {
        if (a.getX() + TetrisGameLogic.MOVE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE && b.getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE
                && c.getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE && d.getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE) {
            int movea = gameLogic.MESH[((int) a.getX() / TetrisGameLogic.SIZE) + 1][((int) a.getY() / TetrisGameLogic.SIZE)];
            int moveb = gameLogic.MESH[((int) b.getX() / TetrisGameLogic.SIZE) + 1][((int) b.getY() / TetrisGameLogic.SIZE)];
            int movec = gameLogic.MESH[((int) c.getX() / TetrisGameLogic.SIZE) + 1][((int) c.getY() / TetrisGameLogic.SIZE)];
            int moved = gameLogic.MESH[((int) d.getX() / TetrisGameLogic.SIZE) + 1][((int) d.getY() / TetrisGameLogic.SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                a.setX(a.getX() + TetrisGameLogic.SIZE);
                b.setX(b.getX() + TetrisGameLogic.SIZE);
                c.setX(c.getX() + TetrisGameLogic.SIZE);
                d.setX(d.getX() + TetrisGameLogic.SIZE);
            }
        }
    }

    protected void MoveRectangleDown(Rectangle rect) {
        if (rect.getY() + TetrisGameLogic.MOVE < TetrisGameLogic.YMAX)
            rect.setY(rect.getY() + TetrisGameLogic.MOVE);
    }

    protected void MoveRectangleRight(Rectangle rect) {
        if (rect.getX() + TetrisGameLogic.MOVE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE)
            rect.setX(rect.getX() + TetrisGameLogic.MOVE);
    }

    protected void MoveRectangleLeft(Rectangle rect) {
        if (rect.getX() - TetrisGameLogic.MOVE >= 0)
            rect.setX(rect.getX() - TetrisGameLogic.MOVE);
    }

    protected void MoveRectangleUp(Rectangle rect) {
        if (rect.getY() - TetrisGameLogic.MOVE > 0)
            rect.setY(rect.getY() - TetrisGameLogic.MOVE);
    }

    protected boolean cB(Rectangle rect, int x, int y, TetrisGameLogic gameLogic) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0)
            xb = rect.getX() + x * TetrisGameLogic.MOVE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE;
        if (x < 0)
            xb = rect.getX() + x * TetrisGameLogic.MOVE >= 0;
        if (y >= 0)
            yb = rect.getY() - y * TetrisGameLogic.MOVE > 0;
        if (y < 0)
            yb = rect.getY() + y * TetrisGameLogic.MOVE < TetrisGameLogic.YMAX;
        return xb && yb && gameLogic.MESH[((int) rect.getX() / TetrisGameLogic.SIZE) + x][((int) rect.getY() / TetrisGameLogic.SIZE) - y] == 0;
    }

}
