import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiceGameUI extends JFrame {

    private GameEngine gameEngine;
    private final JTextArea logTextArea = new JTextArea();
    private final JTextArea scoreboardTextArea = new JTextArea();

    public DiceGameUI() {
        if (!setupGame()) {
            System.exit(0);
        }
        initializeUI();
    }

    private boolean setupGame() {
        int numPlayers = 0;
        try {
            while (numPlayers < 2) {
                String input = JOptionPane.showInputDialog(this, "Enter the number of players (minimum 2):");
                if (input == null) return false;
                numPlayers = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            return false;
        }

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(this, "Enter name for Player " + (i + 1) + ":");
            if (name == null || name.trim().isEmpty()) return false;
            players.add(new Player(name.trim()));
        }

        this.gameEngine = new GameEngine(players);
        return true;
    }

    private void initializeUI() {
        setTitle("Dice Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        logTextArea.setText("Welcome! Click 'Play Next Round' to start.\n\n");
        
        scoreboardTextArea.setEditable(false);
        scoreboardTextArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        scoreboardTextArea.setPreferredSize(new Dimension(200, 0));
        updateScoreboard();

        JButton playButton = new JButton("Play Next Round");
        playButton.addActionListener(e -> playRoundAndUpdateUI());
        
        add(new JScrollPane(logTextArea), BorderLayout.CENTER);
        add(scoreboardTextArea, BorderLayout.EAST);
        add(playButton, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    private void playRoundAndUpdateUI() {
        logTextArea.append(gameEngine.playARound() + "\n");
        updateScoreboard();
    }

    private void updateScoreboard() {
        scoreboardTextArea.setText(gameEngine.getScoreboard());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DiceGameUI::new);
    }
}