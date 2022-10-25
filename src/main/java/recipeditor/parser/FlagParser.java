package recipeditor.parser;

import recipeditor.recipe.Recipe;

public interface FlagParser {

    static FlagType CheckFlagType(String[] parsedCommand) {
        int flagCount = 0;
        for (String s: parsedCommand) {
            if (s.contains("-")) {
                flagCount += 1;
                switch (s) {
                case "-i":
                    return FlagType.INGREDIENT;
                case "-s":
                    return FlagType.STEP;
                case "-t":
                    return FlagType.TITLE;
                case "-d":
                    return FlagType.DESCRIPTION;
                default:
                    return FlagType.INVALID;
                }
            }
        }
        if (flagCount > 1) {
            return FlagType.INVALID;
        }
        return FlagType.NULL;
    }

}
