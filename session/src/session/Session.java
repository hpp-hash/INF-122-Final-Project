package session;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Session {
    int sessionID;
    int currentScore;
    ArrayList<Integer> scoreArr;
    int bestScore;

    public Session(int playerID, int score) {
        sessionID = playerID; // each session is linked to a player
        currentScore = score;
        scoreArr = new ArrayList<>();
        bestScore = score;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public ArrayList<Integer> getScoreArr() {
        return scoreArr;
    }

    public void addScore(int score) {
        this.scoreArr.add(score);
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void start() {

    }

    public void end() {

    }

    public void save() {

    }

    public void saveScore() {
        // save currentScore to a file

        try {
            File scoreFile = new File(sessionID + ".txt");
            FileWriter myWriter = new FileWriter(scoreFile.getName());
            BufferedWriter myBufWriter = new BufferedWriter(myWriter);

            if (scoreFile.createNewFile()) {
                System.out.println("File created: " + scoreFile.getName());
            }
            else {
                System.out.println("File " + scoreFile.getName() + "  already exists.");
            }

            System.out.println("currentScore=" + currentScore);
            myBufWriter.write(Integer.toString(currentScore));
            myBufWriter.close();
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
