package recipeditor.recipe;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Recipe {
    private String title;
    private String description;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;
    private Logger logger = Logger.getLogger("LOGS");

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public Recipe(String title) {
        this.title = title;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public Recipe() {
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
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
        assert index >= 0 && index <= ingredients.size();
        return this.ingredients.get(index);
    }

    public Ingredient getIngredientByName(String ingredientName) {
        for (Ingredient i : ingredients) {
            if (i.getName().equals(ingredientName)) {
                return i;
            }
        }
        return null;
    }

    public void deleteIngredient(int index) {
        assert index >= 0 && index <= ingredients.size();
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
        assert index >= 0 && index <= steps.size();
        return this.steps.get(index);
    }

    public void deleteStep(int index) {
        assert index >= 0 && index <= steps.size();
        this.steps.remove(index);
    }

    public String getIngredientAttributesFormatted() {
        StringBuilder recipeIngredientStringFormatted = new StringBuilder();
        for (Ingredient i : ingredients) {
            String textShown = String.format("%s | %s | %s %n",
                    i.getName(), String.valueOf(i.getAmount()), i.getUnit());
            recipeIngredientStringFormatted.append(textShown);
        }
        logger.log(Level.INFO, "Get ingredients" + ingredients);
        return recipeIngredientStringFormatted.toString();
    }

    public String getStepAttributesFormatted() {
        StringBuilder recipeStepStringFormatted = new StringBuilder();
        for (int i = 0; i < steps.size(); i++) {
            String textShown = String.format("%n%d) %s",
                    i + 1, getStep(i));
            recipeStepStringFormatted.append(textShown);
        }
        logger.log(Level.INFO, "Get steps");
        return recipeStepStringFormatted.toString();
    }

    public String getRecipeAttributesFormatted() {
        StringBuilder recipeAttributesStringFormatted = new StringBuilder();
        recipeAttributesStringFormatted.append("Recipe Name: " + title + "\n");
        recipeAttributesStringFormatted.append("Recipe Description: " + description + "\n");
        recipeAttributesStringFormatted.append("Recipe Ingredients: " + "\n" + getIngredientAttributesFormatted());
        recipeAttributesStringFormatted.append("Recipe Steps: " + getStepAttributesFormatted() + "\n");
        logger.log(Level.INFO, "Get recipes");
        return recipeAttributesStringFormatted.toString();
    }
}
