package recipeditor.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class Storage {

    private static final String DATA_STORAGE_PATH = "./Storage/";
    private static final String DATA_TEMPORARY_PATH = "./Storage/App";
    public static final String TEMPLATE_PATH = "./Storage/App/Template.txt";
    public static final String TEMPORARY_PATH = "./Storage/App/Temp.txt";
    private final static String TEMPLATE_FILE = "# TITLE \n"
    +"Example Title \n\n"
    +"# DESCRIPTION\n"
    +"Example Description\n\n"
    +"# INGREDIENTS <ingredient name> / <amount> / <unit> \n"
    +"1. Example ingredient / 1.2 / example unit \n\n"
    +"# STEPS \n"
    +"1. Example step \n";


    private static final String RECIPE_NAME_FIELD_TYPE = "Recipe Name";
    private static final String RECIPE_DESCRIPTION_FIELD_TYPE = "Recipe Description";
    private static final String RECIPE_INGREDIENTS_FIELD_TYPE = "Recipe Ingredients";
    private static final String RECIPE_STEPS_FIELD_TYPE = "Recipe Steps";

    private static Logger logger = Logger.getLogger("LOGS");

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
     * Create storage folder for recipes and Template files
     */
    public static void createDataFolder() {
        try {
            Files.createDirectories(Paths.get(DATA_STORAGE_PATH));
            Files.createDirectories(Paths.get(DATA_TEMPORARY_PATH));
            Ui.showMessage("Directory created");
            templateFile();
        } catch (IOException e) {
            Ui.showMessage("Error creating folder");
        }
    }

    public static void saveRecipe() {

    }

    public static void loadRecipeToFile(String filePath, Recipe recipe) {
        try {
            logger.log(Level.INFO, "Loading Recipe to Data File");
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(recipe.getRecipeAttributesFormatted());
            fw.write(Ui.DIVIDER + "\n");
            fw.close();
        } catch (IOException ioException) {
            Ui.showMessage("Error in loading recipes to data file");
        }
    }

    public static void loadRecipesFromFile(String filePath) {
        try {
            logger.log(Level.INFO, "Loading Recipe from Data File");
            File dataFile = new File(filePath);
            Scanner s = new Scanner(dataFile);
            Recipe newRecipe = new Recipe();
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] fieldTypeAndData = input.split(": ");
                switch (fieldTypeAndData[0]) {
                    case RECIPE_NAME_FIELD_TYPE:
                        newRecipe.setTitle(fieldTypeAndData[1]);
                        break;
                    case RECIPE_DESCRIPTION_FIELD_TYPE:
                        newRecipe.setDescription(fieldTypeAndData[1]);
                        break;
                    case RECIPE_INGREDIENTS_FIELD_TYPE:
                    case RECIPE_STEPS_FIELD_TYPE:
                        ArrayList<Ingredient> ingredients = getIngredientsDetails(s);
                        newRecipe.addIngredients(ingredients);

                        ArrayList<String> steps = getStepsDetails(s);
                        newRecipe.addSteps(steps);
                        RecipeList.addRecipe(newRecipe);
                        newRecipe = new Recipe();
                        break;
                    default:
                        Ui.showMessage("Error loading recipes from data file");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            Ui.showMessage("File not found :< Creating your data file now...");
            createFile(filePath);
        }
    }

    public static ArrayList<Ingredient> getIngredientsDetails(Scanner s) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient newIngredient = new Ingredient();
        while (true) {
            String ingredientInput = s.nextLine();
            String[] ingredientDetails = ingredientInput.split(" \\| ");
            if (ingredientDetails.length != 3) {
                break;
            }
            newIngredient.setName(ingredientDetails[0]);
            newIngredient.setAmount(Double.parseDouble(ingredientDetails[1]));
            newIngredient.setUnit(ingredientDetails[2]);
            ingredients.add(newIngredient);
            newIngredient = new Ingredient();
        }
        return ingredients;
    }

    public static ArrayList<String> getStepsDetails(Scanner s) {
        ArrayList<String> steps = new ArrayList<>();
        String input;
        while (true) {
            input = s.nextLine();
            if (input.equals(Ui.DIVIDER)) {
                break;
            }
            String[] stepIndexAndDetails = input.split("\\)");
            steps.add(stepIndexAndDetails[1]);
        }
        return steps;
    }

    public static void writeRecipeListToFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            StringBuilder formattedRecipeList = new StringBuilder();
            for (int i = 0; i < RecipeList.getSize(); i++) {
                formattedRecipeList.append(RecipeList.getRecipe(i).getRecipeAttributesFormatted());
                formattedRecipeList.append(Ui.DIVIDER + "\n");
            }
            fw.write(formattedRecipeList.toString());
            fw.close();
        } catch (IOException ioException) {
            Ui.showMessage("Error in loading recipes to data file");
        }
    }

    public static void writeRecipeToFile(String filePath, Recipe addedRecipe) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            StringBuilder formattedRecipeString = new StringBuilder();
            formattedRecipeString.append(addedRecipe.getRecipeAttributesFormatted());
            formattedRecipeString.append(Ui.DIVIDER + "\n");
            fw.write(formattedRecipeString.toString());
            fw.close();
        } catch (IOException ioException) {
            Ui.showMessage("Error in loading recipes to data file");
        }
    }

    private static void templateFile() {
        File file = new File(TEMPLATE_PATH);
        if (file.exists()){
            Ui.showMessage("Template file exists");
        } else {
            generateTemplateFile();
        }
    }

    private static void generateTemplateFile(){
        FileWriter fileWrite = null;
        try {
            fileWrite = new FileWriter(TEMPLATE_PATH);
            fileWrite.write(TEMPLATE_FILE);
            fileWrite.close();
        } catch (IOException e) {
            Ui.showMessage("Template file not generated");
        } finally{
            Ui.showMessage("Template file created");
        }
    }

    public static String loadFileContent(String path) throws FileNotFoundException{
        File file = new File(path);
        StringBuilder getContent = new StringBuilder();

        Scanner scan = new Scanner(file);
        while(scan.hasNext()){
            getContent.append(scan.nextLine() + "\n");
        }
        return getContent.toString();
    }
}
