package library;

import java.util.List;

public interface ScoreBoard {
    void startMatch(String homeTeam, String awayTeam);

    void updateScore(int matchIndex, int homeScore, int awayScore);

    void finishMatch(int matchIndex);

    List<String> getMatchSummary();
}
