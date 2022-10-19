package recipeditor.recipe;

import java.util.ArrayList;

public class RecipeList {
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeList(ArrayList<Recipe> load) {
        recipes = new ArrayList<>();
        recipes.addAll(load);
    }

    public RecipeList() {
        this(null);
    }

    public static Recipe getRecipe(int index) {
        assert index >= 0 && index <= recipes.size();
        return recipes.get(index);
    }

    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static void deleteRecipe(int index) {
        assert index >= 0 && index <= recipes.size();
        recipes.remove(index);
    }

    public static void editRecipe(int index, Recipe newRecipe) {
        recipes.set(index, newRecipe);
    }

    public static Recipe getRecipeFromTitle(String recipleTitle) {
        for (Recipe r : recipes) {
            if (r.getTitle().equals(recipleTitle)) {
                return r;
            }
        }
        return null;
    }

    public static int getRecipeIndexFromTitle(String recipleTitle) {
        int i = 0;
        for (Recipe r : recipes) {
            if (r.getTitle().equals(recipleTitle)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int getSize() {
        return recipes.size();
    }
}
