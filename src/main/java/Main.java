import library.InMemoryScoreBoard;
import library.ScoreBoard;

public class Main {
    public static void main(String[] args) {
        // Use the interface type for flexibility
        ScoreBoard scoreboard = new InMemoryScoreBoard();

        // Start some matches
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");

        // Update scores
        scoreboard.updateScore(0, 0, 5);  // Mexico 0 - 5 Canada
        scoreboard.updateScore(1, 10, 2); // Spain 10 - 2 Brazil
        scoreboard.updateScore(2, 2, 2);  // Germany 2 - 2 France

        // Print summary
        System.out.println("Match Summary:");
        for (String summary : scoreboard.getMatchSummary()) {
            System.out.println(summary);
        }

        // Finish a match
        scoreboard.finishMatch(1);  // Finish Spain vs Brazil

        // Print updated summary
        System.out.println("\nAfter finishing a match:");
        for (String summary : scoreboard.getMatchSummary()) {
            System.out.println(summary);
        }
    }
}



