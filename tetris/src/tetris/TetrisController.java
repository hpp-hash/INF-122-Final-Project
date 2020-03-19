package tetris;

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
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            MoveRight(form);
                            break;
                        case DOWN:
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            gameLogic.fall(form);
                            gameLogic.incrementScore();
                            break;
                        case LEFT:
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            MoveLeft(form);
                            break;
                        case UP:
                            // rotate block
                            System.out.println("You Pressing : " + ((KeyEvent) event).getCode() );
                            Rotate(form);
                            break;
                    }
                }
            }
        });
    }

    private void Rotate(Form form) {
        int f = form.form;
        Rectangle a = form.getA();
        Rectangle b = form.getB();
        Rectangle c = form.getC();
        Rectangle d = form.getD();
        switch (form.getName()) {
            case "j":

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
