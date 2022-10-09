package seedu.data;

import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.FileLoader;

import java.util.List;

/**
 * Container for all the {@link Carpark} classes. Contains method for finding the carpark.
 */
public class CarparkList {
    private final List<Carpark> CARPARKS;
    public CarparkList() throws NoFileFoundException {
        CARPARKS = FileLoader.loadLtaJson();
    }

    /**
     * Finds carpark based on an exact string (case-insensitive) for the carpark ID.
     * @param searchString string that should be matched to
     * @return returns the carpark with this unique ID
     * @throws NoCarparkFoundException If no carpark was found
     */
    public Carpark findCarpark(String searchString) throws NoCarparkFoundException {
        for (Carpark carpark : CARPARKS) {
            if (carpark.getCarparkId().equalsIgnoreCase(searchString)) {
                return carpark;
            }
        }
        throw new NoCarparkFoundException("No carpark was found!");
    }
}
