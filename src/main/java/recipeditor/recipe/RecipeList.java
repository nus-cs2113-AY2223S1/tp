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
        return recipes.get(index);
    }

    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static void deleteRecipe(int index) {
        recipes.remove(index);
    }

    public Recipe getRecipeFromTitle(String title) {
        for (Recipe r : recipes) {
            if (r.getTitle().equals(title)) {
                return r;
            }
        }
        return null;
    }

    public static int getSize() {
        return recipes.size();
    }
}
