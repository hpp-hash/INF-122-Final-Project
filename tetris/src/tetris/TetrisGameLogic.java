package tetris;

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
import gameLogic.GameLogic;
import network.Network;
import player.Multiplayer;
import player.Player;
import tetris.blocks.AbstractBlock;

import java.io.FileNotFoundException;

public class TetrisGameLogic extends GameLogic {//uses the framework
    //constants
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XMAX = SIZE * 12;
    public static final int YMAX = SIZE * 24;

    //Players
    Multiplayer tetrisMultiplayer;

    //Current block
    private AbstractBlock activeBlock;

    // [12][24]
    public int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];

    //TetrisUI View
    private TetrisUI tui;

    //TetrisController
    private TetrisController tc;

    private boolean removePlayer2Btn = false;
    private boolean restartStatus = false;

    private boolean alreadyAdded = false;

    private static TetrisGameLogic instance = null;

    private TetrisGameLogic() {
        System.out.println("start Starts");

        //init players
        tetrisMultiplayer = new Multiplayer();

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
        activeBlock = AbstractBlock.makeRect();
        tui.addBlock(activeBlock);

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        for (int i = 0; i < (XMAX / SIZE); i++) {
                            if (MESH[i][0] != 0) {
                                tui.setGameOverText(true);
                                if (tetrisMultiplayer.isPlayer1GameActive()) {
                                    if (tetrisMultiplayer.isPlayer1()) {
                                        tui.addPlayer2Btn();
                                        alreadyAdded = true;
                                    } else {
                                        tui.exitBtn();
                                    }
                                    tetrisMultiplayer.setPlayer1GameActive(false);
                                    Network.checkResult("tetris", TetrisUI.currentUser, tetrisMultiplayer.getPlayer1HighScore());
                                } else if (tetrisMultiplayer.isPlayer2GameActive() && alreadyAdded) {
                                    tui.exitBtn();
                                    tetrisMultiplayer.setPlayer2GameActive(false);
//                                    player2 = null;
                                    if (tetrisMultiplayer.getPlayer1HighScore() > tetrisMultiplayer.getPlayer2HighScore()) {
                                        tui.changePlayerText("Player 1 (Winner)", "Player 2");
                                    }
                                    else if (tetrisMultiplayer.getPlayer1HighScore() < tetrisMultiplayer.getPlayer2HighScore()){
                                        tui.changePlayerText("Player 1", "Player 2 (Winner)");
                                    }
                                    else {
                                        tui.changePlayerText("Player 1 (Tie)", "Player 2 (Tie)");
                                    }
                                    tui.restartBtn();
                                    Network.checkResult("tetris", TetrisUI.currentUser, tetrisMultiplayer.getPlayer2HighScore());
                                }
                            }
                        }

                        if (removePlayer2Btn) {
                            tui.removePlayer2Btn();
                            switchPlayer();
                            removePlayer2Btn = false;
                            activeBlock = AbstractBlock.makeRect();
                            tui.addBlock(activeBlock);
                            tc.moveOnKeyPress(activeBlock);
                            tetrisMultiplayer.setPlayer1GameActive(false);
                            tetrisMultiplayer.setPlayer2GameActive(true);
                            tetrisMultiplayer.switchPlayer();
                        }

                        if (restartStatus) {
                            MESH = new int[XMAX / SIZE][YMAX / SIZE];
                            tui.restartGame();
                            tetrisMultiplayer.setPlayer1GameActive(true);
                            alreadyAdded = false;

                            //Reset players and their scores
                            tetrisMultiplayer.setPlayer1HighScore(0);
                            tetrisMultiplayer.setPlayer2HighScore(0);
                            tetrisMultiplayer.switchPlayer();

                            restartStatus = false;
                            activeBlock = AbstractBlock.makeRect();
                            tui.addBlock(activeBlock);
                            tc.moveOnKeyPress(activeBlock);
                        } else {
                            if (tetrisMultiplayer.isPlayer1GameActive()) {
                                fall(activeBlock);
                                tui.setScore(tetrisMultiplayer.getPlayer1HighScore());
                            } else if (tetrisMultiplayer.isPlayer2GameActive()) {
                                fall(activeBlock);
                                tui.setScore1(tetrisMultiplayer.getPlayer2HighScore());
                            }
                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 100);
    }

    public void setRestartStatus(boolean input) {
        restartStatus = input;
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
        if (tetrisMultiplayer.isGameActive()) {
            tetrisMultiplayer.setCurrentPlayerHighScore(tetrisMultiplayer.getCurrentPlayerHighScore() + 1);
        }
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
    public void fall(AbstractBlock block) {
        if (block.getA().getY() == YMAX - SIZE || block.getB().getY() == YMAX - SIZE || block.getC().getY() == YMAX - SIZE
                || block.getD().getY() == YMAX - SIZE || moveA(block) || moveB(block) || moveC(block) || moveD(block)) {
            MESH[(int) block.getA().getX() / SIZE][(int) block.getA().getY() / SIZE] = 1;
            MESH[(int) block.getB().getX() / SIZE][(int) block.getB().getY() / SIZE] = 1;
            MESH[(int) block.getC().getX() / SIZE][(int) block.getC().getY() / SIZE] = 1;
            MESH[(int) block.getD().getX() / SIZE][(int) block.getD().getY() / SIZE] = 1;
            RemoveRows(tui.getPane());

            activeBlock = AbstractBlock.makeRect();
            tui.addBlock(activeBlock);
            tc.moveOnKeyPress(activeBlock);

        }

        if (block.getA().getY() + MOVE < YMAX && block.getB().getY() + MOVE < YMAX && block.getC().getY() + MOVE < YMAX
                && block.getD().getY() + MOVE < YMAX) {
            int movea = MESH[(int) block.getA().getX() / SIZE][((int) block.getA().getY() / SIZE) + 1];
            int moveb = MESH[(int) block.getB().getX() / SIZE][((int) block.getB().getY() / SIZE) + 1];
            int movec = MESH[(int) block.getC().getX() / SIZE][((int) block.getC().getY() / SIZE) + 1];
            int moved = MESH[(int) block.getD().getX() / SIZE][((int) block.getD().getY() / SIZE) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                block.getA().setY(block.getA().getY() + MOVE);
                block.getB().setY(block.getB().getY() + MOVE);
                block.getC().setY(block.getC().getY() + MOVE);
                block.getD().setY(block.getD().getY() + MOVE);
            }
        }
    }

    private boolean moveA(AbstractBlock block) {
        return (MESH[(int) block.getA().getX() / SIZE][((int) block.getA().getY() / SIZE) + 1] == 1);
    }

    private boolean moveB(AbstractBlock block) {
        return (MESH[(int) block.getB().getX() / SIZE][((int) block.getB().getY() / SIZE) + 1] == 1);
    }

    private boolean moveC(AbstractBlock block) {
        return (MESH[(int) block.getC().getX() / SIZE][((int) block.getC().getY() / SIZE) + 1] == 1);
    }

    private boolean moveD(AbstractBlock block) {
        return (MESH[(int) block.getD().getX() / SIZE][((int) block.getD().getY() / SIZE) + 1] == 1);
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
                tetrisMultiplayer.setCurrentPlayerHighScore(tetrisMultiplayer.getCurrentPlayerHighScore() + 50);

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
