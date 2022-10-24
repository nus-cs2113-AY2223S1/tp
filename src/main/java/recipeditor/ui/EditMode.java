package recipeditor.ui;

import com.sun.source.tree.ReturnTree;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.command.Command;
import recipeditor.command.InvalidCommand;

public class EditMode {
    private static final String INVALID_INPUT = "Invalid input given. ";
    private static final String INVALID_FLAG = "Invalid flag given. ";
    private static final String INVALID_INDEX = "Invalid index given. ";
    private static final String GENERIC_ERROR = "Something happened. ";

    private static final String ENTER = "Entering edit mode. Currently editing: ";
    private static final String HELP_1 = "Available commands: /add, /del, /swap, /view, /done ";
    private static final String HELP_2 = "Available flags: -i (ingredients), -s (steps) ";
    private static final String CHANGE_1 = "Enter your changes: ";
    private static final String CHANGE_2 = "Ingredient format: <ingredient name> / <amount_in_float> / <unit>. Step format: <step> ";
    private static final String EXIT = "Exiting edit mode. ";
    private static final String NOT_FOUND = "Recipe not found. ";
    private static final String OLD = "Before: ";
    private static final String NEW = "After: ";

    private Recipe originalRecipe;
    private Recipe editedRecipe;

    public void enterEditMode(int index) {
        if (index < 0) {
            Ui.showMessageInline(NOT_FOUND);
            return;
        }
        try {
            originalRecipe = RecipeList.getRecipe(index);
            Ui.showMessageInline(ENTER + originalRecipe.getTitle());

            editedRecipe = new Recipe(originalRecipe.getTitle(), originalRecipe.getDescription());
            editedRecipe.addIngredients(originalRecipe.getIngredients());
            editedRecipe.addSteps(originalRecipe.getSteps());

            Ui.showMessageInline(originalRecipe.getRecipeAttributesFormatted());

            String input = "";

            while (!input.equals("/done")) {
                Ui.showMessageInline(HELP_1);
                Ui.showMessageInline(HELP_2);
                input = Ui.readInput();
                Ui.showMessageInline(parseEditInput(input));
            }
        } catch (IndexOutOfBoundsException i) {
            Ui.showMessageInline(INVALID_INDEX);
        } catch (NumberFormatException n) {
            Ui.showMessageInline(INVALID_INPUT);
        } catch (Exception e) {
            Ui.showMessageInline(GENERIC_ERROR);
        }
    }

    private String parseEditInput(String input) {
        try {
            String[] parsed = input.split(" ");
            String commandWord = parsed[0].toLowerCase();
            int flagType = checkFlagType(parsed);

            switch (commandWord) {
            case "/add":
                return "Add command";
            case "/del":
                if (flagType == 0) {
                    return INVALID_FLAG;
                }
                int index = Integer.parseInt(parsed[2]) - 1;
                if (flagType == 1) {
                    editedRecipe.deleteIngredient(index);
                    return OLD + "\n"
                            + originalRecipe.getIngredientAttributesFormatted() + "\n"
                            + NEW + "\n"
                            + editedRecipe.getIngredientAttributesFormatted();
                } else if (flagType == 2) {
                    editedRecipe.deleteStep(index);
                    return OLD + "\n"
                            + originalRecipe.getStepAttributesFormatted() + "\n"
                            + NEW + "\n"
                            + editedRecipe.getStepAttributesFormatted();
                }
                return GENERIC_ERROR;
            case "/swap":
                if (flagType == 0) {
                    return INVALID_FLAG;
                }
                int index1 = Integer.parseInt(parsed[2]) - 1;
                int index2 = Integer.parseInt(parsed[3]) - 1;
                if (flagType == 1) {
                    editedRecipe.swapIngredients(index1, index2);
                    return OLD + "\n"
                            + originalRecipe.getIngredientAttributesFormatted() + "\n"
                            + NEW + "\n"
                            + editedRecipe.getIngredientAttributesFormatted();
                } else if (flagType == 2) {
                    editedRecipe.swapSteps(index1, index2);
                    return OLD + "\n"
                            + originalRecipe.getStepAttributesFormatted() + "\n"
                            + NEW + "\n"
                            + editedRecipe.getStepAttributesFormatted();
                }
                return GENERIC_ERROR;
                case "/change":
                    if (flagType == 0) {
                        return INVALID_FLAG;
                    }
                    int indexToChange = Integer.parseInt(parsed[2]) - 1;
                    Ui.showMessageInline(CHANGE_1);
                    Ui.showMessageInline(CHANGE_2);
                    String newInput = Ui.readInput();
                    if (flagType == 1) {
                        String[] parsedIngredient = newInput.split("/", 3);
                        double amount = Double.parseDouble(parsedIngredient[1]);
                        Ingredient newIngredient = new Ingredient(parsedIngredient[0], amount, parsedIngredient[2]);
                        editedRecipe.setIngredient(indexToChange, newIngredient);
                        return OLD + "\n"
                                + originalRecipe.getIngredientAttributesFormatted() + "\n"
                                + NEW + "\n"
                                + editedRecipe.getIngredientAttributesFormatted();
                    } else if (flagType == 2) {
                        editedRecipe.setStep(indexToChange, newInput);
                        return OLD + "\n"
                                + originalRecipe.getStepAttributesFormatted() + "\n"
                                + NEW + "\n"
                                + editedRecipe.getStepAttributesFormatted();
                    }
                    return GENERIC_ERROR;
                case "/view":
                    return OLD + "\n"
                            + originalRecipe.getRecipeAttributesFormatted() + "\n"
                            + NEW + "\n"
                            + editedRecipe.getRecipeAttributesFormatted();
            case "/done":
                return "Quit command";
            default:
                return INVALID_INPUT;
            }

        } catch (NumberFormatException n) {
            return INVALID_INPUT;
        } catch (IndexOutOfBoundsException i) {
            return INVALID_INDEX;
        } catch (Exception e) {
            return GENERIC_ERROR;
        }
    }

    private int checkFlagType(String[] stringArray) {
        int flagCount = 0;
        int flagType = 0;
        for (String s: stringArray) {
            if (s.contains("-")) {
                flagCount += 1;
                if (s.equals("-i")) {
                    flagType = 1;
                } else if (s.equals("-s")) {
                    flagType = 2;
                }
            }
        }
        if (flagCount > 1) {
            return 0;
        }
        return flagType;
    }

    public boolean exitEditMode() {
        Ui.showMessageInline(EXIT);
        if (editedRecipe == null) {
            return false;
        } else {
            Ui.showMessageInline(OLD);
            Ui.showMessageInline(originalRecipe.getRecipeAttributesFormatted());
            Ui.showMessageInline(NEW);
            Ui.showMessageInline(editedRecipe.getRecipeAttributesFormatted());
            return true;
        }
    }

    public Recipe getOriginalRecipe() {
        return originalRecipe;
    }

    public Recipe getEditedRecipe() {
        return editedRecipe;
    }
}
