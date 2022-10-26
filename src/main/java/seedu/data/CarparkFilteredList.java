package seedu.data;

import java.util.List;

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
     * Gets a formatted string for use with the {@link seedu.commands.Search Search} command.
     *
     * @return Formatted string.
     */
    public String getSearchListString() {
        StringBuilder bufferString = new StringBuilder();
        for (Carpark carpark : getCarparks()) {
            bufferString.append(carpark.getListViewString()).append("\n");
        }
        return bufferString.toString().trim();
    }

}
