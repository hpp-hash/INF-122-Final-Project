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
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    public Rectangle getA() {
        return a;
    }
    public Rectangle getB() {
        return b;
    }
    public Rectangle getC() {
        return c;
    }
    public Rectangle getD() {
        return d;
    }

    public abstract void rotateBlock(TetrisGameLogic gameLogic);

    protected void changeBlock() {
        if (form != 4) {
            form++;
        } else {
            form = 1;
        }
    }

    public void moveBlockLeft(TetrisGameLogic gameLogic) {
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

    public void moveBlockRight(TetrisGameLogic gameLogic) {
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

    public static AbstractBlock makeRect() {
        int block = (int) (Math.random() * 100);
        Rectangle a = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1), b = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1), c = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1),
                d = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1);
        if (block < 15) {
            a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            b.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            b.setY(TetrisGameLogic.SIZE);
            c.setX(TetrisGameLogic.XMAX / 2);
            c.setY(TetrisGameLogic.SIZE);
            d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            d.setY(TetrisGameLogic.SIZE);
            return new JBlock(a, b, c, d, Color.BLUE);
        } else if (block < 30) {
            a.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            b.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            b.setY(TetrisGameLogic.SIZE);
            c.setX(TetrisGameLogic.XMAX / 2);
            c.setY(TetrisGameLogic.SIZE);
            d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            d.setY(TetrisGameLogic.SIZE);
            return new LBlock(a, b, c, d, Color.ORANGE);
        } else if (block < 45) {
            a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            b.setX(TetrisGameLogic.XMAX / 2);
            c.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            c.setY(TetrisGameLogic.SIZE);
            d.setX(TetrisGameLogic.XMAX / 2);
            d.setY(TetrisGameLogic.SIZE);
            return new OBlock(a, b, c, d, Color.YELLOW);
        } else if (block < 60) {
            a.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            b.setX(TetrisGameLogic.XMAX / 2);
            c.setX(TetrisGameLogic.XMAX / 2);
            c.setY(TetrisGameLogic.SIZE);
            d.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            d.setY(TetrisGameLogic.SIZE);
            return new SBlock(a, b, c, d, Color.GREEN);
        } else if (block < 75) {
            a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            b.setX(TetrisGameLogic.XMAX / 2);
            c.setX(TetrisGameLogic.XMAX / 2);
            c.setY(TetrisGameLogic.SIZE);
            d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            return new TBlock(a, b, c, d, Color.PURPLE);
        } else if (block < 90) {
            a.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            b.setX(TetrisGameLogic.XMAX / 2);
            c.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            c.setY(TetrisGameLogic.SIZE);
            d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE + TetrisGameLogic.SIZE);
            d.setY(TetrisGameLogic.SIZE);
            return new ZBlock(a, b, c, d, Color.RED);
        } else {
            a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE - TetrisGameLogic.SIZE);
            b.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
            c.setX(TetrisGameLogic.XMAX / 2);
            d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
            return new IBlock(a, b, c, d, Color.CYAN);
        }
    }

}
