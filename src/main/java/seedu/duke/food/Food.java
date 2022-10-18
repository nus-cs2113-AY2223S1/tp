package seedu.duke.food;

public class Food {
    private String foodDescription;
    private int calories;

    public Food(String foodDescription, int calories) {
        this.foodDescription = foodDescription;
        this.calories = calories;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food Description: " + foodDescription + System.lineSeparator()
                + "calories: " + calories;
    }

    public String saveFood() {
        return String.format("/%s /%d", foodDescription, calories);
    }
}
