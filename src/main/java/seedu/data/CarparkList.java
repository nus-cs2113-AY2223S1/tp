package seedu.data;

import seedu.api.exception.NoCarparkFoundException;
import seedu.files.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarparkList {
    private final List<Carpark> CARPARKS;
    public CarparkList() throws IOException {
        CARPARKS = FileLoader.loadLtaJson();
    }

    public Carpark findCarpark(String searchString) throws NoCarparkFoundException {
        for (Carpark carpark : CARPARKS) {
            if (carpark.getCarparkId().equalsIgnoreCase(searchString)) {
                return carpark;
            }
        }
        throw new NoCarparkFoundException("No carpark was found!");
    }
}
