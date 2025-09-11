package battleEngine;

public class BattleEngineUtil {
    public static double round(double x, int places) {
        double scale = Math.pow(10, places);
        return (Math.round(x * scale) / scale);
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean cooldown(int cooldown, int cooldownMax) {
        if (cooldown >= cooldownMax) {
            return true;
        } else {
            return false;
        }
    }
}