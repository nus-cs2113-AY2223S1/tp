package recipeditor.recipe;

import java.util.ArrayList;

public class RecipeList {
    private static final int MAX_RECIPE = 100;
    private static ArrayList<Recipe> recipes = new ArrayList<Recipe>(MAX_RECIPE);

    public RecipeList(ArrayList<Recipe> load) {
        recipes = new ArrayList<>();
        recipes.addAll(load);
    }

    public RecipeList() {
        this(null);
    }

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public static Recipe getRecipe(int index) {
        return recipes.get(index);
    }

    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static void deleteRecipe(int index) {
        recipes.remove(index);
    }

    public static int getSize() {
        return recipes.size();
    }
}
