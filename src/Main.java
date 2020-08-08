public class Main {
    public static void main(String[] args) {

        final int numberOfGoalsPrediction = 11;

        String csvFile = "D1.csv";
        String cvsSplitBy = ",";


        //print file D1.csv to console and collect all the data

        TeamsData data = IO.collectData(csvFile, cvsSplitBy);

        String homeTeam = data.getHomeTeam();
        String awayTeam = data.getAwayTeam();

        int allMatchesCount = data.getAllMatchesCount();
        int allHomeGoals = data.getAllHomeGoals();
        int allAwayGoals = data.getAllAwayGoals();

        int countHomeTeamMatches = data.getCountHomeMatches();
        int countAwayTeamMatches = data.getCountAwayMatches();
        int summaryHomeTeamGoals = data.getSummaryHomeGoals();
        int summaryAwayTeamGoals = data.getSummaryAwayGoals();
        int summaryHomeTeamConceded = data.getSummaryHomeConceded();
        int summaryAwayTeamConceded = data.getSummaryAwayConceded();

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("________________________________________SEASON 2019/2020:______________________________");
        System.out.println("All matches count in season 2019/2020 is " + allMatchesCount);
        System.out.println("All matches home goals is " + allHomeGoals);
        System.out.println("All matches away goals is " + allAwayGoals);

        double avgGoalsHomeAllTeams = (double) allHomeGoals / allMatchesCount;
        double avgGoalsAwayAllTeams = (double) allAwayGoals / allMatchesCount;
        System.out.println("Average goals home during season is " + Format.form(avgGoalsHomeAllTeams));
        System.out.println("Average goals away during season is " + Format.form(avgGoalsAwayAllTeams));

        System.out.println();

        //HOME TEAM DATA
        System.out.println("---------------------All \"" + homeTeam + "\" Matches---------------------------------------------_");
        data.printHomeTeamMatches();

        System.out.println("*** Number of \"" + homeTeam + "\" Home Matches is " + countHomeTeamMatches);
        System.out.println("*** Count of goals of \"" + homeTeam + "\" Home Matches is " + summaryHomeTeamGoals);
        System.out.println("*** Count of conceded of \"" + homeTeam + "\" Home Matches is " + summaryHomeTeamConceded);

        //Calculation of prediction of number of goals of Home Team
        //1
        double avgHomeTeamGoals = (double) summaryHomeTeamGoals / countHomeTeamMatches;
        System.out.println("*** Average count of goals of \"" + homeTeam + "\" Home Matches is " + Format.form(avgHomeTeamGoals));

        //2  Attack Strength of Home Team
        double attackStrengthHomeTeam = avgHomeTeamGoals / avgGoalsHomeAllTeams;
        System.out.print("*** Attack Strength of \"" + homeTeam + "\" at Home is " + Format.form(attackStrengthHomeTeam));
        //compare with avg result
        CompareStrength.compareAttackStrength(attackStrengthHomeTeam);

        //3
        double avgAwayTeamConceded = (double) summaryAwayTeamConceded / countAwayTeamMatches;
        System.out.println("*** Average count of conceded of \"" + awayTeam + "\" Away Matches is " + Format.form(avgAwayTeamConceded));

        //4 Defence Strength of Away Team
        double defenceStrengthAwayTeam = avgAwayTeamConceded / avgGoalsHomeAllTeams;
        System.out.print("*** Defence Strength of \"" + awayTeam + "\" away is " + Format.form(defenceStrengthAwayTeam));
        //compare with avg result
        CompareStrength.compareDefenceStrength(defenceStrengthAwayTeam);

        //Prediction number of goals of HOME TEAM
        double homeTeamPredictedNumberOfGoals = attackStrengthHomeTeam * defenceStrengthAwayTeam * avgGoalsHomeAllTeams;
        System.out.println("Predicted \"" + homeTeam + "\" Home Team Number Of Goals is " + Format.form(homeTeamPredictedNumberOfGoals));

        //Predictions from 0 to 10 for Home Team
        double[] predictionScoresHomeArray = Predictor.getPredictionsArray(numberOfGoalsPrediction, homeTeamPredictedNumberOfGoals);

        //AWAY TEAM DATA
        System.out.println("---------------------All \"" + awayTeam + "\" Matches-----------------------------------------_");
        data.printAwayTeamMatches();

        System.out.println("*** Number of \"" + awayTeam + "\" Away Matches is " + countAwayTeamMatches);
        System.out.println("*** Count of goals of \"" + awayTeam + "\" Away Matches is " + summaryAwayTeamGoals);
        System.out.println("*** Count of conceded of \"" + awayTeam + "\" Away Matches is " + summaryAwayTeamConceded);

        //Calculation of prediction of number of goals of Away Team
        //1
        double avgAwayTeamGoals = (double) summaryAwayTeamGoals / countAwayTeamMatches;
        System.out.println("*** Average count of goals of \"" + awayTeam + "\" Away Matches is " + Format.form(avgAwayTeamGoals));

        //2  Attack Strength of Away Team
        double attackStrengthAwayTeam = avgAwayTeamGoals / avgGoalsAwayAllTeams;
        System.out.print("*** Attack Strength of \"" + awayTeam + "\" Away is " + Format.form(attackStrengthAwayTeam));
        //compare with avg result
        CompareStrength.compareAttackStrength(attackStrengthAwayTeam);

        //3
        double avgHomeTeamConceded = (double) summaryHomeTeamConceded / countHomeTeamMatches;
        System.out.println("*** Average count of conceded of \"" + homeTeam + "\" Home Matches is " + Format.form(avgHomeTeamConceded));

        //4 Defence Strength of Home Team
        double defenceStrengthHomeTeam = avgHomeTeamConceded / avgGoalsAwayAllTeams;
        System.out.print("*** Defence Strength of \"" + awayTeam + "\" away is " + Format.form(defenceStrengthHomeTeam));
        //compare with avg result
        CompareStrength.compareDefenceStrength(defenceStrengthHomeTeam);

        //Prediction number of goals of AWAY TEAM
        double awayTeamPredictedNumberOfGoals = attackStrengthAwayTeam * defenceStrengthHomeTeam * avgGoalsAwayAllTeams;
        System.out.println("Predicted \"" + awayTeam + "\" Away Team Number Of Goals is " + Format.form(awayTeamPredictedNumberOfGoals));

        //Predictions from 0 to 10 for AWAY TEAM
        double[] predictionScoresAwayArray = Predictor.getPredictionsArray(numberOfGoalsPrediction, awayTeamPredictedNumberOfGoals);

        //FINAL RESULTS
        //print 2-dimension array with predictions
        double[][] results = Predictor.createPredictTable(numberOfGoalsPrediction, predictionScoresHomeArray, predictionScoresAwayArray, data);

        //provide final result - PREDICTED SCORE
        Predictor.printFinalPredictedScore(numberOfGoalsPrediction, results, data);
    }
}
