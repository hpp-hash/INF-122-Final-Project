package tmge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Session {
    public String sessionID;
    public int currentScore;
    public int[] bestScoreArr;
    public int timeElapsed;

    public Session(String playerID, int score) {
        sessionID = playerID; // each session is linked to a player
        currentScore = score;
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
