package recipeditor.recipe;

import java.util.ArrayList;

public class Recipe {
    private String title;
    private String description;
    private ArrayList<Ingredient> ingredients;
    private String[] steps;

    public Recipe(String title, String description, ArrayList<Ingredient> ingredients, String[] steps) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Recipe(String title) {
        this.title = title;
    }

    public Recipe() {
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

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }
}
