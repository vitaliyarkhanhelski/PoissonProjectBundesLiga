import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MalformedURLException {

        String csvFile = "https://www.football-data.co.uk/mmz4281/1920/D1.csv";
        URL stockURL = new URL(csvFile);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        boolean flag = true;

        int allMatchesCount = 0;
        int allHomeGoals = 0;
        int allAwayGoals = 0;

        int countKolnHomeMatches = 0;
        int countFreiburgAwayMatches = 0;
        int kolnSummaryHomeGoals = 0;
        int freiburgSummaryAwayGoals = 0;

        int kolnSummaryHomeConceded = 0;
        int freiburgSummaryAwayConceded = 0;

        List<FootballTeam> kolnHomeMatches = new LinkedList<>();
        List<FootballTeam> freiburgAwayMatches = new LinkedList<>();

        try {

            br = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] d1 = line.split(cvsSplitBy);
                if (flag) {
                    System.out.printf("Home Team   %-20s| Away Team   %-20s|   Score= %s : %s\n", "", "", d1[5], d1[6]);
                    flag = false;
                    System.out.println("----------------------------------------------------------------------------------");
                } else {
                    System.out.printf("Home Team = %-20s| Away Team = %-20s|   Score= %s : %s\n", d1[3], d1[4], d1[5], d1[6]);
                    allMatchesCount++;
                    allHomeGoals += Integer.valueOf(d1[5]);
                    allAwayGoals += Integer.valueOf(d1[6]);

// KOLN HOME DATA
                    if (d1[3].equals("FC Koln")) {
                        kolnHomeMatches.add(new FootballTeam(d1[3], d1[4], d1[5], d1[6]));
                        countKolnHomeMatches++;
                        kolnSummaryHomeGoals += Integer.valueOf(d1[5]);
                        kolnSummaryHomeConceded += Integer.valueOf(d1[6]);
                    }

//FREIBURG AWAY DATA
                    if (d1[4].equals("Freiburg")) {
                        freiburgAwayMatches.add(new FootballTeam(d1[3], d1[4], d1[5], d1[6]));
                        countFreiburgAwayMatches++;
                        freiburgSummaryAwayGoals += Integer.valueOf((d1[6]));
                        freiburgSummaryAwayConceded += Integer.valueOf((d1[5]));
                    }

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("________________________________________SEASON:____________________________________");
        System.out.println("All matches count is " + allMatchesCount);
        System.out.println("All matches home goals is " + allHomeGoals);
        System.out.println("All matches away goals is " + allAwayGoals);

        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        DecimalFormat decimalFormat2 = new DecimalFormat("#.#");

        double avgGoalsHome = (double) allHomeGoals / allMatchesCount;
        double avgGoalsAway = (double) allAwayGoals / allMatchesCount;
        System.out.println("Average goals home during season is " + decimalFormat.format(avgGoalsHome));
        System.out.println("Average goals away during season is " + decimalFormat.format(avgGoalsAway));


        System.out.println();
        System.out.println("---------------------All FC KOLN Matches---------------------------------------------_");


        for (FootballTeam i : kolnHomeMatches)
            i.printResult();
        System.out.println("------------------------------------------------------------------------------------_|");

        System.out.println("*** Number of Koln Home Matches is " + countKolnHomeMatches);
        System.out.println("*** Count of goals of Koln Home Matches is " + kolnSummaryHomeGoals);
        System.out.println("*** Count of conceded of Koln Home Matches is " + kolnSummaryHomeConceded);

        //0.5
        double kolnAvgHomeGoals = (double) kolnSummaryHomeGoals / countKolnHomeMatches;
        System.out.println("*** Average count of goals of Koln Home Matches is " + decimalFormat.format(kolnAvgHomeGoals));

        //1
        double attackStrengthHomeTeam = kolnAvgHomeGoals / avgGoalsHome;
        System.out.print("*** Attack Strength of Koln at Home is " + decimalFormat.format(attackStrengthHomeTeam));

        if (attackStrengthHomeTeam < 1.0)
            System.out.println(";   " + decimalFormat2.format((1.0 - attackStrengthHomeTeam) * 100) + "% worse than average");
        else if (attackStrengthHomeTeam > 1.0)
            System.out.println(";   " + decimalFormat2.format((attackStrengthHomeTeam - 1) * 100) + "% better than average");
        else System.out.println(";   Attack Strength is the same as average");

        //2
        double freiburgAvgAwayConceded = (double) freiburgSummaryAwayConceded / countFreiburgAwayMatches;
        System.out.println("*** Average count of conceded of Freiburg Away Matches is " + decimalFormat.format(freiburgAvgAwayConceded));

        //3
        double defenceStrengthAwayTeam = freiburgAvgAwayConceded / avgGoalsHome;
        System.out.print("*** Defence Strength of Freiburg away is " + decimalFormat.format(defenceStrengthAwayTeam));

        if (defenceStrengthAwayTeam < 1.0)
            System.out.println(";   " + decimalFormat2.format((1.0 - defenceStrengthAwayTeam) * 100) + "% better than average");
        else if (defenceStrengthAwayTeam > 1.0)
            System.out.println(";   " + decimalFormat2.format((defenceStrengthAwayTeam - 1) * 100) + "% worse than average");
        else System.out.println(";   Defence Strength is the same as average");

        //Prediction number of goals of HOME TEAM FC KOLN
        double homeTeamPredictedNumberOfGoals = attackStrengthHomeTeam * defenceStrengthAwayTeam * avgGoalsHome;
        System.out.println("Predicted Home Team Number Of Goals is " + decimalFormat.format(homeTeamPredictedNumberOfGoals));

        //Predictions from 0 to 10 for FC KOLN
        double[] predictionScoresHome = new double[11];
        for (int i = 0; i <= 10; i++)
            predictionScoresHome[i] = predictionScores(i, homeTeamPredictedNumberOfGoals);

        for (int i = 0; i <= 10; i++)
            System.out.println("For " + i + " goals prediction is " + decimalFormat.format(predictionScoresHome[i]));

        System.out.println();
        System.out.println("---------------------All FC FREIBURG Matches-----------------------------------------_");

        for (FootballTeam i : freiburgAwayMatches)
            i.printResult();
        System.out.println("------------------------------------------------------------------------------------_|");

        System.out.println("*** Number of Freiburg Away Matches is " + countFreiburgAwayMatches);
        System.out.println("*** Count of goals of Freiburg Away Matches is " + freiburgSummaryAwayGoals);
        System.out.println("*** Count of conceded of Freiburg Away Matches is " + freiburgSummaryAwayConceded);

        //0.5
        double freiburgAvgAwayGoals = (double) freiburgSummaryAwayGoals / countFreiburgAwayMatches;
        System.out.println("*** Average count of goals of Freiburg Away Matches is " + decimalFormat.format(freiburgAvgAwayGoals));

        //1
        double attackStrengthAwayTeam = freiburgAvgAwayGoals / avgGoalsAway;
        System.out.print("*** Attack Strength of Freiburg Away is " + decimalFormat.format(attackStrengthAwayTeam));


        if (attackStrengthAwayTeam < 1.0)
            System.out.println(";   " + decimalFormat2.format((1.0 - attackStrengthAwayTeam) * 100) + "% worse than average");
        else if (attackStrengthAwayTeam > 1.0)
            System.out.println(";   " + decimalFormat2.format((attackStrengthAwayTeam - 1) * 100) + "% better than average");
        else System.out.println(";   Attack Strength is the same as average");

        //2
        double kolnAvgHomeConceded = (double) kolnSummaryHomeConceded / countKolnHomeMatches;
        System.out.println("*** Average count of conceded of Koln Home Matches is " + decimalFormat.format(kolnAvgHomeConceded));


        //3
        double defenceStrengthHomeTeam = kolnAvgHomeConceded / avgGoalsAway;
        System.out.print("*** Defence Strength of Freiburg away is " + decimalFormat.format(defenceStrengthHomeTeam));

        if (defenceStrengthHomeTeam < 1.0)
            System.out.println(";   " + decimalFormat2.format((1.0 - defenceStrengthHomeTeam) * 100) + "% better than average");
        else if (defenceStrengthHomeTeam > 1.0)
            System.out.println(";   " + decimalFormat2.format((defenceStrengthHomeTeam - 1) * 100) + "% worse than average");
        else System.out.println(";   Defence Strength is the same as average");

        //Prediction number of goals of AWAY TEAM FREIBURG
        double awayTeamPredictedNumberOfGoals = attackStrengthAwayTeam * defenceStrengthHomeTeam * avgGoalsAway;

        System.out.println("Predicted Away Team Number Of Goals is " + decimalFormat.format(awayTeamPredictedNumberOfGoals));

        //Predictions from 0 to 10 for FREIBURG
        double[] predictionScoresAway = new double[11];
        for (int i = 0; i <= 10; i++)
            predictionScoresAway[i] = predictionScores(i, awayTeamPredictedNumberOfGoals);

        for (int i = 0; i <= 10; i++)
            System.out.println("For " + i + " goals prediction is " + decimalFormat.format(predictionScoresAway[i]));

        System.out.println("______________________________RESULTS OF PREDICTIONS FROM 0 TO 10 GOALS:_______________________________________________________");

        //CREATING A TABLE
        double[][] results = new double[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                results[i][j] = predictionScoresAway[i] * predictionScoresHome[j];
            }
        }


        //1 row
        System.out.printf("%-10s", "");
        System.out.printf("|%-9s", " FC KOLN");


        for (int i = 0; i <= 10; i++)
            System.out.printf("|    %-5d", i);
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");


        //2 row
        System.out.printf("%-10s", "Freiburg");
        System.out.printf("|%-9s", "  %");

        for (int i = 0; i <= 10; i++)
            System.out.printf("| %-8s", decimalFormat2.format(predictionScoresHome[i] * 100) + "%");

        System.out.println();

        //3 row

//PRINTING OF TABLE
        int n = 0;
        for (double[] i : results) {
            System.out.printf("%-10d", n);
            System.out.printf("| %-8s", decimalFormat2.format(predictionScoresAway[n++] * 100) + "%");
            for (double j : i) {

                System.out.printf("| %-8s", decimalFormat.format(j * 100) + "%");
            }
            System.out.println();
        }

        double max = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
                if (results[i][j] > max) {
                    max = results[i][j];
                    x = j;
                    y = i;
                }
        }
        System.out.println();
        System.out.println("___________________FINAL PREDICTED SCORE IS:____________");
        System.out.println("FC Koln (Home) - Freiburg (Away) is   " + x + " : " + y);


    }

    public static long factorial(int a) {
        long result = 1;
        if (a == 0) return result;
        for (int i = 1; i <= a; i++)
            result *= i;
        return result;
    }

    public static double predictionScores(int k, double a) {
        double result = 1;
        result = (Math.pow(a, k) * Math.pow(Math.E, -a)) / factorial(k);
        return result;
    }

}
