package seedu.duke.records.food;

import seedu.duke.logic.exception.IllegalValueException;

import java.util.ArrayList;

import static seedu.duke.logic.command.DateCommand.sortDateForFood;

/**
 * Represents and manages all the food records that the user has added.
 */
public class FoodList {
    ArrayList<Food> foodList;

    public FoodList() {
        this.foodList = new ArrayList<>();
    }

    /**
     * Adds a new food record to the foodList.
     *
     * @param food a new food record
     */
    public void addFood(Food food) {
        foodList.add(food);
    }

    /**
     * Returns the food record at the specified index from the foodList.
     *
     * @param index the index of the food record to be retrieved
     *
     * @throws IllegalValueException if the specified index is beyond bounds.
     */
    public Food getFood(int index) throws IllegalValueException {
        if (index < 0 || index >= getFoodListSize()) {
            throw new IllegalValueException("Please provide a valid index for food");
        }
        return foodList.get(index);
    }

    /**
     * Deletes the food record at the specified index from the foodList.
     *
     * @param index the index of the food record that the user wants to delete
     *
     * @throws IllegalValueException if the specified index is beyond bounds.
     */
    public void removeFood(int index) throws IllegalValueException {
        if (index < 0 || index >= getFoodListSize()) {
            throw new IllegalValueException("Please provide a valid index for food");
        }
        foodList.remove(index);
    }

    public ArrayList<Food> getFoodList() {
        sortDateForFood(foodList);
        return foodList;
    }

    /**
     * Returns the size of the food list.
     */
    public int getFoodListSize() {
        return foodList.size();
    }

}