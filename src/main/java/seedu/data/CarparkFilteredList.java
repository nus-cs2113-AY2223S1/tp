package seedu.data;

import java.util.List;

import seedu.commands.FilterAddressCommand;

/**
 * A subset of {@link CarparkList} that contains items that were filtered or searched for.
 */
public class CarparkFilteredList extends CarparkList {
    /**
     * Constructor for the {@link CarparkList} class. Initializes an object from a given
     * list of {@link Carpark} objects.
     *
     * @param carparks {@link List} of {@link Carpark} objects.
     */
    public CarparkFilteredList(List<Carpark> carparks) {
        super(carparks);
    }

    /**
     * Gets a formatted string for use with the {@link FilterAddressCommand Filter} command.
     *
     * @return Formatted String.
     */
    public String getSearchListString() {
        StringBuilder bufferString = new StringBuilder();
        for (Carpark carpark : getCarparks()) {
            bufferString.append(carpark.getListViewString()).append("\n");
        }
        return bufferString.toString().trim();
    }
}
