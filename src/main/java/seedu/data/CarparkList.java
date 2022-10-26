package seedu.data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import seedu.exception.FileWriteException;
import seedu.exception.InvalidFormatException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.FileReader;
import seedu.parser.search.Sentence;
import seedu.parser.search.Word;

/**
 * Container for all the {@link Carpark} classes. Contains method for finding the carpark.
 */
public class CarparkList {
    public final HashMap<String, Carpark> carparkHashMap = new HashMap<String, Carpark>();
    private List<Carpark> carparks;


    /**
     * Constructor for the {@link CarparkList} class. Loads from a {@link Path} object
     * that points to a {@code .json} file.
     *
     * @param filepath       Filepath to load from.
     * @param filepathBackup Backup filepath to load from.
     * @throws NoFileFoundException If no valid file is found in either location.
     */
    public CarparkList(Path filepath, Path filepathBackup) throws NoFileFoundException, FileWriteException {
        carparks = FileReader.loadLtaJson(filepath, filepathBackup);
        combineByLotType();
    }

    /**
     * Constructor for the {@link CarparkList} class. Initializes an object from a given
     * list of {@link Carpark} objects.
     *
     * @param carparks {@link List} of {@link Carpark} objects.
     */
    CarparkList(List<Carpark> carparks) {
        this.carparks = carparks;
    }

    /**
     * Constructor for the {@link CarparkList} class. Initializes an object from a
     * save expressed in a string.
     * @param saveStringFull A string that is a save of the entire List of {@link Carpark} objects.
     */
    public CarparkList(String saveStringFull) throws InvalidFormatException {
        carparks = new ArrayList<>();
        if (saveStringFull.equals("")) {
            throw new InvalidFormatException("Save string empty! Loading from backup: ");
        } else {
            try {
                String[] saveStrings = saveStringFull.split("\n");
                for (String saveString : saveStrings) {
                    carparks.add(Carpark.parseCarpark(saveString));
                }
            } catch (Exception e) {
                throw new InvalidFormatException("Save string format invalid. Loading from backup: ");
            }
        }
        combineByLotType();
    }

    /**
     * Constructor for the {@link CarparkList} class. Initialises an empty CarparkList object.
     */
    public CarparkList() {
        carparks = new ArrayList<>();
    }

    /**
     * Finds carpark based on an exact string (case-insensitive) for the carpark ID.
     *
     * @param searchString string that should be matched to
     * @return returns the carpark with this unique ID
     * @throws NoCarparkFoundException If no carpark was found
     */
    public Carpark findCarpark(String searchString) throws NoCarparkFoundException {
        if (carparkHashMap.get(searchString.toLowerCase()) == null) {
            throw new NoCarparkFoundException();
        } else {
            return carparkHashMap.get(searchString.toLowerCase());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Carpark carpark : carparks) {
            result.append(carpark.getListViewString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Filter {@link CarparkList#carparks} with a {@link Sentence} object, where
     * every word in the query must be present or a prefixing substring of a word
     * in the {@link Carpark} object's development string.
     *
     * @param searchQuery {@link Sentence} object to use as a search.
     * @return Filtered {@link CarparkList} object.
     */
    public CarparkFilteredList filterByAllStrings(Sentence searchQuery) {
        HashSet<Carpark> carparkListBuffer = new HashSet<>(carparks);
        for (Word word : searchQuery.getWords()) {
            carparkListBuffer = filterBySubstring(carparkListBuffer, word.toString());
        }
        return new CarparkFilteredList(new ArrayList<>(carparkListBuffer));
    }

    private HashSet<Carpark> filterBySubstring(HashSet<Carpark> carparkList, String wordString) {
        HashSet<Carpark> bufferList = new HashSet<>();
        for (Carpark carpark : carparkList) {
            for (Word word : carpark.getDevelopmentSentence().getWords()) {
                if (word.toString().toLowerCase().startsWith(wordString.toLowerCase())) {
                    word.makeBold(true);
                    bufferList.add(carpark);
                    break;
                }
            }
        }
        return bufferList;
    }

    /**
     * Combines multiple {@link Carpark} objects that have the same {@link Carpark#carparkId} value, and groups them
     * based on lot type.
     */
    public void combineByLotType() {
        for (Carpark carpark : carparks) {
            String carparkId = carpark.getCarparkId().toLowerCase();
            if (!carparkHashMap.containsKey(carparkId)) {
                carparkHashMap.put(carparkId, carpark);
            } else {
                if (carparkHashMap.get(carparkId).getLotType() != null) {
                    carparkHashMap.get(carparkId).addCarparkLotType(carpark);
                }
            }
        }
        carparks = new ArrayList<>(carparkHashMap.values());
    }

    public List<Carpark> getCarparks() {
        return carparks;
    }

    /**
     * Resets the {@link Word#isBold} attribute in all {@link Carpark} objects to false.
     */
    public void resetBoldForAllCarparks() {
        for (Carpark carpark : carparks) {
            carpark.resetBold();
        }
    }

    public String getSaveString() {
        StringBuilder bufferString = new StringBuilder();
        for (Carpark carpark : carparks) {
            bufferString.append(carpark.getSaveString());
        }
        return bufferString.toString();
    }

    /**
     * Updates the current {@link CarparkList} object with a newer generated one. The existing
     * {@link Carpark} objects in {@link CarparkList#carparks} should not be affected by this and
     * remain.
     *
     * @param carparkListNew The new carpark list to update with.
     */
    public void update(CarparkList carparkListNew) {
        for (Carpark carpark : carparkListNew.getCarparks()) {
            try {
                Carpark carparkToBeUpdated = findCarpark(carpark.getCarparkId());
                carparkToBeUpdated.setAllAvailableLots(carpark.getAllAvailableLots());
                carparkToBeUpdated.updateTime();
            } catch (NoCarparkFoundException e) {
                carpark.updateTime();
                carparks.add(carpark);
            }
        }
        combineByLotType();
    }

    private boolean carparkExists(Carpark carpark) {
        try {
            findCarpark(carpark.getCarparkId());
            return true;
        } catch (NoCarparkFoundException e) {
            return false;
        }
    }
}
