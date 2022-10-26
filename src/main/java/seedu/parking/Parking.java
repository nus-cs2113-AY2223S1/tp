package seedu.parking;

import static seedu.common.CommonFiles.API_JSON_DIRECTORY;
import static seedu.common.CommonFiles.API_KEY_FILE;
import static seedu.common.CommonFiles.CARPARK_LIST_DIRECTORY;
import static seedu.common.CommonFiles.CARPARK_LIST_FILE;
import static seedu.common.CommonFiles.FAVOURITE_DIRECTORY;
import static seedu.common.CommonFiles.FAVOURITE_FILE;
import static seedu.common.CommonFiles.LTA_JSON_FILE;

import java.io.IOException;

import seedu.api.Api;
import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.commands.ExitCommand;
import seedu.common.CommonFiles;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.exception.ParkingException;
import seedu.files.Favourite;
import seedu.files.FileReader;
import seedu.files.FileStorage;
import seedu.parser.Parser;
import seedu.ui.Ui;



/**
 * Main class of the program.
 */
public class Parking {
    private Ui ui;
    private Api api;
    private CarparkList carparkList;
    private Favourite favourite;


    public static void main(String[] args) {
        new Parking().run();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        loadFavourite();
        loadApi();
        loadJson();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads the data from the files and prints the welcome message.
     */
    private void start() {
        this.ui = new Ui();
        this.api = new Api(LTA_JSON_FILE, API_JSON_DIRECTORY);
        this.favourite = new Favourite(FAVOURITE_DIRECTORY, FAVOURITE_FILE);
        ui.greetUser();
    }

    /**
     * Loads the data from favouriteList.txt into program.
     */
    private void loadFavourite() {
        try {
            favourite.updateFavouriteList();
        } catch (IOException e) {
            ui.showUpdateFavouriteError();
        } catch (NoFileFoundException | FileWriteException e) {
            ui.printError(e);
        }
    }

    /**
     * Loads the api and fetches the data from api.
     */
    private void loadApi() {
        try {
            api.loadApiKey(API_KEY_FILE, API_JSON_DIRECTORY, true);
            api.syncFetchData();
            ui.println("Fetching data from API successful!");
        } catch (ParkingException e) {
            ui.println(e.getMessage());
        }
    }

    /**
     * Loads the JSON file and writes the data from the api into the json file.
     */
    private void loadJson() {
        ui.showLoadingDataMessage();
        try {
            carparkList = FileReader.loadCarparkListFromTxt(CARPARK_LIST_FILE, CARPARK_LIST_DIRECTORY);
            CarparkList newCarparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
            carparkList.update(newCarparkList);
            FileStorage.saveCarparkList(carparkList);
            ui.showLoadingDataSuccess();
        } catch (ParkingException e) {
            ui.printError(e);
        }
    }

    /**
     * Prints the exit message and exits the program.
     */
    private void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

    /**
     * Reads the user input and parses it into its respective format, and executes the command, until the user issues
     * the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String input = ui.getCommand();
            command = new Parser().parseCommand(input, api, carparkList, favourite);
            CommandResult result = executeCommand(command);
            ui.printResult(result);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the user input command and returns the result.
     *
     * @param command User command
     * @return result of the command.
     */
    private CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute();
            return result;
        } catch (FileWriteException | NoCarparkFoundException e) {
            return new CommandResult(e.getMessage());
        } catch (InvalidCommandException e) {
            throw new RuntimeException(e);
        }
    }
}
