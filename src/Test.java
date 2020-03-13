public class Test {

    public static void main(String[] args) {
        Player player = new Player();
        Session session = new Session(player.playerID, 10);
        session.saveScore();

    }
}
