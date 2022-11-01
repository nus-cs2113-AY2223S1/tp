package recipeditor.parser;

import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

public class TitleFileParser {

    public static void parseTitleFileToRecipeTitles(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isBlank() || ParserUtils.isTitleNotAlphanumeric(line) || ParserUtils.doesTitleExceedLimit(line)
                    || RecipeList.containsRecipeTitle(line)) {
                continue;
            }
            String path = Storage.titleToFilePath(line);
            if (!Storage.checkIfFileExists(path)) {
                Ui.showMessage("RecipeTitle is found without the Recipe. Skip this recipe!");
                continue;
            }
            RecipeList.recipeTitles.add(line);
        }
    }
}
