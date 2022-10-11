package recipeditor.recipe;

import java.util.ArrayList;

public class Recipe {
    private String title;
    private String description;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
        ingredients = new ArrayList<>();
    }

    public Recipe(String title) {
        this.title = title;
        ingredients = new ArrayList<>();
    }

    public Recipe() {
        ingredients = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public Ingredient getIngredient(int index) {
        return this.ingredients.get(index);
    }

    public Ingredient getIngredientByName(String name) {
        for (Ingredient i : ingredients) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public void deleteIngredient(int index) {
        this.ingredients.remove(index);
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public String getStep(int index) {
        return this.steps.get(index);
    }

    public void deleteStep(int index) {
        this.steps.remove(index);
    }
}
