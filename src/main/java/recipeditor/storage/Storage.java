package recipeditor.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.ui.Ui;

public class Storage {
    private static final String DATA_FILE_PATH = "data/data.txt";
    private static final String RECIPE_NAME_FIELD_TYPE = "Recipe Name";
    private static final String RECIPE_DESCRIPTION_FIELD_TYPE = "Recipe Description";
    private static final String RECIPE_INGREDIENTS_FIELD_TYPE = "Recipe Ingredients";
    private static final int INGREDIENT_NAME = 1;
    private static final int INGREDIENT_AMOUNT = 2;
    private static final int INGREDIENT_UNIT = 3;

    public static void createDataFile() {
        File file = new File(DATA_FILE_PATH);
        if (!file.getParentFile().mkdirs()) {
            Ui.printCreateParentFolderErrorText();
        }
        Ui.printFilePath(file, DATA_FILE_PATH);
    }

    public static void loadRecipeToDataFile(Recipe recipe) {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
            fw.write(recipe.getRecipeAttributesFormatted());
            fw.write(Ui.DIVIDER);
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
                switch (fieldTypeAndData[ 0 ]) {
                case RECIPE_NAME_FIELD_TYPE:
                    newRecipe.setTitle(fieldTypeAndData[ 1 ]);
                    break;
                case RECIPE_DESCRIPTION_FIELD_TYPE:
                    newRecipe.setDescription(fieldTypeAndData[ 1 ]);
                    break;
                case RECIPE_INGREDIENTS_FIELD_TYPE:
                    ArrayList<Ingredient> ingredients = setIngredientsDetails(s);
                    newRecipe.setIngredients(ingredients);
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

    public static ArrayList<Ingredient> setIngredientsDetails(Scanner s) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient newIngredient = new Ingredient();
        int parameter = 1;
        String ingredientInput = s.nextLine();
        do {
            String[] ingredientDetails = ingredientInput.split(" - ");
            if (ingredientDetails.length == 2) {
                switch (parameter) {
                case INGREDIENT_NAME:
                    newIngredient.setName(ingredientDetails[ 1 ]);
                    parameter++;
                    break;
                case INGREDIENT_AMOUNT:
                    newIngredient.setAmount(Double.parseDouble(ingredientDetails[ 1 ]));
                    parameter++;
                    break;
                case INGREDIENT_UNIT:
                    newIngredient.setUnit(ingredientDetails[ 1 ]);
                    ingredients.add(newIngredient);
                    newIngredient = new Ingredient();
                    parameter = 1;
                    break;
                default:
                    Ui.showMessage("Error setting ingredients from data file");
                    break;
                }
            }
            ingredientInput = s.nextLine();
        } while (!ingredientInput.equals(Ui.DIVIDER));
        return ingredients;
    }
}
