package recipeditor.parser;

import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TitleFileParser {
    private static final String FILE_NOT_EXIST = "Recipe Title is found without the Recipe. Skip this recipe!";
    private static final String TITLE_INVALID = "Recipe Title is not valid!";
    private static final Logger logger = Logger.getLogger(TitleFileParser.class.getName());

    /**
     * Parse AllRecipes.txt and check if the title are valid and have the associated recipe files.
     * @param lines
     */
    public static void parseTitleFileToRecipeTitles(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isBlank() || ParserUtils.isTitleNotAlphanumeric(line) || ParserUtils.doesTitleExceedLimit(line)
                    || RecipeList.containsRecipeTitle(line)) {
                logger.log(Level.INFO, TITLE_INVALID);
                continue;
            }
            String path = Storage.titleToFilePath(line);
            if (!Storage.checkIfFileExists(path)) {
                logger.log(Level.INFO, FILE_NOT_EXIST);
                continue;
            }
            RecipeList.addRecipeTitle(line);
        }
    }
}
