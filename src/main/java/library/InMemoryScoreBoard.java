package library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import structure.Match;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryScoreBoard implements ScoreBoard {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryScoreBoard.class);
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
        } else {
            LOGGER.error("Provided match index: {} is invalid", matchIndex);
        }
    }

    @Override
    public void finishMatch(int matchIndex) {
        if (matchIndex >= 0 && matchIndex < matches.size()) {
            matches.remove(matchIndex);
        } else {
            LOGGER.error("Can not remove match with invalid index: {}", matchIndex);
        }
    }

    @Override
    public List<String> getMatchSummary() {
        return matches.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()  // Sort by total score descending
                        .thenComparing(Match::getStartTime, Comparator.reverseOrder()))  // Sort by start time (most recent first)
                .map(Match::getMatchSummary)
                .collect(Collectors.toList());
    }
}