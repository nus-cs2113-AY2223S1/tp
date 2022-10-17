package seedu.data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.FileReader;
import seedu.parser.search.Sentence;
import seedu.parser.search.Word;

/**
 * Container for all the {@link Carpark} classes. Contains method for finding the carpark.
 */
public class CarparkList {
    private final List<Carpark> carparks;

    public CarparkList(Path filepath, Path filepathBackup) throws NoFileFoundException {
        carparks = FileReader.loadLtaJson(filepath, filepathBackup);
    }

    public CarparkList(List<Carpark> carparks) {
        this.carparks = carparks;
    }

    /**
     * Finds carpark based on an exact string (case-insensitive) for the carpark ID.
     *
     * @param searchString string that should be matched to
     * @return returns the carpark with this unique ID
     * @throws NoCarparkFoundException If no carpark was found
     */
    public Carpark findCarpark(String searchString) throws NoCarparkFoundException {
        for (Carpark carpark : carparks) {
            if (carpark.getCarparkId().equalsIgnoreCase(searchString)) {
                return carpark;
            }
        }
        throw new NoCarparkFoundException();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Carpark carpark : carparks) {
            result.append(carpark.toString()).append("\n");
        }
        return result.toString();
    }

    public CarparkList searchStrings(Sentence searchQuery) {
        List<Carpark> carparkListBuffer = carparks;
        for (Word word : searchQuery.getWords()) {
            carparkListBuffer = filterBySubstring(carparkListBuffer, word.toString());
        }
        return new CarparkList(carparkListBuffer);
    }

    private List<Carpark> filterBySubstring(List<Carpark> carparkList, String wordString) {
        List<Carpark> bufferList = new ArrayList<>();
        for (Carpark carpark : carparkList) {
            for (Word word : carpark.getDevelopmentSentence().getWords()) {
                if (word.toString().equalsIgnoreCase(wordString)) {
                    word.makeBold(true);
                    bufferList.add(carpark);
                    break;
                }
            }
        }
        return bufferList;
    }

    public void resetBoldForAllCarparks() {
        for (Carpark carpark : carparks) {
            carpark.resetBold();
        }
    }

    public String getSearchListString() {
        StringBuilder bufferString = new StringBuilder();
        for (Carpark carpark: carparks) {
            bufferString.append(carpark.getDevelopmentSentence().toString()).append("\n");
        }
        return bufferString.toString();
    }
}
