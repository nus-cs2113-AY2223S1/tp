package seedu.duke.item;

public class Category {
    enum Categories {
        SPORTS_EQUIPMENT,
        TEXTBOOKS_AND_NOTES,
        ELECTRICAL_APPLIANCES,
        OTHERS //TO BE ADDED LATER
    }

    public static int setCategory(Categories category) {
        switch (category) {
        case SPORTS_EQUIPMENT:
            return 0;
        case TEXTBOOKS_AND_NOTES:
            return 1;
        case ELECTRICAL_APPLIANCES:
            return 2;
        default: //others
            return 3;
        }
    }

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

    public static String listCategories() {
        StringBuilder list = new StringBuilder("Here are available categories: ");
        int index = 1;
        for (Categories category : Categories.values()) {
            list.append('\n').append(index++).append(". ").append(category);
        }
        return list.toString();
    }
}
