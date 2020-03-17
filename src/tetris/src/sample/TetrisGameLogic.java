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
import tmge.gameLogic.GameLogic;

import java.io.FileNotFoundException;

public class TetrisGameLogic extends GameLogic {
    //constants
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XMAX = SIZE * 12;
    public static final int YMAX = SIZE * 24;

    //Scoreboard
    public int score = 0;

    //Is game over?
    private boolean game = true;

    //Current block
    private Form activeBlock;
    protected int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];

    //TetrisUI View
    private TetrisUI tui;

    //TetrisController
    private TetrisController tc;

    private int totalHeight;

    private boolean removeRestartBtn = false;

    public TetrisGameLogic() {
        System.out.println("start Starts");
        try {
            tui = new TetrisUI();
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
            System.out.print("Tetris UI failed");
        }
        tc = new TetrisController(tui.getScene(), this);
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
                                game = false;
                                tui.setGameOverText(true);

                                Button exitBtn = new Button("Exit");
                                exitBtn.relocate(TetrisGameLogic.XMAX + 55, 400);
                                exitBtn.setStyle("-fx-font-size: 15px;");
                                exitBtn.setOnMouseReleased(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        removeRestartBtn = true;
                                        System.exit(0);
                                    }
                                });

                                if (removeRestartBtn) {
                                    tui.getPane().getChildren().clear();
                                    tui.getPane().getChildren().remove(exitBtn);
                                }
                                else {
                                    tui.getPane().getChildren().addAll(exitBtn);
                                }
                            }
                        }


                        if (game) {
                            fall(activeBlock);
                            tui.setScore(score);
                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 10);

    }

    // suggestion to add incrementScore to the framework
    public void incrementScore() {
        score++;
    }

    public boolean getGameStatus() {
        return game;
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
        if (form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
                || form.d.getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            MESH[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
            MESH[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
            MESH[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
            MESH[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
            RemoveRows(tui.getPane());

            activeBlock = Form.makeRect();
            tui.addBlock(activeBlock);
            tc.moveOnKeyPress(activeBlock);
        }

        if (form.a.getY() + MOVE < YMAX && form.b.getY() + MOVE < YMAX && form.c.getY() + MOVE < YMAX
                && form.d.getY() + MOVE < YMAX) {
            int movea = MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
            int moveb = MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
            int movec = MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
            int moved = MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setY(form.a.getY() + MOVE);
                form.b.setY(form.b.getY() + MOVE);
                form.c.setY(form.c.getY() + MOVE);
                form.d.setY(form.d.getY() + MOVE);
            }
        }
    }

    private boolean moveA(Form form) {
        return (MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
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
