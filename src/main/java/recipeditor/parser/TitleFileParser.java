package recipeditor.parser;

import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class TitleFileParser {

    public static void parseTitleFileToRecipeTitles(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isBlank() || ParserUtils.isTitleNotAlphanumeric(line) || ParserUtils.doesTitleExceedLimit(line)
                    || RecipeList.containsRecipeTitle(line)) {
                continue;
            }
            RecipeList.recipeTitles.add(line);
        }
    }
}
