package seedu.duke.item;

public class Category {
    enum Categories {
        SPORTS_EQUIPMENT,
        TEXTBOOKS_AND_NOTES,
        ELECTRICAL_APPLIANCES,
        //OTHERS
    }

    public int setCategory(Categories category) {
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
}
