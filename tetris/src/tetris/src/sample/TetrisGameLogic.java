package tetris.src.sample;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import tetris.src.sample.UI.TetrisUI;
import gameLogic.GameLogic;

import java.io.FileNotFoundException;

public class TetrisGameLogic extends GameLogic {
    //constants
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XMAX = SIZE * 12;
    public static final int YMAX = SIZE * 24;

    //Scoreboard
    public int score = 0;
    public int score1 = 0;

    //Is game over?
    private boolean game = true;
    private boolean game1 = false;
    private boolean isPlayer1 = true;

    //Current block
    private Form activeBlock;

    // [12][24]
    protected int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];

    //TetrisUI View
    private TetrisUI tui;

    //TetrisController
    private TetrisController tc;

    private boolean removePlayer2Btn = false;

    private boolean alreadyAdded = false;

    private static TetrisGameLogic instance = null;

    private TetrisGameLogic() {
        System.out.println("start Starts");
        try {
            tui = TetrisUI.getInstance();
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
            System.out.print("Tetris UI failed");
        }
        tc = new TetrisController(tui.getScene(), this);
    }

    public static TetrisGameLogic getInstance() {
        if (instance == null) {
            instance = new TetrisGameLogic();
        }

        return instance;
    }

    // initialize the background
    public void initializeTileMap() {
        tui.setScore(0);
        activeBlock = Form.makeRect();
        tui.addBlock(activeBlock);

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        for (int i = 0; i < (XMAX / SIZE); i++) {
                            if (MESH[i][0] != 0) {
                                tui.setGameOverText(true);
                                if (game) {
                                    if (isPlayer1) {
                                        tui.addPlayer2Btn();
                                        alreadyAdded = true;
                                    } else {
                                        tui.exitBtn();
                                    }
                                    game = false;
                                } else if (game1 && alreadyAdded) {
                                    tui.exitBtn();
                                    game1 = false;
                                    if (score > score1) {
                                        tui.changePlayerText("Player 1 (Winner)", "Player 2");
                                    }
                                    else if (score < score1){
                                        tui.changePlayerText("Player 1", "Player 2 (Winner)");
                                    }
                                    else {
                                        tui.changePlayerText("Player 1 (Tie)", "Player 2 (Tie)");
                                    }
                                }
                            }
                        }

                        if (removePlayer2Btn) {
                            tui.removePlayer2Btn();
                            switchPlayer();
                            removePlayer2Btn = false;
                            activeBlock = Form.makeRect();
                            tui.addBlock(activeBlock);
                            tc.moveOnKeyPress(activeBlock);
                            game = false;
                            game1 = true;
                        }

                        if (game) {
                            fall(activeBlock);
                            tui.setScore(score);
                        } else if (game1) {
                            fall(activeBlock);
                            tui.setScore1(score1);
                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 100);
    }

    public void switchPlayer() {
        MESH = new int[XMAX / SIZE][YMAX / SIZE];
        tui.resetPlayer();
    }

    public void setRemovePlayer2Btn(boolean in) {
        removePlayer2Btn = in;
    }

    public boolean getAlreadyAdded() {
        return alreadyAdded;
    }

    // suggestion to add incrementScore to the framework
    public void incrementScore() {
        if (game) {
            score++;
        }
        else if (game1) {
            score1++;
        }
    }


    public boolean getGameStatus() {
        return game;
    }

    public boolean getGameStatus1() {
        return game1;
    }

    @Override
    public void generateTileEntity() {

    }

    @Override
    public void handleUserInput() {
        tc.moveOnKeyPress(activeBlock);
    }

    @Override
    public void clearTiles() {

    }

    @Override
    public boolean checkEndGame() {
        return false;
    }

    @Override
    public void save() {

    }

    @Override
    public void quit() {

    }

    // moveDown modifies MESH -> changed game state
    public void fall(Form form) {
        if (form.getA().getY() == YMAX - SIZE || form.getB().getY() == YMAX - SIZE || form.getC().getY() == YMAX - SIZE
                || form.getD().getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            MESH[(int) form.getA().getX() / SIZE][(int) form.getA().getY() / SIZE] = 1;
            MESH[(int) form.getB().getX() / SIZE][(int) form.getB().getY() / SIZE] = 1;
            MESH[(int) form.getC().getX() / SIZE][(int) form.getC().getY() / SIZE] = 1;
            MESH[(int) form.getD().getX() / SIZE][(int) form.getD().getY() / SIZE] = 1;
            RemoveRows(tui.getPane());

            activeBlock = Form.makeRect();
            tui.addBlock(activeBlock);
            tc.moveOnKeyPress(activeBlock);

        }

        if (form.getA().getY() + MOVE < YMAX && form.getB().getY() + MOVE < YMAX && form.getC().getY() + MOVE < YMAX
                && form.getD().getY() + MOVE < YMAX) {
            int movea = MESH[(int) form.getA().getX() / SIZE][((int) form.getA().getY() / SIZE) + 1];
            int moveb = MESH[(int) form.getB().getX() / SIZE][((int) form.getB().getY() / SIZE) + 1];
            int movec = MESH[(int) form.getC().getX() / SIZE][((int) form.getC().getY() / SIZE) + 1];
            int moved = MESH[(int) form.getD().getX() / SIZE][((int) form.getD().getY() / SIZE) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.getA().setY(form.getA().getY() + MOVE);
                form.getB().setY(form.getB().getY() + MOVE);
                form.getC().setY(form.getC().getY() + MOVE);
                form.getD().setY(form.getD().getY() + MOVE);
            }
        }
    }

    private boolean moveA(Form form) {
        return (MESH[(int) form.getA().getX() / SIZE][((int) form.getA().getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (MESH[(int) form.getB().getX() / SIZE][((int) form.getB().getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (MESH[(int) form.getC().getX() / SIZE][((int) form.getC().getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (MESH[(int) form.getD().getX() / SIZE][((int) form.getD().getY() / SIZE) + 1] == 1);
    }

    private void RemoveRows(Pane pane) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;
        for (int i = 0; i < MESH[0].length; i++) {
            for (int j = 0; j < MESH.length; j++) {
                if (MESH[j][i] == 1)
                    full++;
            }
            if (full == MESH.length)
                lines.add(i + lines.size());
            full = 0;
        }
        if (lines.size() > 0)
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                score += 50;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newrects.add(node);
                }

                for (Node node : newrects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * SIZE) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                        a.setY(a.getY() + SIZE);
                    }
                }
                lines.remove(0);
                rects.clear();
                newrects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
    }
}
