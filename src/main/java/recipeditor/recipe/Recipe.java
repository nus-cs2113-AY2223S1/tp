package recipeditor.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Recipe {
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<String> steps;
    private final Logger logger = Logger.getLogger("LOGS");
    private String title = "";
    private String description = "";

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

    public void addIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public void swapIngredients(int index1, int index2) throws IndexOutOfBoundsException {
        Collections.swap(ingredients, index1, index2);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public Ingredient getIngredient(int index) throws IndexOutOfBoundsException {
        return this.ingredients.get(index);
    }

    public void deleteIngredient(int index) throws IndexOutOfBoundsException {
        this.ingredients.remove(index);
    }

    /**
     * Set the ingredient in the given index according to the input.
     *
     * @param index ingredient index to be set
     * @param ingredient ingredient to be set
     */
    public void setIngredient(int index, Ingredient ingredient) throws IndexOutOfBoundsException {
        this.ingredients.set(index, ingredient);
        if (index >= ingredients.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void addSteps(ArrayList<String> steps) {
        this.steps.addAll(steps);
    }

    public void swapSteps(int index1, int index2) throws IndexOutOfBoundsException {
        Collections.swap(steps, index1, index2);
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public String getStep(int index) throws IndexOutOfBoundsException {
        return this.steps.get(index);
    }

    public void deleteStep(int index) throws IndexOutOfBoundsException {
        this.steps.remove(index);
    }

    public void setStep(int index, String step) throws IndexOutOfBoundsException {
        this.steps.set(index, step);
    }

    /**
     * Get a formatted string output of the ingredient details for printing on the console.
     */
    public String getIngredientAttributesFormatted() {
        StringBuilder recipeIngredientStringFormatted = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            String textShown = String.format("%s.%s/ %s /%s%n", i
                    + 1, ingredient.getName(), ingredient.getAmount(), ingredient.getUnit().trim());
            recipeIngredientStringFormatted.append(textShown);
        }
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Get ingredients in" + title);
        return recipeIngredientStringFormatted.toString();
    }

    /**
     * Get a formatted string output of the step details for printing on the console.
     */
    public String getStepAttributesFormatted() {
        StringBuilder recipeStepStringFormatted = new StringBuilder();
        for (int i = 0; i < steps.size(); i++) {
            String textShown = String.format("%d. %s%n", i + 1, getStep(i));
            recipeStepStringFormatted.append(textShown);
        }
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Get steps");
        return recipeStepStringFormatted.toString();
    }

    /**
     * Get a formatted string output of the recipe details to save as the individual recipe file.
     */
    public String getRecipeSaveableFormatted() {
        String recipeAttributesStringFormatted = "# TITLE \n"
                + title + "\n\n" + "# DESCRIPTION \n"
                + description.trim() + "\n\n" + "# INGREDIENTS <ingredient name> / <amount> / <unit> \n"  //Remove trim
                + getIngredientAttributesFormatted() + "\n" + "# STEPS \n"
                + getStepAttributesFormatted() + "\n\n";
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Get attributes of " + title);
        return recipeAttributesStringFormatted;
    }

    /**
     * Get a formatted string output of the recipe details.
     */
    public String getRecipeAttributesFormatted() {
        String recipeAttributesStringFormatted = "TITLE:\n"
                + title + "\n\n" + "DESCRIPTION:\n"
                + description.trim() + "\n\n" + "INGREDIENTS: "
                + "\n" + getIngredientAttributesFormatted() + "\n" + "STEPS: "
                + "\n" + getStepAttributesFormatted();
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Get attributes of " + title);
        return recipeAttributesStringFormatted;
    }

    /**
     * Checks if all the fields of the recipe are filled to be a valid recipe.
     *
     * @return if the recipe is valid
     */
    public boolean isNotRecipeValid() {
        return (this.getTitle().isBlank() || this.getDescription().isBlank() || this.getIngredients().isEmpty()
                || this.getSteps().isEmpty());
    }
}
