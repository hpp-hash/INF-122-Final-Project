package tetris.src.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tmge.GameLogic;


public class TetrisGameLogic extends GameLogic {
    public static int score = 0;
    private static int top = 0;
    private static boolean game = true;
    private TetrisUI tui;

    // initialize the background
    public void initializeTileMap() {
        System.out.println("start Starts");
        tui = new TetrisUI();
        tui.setScore(0);
    }

    @Override
    public void generateTileEntity() {

    }

    @Override
    public void handleUserInput() {

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
}
