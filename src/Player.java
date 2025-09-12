public class Player {
    private final String name;
    private int wins = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        this.wins++;
    }
}