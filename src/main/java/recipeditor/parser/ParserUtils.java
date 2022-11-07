package recipeditor.parser;

import org.apache.commons.lang3.StringUtils;
import recipeditor.ui.Ui;

/**
 * Utility class with useful method for parsing.
 */
public class ParserUtils {
    /**
     * Check for alphanumeric.
     */
    public static boolean isTitleNotAlphanumeric(String title) {
        String[] parsed = title.split(Ui.SPACE_DIVIDER);
        for (String word : parsed) {
            if (!StringUtils.isAlphanumeric(word.trim()) && !word.isBlank()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if exceed char limit.
     */
    public static boolean doesTitleExceedLimit(String title) {
        return title.length() > 255;
    }
}

