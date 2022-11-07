package recipeditor.recipe;

import recipeditor.exception.RecipeNotFoundException;
import recipeditor.storage.Storage;

import java.util.ArrayList;

public class RecipeList {
    private static final String NUMBER_OF_RECIPE_PRINT = "There are %d recipes in the recipe list";
    private static ArrayList<String> recipeTitles = new ArrayList<>();
    private static ArrayList<Recipe> recipes = new ArrayList<>();

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

    /**
     * Delete recipe given the recipe title.
     */
    public static void deleteRecipeFromTitle(String recipeTitle) {
        recipes.remove(getRecipeFromTitle(recipeTitle));
        recipeTitles.removeIf(r -> r.equals(recipeTitle));
    }

    /**
     * Edit Recipe according to the given index.
     *
     * @param index recipe index to be edited
     * @param newRecipe new recipe in place of the old recipe
     * @param oldTitle previous recipe title that was edited away
     */
    public static void editRecipe(int index, Recipe newRecipe, String oldTitle) {
        assert index > -1;
        recipes.set(index, newRecipe);
        recipeTitles.set(index, newRecipe.getTitle());
        String oldFile = Storage.titleToFilePath(oldTitle);
        String recipeFileSourcePath = Storage.titleToFilePath(newRecipe.getTitle());
        Storage.saveRecipe(newRecipe, oldFile, recipeFileSourcePath);
    }

    /**
     * Retrieve the recipe from its recipe title as input.
     *
     * @param recipeTitle recipe title to be retrieved
     * @return recipe
     */
    public static Recipe getRecipeFromTitle(String recipeTitle) {
        for (Recipe r : recipes) {
            if (r.getTitle().equalsIgnoreCase(recipeTitle)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Get the recipe title from its recipe index in the recipe list.
     *
     * @param index recipe index of the recipe title to be retrieved
     * @return recipe title
     */
    public static String getTitleFromIndex(int index) throws IndexOutOfBoundsException {
        assert index > -1;
        return getRecipe(index).getTitle();
    }

    /**
     * Get the recipe index from its recipe title in the recipe list.
     *
     * @param recipeTitle recipe title of the recipe index to be retrieved
     * @return recipe index
     */
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

    /**
     * Find a list of recipe titles that contains the input (recipe title).
     *
     * @param findInput input to search in the recipe titles
     * @return a list of recipe titles that contains the input (recipe title)
     */
    public static ArrayList<String> findRecipeTitlesFromRecipeTitle(String findInput) {
        ArrayList<String> foundRecipeTitlesList = new ArrayList<>();
        for (String r : recipeTitles) {
            if (r.toLowerCase().contains(findInput.toLowerCase())) {
                foundRecipeTitlesList.add(r);
            }
        }
        return foundRecipeTitlesList;
    }

    /**
     * Find a list of recipe titles that contains the input (ingredient name).
     *
     * @param findInput input to search in the ingredient name
     * @return a list of recipe titles that contains the input (ingredient name)
     */
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

    /**
     * Checks if the recipe list contains the recipe input.
     *
     * @param incomingRecipe recipe to be checked
     * @return if the recipe list contains the recipe
     */
    public static boolean containsRecipe(Recipe incomingRecipe) {
        for (Recipe r : recipes) {
            if (r.getTitle().equals(incomingRecipe.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the recipe list contains the recipe title.
     *
     * @param title recipe title to be checked
     * @return if the recipe list contains the recipe title
     */
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
