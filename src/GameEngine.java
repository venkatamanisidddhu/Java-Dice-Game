import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final List<Player> players;
    private final Dice dice = new Dice();
    private int roundCount = 0;

    public GameEngine(List<Player> players) {
        this.players = players;
    }

    public String playARound() {
        roundCount++;
        StringBuilder roundLog = new StringBuilder("--- Round " + roundCount + " ---\n");

        int highestRoll = 0;
        List<Player> roundWinners = new ArrayList<>();

        for (Player player : players) {
            int roll = dice.roll();
            roundLog.append(player.getName()).append(" rolled a ").append(roll).append("\n");

            if (roll > highestRoll) {
                highestRoll = roll;
                roundWinners.clear();
                roundWinners.add(player);
            } else if (roll == highestRoll) {
                roundWinners.add(player);
            }
        }

        if (roundWinners.size() == 1) {
            Player winner = roundWinners.get(0);
            winner.incrementWins();
            roundLog.append("\n🎉 ").append(winner.getName()).append(" wins this round!\n");
        } else {
            roundLog.append("\n⚖️ This round is a tie!\n");
        }
        return roundLog.toString();
    }

    public String getScoreboard() {
        StringBuilder scores = new StringBuilder("--- Scoreboard ---\n");
        for (Player player : players) {
            scores.append(player.getName()).append(": ").append(player.getWins()).append(" wins\n");
        }
        return scores.toString();
    }
}
