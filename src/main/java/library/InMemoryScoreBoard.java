package library;

import structure.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InMemoryScoreBoard implements ScoreBoard {
    private List<Match> matches;

    public InMemoryScoreBoard() {
        this.matches = new ArrayList<>();
    }

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    @Override
    public void updateScore(int matchIndex, int homeScore, int awayScore) {
        if (matchIndex >= 0 && matchIndex < matches.size()) {
            matches.get(matchIndex).updateScore(homeScore, awayScore);
        }
    }

    @Override
    public void finishMatch(int matchIndex) {
        if (matchIndex >= 0 && matchIndex < matches.size()) {
            matches.remove(matchIndex);
        }
    }

    @Override
    public List<String> getMatchSummary() {
        matches.sort(Comparator.comparingInt(Match::getTotalScore).reversed()
                .thenComparingLong(m -> System.currentTimeMillis() - m.getHomeScore())); // Using currentTimeMillis for sorting

        List<String> summaries = new ArrayList<>();
        for (Match match : matches) {
            summaries.add(match.getMatchSummary());
        }
        return summaries;
    }
}