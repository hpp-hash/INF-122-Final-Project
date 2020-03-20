package player;

public class Multiplayer {
    Player player1;
    Player player2;
    Player currentPlayer;

    boolean player1GameActive;
    boolean player2GameActive;

    public Multiplayer () {
        player1 = new Player();
        player2 = new Player();
        currentPlayer = player1;

        player1GameActive = true;
        player2GameActive = false;
    }

    public Multiplayer (boolean active) {
        player1 = new Player();
        player2 = new Player();
        currentPlayer = player1;

        player2GameActive = active;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void switchPlayer () {
        if(currentPlayer == player1) currentPlayer = player2;
        else currentPlayer = player1;
    }

    public boolean isPlayer1GameActive() {
        return player1GameActive;
    }

    public void setPlayer1GameActive(boolean player1GameActive) {
        this.player1GameActive = player1GameActive;
    }

    public boolean isPlayer2GameActive() {
        return player2GameActive;
    }

    public void setPlayer2GameActive(boolean player2GameActive) {
        this.player2GameActive = player2GameActive;
    }

    public boolean isPlayer1 () {
        return currentPlayer == player1;
    }

    public int getPlayer1HighScore () {
        return player1.getHighScore();
    }

    public int getPlayer2HighScore () {
        return player2.getHighScore();
    }

    public void setPlayer1HighScore (int score) {
        player1.setHighScore(score);
    }

    public void setPlayer2HighScore (int score) {
        player2.setHighScore(score);
    }

    public boolean isGameActive() {
        return (isPlayer1GameActive() || isPlayer2GameActive());
    }

    public void setCurrentPlayerHighScore (int score ) {
        currentPlayer.setHighScore(score);
    }

    public int getCurrentPlayerHighScore () {
        return currentPlayer.getHighScore();
    }

    public void switchActiveGame() {
        if(player2GameActive) player2GameActive = false;
        else player2GameActive = true;
    }
}
