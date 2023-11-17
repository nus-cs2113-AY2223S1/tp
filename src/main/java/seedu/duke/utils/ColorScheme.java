package seedu.duke.utils;

import java.util.List;

/**
 * A simple color scheme to assign colors to a set of items. 
 * The colors assigned will cycle when there are many items.
 */
public class ColorScheme {

    private static final List<String> scheme = List.of(
            Color.ANSI_GREEN, Color.ANSI_YELLOW, Color.ANSI_BLUE, Color.ANSI_PURPLE, Color.ANSI_CYAN);

    /**
     * Assigns a color to an item identified by an index.
     * @param index The index of the item.
     * @return A color, represented by its ANSI color code.
     */
    public static String get(int index) {
        return scheme.get(index % scheme.size());
    }

}
