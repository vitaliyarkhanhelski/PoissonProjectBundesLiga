public class Poisson {
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
