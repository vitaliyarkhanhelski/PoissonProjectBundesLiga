public class FootballTeam {
    private final String homeTeam;
    private final String awayTeam;
    private final String scoreForHomeTeam;
    private final String scoreForAwayTeam;

    public FootballTeam(String homeTeam, String awayTeam, String scoreForHomeTeam, String scoreForAwayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scoreForHomeTeam = scoreForHomeTeam;
        this.scoreForAwayTeam = scoreForAwayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getScoreForHomeTeam() {
        return scoreForHomeTeam;
    }

    public String getScoreForAwayTeam() {
        return scoreForAwayTeam;
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", scoreForHomeTeam='" + scoreForHomeTeam + '\'' +
                ", scoreForAwayTeam='" + scoreForAwayTeam + '\'' +
                '}';
    }

    public void printResult(){
        System.out.printf("| Home Team = %-20s| Away Team = %-20s|   Score= %s : %s |\n", homeTeam, awayTeam, scoreForHomeTeam, scoreForAwayTeam);
    }

}
