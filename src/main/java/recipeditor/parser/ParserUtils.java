package recipeditor.parser;

import org.apache.commons.lang3.StringUtils;

public class ParserUtils {
    public static boolean isTitleNotAlphanumeric(String title) {
        String[] parsed = title.split(" ");
        for (String word : parsed) {
            if (!StringUtils.isAlphanumeric(word.trim()) && !word.isBlank()) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesTitleExceedLimit(String title) {
        return title.length() > 255;
    }
}
