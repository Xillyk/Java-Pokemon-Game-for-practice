
public class GameUtility {

    public static void delay(int _delayInMicrosec) {
        try {
            Thread.sleep(_delayInMicrosec);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    public static int randomInt(int min, int max) {
        int randNum = 0;
        randNum = (int) (Math.random() * ((max - min) + 1)) + min;
        return randNum;
    }

    public static double randomDouble(int min, int max) {
        double randNum = 0;
        randNum = (double) Math.random() * ((max - min) + 1) + min;
        return randNum;
    }
}