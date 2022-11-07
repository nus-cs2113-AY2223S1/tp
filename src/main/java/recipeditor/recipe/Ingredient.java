package recipeditor.recipe;

import recipeditor.exception.ParseException;
import recipeditor.ui.Ui;

public class Ingredient {
    public static final String INGREDIENT_FORMAT = "Ingredient must follow format:"
            + '\n' + "<name>/<amount in number>/<unit>";
    public static final String EMPTY_INGREDIENT_FILEDS =
            "Ingredient name and unit cannot be empty!";
    private static final String DIVIDER = "/";
    private static final int MAX_DIVIDES = 3;
    private static final int START_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int UNIT_INDEX = 2;
    private String name;
    private double amount;
    private String unit;

    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    /**
     * Parse ingredient into name, amount, unit.
     *
     * @param input the ingredient to be parsed as String
     * @return parsed ingredient
     * @throws ParseException the ingredient is of wrong format and cannot be parsed
     */
    public static Ingredient parsedIngredients(String input) throws ParseException {
        String[] parsed = input.split(DIVIDER, MAX_DIVIDES);
        try {
            double amount = Double.parseDouble(parsed[AMOUNT_INDEX]);
            if (parsed[START_INDEX].isBlank() || parsed[UNIT_INDEX].isBlank()) {
                Ui.showMessage(EMPTY_INGREDIENT_FILEDS);
                throw new ParseException(INGREDIENT_FORMAT);
            }
            return new Ingredient(parsed[START_INDEX], amount, parsed[UNIT_INDEX]);
        } catch (Exception e) {
            throw new ParseException(INGREDIENT_FORMAT);
        }
    }
}
