package recipeditor.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import recipeditor.exception.ParseFileException;
import recipeditor.parser.RecipeFileParser;
import recipeditor.parser.TitleFileParser;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class Storage {
    public static final String LOG_FILE = "./RecipeData/App/Log.txt";
    public static final String TEMPLATE_FILE_PATH = "./RecipeData/App/Template.txt";
    public static final String TEMPORARY_FILE_PATH = "./RecipeData/App/TemporaryFile.txt";
    public static final String RECIPES_FOLDER_PATH = "./RecipeData/Recipes";
    public static final String ALL_RECIPES_FILE_PATH = "./RecipeData/AllRecipes.txt";
    private static final String APP_DATA_FOLDER_PATH = "./RecipeData/App";
    private static final String TEMPLATE_FILE = "# TITLE (1 line)\n"
            + "Example Title\n\n"
            + "# DESCRIPTION\n"
            + "Example Description\n\n"
            + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
            + "1. Example "
            + "ingredient / 1.2 / example unit \n\n"
            + "# STEPS index. description\n"
            + "1. Example step \n";

    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    public static void createFolder(String folderPath) {
        try {
            Files.createDirectories(Paths.get(folderPath));
            logger.log(Level.INFO, folderPath + " Directory created");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error creating folder");
        }
    }

    public static void createFile(String filePath) {

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.createNewFile()) {
                logger.log(Level.INFO, "New data file is created at " + filePath);
            } else {
                logger.log(Level.INFO, "Data file already exists at " + filePath);
            }
        } catch (IOException ioException) {
            Ui.showMessage(ioException.getMessage());
        }
    }

    /**
     * Create storage folder for recipes and Template files.
     */
    public static void createAppFolder() {
        try {
            Files.createDirectories(Paths.get(RECIPES_FOLDER_PATH));
            Files.createDirectories(Paths.get(APP_DATA_FOLDER_PATH));
            logger.log(Level.INFO, RECIPES_FOLDER_PATH + " Directory created");
            templateFile();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error creating folder");
        }
    }

    public static void deleteRecipeFile(String recipeTitleToDelete) {
        String recipeFilePath = RECIPES_FOLDER_PATH + "/" + recipeTitleToDelete.stripTrailing();
        try {
            File file = new File(recipeFilePath);
            if (file.delete()) {
                logger.log(Level.INFO, recipeTitleToDelete + " is deleted from the recipe list.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Loading the recipe titles into RecipeList recipeTitle array from AllRecipeFile
    public static void loadRecipesToRecipeTitlesList() {
        try {
            String allRecipeFileContent = loadFileContent(ALL_RECIPES_FILE_PATH);
            String[] recipeTitles = allRecipeFileContent.split("\\r?\\n");
            TitleFileParser.parseTitleFileToRecipeTitles(recipeTitles);
        } catch (FileNotFoundException e) {
            Ui.showMessage("File not found :< Creating your data file for all recipes now...");
            createFile(ALL_RECIPES_FILE_PATH);
        }
    }

    // Loading the recipe into RecipeList recipe array from individual recipe file
    public static void loadRecipesToRecipeList() {
        try {
            for (String recipeTitle : RecipeList.iterateRecipeTitles()) {
                logger.log(Level.INFO, recipeTitle);
                String recipeFilePath = titleToFilePath(recipeTitle);
                String content = Storage.loadFileContent(recipeFilePath);
                Recipe addedRecipe = new RecipeFileParser().parseTextToRecipe(content);
                RecipeList.addRecipe(addedRecipe);
                logger.log(Level.INFO, recipeTitle + " is added to recipeList");
            }
        } catch (FileNotFoundException e) {
            Ui.showMessage("RecipesToRecipeList Fail");
        } catch (ParseFileException e) {
            Ui.showMessage("Error in parsing recipe file content.");
        }
    }

    public static String titleToFilePath(String title) {
        return RECIPES_FOLDER_PATH + "/" + title + "/";
    }

    public static void rewriteRecipeListToFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            for (String recipeTitle : RecipeList.iterateRecipeTitles()) {
                fw.write(recipeTitle + "\n");
            }
            fw.close();
        } catch (IOException ioException) {
            Ui.showMessage("Error in loading recipes to data file");
        }
    }


    public static void saveRecipe(Recipe recipe, String oldFile, String recipeFileDestinationPath) {
        try {
            deleteFile(oldFile);
            rewriteRecipeListToFile(Storage.ALL_RECIPES_FILE_PATH);
            FileWriter fw = new FileWriter(recipeFileDestinationPath);
            fw.write(recipe.getRecipeSaveableFormatted() + "\n");
            fw.close();
            logger.log(Level.INFO, recipe.getTitle() + " saved to the file.");
        } catch (IOException ioException) {
            Ui.showMessage("Error in saving recipe to data file");
        }
    }

    public static boolean checkIfFileExists(String filePath) {
        File f = new File(filePath);
        return f.exists();
    }

    public static void deleteFile(String filePath) {
        if (checkIfFileExists(filePath)) {
            File f = new File(filePath);
            f.delete();
        }
    }

    private static void templateFile() {
        File file = new File(TEMPLATE_FILE_PATH);
        if (file.exists()) {
            logger.log(Level.INFO, "Template File exists");
        } else {
            generateTemplateFile();
        }
    }

    public static void generateTemplateFile() {
        FileWriter fileWrite;
        try {
            fileWrite = new FileWriter(TEMPLATE_FILE_PATH);
            fileWrite.write(TEMPLATE_FILE);
            fileWrite.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error creating template file");
        } finally {
            logger.log(Level.INFO, "Template file created");
        }
    }

    public static String loadFileContent(String path) throws FileNotFoundException {
        File file = new File(path);
        StringBuilder getContent = new StringBuilder();
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            getContent.append(scan.nextLine() + "\n");
        }
        return getContent.toString();
    }

    public static void deleteAllRecipe() {
        File directory = new File(Storage.RECIPES_FOLDER_PATH);
        for (File file : directory.listFiles()) {
            file.delete();
        }
    }

    public static void saveAllRecipe() {
        for (Recipe r : RecipeList.getRecipes()) {
            String recipeFileSourcePath = Storage.titleToFilePath(r.getTitle());
            Storage.saveRecipe(r, "", recipeFileSourcePath);
        }
    }
}
