package seedu.duke.item;

public class Category {
    enum Categories {
        SPORTS_EQUIPMENT,
        TEXTBOOKS_AND_NOTES,
        ELECTRICAL_APPLIANCES,
        OTHERS //TO BE ADDED LATER
    }

    /**
     * Sets the category number based on the category name.
     * @param category type of category for the item user has added
     * @return corresponding category number
     */
    public static int setCategory(Categories category) {
        switch (category) {
        case SPORTS_EQUIPMENT:
            return 1;
        case TEXTBOOKS_AND_NOTES:
            return 2;
        case ELECTRICAL_APPLIANCES:
            return 3;
        default: //others
            return 4;
        }
    }

    /**
     * Maps the category number to the corresponding category.
     * @param categoryNumber integer to be mapped
     * @return corresponding category enum
     */
    public static Categories mapCategory(int categoryNumber) {
        switch (categoryNumber) {
        case 1:
            return Categories.SPORTS_EQUIPMENT;
        case 2:
            return Categories.TEXTBOOKS_AND_NOTES;
        case 3:
            return Categories.ELECTRICAL_APPLIANCES;
        default: //others
            return Categories.OTHERS;
        }
    }

    /**
     * List all available categories for the user to place their item in.
     * @return list of categories that are available
     */
    public static String listCategories() {
        StringBuilder list = new StringBuilder("Here are available categories: ");
        int index = 1;
        for (Categories category : Categories.values()) {
            list.append('\n').append(index++).append(". ").append(category);
        }
        return list.toString();
    }
}
