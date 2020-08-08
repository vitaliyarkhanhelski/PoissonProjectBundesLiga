public class Predictor {

    public static double[][] createPredictTable(int numberOfGoalsPrediction,
                                                double[] predictionScoresHomeArray,
                                                double[] predictionScoresAwayArray,
                                                TeamsData data) {
        System.out.println("______________________________RESULTS OF PREDICTIONS FROM 0 TO 10 GOALS:_______________________________________________________");
        //CREATING A TABLE
        double[][] results = new double[numberOfGoalsPrediction][numberOfGoalsPrediction];
        for (int i = 0; i < numberOfGoalsPrediction; i++) {
            for (int j = 0; j < numberOfGoalsPrediction; j++) {
                results[i][j] = predictionScoresAwayArray[i] * predictionScoresHomeArray[j];
            }
        }

        //1 row
        System.out.printf("%-10s", "");
        System.out.printf("|%-9s", data.getHomeTeam());
        for (int i = 0; i < numberOfGoalsPrediction; i++)
            System.out.printf("|    %-5d", i);
        System.out.println();
        Format.printDash();

        //2 row
        System.out.printf("%-10s", data.getAwayTeam());
        System.out.printf("|%-9s", "  %");
        for (int i = 0; i < numberOfGoalsPrediction; i++)
            System.out.printf("| %-8s", Format.form2(predictionScoresHomeArray[i] * 100) + "%");
        System.out.println();

        //3 row
        //PRINTING OF TABLE
        int n = 0;
        for (double[] i : results) {
            System.out.printf("%-10d", n);
            System.out.printf("| %-8s", Format.form2(predictionScoresAwayArray[n++] * 100) + "%");
            for (double j : i) {

                System.out.printf("| %-8s", Format.form(j * 100) + "%");
            }
            System.out.println();
        }
        return results;
    }


    public static double[] getPredictionsArray(int numberOfGoalsPrediction, double TeamPredictedNumberOfGoals) {
        double[] predictionScoresAway = new double[numberOfGoalsPrediction];
        for (int i = 0; i < numberOfGoalsPrediction; i++) {
            predictionScoresAway[i] = Poisson.predictionScores(i, TeamPredictedNumberOfGoals);
            System.out.println("For " + i + " goals prediction is " + Format.form2(predictionScoresAway[i] * 100) + "%");
        }
        return predictionScoresAway;
    }


    public static void printFinalPredictedScore(int numberOfGoalsPrediction, double[][] results, TeamsData data) {
        double max = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < numberOfGoalsPrediction; i++) {
            for (int j = 0; j < numberOfGoalsPrediction; j++)
                if (results[i][j] > max) {
                    max = results[i][j];
                    x = j;
                    y = i;
                }
        }
        System.out.println();
        System.out.println("___________________FINAL PREDICTED SCORE IS:____________");
        System.out.println("\"" + data.getHomeTeam() + "\" (Home) - " + "\"" + data.getAwayTeam() + "\" (Away) is   " + x + " : " + y);
    }

}
