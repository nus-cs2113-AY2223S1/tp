package recipeditor.recipe;

import java.util.ArrayList;

public class RecipeList {
    private static ArrayList<Recipe> recipes;

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

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void deleteRecipe(int index) {
        recipes.remove(index);
    }
}
