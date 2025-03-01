package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        // Using the InMemory implementation
        scoreBoard = new InMemoryScoreBoard();
        scoreBoard.getMatchSummary().clear();
    }

    @Test
    void testStartMatch() {
        // Given: No match is started yet
        assertTrue(scoreBoard.getMatchSummary().isEmpty(), "Score board should be empty initially.");

        // When: A match is started
        scoreBoard.startMatch("Mexico", "Canada");

        // Then: The match should appear in the score board with an initial score of 0-0
        assertEquals(1, scoreBoard.getMatchSummary().size(), "There should be one match in progress.");
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Mexico 0 - 0 Canada"),
                "The match should display the correct teams and score.");
    }

    @Test
    void testUpdateScore() {
        // Given: A match is started
        scoreBoard.startMatch("Mexico", "Canada");
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Mexico 0 - 0 Canada"));

        // When: The score of the match is updated
        scoreBoard.updateScore(0, 3, 1);

        // Then: The match score should be updated in the score board
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Mexico 3 - 1 Canada"),
                "The score of the match should be updated correctly.");
    }

    @Test
    void testFinishMatch() {
        // Given: Two matches are started
        scoreBoard.startMatch("Mexico", "Canada");
        scoreBoard.startMatch("Spain", "Brazil");
        assertEquals(2, scoreBoard.getMatchSummary().size(), "There should be two matches in progress.");

        // When: One match is finished
        scoreBoard.finishMatch(0);

        // Then: The match should be removed from the scoreboard
        assertEquals(1, scoreBoard.getMatchSummary().size(), "There should be one match remaining.");
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Spain 0 - 0 Brazil"),
                "Only the second match should remain in the score board.");
    }

    @Test
    void testGetMatchSummaryOrderedByTotalScore() {
        // Given: Three matches are started
        scoreBoard.startMatch("Mexico", "Canada");
        scoreBoard.startMatch("Spain", "Brazil");
        scoreBoard.startMatch("Germany", "France");

        // When: The scores are updated
        scoreBoard.updateScore(0, 3, 1);  // Mexico 3 - 1 Canada
        scoreBoard.updateScore(1, 10, 2); // Spain 10 - 2 Brazil
        scoreBoard.updateScore(2, 0, 2);  // Germany 0 - 2 France

        // Then: The match summary should be sorted by total score and recency
        var summary = scoreBoard.getMatchSummary();

        // First match should be Spain vs Brazil (total score: 12)
        assertTrue(summary.get(0).contains("Spain 10 - 2 Brazil"));

        // Second match should be Mexico vs Canada (total score: 4)
        assertTrue(summary.get(1).contains("Mexico 3 - 1 Canada"));

        // Third match should be Germany vs France (total score: 2)
        assertTrue(summary.get(2).contains("Germany 0 - 2 France"));
    }

    @Test
    void testGetSummaryWithNoMatches() {
        // Given: No matches are started
        assertTrue(scoreBoard.getMatchSummary().isEmpty(), "The score Board should be empty when no matches are started.");
    }

    @Test
    void testGetMatchSummaryWithSameTotalScore() {
        scoreBoard.startMatch("Mexico", "Canada");
        //To avoid flaky tests related to the match startTime (in some cases being close to the same for two games)
        sleep();
        scoreBoard.startMatch("Spain", "Brazil");

        // When: Scores are updated with the same total score
        scoreBoard.updateScore(0, 3, 1);  // Mexico 3 - 1 Canada
        scoreBoard.updateScore(1, 2, 2);  // Spain 2 - 2 Brazil

        // Then: The summary should order the most recent match first, even with the same total score
        var summary = scoreBoard.getMatchSummary();

        assertTrue(summary.get(0).contains("Spain 2 - 2 Brazil"), "The most recent match should appear first.");
        assertTrue(summary.get(1).contains("Mexico 3 - 1 Canada"));
    }

    @Test
    void testFinishMatchWithInvalidIndex() {
        // Given: One match is started
        scoreBoard.startMatch("Mexico", "Canada");
        assertEquals(1, scoreBoard.getMatchSummary().size(), "There should be one match.");

        // When: An invalid match index is used
        scoreBoard.finishMatch(100);

        // Then: The match should remain in the score board
        assertEquals(1, scoreBoard.getMatchSummary().size(), "The match should still be in progress.");
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Mexico 0 - 0 Canada"));
    }

    @Test
    void testFinishMatchWithNegativeIndex() {
        // Given: One match is started
        scoreBoard.startMatch("Mexico", "Canada");
        assertEquals(1, scoreBoard.getMatchSummary().size(), "There should be one match.");

        // When: A negative index is used to finish a match
        scoreBoard.finishMatch(-1);

        // Then: The match should remain in the scoreboard, as the index is invalid
        assertEquals(1, scoreBoard.getMatchSummary().size(), "The match should still be in progress.");
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Mexico 0 - 0 Canada"));
    }

    @Test
    void testUpdateScoreWithInvalidIndex() {
        // Given: One match is started
        scoreBoard.startMatch("Mexico", "Canada");
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Mexico 0 - 0 Canada"));

        // When: An invalid match index is used for updating score
        scoreBoard.updateScore(100, 3, 1);

        // Then: The match score should remain unchanged
        assertTrue(scoreBoard.getMatchSummary().get(0).contains("Mexico 0 - 0 Canada"));
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}