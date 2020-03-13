import java.util.UUID;

public class Player {
    public String playerID;
    public Session currentSession;
    public Session[] finishedSessions;
    public int highScore;

    public Player() {
        playerID = UUID.randomUUID().toString();
        currentSession = new Session(playerID);
    }

    public void startSession() {

    }
}
