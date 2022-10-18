package recipeditor.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.namespace.QName;

import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class Storage {
    private static final String DATA_FILE_PATH = "./data/data.txt";

    private static final String DATA_STORAGE = "./data/";
    private static final String RECIPE_NAME_FIELD_TYPE = "Recipe Name";
    private static final String RECIPE_DESCRIPTION_FIELD_TYPE = "Recipe Description";
    private static final String RECIPE_INGREDIENTS_FIELD_TYPE = "Recipe Ingredients";
    private static final String RECIPE_STEPS_FIELD_TYPE = "Recipe Steps";

    public static void createDataFile() {
        try {
            File file = new File(DATA_FILE_PATH);
            if (file.createNewFile()) {
                Ui.printFilePath(file, DATA_FILE_PATH);
            } else {
                Ui.printFilePath(file, DATA_FILE_PATH);
            }
        } catch (IOException ioException) {
            Ui.showMessage("Error creating data file");
        }
    }

    /** Create storage folder for recipes */
    // public static void createDataFolder() {
    //     Path path = Paths.get()
    //     try{
            
    //     } catch (IOException e){
    //         Ui.showMessage("Error creating folder");
    //     }
    // }



    public static void loadRecipeToDataFile(Recipe recipe) {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
            fw.write(recipe.getRecipeAttributesFormatted());
            fw.write(Ui.DIVIDER + "\n");
            fw.close();
        } catch (IOException ioException) {
            Ui.showMessage("Error in loading recipes to data file");
        }
    }

    public static void loadRecipesFromDataFile() {
        try {
            File dataFile = new File(DATA_FILE_PATH);
            Scanner s = new Scanner(dataFile);
            Recipe newRecipe = new Recipe();
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] fieldTypeAndData = input.split(":");
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
                    newRecipe.setIngredients(ingredients);

                    ArrayList<String> steps = getStepsDetails(s);
                    newRecipe.setSteps(steps);
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
            createDataFile();
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

    public static void writeRecipeListToFile() {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
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


}
