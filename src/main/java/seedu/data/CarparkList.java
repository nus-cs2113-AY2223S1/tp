package seedu.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import seedu.common.CommonFiles;
import seedu.exception.DuplicateCarparkIdException;
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
     * @param filepath Filepath to load from.
     * @param filepathBackup Backup filepath to load from.
     * @throws NoFileFoundException If no valid file is found in either location.
     * @throws FileWriteException If unable to write to backup file.
     */
    public CarparkList(Path filepath, Path filepathBackup) throws NoFileFoundException, FileWriteException {
        carparks = FileReader.loadLtaJson(filepath, filepathBackup);
        combineByLotType();
        sortCarparksById();
    }

    /**
     * Constructor for the {@link CarparkList} class. Initializes an object from a given
     * list of {@link Carpark} objects.
     *
     * @param carparks {@link List} of {@link Carpark} objects.
     */
    CarparkList(List<Carpark> carparks) {
        assert carparks != null : "Error creating carparks from list. Please contact the developer!";
        this.carparks = carparks;
        sortCarparksById();
    }

    /**
     * Constructor for the {@link CarparkList} class. Initializes an object from a
     * save expressed in a string.
     * @param saveStringFull a string that is a save of the entire List of {@link Carpark} objects.
     * @throws InvalidFormatException If save string is empty.
     * @throws DuplicateCarparkIdException If carpark ID is already added.
     */
    public CarparkList(String saveStringFull) throws InvalidFormatException, DuplicateCarparkIdException {
        carparks = new ArrayList<>();
        HashSet<String> carparkIds = new HashSet<>();
        String filePath = Paths.get(CommonFiles.CARPARK_LIST_DIRECTORY, CommonFiles.CARPARK_LIST_FILE).toString();

        if (saveStringFull.isBlank()) {
            throw new InvalidFormatException("Save string empty! Loading from backup: ");
        } else {
            try {
                String[] saveStrings = saveStringFull.split("\n");
                for (String saveString : saveStrings) {
                    Carpark carpark = Carpark.parseCarpark(saveString);
                    String carparkId = carpark.getCarparkId().trim().toLowerCase();
                    if (carparkIds.contains(carparkId)) {
                        throw new DuplicateCarparkIdException(filePath);
                    } else {
                        carparkIds.add(carparkId);
                    }
                    carparks.add(carpark);
                }
            } catch (DuplicateCarparkIdException duplicateCarparkIdException) {
                throw new DuplicateCarparkIdException(filePath);
            } catch (Exception e) {
                throw new InvalidFormatException("Save string format invalid. Loading from backup and restoring "
                    + "files...");
            }
        }
        combineByLotType();
        sortCarparksById();
    }

    /**
     * Sort carparks by carpark ID.
     */
    private void sortCarparksById() {
        carparks.sort(Carpark::compareTo);
    }

    /**
     * Finds carpark based on an exact string (case-insensitive) for the carpark ID.
     *
     * @param searchString String that should be matched to.
     * @return Carpark with this unique ID.
     * @throws NoCarparkFoundException If no carpark was found.
     */
    public Carpark findCarpark(String searchString) throws NoCarparkFoundException {
        if (carparkHashMap.get(searchString.toLowerCase()) == null) {
            throw new NoCarparkFoundException();
        } else {
            return carparkHashMap.get(searchString.toLowerCase());
        }
    }

    /**
     * Checks if carpark ID exists in carpark list.
     *
     * @param searchString carpark ID to search for.
     * @return True if carpark ID exists, false otherwise.
     */
    public boolean isCarparkValid(String searchString) {
        return carparkHashMap.get(searchString.toLowerCase()) != null;
    }

    /**
     * Returns a String of all carparks and their brief information.
     *
     * @return String of all carparks and their brief information.
     */
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
     * any word must be present as a substring in the carpark object's carparkId field.
     *
     * @param searchQuery {@link Sentence} object to use as a search.
     * @return Filtered {@link CarparkList} object.
     */
    public CarparkFilteredList filterByCarparkId(Sentence searchQuery) {
        HashSet<Carpark> carparkListBuffer = new HashSet<>();
        for (Word word : searchQuery.getWords()) {
            for (Carpark carpark : carparks) {
                if (carpark.getCarparkId().toLowerCase().contains(word.getText().toLowerCase())) {
                    carparkListBuffer.add(carpark);
                }
            }
        }
        return new CarparkFilteredList(new ArrayList<>(carparkListBuffer));
    }

    /**
     * Filter {@link CarparkList#carparks} with a {@link Sentence} object, where
     * every word in the query must be present or a prefixing substring of a word
     * in the {@link Carpark} object's development string.
     *
     * @param searchQuery {@link Sentence} object to use as a search.
     * @return Filtered {@link CarparkList} object.
     */
    public CarparkFilteredList filterByAddress(Sentence searchQuery) {
        HashSet<Carpark> carparkListBuffer = new HashSet<>(carparks);
        for (Word word : searchQuery.getWords()) {
            carparkListBuffer = filterBySubstring(carparkListBuffer, word.toString());
        }
        return new CarparkFilteredList(new ArrayList<>(carparkListBuffer));
    }

    /**
     * Filter {@link CarparkList#carparks} with a substring, returning only Carparks with their
     * development sentence containing the substring.
     *
     * @param carparkList {@link CarparkList#carparks} to be searched and filtered from.
     * @param wordString substring to be searched for.
     * @return Filtered {@link CarparkList} object.
     */
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

    /**
     * Combines multiple {@link Carpark} objects that have the same carpark ID, and groups them based on lot type.
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
     * Resets the isBold attribute in all {@link Carpark} objects to false.
     */
    public void resetBoldForAllCarparks() {
        for (Carpark carpark : carparks) {
            carpark.resetBold();
        }
    }

    /**
     * Returns a String of formatted Strings of carparks for saving.
     *
     * @return String of formatted Strings of carparks for saving.
     */
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
                if (!carpark.getAllAvailableLots().equals(carparkToBeUpdated.getAllAvailableLots())) {
                    carparkToBeUpdated.updateTime();
                }
                carparkToBeUpdated.setAllAvailableLots(carpark.getAllAvailableLots());
            } catch (NoCarparkFoundException e) {
                carpark.updateTime();
                carparks.add(carpark);
            }
        }
        combineByLotType();
        sortCarparksById();
    }
}
