package player;

import session.Session;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    int playerID;
    Session lastSession;
    ArrayList<Session> finishedSessions;
    int highScore;

    private static AtomicInteger playerCount = new AtomicInteger(1);

    public Player() {
        playerID = playerCount.getAndIncrement();
        finishedSessions = new ArrayList<>();
    }

    public void saveSession(int score) {
        lastSession = new Session(playerID, score);
        finishedSessions.add(lastSession);
        if (score > highScore) highScore = score;
    }

    public Session getLastSession() {
        return lastSession;
    }

    public void setLastSession(Session lastSession) {
        this.lastSession = lastSession;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void startSession() {

    }

    public String getName() {
        return "Player " + playerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerID == player.playerID &&
                highScore == player.highScore &&
                Objects.equals(lastSession, player.lastSession) &&
                Objects.equals(finishedSessions, player.finishedSessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID, lastSession, finishedSessions, highScore);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", highScore=" + highScore +
                '}';
    }
}
