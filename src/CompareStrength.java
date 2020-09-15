public class CompareStrength {
    public static void compareAttackStrength(double attackStrength) {
        if (attackStrength < 1.0)
            System.out.println(";   " + Formatting.form2((1.0 - attackStrength) * 100) + "% worse than average");
        else if (attackStrength > 1.0)
            System.out.println(";   " + Formatting.form2((attackStrength - 1) * 100) + "% better than average");
        else System.out.println(";   Attack Strength is the same as average");
    }

    public static void compareDefenceStrength(double defenceStrength) {
        if (defenceStrength < 1.0)
            System.out.println(";   " + Formatting.form2((1.0 - defenceStrength) * 100) + "% better than average");
        else if (defenceStrength > 1.0)
            System.out.println(";   " + Formatting.form2((defenceStrength - 1) * 100) + "% worse than average");
        else System.out.println(";   Defence Strength is the same as average");
    }
}
