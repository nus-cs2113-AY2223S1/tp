package recipeditor.parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recipeditor.recipe.RecipeList;

import org.junit.jupiter.api.Assertions;
import recipeditor.storage.Storage;

import java.io.File;
import java.io.FileWriter;


public class TitleFileParserTest {
    void createFiles(String title) {
        Storage.createFile(Storage.titleToFilePath(title));
    }

    void createFileWithContentAndTitle(String content, String title) {
        String path = Storage.titleToFilePath(title);
        new File(path);
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            Storage.deleteFile(Storage.titleToFilePath(s));
        }
    }

    @Test
    void parse1Valid_1Invalid() {
        String[] strings = {"Recipe", "Recipe1_@#$"};
        for (String s : strings) {
            createFiles(s);
        }
        String content = "# TITLE \n"
                + "Recipe\n\n"
                + "# DESCRIPTION\n"
                + "Example Description\n\n"
                + "# INGREDIENTS  INDEX. INGREDIENT_NAME / AMOUNT / UNIT \n"
                + "1. Example "
                + "ingredient / 1.2 / example unit \n\n"
                + "# STEPS INDEX. DESCRIPTION\n"
                + "1. Example step \n";
        createFileWithContentAndTitle(content,"Recipe");
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
        String content = "# TITLE \n"
                + "Recipe\n\n"
                + "# DESCRIPTION\n"
                + "Example Description\n\n"
                + "# INGREDIENTS  INDEX. INGREDIENT_NAME / AMOUNT / UNIT \n"
                + "1. Example "
                + "ingredient / 1.2 / example unit \n\n"
                + "# STEPS INDEX. DESCRIPTION\n"
                + "1. Example step \n";
        createFileWithContentAndTitle(content,"Recipe");
        TitleFileParser.parseTitleFileToRecipeTitles(strings);
        Assertions.assertEquals(1, RecipeList.getRecipeTitlesSize());
        for (String s : strings) {
            Storage.deleteFile(Storage.titleToFilePath(s));
            RecipeList.deleteRecipeFromTitle(s);
        }
    }

    @Test
    void parse2NoFileTitle() {
        String[] strings = {"Recipe12", "Recipe23"};
        for (String s : strings) {
            Storage.deleteFile(Storage.titleToFilePath(s));
            RecipeList.deleteRecipeFromTitle(s);
        }
        TitleFileParser.parseTitleFileToRecipeTitles(strings);
        Assertions.assertEquals(0, RecipeList.getRecipeTitlesSize());
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
        String content = "# TITLE \n"
                + "Recipe\n\n"
                + "# DESCRIPTION\n"
                + "Example Description\n\n"
                + "# INGREDIENTS  INDEX. INGREDIENT_NAME / AMOUNT / UNIT \n"
                + "1. Example "
                + "ingredient / 1.2 / example unit \n\n"
                + "# STEPS INDEX. DESCRIPTION\n"
                + "1. Example step \n";
        createFileWithContentAndTitle(content,"Recipe");
        String content1 = "# TITLE \n"
                + "Recipe1\n\n"
                + "# DESCRIPTION\n"
                + "Example Description\n\n"
                + "# INGREDIENTS  INDEX. INGREDIENT_NAME / AMOUNT / UNIT \n"
                + "1. Example "
                + "ingredient / 1.2 / example unit \n\n"
                + "# STEPS INDEX. DESCRIPTION\n"
                + "1. Example step \n";
        createFileWithContentAndTitle(content1,"Recipe1");
        TitleFileParser.parseTitleFileToRecipeTitles(strings);
        Assertions.assertEquals(2, RecipeList.getRecipeTitlesSize());
        for (String s : strings) {
            Storage.deleteFile(Storage.titleToFilePath(s));
            RecipeList.deleteRecipeFromTitle(s);
        }
    }




}
