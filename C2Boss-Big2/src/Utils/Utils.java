package Utils;

import java.io.PrintStream;

public class Utils {
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void print(String content) {
        System.out.print(content);
    }

    public static void println(String content) {
        System.out.println(content);
    }
}
