# Live Football World Cup Score Board

## Description
The Live Football World Cup Scoreboard is a simple Java-based library that allows users to manage ongoing football matches and track their scores. It supports the following features:
1. Starting a new match with an initial score of 0 - 0.
2. Updating the score of an ongoing match.
3. Finishing a match and removing it from the scoreboard.
4. Retrieving a summary of all ongoing matches sorted by their total score and start time.

## Requirements
- Java 8 or later
- JUnit for unit tests (JUnit 5 recommended)

## Project Classes
### `Match`
Represents a football match between two teams. The `Match` class contains:
- `homeTeam`: Name of the home team.
- `awayTeam`: Name of the away team.
- `homeScore`: The current score of the home team.
- `awayScore`: The current score of the away team.
- `startTime`: The timestamp when the match started.

### `InMemoryScoreBoard`
The `InMemoryScoreBoard` class implements the `ScoreBoard` interface and uses an `LinkedList` to store ongoing matches in memory. It provides methods to:
- Start a new match (`startMatch()`).
- Update the score of an ongoing match (`updateScore()`).
- Finish a match and remove it from the scoreboard (`finishMatch()`).
- Retrieve a match summary (`getMatchSummary()`).

### `ScoreBoard`
This is the interface that defines the contract for the scoreboard. It has the following methods:
- `startMatch(String homeTeam, String awayTeam)`: Starts a new match.
- `updateScore(int matchIndex, int homeScore, int awayScore)`: Updates the score for an ongoing match.
- `finishMatch(int matchIndex)`: Finishes and removes a match.
- `getMatchSummary()`: Retrieves a list of ongoing matches sorted by total score and start time.

## How to Use
To integrate the Football Scoreboard library into your project, follow the instructions based on the build system you're using.

### `Maven`
If you're using Maven, you can easily add the dependency to your project by modifying the pom.xml file. Add the following dependency inside the <dependencies> section:

<dependency>
    <groupId>org.example</groupId>
    <artifactId>score-board</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>

### `Gradle`
If you're using Gradle, simply add the following line to your build.gradle file inside the dependencies block:

dependencies {
implementation 'org.example:score-board:1.0-SNAPSHOT'
}

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        // Use the interface in memory version
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

        // Finish Spain vs Brazil
        scoreboard.finishMatch(1);  

        // Updated summary
        System.out.println("\nAfter finishing a match:");
        for (String summary : scoreboard.getMatchSummary()) {
            System.out.println(summary);
        }
    }
}
