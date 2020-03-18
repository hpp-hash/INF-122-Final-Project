package tetris.src.sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class TetrisController {
    private Scene scene;
    private TetrisGameLogic gameLogic;

    public TetrisController(Scene scene, TetrisGameLogic gameLogic){
        this.scene = scene;
        this.gameLogic = gameLogic;
    }

    public void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (gameLogic.getGameStatus()) {
                    switch (event.getCode()) {
                        case RIGHT:
                            MoveRight(form);
                            break;
                        case DOWN:
                            gameLogic.fall(form);
                            gameLogic.incrementScore();
                            break;
                        case LEFT:
                            MoveLeft(form);
                            break;
                        case UP:
                            // rotate block
                            Rotate(form);
                            break;
                    }
                }
            }
        });
    }

    public void MoveRight(Form form) {
        if (form.a.getX() + TetrisGameLogic.MOVE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE && form.b.getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE
                && form.c.getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE && form.d.getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE) {
            int movea = gameLogic.MESH[((int) form.a.getX() / TetrisGameLogic.SIZE) + 1][((int) form.a.getY() / TetrisGameLogic.SIZE)];
            int moveb = gameLogic.MESH[((int) form.b.getX() / TetrisGameLogic.SIZE) + 1][((int) form.b.getY() / TetrisGameLogic.SIZE)];
            int movec = gameLogic.MESH[((int) form.c.getX() / TetrisGameLogic.SIZE) + 1][((int) form.c.getY() / TetrisGameLogic.SIZE)];
            int moved = gameLogic.MESH[((int) form.d.getX() / TetrisGameLogic.SIZE) + 1][((int) form.d.getY() / TetrisGameLogic.SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() + TetrisGameLogic.SIZE);
                form.b.setX(form.b.getX() + TetrisGameLogic.SIZE);
                form.c.setX(form.c.getX() + TetrisGameLogic.SIZE);
                form.d.setX(form.d.getX() + TetrisGameLogic.SIZE);
            }
        }
    }

    public void MoveLeft(Form form) {
        if (form.a.getX() - TetrisGameLogic.MOVE >= 0 && form.b.getX() - TetrisGameLogic.MOVE >= 0 && form.c.getX() - TetrisGameLogic.MOVE >= 0
                && form.d.getX() - TetrisGameLogic.MOVE >= 0) {
            int movea = gameLogic.MESH[((int) form.a.getX() / TetrisGameLogic.SIZE) - 1][((int) form.a.getY() / TetrisGameLogic.SIZE)];
            int moveb = gameLogic.MESH[((int) form.b.getX() / TetrisGameLogic.SIZE) - 1][((int) form.b.getY() / TetrisGameLogic.SIZE)];
            int movec = gameLogic.MESH[((int) form.c.getX() / TetrisGameLogic.SIZE) - 1][((int) form.c.getY() / TetrisGameLogic.SIZE)];
            int moved = gameLogic.MESH[((int) form.d.getX() / TetrisGameLogic.SIZE) - 1][((int) form.d.getY() / TetrisGameLogic.SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() - TetrisGameLogic.MOVE);
                form.b.setX(form.b.getX() - TetrisGameLogic.MOVE);
                form.c.setX(form.c.getX() - TetrisGameLogic.MOVE);
                form.d.setX(form.d.getX() - TetrisGameLogic.MOVE);
            }
        }
    }

    private void MoveDown(Rectangle rect) {
        if (rect.getY() + TetrisGameLogic.MOVE < TetrisGameLogic.YMAX)
            rect.setY(rect.getY() + TetrisGameLogic.MOVE);

    }

    private void MoveRight(Rectangle rect) {
        if (rect.getX() + TetrisGameLogic.MOVE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE)
            rect.setX(rect.getX() + TetrisGameLogic.MOVE);
    }

    private void MoveLeft(Rectangle rect) {
        if (rect.getX() - TetrisGameLogic.MOVE >= 0)
            rect.setX(rect.getX() - TetrisGameLogic.MOVE);
    }

    private void MoveUp(Rectangle rect) {
        if (rect.getY() - TetrisGameLogic.MOVE > 0)
            rect.setY(rect.getY() - TetrisGameLogic.MOVE);
    }

    private boolean cB(Rectangle rect, int x, int y) {
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

    private void Rotate(Form form) {
        int f = form.form;
        Rectangle a = form.a;
        Rectangle b = form.b;
        Rectangle c = form.c;
        Rectangle d = form.d;
        switch (form.getName()) {
            case "j":
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "l":
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "o":
                break;
            case "s":
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "t":
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "z":
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "i":
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                break;
        }
    }
}
