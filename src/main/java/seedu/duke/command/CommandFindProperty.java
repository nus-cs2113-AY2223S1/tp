package seedu.duke.command;

//@@author wilsonngja
import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.Property;
import seedu.duke.PropertyList;
import seedu.duke.Storage;
import seedu.duke.Ui;

import static seedu.duke.Messages.MESSAGE_NO_PROPERTY_MATCHES;

public class CommandFindProperty extends Command {

    private final String queryText;
    private static final int INCREMENT_BY_ONE = 1;
    private static final int NO_MATCHES = 0;

    public CommandFindProperty(String queryText) {
        this.queryText = queryText;
    }


    @Override
    public void execute(Ui ui, Storage storage, PropertyList propertyList,
                        ClientList clientList, PairingList pairingList) {
        int propertyListSize = propertyList.getCurrentListSize();
        int numOfMatches = 0;

        for (int i = 0; i < propertyListSize; i += 1) {
            Property currentProperty = propertyList.getPropertyList().get(i);
            if (hasQueryText(queryText,currentProperty)) {
                int index = i + INCREMENT_BY_ONE;
                ui.displayOneProperty(currentProperty, index);
                numOfMatches += INCREMENT_BY_ONE;
            }
        }

        boolean hasNoMatches = numOfMatches == NO_MATCHES;

        // Display to user that there is no match
        if (hasNoMatches) {
            ui.showToUser(MESSAGE_NO_PROPERTY_MATCHES);
        }

    }

    /**
     * Determines if the property contains the user-inputted query text.
     *
     * @param queryText The input text the user intend to query.
     * @param currentProperty The current Property object that will be checked.
     * @return true if property contains the queried text and false if it does not.
     */
    public boolean hasQueryText(String queryText, Property currentProperty) {
        // Change the string to lower case to ignore case when searching
        String landlordName = currentProperty.getLandlordName().toLowerCase();
        String propertyAddress = currentProperty.getPropertyAddress().toLowerCase();
        String propertyRentalPrice = currentProperty.getRentingPrice().toLowerCase();
        String unitType = currentProperty.getUnitType().toLowerCase();
        String lowerCaseQueryText = queryText.toLowerCase();


        boolean hasQueryTextInProperty = landlordName.contains(lowerCaseQueryText)
                || propertyAddress.contains(lowerCaseQueryText) || propertyRentalPrice.contains(lowerCaseQueryText)
                || unitType.contains(lowerCaseQueryText);
        return hasQueryTextInProperty;

    }
}
//@@author
