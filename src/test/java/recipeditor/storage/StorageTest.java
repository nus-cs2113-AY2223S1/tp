package recipeditor.storage;

import org.junit.jupiter.api.Test;

import recipeditor.recipe.RecipeList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    void generateCorrectTemplateFile_validTemplateFilePath_templateContent() throws IOException {
        String templateFileContent = "# TITLE \n"
                + "Example Title\n"
                + "\n"
                + "# DESCRIPTION\n"
                + "Example Description\n"
                + "\n"
                + "# INGREDIENTS  INDEX. INGREDIENT_NAME / AMOUNT / UNIT \n"
                + "1. Example ingredient / 1.2 / example unit \n"
                + "\n"
                + "# STEPS INDEX. DESCRIPTION\n"
                + "1. Example step \n";
        Storage.generateTemplateFile();
        String actualContent = Files.readString(Path.of(Storage.TEMPLATE_FILE_PATH));
        assertEquals(actualContent,templateFileContent);
    }

    @Test
    void createFolder_validFolderPathString_folderCreation() {
        String folderPathString = "./testFolder";
        Storage.createFolder(folderPathString);
        File f = new File(folderPathString);
        assertTrue(f.exists() && f.isDirectory());
    }

    @Test
    void createFile_validFilePathString_fileCreation() {
        String filePathString = "./file";
        Storage.createFile(filePathString);
        Path filePath = Paths.get(filePathString);
        assertTrue(Files.exists(filePath));
        File f = new File(filePathString);
        assertTrue(f.exists() && !f.isDirectory());
    }

    @Test
    void deleteRecipeFile_validrecipeTitleToDelete_recipeFileDeleted() {
        String recipeTitleToDelete = "Example Title";
        Storage.deleteRecipeFile(recipeTitleToDelete);
        Path recipeFilePath = Paths.get(recipeTitleToDelete);
        assertFalse(Files.exists(recipeFilePath));
    }

    @Test
    void testLoadRecipesToRecipeTitlesList() {
        Storage.loadRecipesToRecipeTitlesList();
        assertNotNull(RecipeList.getRecipeTitlesSize());
    }

    @Test
    void testLoadRecipesToRecipeList() {
        Storage.loadRecipesToRecipeTitlesList();
        assertNotNull(RecipeList.getRecipeSize());
    }

    @Test
    void titleToFilePathConversion_validTitle_filePathString() {
        String title = "Example Title";
        String output = Storage.titleToFilePath(title);
        String expectedFilePath = "./RecipeData/Recipes/Example Title/";
        assertEquals(expectedFilePath, output);
    }

    @Test
    void checkIfFileExists_invalidFilePath_returnFalse() {
        String filePath = "./newfile";
        File f = new File(filePath);
        assertFalse(f.exists());
    }

    @Test
    void checkIfFileExists_validFilePath_returnTrue() throws IOException {
        String filePathString = "./file";
        File f = new File(filePathString);
        f.createNewFile();
        assertTrue(f.exists());
    }

    @Test
    void deleteFile_validFilePath_fileDeleted() {
        String filePath = "./file";
        File f = new File(filePath);
        Storage.deleteFile(filePath);
        assertFalse(f.exists());
    }

}
