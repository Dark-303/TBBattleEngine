package battleEngine;

public class BattleEngineUtil {
    public static double round(double x, int places) {
        double scale = Math.pow(10, places);
        return(Math.round(x * scale) / scale);
    }
}
