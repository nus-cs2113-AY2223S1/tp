package seedu.duke.utils;

/**
 * Color utilities - Outputting the ANSI color codes will change the color of text in the terminal.
 * Resetting to the original color can be done by outputting the ANSI_RESET code.
 * Reference: https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html
 * Some of the colors may not be used at the current stage of development but still preserved for possible future use.
 */
public class Color {
    public static final String NOTHING = "";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
