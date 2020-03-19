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
                if (gameLogic.getGameStatus() ^ gameLogic.getGameStatus1()) {
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
        if (form.getA().getX() + TetrisGameLogic.MOVE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE && form.getB().getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE
                && form.getC().getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE && form.getD().getX() + TetrisGameLogic.SIZE <= TetrisGameLogic.XMAX - TetrisGameLogic.SIZE) {
            int movea = gameLogic.MESH[((int) form.getA().getX() / TetrisGameLogic.SIZE) + 1][((int) form.getA().getY() / TetrisGameLogic.SIZE)];
            int moveb = gameLogic.MESH[((int) form.getB().getX() / TetrisGameLogic.SIZE) + 1][((int) form.getB().getY() / TetrisGameLogic.SIZE)];
            int movec = gameLogic.MESH[((int) form.getC().getX() / TetrisGameLogic.SIZE) + 1][((int) form.getC().getY() / TetrisGameLogic.SIZE)];
            int moved = gameLogic.MESH[((int) form.getD().getX() / TetrisGameLogic.SIZE) + 1][((int) form.getD().getY() / TetrisGameLogic.SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.getA().setX(form.getA().getX() + TetrisGameLogic.SIZE);
                form.getB().setX(form.getB().getX() + TetrisGameLogic.SIZE);
                form.getC().setX(form.getC().getX() + TetrisGameLogic.SIZE);
                form.getD().setX(form.getD().getX() + TetrisGameLogic.SIZE);
            }
        }
    }

    public void MoveLeft(Form form) {
        if (form.getA().getX() - TetrisGameLogic.MOVE >= 0 && form.getB().getX() - TetrisGameLogic.MOVE >= 0 && form.getC().getX() - TetrisGameLogic.MOVE >= 0
                && form.getD().getX() - TetrisGameLogic.MOVE >= 0) {
            int movea = gameLogic.MESH[((int) form.getA().getX() / TetrisGameLogic.SIZE) - 1][((int) form.getA().getY() / TetrisGameLogic.SIZE)];
            int moveb = gameLogic.MESH[((int) form.getB().getX() / TetrisGameLogic.SIZE) - 1][((int) form.getB().getY() / TetrisGameLogic.SIZE)];
            int movec = gameLogic.MESH[((int) form.getC().getX() / TetrisGameLogic.SIZE) - 1][((int) form.getC().getY() / TetrisGameLogic.SIZE)];
            int moved = gameLogic.MESH[((int) form.getD().getX() / TetrisGameLogic.SIZE) - 1][((int) form.getD().getY() / TetrisGameLogic.SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.getA().setX(form.getA().getX() - TetrisGameLogic.MOVE);
                form.getB().setX(form.getB().getX() - TetrisGameLogic.MOVE);
                form.getC().setX(form.getC().getX() - TetrisGameLogic.MOVE);
                form.getD().setX(form.getD().getX() - TetrisGameLogic.MOVE);
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
        Rectangle a = form.getA();
        Rectangle b = form.getB();
        Rectangle c = form.getC();
        Rectangle d = form.getD();
        switch (form.getName()) {
            case "j":
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    MoveRight(form.getA());
                    MoveDown(form.getA());
                    MoveDown(form.getC());
                    MoveLeft(form.getC());
                    MoveDown(form.getD());
                    MoveDown(form.getD());
                    MoveLeft(form.getD());
                    MoveLeft(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    MoveDown(form.getA());
                    MoveLeft(form.getA());
                    MoveLeft(form.getC());
                    MoveUp(form.getC());
                    MoveLeft(form.getD());
                    MoveLeft(form.getD());
                    MoveUp(form.getD());
                    MoveUp(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
                    MoveLeft(form.getA());
                    MoveUp(form.getA());
                    MoveUp(form.getC());
                    MoveRight(form.getC());
                    MoveUp(form.getD());
                    MoveUp(form.getD());
                    MoveRight(form.getD());
                    MoveRight(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    MoveUp(form.getA());
                    MoveRight(form.getA());
                    MoveRight(form.getC());
                    MoveDown(form.getC());
                    MoveRight(form.getD());
                    MoveRight(form.getD());
                    MoveDown(form.getD());
                    MoveDown(form.getD());
                    form.changeForm();
                    break;
                }
                break;
            case "l":
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    MoveRight(form.getA());
                    MoveDown(form.getA());
                    MoveUp(form.getC());
                    MoveRight(form.getC());
                    MoveUp(form.getB());
                    MoveUp(form.getB());
                    MoveRight(form.getB());
                    MoveRight(form.getB());
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    MoveDown(form.getA());
                    MoveLeft(form.getA());
                    MoveRight(form.getB());
                    MoveRight(form.getB());
                    MoveDown(form.getB());
                    MoveDown(form.getB());
                    MoveRight(form.getC());
                    MoveDown(form.getC());
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    MoveLeft(form.getA());
                    MoveUp(form.getA());
                    MoveDown(form.getC());
                    MoveLeft(form.getC());
                    MoveDown(form.getB());
                    MoveDown(form.getB());
                    MoveLeft(form.getB());
                    MoveLeft(form.getB());
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
                    MoveUp(form.getA());
                    MoveRight(form.getA());
                    MoveLeft(form.getB());
                    MoveLeft(form.getB());
                    MoveUp(form.getB());
                    MoveUp(form.getB());
                    MoveLeft(form.getC());
                    MoveUp(form.getC());
                    form.changeForm();
                    break;
                }
                break;
            case "o":
                break;
            case "s":
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.getA());
                    MoveLeft(form.getA());
                    MoveLeft(form.getC());
                    MoveUp(form.getC());
                    MoveUp(form.getD());
                    MoveUp(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.getA());
                    MoveRight(form.getA());
                    MoveRight(form.getC());
                    MoveDown(form.getC());
                    MoveDown(form.getD());
                    MoveDown(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.getA());
                    MoveLeft(form.getA());
                    MoveLeft(form.getC());
                    MoveUp(form.getC());
                    MoveUp(form.getD());
                    MoveUp(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.getA());
                    MoveRight(form.getA());
                    MoveRight(form.getC());
                    MoveDown(form.getC());
                    MoveDown(form.getD());
                    MoveDown(form.getD());
                    form.changeForm();
                    break;
                }
                break;
            case "t":
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    MoveUp(form.getA());
                    MoveRight(form.getA());
                    MoveDown(form.getD());
                    MoveLeft(form.getD());
                    MoveLeft(form.getC());
                    MoveUp(form.getC());
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    MoveRight(form.getA());
                    MoveDown(form.getA());
                    MoveLeft(form.getD());
                    MoveUp(form.getD());
                    MoveUp(form.getC());
                    MoveRight(form.getC());
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    MoveDown(form.getA());
                    MoveLeft(form.getA());
                    MoveUp(form.getD());
                    MoveRight(form.getD());
                    MoveRight(form.getC());
                    MoveDown(form.getC());
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    MoveLeft(form.getA());
                    MoveUp(form.getA());
                    MoveRight(form.getD());
                    MoveDown(form.getD());
                    MoveDown(form.getC());
                    MoveLeft(form.getC());
                    form.changeForm();
                    break;
                }
                break;
            case "z":
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.getB());
                    MoveRight(form.getB());
                    MoveLeft(form.getC());
                    MoveUp(form.getC());
                    MoveLeft(form.getD());
                    MoveLeft(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.getB());
                    MoveLeft(form.getB());
                    MoveRight(form.getC());
                    MoveDown(form.getC());
                    MoveRight(form.getD());
                    MoveRight(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.getB());
                    MoveRight(form.getB());
                    MoveLeft(form.getC());
                    MoveUp(form.getC());
                    MoveLeft(form.getD());
                    MoveLeft(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.getB());
                    MoveLeft(form.getB());
                    MoveRight(form.getC());
                    MoveDown(form.getC());
                    MoveRight(form.getD());
                    MoveRight(form.getD());
                    form.changeForm();
                    break;
                }
                break;
            case "i":
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.getA());
                    MoveUp(form.getA());
                    MoveRight(form.getA());
                    MoveRight(form.getA());
                    MoveUp(form.getB());
                    MoveRight(form.getB());
                    MoveDown(form.getD());
                    MoveLeft(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.getA());
                    MoveDown(form.getA());
                    MoveLeft(form.getA());
                    MoveLeft(form.getA());
                    MoveDown(form.getB());
                    MoveLeft(form.getB());
                    MoveUp(form.getD());
                    MoveRight(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.getA());
                    MoveUp(form.getA());
                    MoveRight(form.getA());
                    MoveRight(form.getA());
                    MoveUp(form.getB());
                    MoveRight(form.getB());
                    MoveDown(form.getD());
                    MoveLeft(form.getD());
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.getA());
                    MoveDown(form.getA());
                    MoveLeft(form.getA());
                    MoveLeft(form.getA());
                    MoveDown(form.getB());
                    MoveLeft(form.getB());
                    MoveUp(form.getD());
                    MoveRight(form.getD());
                    form.changeForm();
                    break;
                }
                break;
        }
    }
}
