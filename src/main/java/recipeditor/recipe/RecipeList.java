package recipeditor.recipe;

import recipeditor.storage.Storage;


import java.util.ArrayList;

public class RecipeList {
    public static ArrayList<String> recipeTitles = new ArrayList<>();
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    private RecipeList(ArrayList<Recipe> load) {
        recipes = new ArrayList<>();
        recipes.addAll(load);
    }

    private RecipeList() {
        this(null);
    }

    public static Recipe getRecipe(int index) throws IndexOutOfBoundsException {
        return recipes.get(index);
    }

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static void deleteRecipeFromIndex(int index) throws IndexOutOfBoundsException {
        recipeTitles.remove(index);
        recipes.remove(index);
    }

    public static void deleteRecipeFromTitle(String recipeTitle) {
        recipes.remove(getRecipeFromTitle(recipeTitle));
        recipeTitles.removeIf(r -> r.equals(recipeTitle));
    }

    public static void editRecipe(int index, Recipe newRecipe, String oldTitle) {
        recipes.set(index, newRecipe);
        recipeTitles.set(index, newRecipe.getTitle());
        String oldFile = Storage.RECIPES_FOLDER_PATH + "/" + oldTitle;
        String recipeFileSourcePath = Storage.RECIPES_FOLDER_PATH + "/" + newRecipe.getTitle();
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

    public static String getTitleFromIndex(int index) {
        return getRecipe(index).getTitle();
    }

    public static int getRecipeIndexFromTitle(String recipeTitle) {
        int i = 0;
        for (Recipe r : recipes) {
            if (r.getTitle().equalsIgnoreCase(recipeTitle)) {
                return i;
            }
            i++;
        }
        return -1;
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
            ArrayList<Ingredient> recipeIngredients = r.getIngredients();
            for (Ingredient i : recipeIngredients) {
                if (i.getName().toLowerCase().contains(findInput.toLowerCase())) {
                    foundRecipeTitlesList.add(r.getTitle());
                }
            }
        }
        return foundRecipeTitlesList;
    }

    public static int getSize() {
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
}
