package recipeditor.parser;

import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TitleFileParser {
    private static final String FILE_NOT_EXIST = "Recipe Title is found without the Recipe. Skip this recipe!";
    private static final String TITLE_INVALID = "Recipe Title is not valid!";
    private static final String ADDED = "%s added";
    private static final Logger logger = Logger.getLogger(TitleFileParser.class.getName());
    private static final int START_LINE_INDEX = 0;

    /**
     * Parse AllRecipes.txt and check if the title are valid and have the associated recipe files.
     *
     * @param lines content of the AllRecipes.txt
     */
    public static void parseTitleFileToRecipeTitles(String[] lines) {
        for (int i = START_LINE_INDEX; i < lines.length; i++) {
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
            try {
                String recipeFilePath = Storage.titleToFilePath(line);
                String content = Storage.loadFileContent(recipeFilePath);
                Recipe addedRecipe = new RecipeFileParser().parseTextToRecipe(content);
                addedRecipe.setTitle(line);
                RecipeList.addRecipe(addedRecipe);
                RecipeList.addRecipeTitle(line);
                logger.log(Level.INFO, String.format(ADDED, line));
            } catch (Exception e) {
                logger.log(Level.INFO, "Error in parsing recipe file content.");
            }
        }
    }
}
