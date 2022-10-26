package recipeditor.recipe;

import recipeditor.exception.ParseException;
import recipeditor.ui.Ui;

import java.util.ArrayList;
import java.util.Objects;

public class Ingredient {
    private static final String ERROR_PARSING = "Error Parsing Ingredients";
    private String name;
    private double amount;
    private String unit;

    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static Ingredient parsedIngredients(String input) throws ParseException {
        String[] parsed = input.split("/", 3);
        try {
            double amount = Double.parseDouble(parsed[1]);
            return new Ingredient(parsed[0], amount, parsed[2]);
        } catch (Exception e) {
            Ui.showMessage(ERROR_PARSING);
            throw new ParseException();
        }
    }
}
