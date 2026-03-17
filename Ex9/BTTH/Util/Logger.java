package Ex9.BTTH.Util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void log(String message) {
        System.out.println("[Time: " + LocalTime.now().format(FORMATTER) + "] " + message);
    }
}