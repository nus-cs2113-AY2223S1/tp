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

    public static final String TEMPLATE_FILE_PATH = "./RecipeData/App/Template.txt";
    public static final String TEMPORARY_FILE_PATH = "./RecipeData/App/TemporaryFile.txt";
    public static final String RECIPES_FOLDER_PATH = "./RecipeData/Recipes";
    public static final String ALL_RECIPES_FILE_PATH = "./RecipeData/AllRecipes.txt";
    private static final String DATA_STORAGE_FOLDER_PATH = "./RecipeData/";
    private static final String DATA_TEMPORARY_FOLDER_PATH = "./RecipeData/App";
    private static final String TEMPLATE_FILE = "# TITLE \n"
            + "Example Title \n\n"
            + "# DESCRIPTION\n"
            + "Example Description\n\n"
            + "# INGREDIENTS ingredient_name / amount / unit \n"
            + "1. Example ingredient / 1.2 / example unit \n\n"
            + "# STEPS \n"
            + "1. Example step \n";

    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    /**
     * Creates a new folder according to the folder path given in parameter.
     *
     * @param folderPath path of the new folder
     */
    public static void createFolder(String folderPath) {
        try {
            Files.createDirectories(Paths.get(folderPath));
            logger.log(Level.INFO, folderPath + " Directory created");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error creating folder");
        }
    }

    /**
     * Creates a new file according to the file path given in parameter.
     *
     * @param filePath path of the file folder
     */
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
            Files.createDirectories(Paths.get(DATA_STORAGE_FOLDER_PATH));
            Files.createDirectories(Paths.get(DATA_TEMPORARY_FOLDER_PATH));
            logger.log(Level.INFO, DATA_STORAGE_FOLDER_PATH + " Directory created");
            templateFile();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error creating folder");
        }
    }

    /**
     * Deletes the individual recipe file according to the recipe title.
     *
     * @param recipeTitleToDelete recipe title of file to be deleted
     */
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

    /**
     * Loading the recipe titles into RecipeList recipeTitle array from AllRecipeFile.
     */
    public static void loadRecipesToRecipeTitlesList() {
        try {
            String allRecipeFileContent = loadFileContent(ALL_RECIPES_FILE_PATH);
            String[] recipeTitles = allRecipeFileContent.split("\\r?\\n");
            TitleFileParser.parseTitleFileToRecipeTitles(recipeTitles); //Check Title Validity
        } catch (FileNotFoundException e) {
            Ui.showMessage("File not found :< Creating your data file for all recipes now...");
            createFile(ALL_RECIPES_FILE_PATH);
        }
    }

    /**
     * Loading the recipe into RecipeList recipe array from individual recipe file
     */
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

    /**
     * Find file path of the given recipe title.
     *
     * @param title recipe title of file to change to file path
     */
    public static String titleToFilePath(String title) {
        return RECIPES_FOLDER_PATH + "/" + title + "/";
    }

    /**
     * Rewrites AllRecipe file with the titles in the recipe list.
     */
    public static void rewriteRecipeListToFile() {
        try {
            FileWriter fw = new FileWriter(ALL_RECIPES_FILE_PATH, false);
            for (String recipeTitle : RecipeList.iterateRecipeTitles()) {
                fw.write(recipeTitle + "\n");
            }
            fw.close();
        } catch (IOException ioException) {
            Ui.showMessage("Error in loading recipes to data file");
        }
    }

    /**
     * Deletes the old file where the previous recipe is in and updates the AllRecipe file.
     * Then, save the recipe passed in the parameter in the given destination path.
     *
     * @param recipe Recipe to be saved in the new file
     * @param oldFilePath the file path where the previous recipe is in
     * @param recipeFileDestinationPath the new file path where the saved recipe is in
     */
    public static void saveRecipe(Recipe recipe, String oldFilePath, String recipeFileDestinationPath) {
        try {
            deleteFile(oldFilePath);
            rewriteRecipeListToFile();
            FileWriter fw = new FileWriter(recipeFileDestinationPath);
            fw.write(recipe.getRecipeSaveableFormatted() + "\n");
            fw.close();
            logger.log(Level.INFO, recipe.getTitle() + " saved to the file.");
        } catch (IOException ioException) {
            Ui.showMessage("Error in saving recipe to data file");
        }
    }

    /**
     * Checks whether the file exists given the file path in parameter
     *
     * @param filePath file path of the file to check
     * @return Boolean of whether the file exists
     */
    public static boolean checkIfFileExists(String filePath) {
        File f = new File(filePath);
        return f.exists();
    }

    /**
     * Deletes a file given file path in parameter.
     *
     * @param filePath file path of the file to delete
     */
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

    /**
     * Generates the template recipe file for the GUI.
     */
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

    /**
     * Loads the file content in the file given in the parameter.
     *
     * @param filePath file path of load the content
     * @return String that contains all the content in the file
     */
    public static String loadFileContent(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        StringBuilder getContent = new StringBuilder();
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            getContent.append(scan.nextLine() + "\n");
        }
        return getContent.toString();
    }
}
