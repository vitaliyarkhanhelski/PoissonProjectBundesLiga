import java.text.DecimalFormat;

public class Formatting {
    public static DecimalFormat decimalFormat() {
        return new DecimalFormat("#.###");
    }

    public static DecimalFormat decimalFormatShort() {
        return new DecimalFormat("#.#");
    }

    public static String form(double d) {
        return decimalFormat().format(d);
    }

    public static String form2(double d) {
        return decimalFormatShort().format(d);
    }

    public static void printDash() {
        System.out.println("___________________________________________________________________________________________");
    }
}
