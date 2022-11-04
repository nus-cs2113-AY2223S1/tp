package seedu.duke.utils;

import org.apache.commons.lang3.SystemUtils;
import seedu.duke.exceptions.YamomException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the saving and loading of states.
 */
public class Storage {
    public static final String FILE_PATH = "data/.duke.txt";

    public static final String NO_PREVIOUS_STATE_ERROR_MESSAGE = "There was no previously saved state.";

    public static final String LOADING_PREVIOUS_STATE_MESSAGE = "Loading previous state.";

    public static final String NO_PREVIOUS_SAVED_MODULE_ERROR_MESSAGE = "There are no modules saved.";

    public static final String EXPORT_MESSAGE = "These are your export links:";

    private Logger logger = Logger.getLogger(Storage.class.getName());

    public static final String SUBSYSTEM_NAME = "storage";

    /**
     * Tries to open the file containing the previously saved state from specified file path.
     * Then outputs to the user if the opening of file was successful.
     *
     * @param state Current state of the application to be saved.
     * @param ui    To output to the user.
     */
    public void openPreviousState(State state, Ui ui) {
        assert state != null : "List of lessons should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Attempting to open previous saved file");
        try {
            ArrayList<String> links = readPreviousState();
            for (String link: links) {
                Link.parseLink(link, state, ui);
                if (Link.isEmptyLink(link)) {
                    ui.addMessage(NO_PREVIOUS_SAVED_MODULE_ERROR_MESSAGE);
                }
            }
            state.setSemester(1);
            ui.addMessage(LOADING_PREVIOUS_STATE_MESSAGE);
            logger.log(Level.FINE, "Opened previous saved file");
        } catch (FileNotFoundException e) {
            ui.addMessage(NO_PREVIOUS_STATE_ERROR_MESSAGE);
            logger.log(Level.FINE, "No previous saved file");
        } catch (YamomException e) {
            ui.addMessage(e.getMessage());
            logger.log(Level.FINE, e.getMessage());
        }
        ui.displayUi();
    }

    /**
     * Opens the previously saved file. The saved file should only contain one line which is the
     * link for exporting to NUSMods.
     *
     * @return The links for exporting to NUSMods.
     * @throws FileNotFoundException The file in the file path cannot be found.
     */
    private ArrayList<String> readPreviousState() throws FileNotFoundException {
        ArrayList<String> links = new ArrayList<>();
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (Link.isValidLink(line)) {
                links.add(line);
            }
        }
        scanner.close();
        return links;
    }

    /**
     * Saves all current selected modules for all semesters by overriding the file.
     *
     * @param state The current state of the application.
     * @param ui    To output to the user.
     * @throws IOException Failed or interrupted I/O operations.
     */
    public void saveState(State state, Ui ui, boolean isExit) throws IOException {
        assert state != null : "State should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Saving current state with " + state.getSelectedModulesList().size()
            + " modules into a file. The format will be NUSMods export link.");
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        file.createNewFile();

        file.setWritable(true);
        Path path = Paths.get(FILE_PATH);
        if (!SystemUtils.IS_OS_LINUX) {
            Files.setAttribute(path, "dos:hidden", false);
        }
        ui.addMessage(EXPORT_MESSAGE);
        FileWriter fw = new FileWriter(file);
        int currSem = state.getSemester();
        for (int i = 1; i <= 4; i++) {
            state.setSemester(i);
            String toSave = Link.getLink(state);
            ui.addMessage(toSave);
            fw.write(toSave + System.lineSeparator());
        }
        state.setSemester(currSem);
        if (!isExit) {
            ui.clearUiBuffer();
        }
        ui.displayUi();
        fw.close();
        file.setReadOnly();
        //set hidden attribute
        if (!SystemUtils.IS_OS_LINUX) {
            Files.setAttribute(path, "dos:hidden", true);
        }

    }
}
