package library;

import structure.Match;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryScoreBoard implements ScoreBoard {
    private final List<Match> matches;

    public InMemoryScoreBoard() {
        this.matches = new LinkedList<>();
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
        return matches.stream()  // Create a stream from the matches list
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()  // Sort by total score descending
                        .thenComparing(Match::getStartTime))  // Sort by start time (most recent first)
                .map(Match::getMatchSummary)  // Map each match to its summary
                .collect(Collectors.toList());
    }
}