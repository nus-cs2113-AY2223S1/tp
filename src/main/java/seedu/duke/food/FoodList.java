package seedu.duke.food;

import seedu.duke.exception.IllegalValueException;

import java.util.ArrayList;

public class FoodList {
    ArrayList<Food> foodList;

    public FoodList() {
        this.foodList = new ArrayList<>();
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public Food getFood(int index) throws IllegalValueException {
        if (index < 0 || index >= getFoodListSize()) {
            throw new IllegalValueException("Please provide a valid index for food");
        }
        return foodList.get(index);
    }

    public Food removeFood(int index) throws IllegalValueException {
        if (index < 0 || index >= getFoodListSize()) {
            throw new IllegalValueException("Please provide a valid index for food");
        }
        return foodList.remove(index);
    }


    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public int getFoodListSize() {
        return foodList.size();
    }

}