package structure;

import java.time.OffsetDateTime;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final OffsetDateTime startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = OffsetDateTime.now();
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    public String getMatchSummary() {
        return homeTeam + " " + homeScore + " - " + awayScore + " " + awayTeam;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }
}
