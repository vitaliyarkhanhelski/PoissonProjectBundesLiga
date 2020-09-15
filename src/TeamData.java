import java.util.LinkedList;
import java.util.List;

public class TeamData {
    private String homeTeam;
    private String awayTeam;

    private int allMatchesCount = 0;
    private int allHomeGoals = 0;
    private int allAwayGoals = 0;

    private int countHomeMatches = 0;
    private int countAwayMatches = 0;
    private int summaryHomeGoals = 0;
    private int summaryAwayGoals = 0;

    private int summaryHomeConceded = 0;
    private int summaryAwayConceded = 0;

    private List<MatchData> homeTeamMatchesList = new LinkedList<>();
    private List<MatchData> awayTeamMatchesList = new LinkedList<>();

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getAllMatchesCount() {
        return allMatchesCount;
    }

    public void setAllMatchesCount(int allMatchesCount) {
        this.allMatchesCount = allMatchesCount;
    }

    public int getAllHomeGoals() {
        return allHomeGoals;
    }

    public void setAllHomeGoals(int allHomeGoals) {
        this.allHomeGoals = allHomeGoals;
    }

    public int getAllAwayGoals() {
        return allAwayGoals;
    }

    public void setAllAwayGoals(int allAwayGoals) {
        this.allAwayGoals = allAwayGoals;
    }

    public int getCountHomeMatches() {
        return countHomeMatches;
    }

    public void setCountHomeMatches(int countHomeMatches) {
        this.countHomeMatches = countHomeMatches;
    }

    public int getCountAwayMatches() {
        return countAwayMatches;
    }

    public void setCountAwayMatches(int countAwayMatches) {
        this.countAwayMatches = countAwayMatches;
    }

    public int getSummaryHomeGoals() {
        return summaryHomeGoals;
    }

    public void setSummaryHomeGoals(int summaryHomeGoals) {
        this.summaryHomeGoals = summaryHomeGoals;
    }

    public int getSummaryAwayGoals() {
        return summaryAwayGoals;
    }

    public void setSummaryAwayGoals(int summaryAwayGoals) {
        this.summaryAwayGoals = summaryAwayGoals;
    }

    public int getSummaryHomeConceded() {
        return summaryHomeConceded;
    }

    public void setSummaryHomeConceded(int summaryHomeConceded) {
        this.summaryHomeConceded = summaryHomeConceded;
    }

    public int getSummaryAwayConceded() {
        return summaryAwayConceded;
    }

    public void setSummaryAwayConceded(int summaryAwayConceded) {
        this.summaryAwayConceded = summaryAwayConceded;
    }

    public void setHomeTeamMatchesList(MatchData matchData) {
        homeTeamMatchesList.add(matchData);
    }

    public void setAwayTeamMatchesList(MatchData matchData) {
        awayTeamMatchesList.add(matchData);
    }

    public void printHomeTeamMatches() {
        for (MatchData i : homeTeamMatchesList)
            i.printResult();
        System.out.println("------------------------------------------------------------------------------------_|");
    }

    public void printAwayTeamMatches() {
        for (MatchData i : awayTeamMatchesList)
            i.printResult();
        System.out.println("------------------------------------------------------------------------------------_|");
    }
}
