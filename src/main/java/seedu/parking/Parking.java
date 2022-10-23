package seedu.parking;

import static seedu.common.CommonFiles.API_JSON_DIRECTORY;
import static seedu.common.CommonFiles.API_KEY_FILE;
import static seedu.common.CommonFiles.FAVOURITE_DIRECTORY;
import static seedu.common.CommonFiles.FAVOURITE_FILE;
import static seedu.common.CommonFiles.LTA_JSON_FILE;

import java.io.IOException;
import java.util.Objects;

import seedu.api.Api;
import seedu.commands.*;
import seedu.common.CommonFiles;
import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.*;
import seedu.files.Favourite;
import seedu.parser.Parser;
import seedu.parser.search.Sentence;
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

    public void run() {
        start();
        loadFavourite();
        loadApi();
        loadJson();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        this.ui = new Ui();
        this.api = new Api(LTA_JSON_FILE, API_JSON_DIRECTORY);
        this.favourite = new Favourite(FAVOURITE_DIRECTORY, FAVOURITE_FILE);
        ui.greetUser();
    }

    private void loadFavourite() {
        try {
            favourite.updateFavouriteList();
        } catch (IOException e) {
            ui.showUpdateFavouriteError();
        } catch (NoFileFoundException e) {
            ui.printError(e);
        }
    }
    private void loadApi() {
        try {
            api.loadApiKey(API_KEY_FILE, API_JSON_DIRECTORY, true);
            api.asyncExecuteRequest();
            api.fetchData();
            ui.print("Fetching data from API successful!");
        } catch (ParkingException e) {
            ui.print(e.getMessage());
            ui.print("Loading from local backup: ");
        }
    }

    private void loadJson() {
        ui.showLoadingDataMessage();
        try {
            carparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
            ui.showLoadingDataSuccess();
        } catch (ParkingException e) {
            ui.printError(e);
        }
    }

    private void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String input = ui.getCommand();
            command = new Parser().parseCommand(input, api, carparkList, favourite);
            CommandResult result = executeCommand(command);
            ui.printResult(result);
        } while (!ExitCommand.isExit(command));
    }

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