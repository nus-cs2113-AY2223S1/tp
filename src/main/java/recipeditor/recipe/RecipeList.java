package recipeditor.recipe;

import recipeditor.exception.RecipeNotFoundException;
import recipeditor.storage.Storage;


import java.util.ArrayList;

public class RecipeList {
    private static final String NUMBER_OF_RECIPE_PRINT = "There are %d recipes in the recipe list";
    private static ArrayList<String> recipeTitles = new ArrayList<>();
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    private RecipeList(ArrayList<Recipe> load) {
        recipes = new ArrayList<>();
        recipeTitles = new ArrayList<>();
        recipes.addAll(load);
    }

    public static Recipe getRecipe(int index) throws IndexOutOfBoundsException {
        assert index > -1;
        return recipes.get(index);
    }

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static void addRecipeTitle(String title) {
        recipeTitles.add(title);
    }

    public static Iterable<String> iterateRecipeTitles() {
        return recipeTitles;
    }

    public static int getRecipeTitlesSize() {
        return recipeTitles.size();
    }

    public static String getRecipeTitle(int index) throws IndexOutOfBoundsException {
        return recipeTitles.get(index);
    }

    public static void deleteRecipeFromTitle(String recipeTitle) {
        recipes.remove(getRecipeFromTitle(recipeTitle));
        recipeTitles.removeIf(r -> r.equals(recipeTitle));
    }

    public static void editRecipe(int index, Recipe newRecipe, String oldTitle) {
        assert index > -1;
        recipes.set(index, newRecipe);
        recipeTitles.set(index, newRecipe.getTitle());
        String oldFile = Storage.titleToFilePath(oldTitle);
        String recipeFileSourcePath = Storage.titleToFilePath(newRecipe.getTitle());
        Storage.saveRecipe(newRecipe, oldFile, recipeFileSourcePath);
    }

    public static Recipe getRecipeFromTitle(String recipeTitle) {
        for (Recipe r : recipes) {
            if (r.getTitle().equalsIgnoreCase(recipeTitle)) {
                return r;
            }
        }
        return null;
    }

    public static String getTitleFromIndex(int index) throws IndexOutOfBoundsException {
        assert index > -1;
        return getRecipe(index).getTitle();
    }

    public static int getRecipeIndexFromTitle(String recipeTitle) throws RecipeNotFoundException {
        int i = 0;
        for (Recipe r : recipes) {
            if (r.getTitle().equalsIgnoreCase(recipeTitle)) {
                return i;
            }
            i++;
        }
        throw new RecipeNotFoundException(recipeTitle);
    }

    public static ArrayList<String> findRecipeTitles(String findInput) {
        ArrayList<String> output = findRecipeTitlesFromRecipeTitle(findInput);
        ArrayList<String> foundRecipeTitlesFromIngredientName = findRecipeTitlesFromIngredientName(findInput);
        output.addAll(foundRecipeTitlesFromIngredientName);
        return output;
    }

    public static ArrayList<String> findRecipeTitlesFromRecipeTitle(String findInput) {
        ArrayList<String> foundRecipeTitlesList = new ArrayList<>();
        for (String r : recipeTitles) {
            if (r.toLowerCase().contains(findInput.toLowerCase())) {
                foundRecipeTitlesList.add(r);
            }
        }
        return foundRecipeTitlesList;
    }

    public static ArrayList<String> findRecipeTitlesFromIngredientName(String findInput) {
        ArrayList<String> foundRecipeTitlesList = new ArrayList<>();
        for (Recipe r : recipes) {
            boolean isRecipeIncluded = false;
            ArrayList<Ingredient> recipeIngredients = r.getIngredients();
            for (Ingredient i : recipeIngredients) {
                if (isRecipeIncluded == false
                        && i.getName().toLowerCase().contains(findInput.toLowerCase())) {
                    foundRecipeTitlesList.add(r.getTitle());
                    isRecipeIncluded = true;
                }
            }
        }
        return foundRecipeTitlesList;
    }

    public static int getRecipeSize() {
        return recipes.size();
    }

    public static boolean containsRecipe(Recipe incomingRecipe) {
        for (Recipe r : recipes) {
            if (r.getTitle().equals(incomingRecipe.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsRecipeTitle(String title) {
        for (String r : recipeTitles) {
            if (r.equals(title)) {
                return true;
            }
        }
        return false;
    }

    public static String printNumberOfRecipes() {
        return String.format(NUMBER_OF_RECIPE_PRINT, RecipeList.getRecipeTitlesSize());
    }
}
