package library;

import structure.Match;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScoreBoard implements ScoreBoard {
    private List<Match> matches;

    public InMemoryScoreBoard() {
        this.matches = new ArrayList<>();
    }

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
    }

    @Override
    public void updateScore(int matchIndex, int homeScore, int awayScore) {
    }

    @Override
    public void finishMatch(int matchIndex) {
    }

    @Override
    public List<String> getMatchSummary() {
        return null;
    }
}