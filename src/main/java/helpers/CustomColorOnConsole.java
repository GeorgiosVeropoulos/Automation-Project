package helpers;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomColorOnConsole {

    /**
     * The only reason this class exists is to play around with the colors in a more custom way instead of
     * fucking around with a log4j file.
     * ex. green text on Test PASS log.
     */

    public enum Color {
        WHITE,
        GREEN,
        RED;
    }

    public static String setColor(String input, Color color) {
        String output = input;
        switch (color) {
            case WHITE:
                output = "\033[1;33m" + input + "\033[0m";
                break;
            case GREEN:
                output = "\033[1;36m" + input + "\033[0m";
                break;
            case RED:
                output = "\033[1;31m" + input + "\033[0m";
                break;
            default:
                log.warn("Failed to add color returning input as is.");
                break;
        }
        return output;
    }
}
