package seedu.duke.utils;

import java.util.List;

public class ColorScheme {

    private static final List<String> scheme = List.of(
            Color.ANSI_GREEN, Color.ANSI_YELLOW, Color.ANSI_BLUE, Color.ANSI_PURPLE, Color.ANSI_CYAN);

    public static String get(int index) {
        return scheme.get(index % scheme.size());
    }

}
