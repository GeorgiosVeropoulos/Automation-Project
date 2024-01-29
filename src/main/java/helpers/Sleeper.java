package helpers;

import lombok.extern.log4j.Log4j2;

/**
 * Basic {@code Thread} sleep implemention.
 * <p><b>Don't use it as a waiter unless absolutely forced to.</b></p>
 */
@Log4j2
public class Sleeper {


    public static void sleepInSeconds(int seconds) {
        sleepInMillis(seconds * 1_000L);
    }

    public static void sleepInMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }
}
