package recipeditor.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recipeditor.recipe.RecipeList;

import org.junit.jupiter.api.Assertions;
import recipeditor.storage.Storage;

public class TitleFileParserTest {
    void createFiles(String title) {
        Storage.createFile(Storage.titleToFilePath(title));
    }

    @Test
    void parse2InvalidTitle() {
        String[] strings = {"Recipe-", "Recipe1_"};
        for (String s : strings) {
            createFiles(s);
        }
        TitleFileParser.parseTitleFileToRecipeTitles(strings);
        Assertions.assertEquals(0, RecipeList.getRecipeTitlesSize());
        for (String s : strings) {
            Storage.deleteFile(Storage.titleToFilePath(s));
        }
    }

    @Test
    void parse1Valid_1Invalid() {
        String[] strings = {"Recipe", "Recipe1_@#$"};
        for (String s : strings) {
            createFiles(s);
        }
        TitleFileParser.parseTitleFileToRecipeTitles(strings);
        Assertions.assertEquals(1, RecipeList.getRecipeTitlesSize());
        for (String s : strings) {
            Storage.deleteFile(Storage.titleToFilePath(s));
            RecipeList.deleteRecipeFromTitle(s);
        }
    }

    @Test
    void parse1Valid_1TooLong() {
        String[] strings = {"Recipe", "Recipeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaa"};
        for (String s : strings) {
            createFiles(s);
        }
        TitleFileParser.parseTitleFileToRecipeTitles(strings);
        Assertions.assertEquals(1, RecipeList.getRecipeTitlesSize());
        for (String s : strings) {
            Storage.deleteFile(Storage.titleToFilePath(s));
            RecipeList.deleteRecipeFromTitle(s);
        }
    }

    @Test
    void parse2ValidTitle() {
        String[] strings = {"Recipe", "Recipe1"};
        for (String s : strings) {
            createFiles(s);
        }
        TitleFileParser.parseTitleFileToRecipeTitles(strings);
        Assertions.assertEquals(2, RecipeList.getRecipeTitlesSize());
        for (String s : strings) {
            Storage.deleteFile(Storage.titleToFilePath(s));
            RecipeList.deleteRecipeFromTitle(s);
        }
    }
}
