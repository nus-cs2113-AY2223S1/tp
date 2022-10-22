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

    public static void main(String[] args) {
        new Parking().run();
    }

    public void run() {
        start();
        loadApi();
        loadJson();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        this.ui = new Ui();
        this.api = new Api(LTA_JSON_FILE, API_JSON_DIRECTORY);
        ui.greetUser();
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
            command = new Parser().parseCommand(input, api, carparkList);
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

//----------------------------------------------------------------------------------------------------------------------------------------
//    /**
//     * Main entry-point for the java.duke.Duke application.
//     */
//    public static void main(String[] args) {
//        Parser parser = new Parser();
//        Find find = new Find();
//        Ui ui = new Ui();
//        ui.greetUser();
//        Auth auth = new Auth();
//        Api api = new Api(LTA_JSON_FILE, API_JSON_DIRECTORY);
//        Favourite favourite = new Favourite(FAVOURITE_DIRECTORY, FAVOURITE_FILE);
//        try {
//            favourite.updateFavouriteList();
//        } catch (IOException e) {
//            ui.showUpdateFavouriteError();
//        } catch (NoFileFoundException e) {
//            ui.printError(e);
//        }
//        try {
//            api.loadApiKey(API_KEY_FILE, API_JSON_DIRECTORY, true);
//            api.asyncExecuteRequest();
//            api.fetchData();
//            ui.print("Fetching data from API successful!");
//        } catch (ParkingException e) {
//            ui.print(e.getMessage());
//            ui.print("Loading from local backup: ");
//        }
//
//        // Load file from json
//        ui.showLoadingDataMessage();
//        try {
//            carparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
//            ui.showLoadingDataSuccess();
//        } catch (ParkingException e) {
//            ui.printError(e);
//        }
//
//        // Main loop for user to use program
//        boolean isExit = false;
//        while (!isExit) {
//            String input = ui.getCommand();
//            Command command;
//            try {
//                command = parser.parseInputString(input);
//            } catch (ParkingException e) {
//                ui.printError(e);
//                continue;
//            }
//            switch (Objects.requireNonNull(command)) {
//            case EXIT:
//                ui.showByeMessage();
//                isExit = true;
//                break;
//            case FIND:
//                try {
//                    String carparkID = find.getCarparkId(input);
//                    Carpark carpark = carparkList.findCarpark(carparkID);
//                    ui.print(carpark.getDetailViewString());
//                } catch (NoCommandArgumentException | NoCarparkFoundException | UnneededArgumentsException exception) {
//                    ui.printError(exception);
//                }
//                break;
//            case UPDATE:
//                try {
//                    //fetch api
//                    api.syncFetchData();
//                    //update json
//                    carparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
//                    ui.showUpdateDataSuccess();
//                } catch (ParkingException e) {
//                    ui.printError(e);
//                } finally {
//                    System.out.println("Update data terminated"); // Debug line
//                }
//                break;
//            case AUTH:
//                try {
//                    String[] words = input.trim().split("\\s+", 2);
//                    if (api.isApiValid(words[1])) {
//                        auth.saveApiKey(input);
//                        ui.showApiKeySaved();
//                    }
//                } catch (ParkingException e) {
//                    ui.printError(e);
//                }
//                break;
//            case LIST:
//                ui.print(carparkList.toString());
//                break;
//            case SEARCH:
//                Sentence searchQuery = new Sentence(Parser.splitCommandArgument(input)[1]);
//                ui.print(Search.runSearch(carparkList, searchQuery).getSearchListString());
//                carparkList.resetBoldForAllCarparks();
//                break;
//            case FAVOURITE:
//                try {
//                    String carparkID = find.getCarparkId(input);
//                    if (carparkID.equalsIgnoreCase("list")) {
//                        favourite.showList();
//                    } else {
//                        Carpark carpark = carparkList.findCarpark(carparkID);
//                        favourite.setFavourite(carparkID);
//                        ui.showFavouriteAddSuccess(carparkID);
//                    }
//                } catch (FileWriteException | UnneededArgumentsException | NoCommandArgumentException
//                         | NoCarparkFoundException | DuplicateCarparkException e) {
//                    ui.printError(e);
//                }
//                break;
//            case UNFAVOURITE:
//                try {
//                    String carparkId = favourite.getCarparkId(input);
//                    favourite.setUnfavourite(carparkId);
//                    ui.showUnfavouriteSuccess(carparkId);
//                } catch (FileWriteException | UnneededArgumentsException | NoCommandArgumentException
//                         | NoCarparkFoundException e) {
//                    ui.printError(e);
//                }
//                break;
//            default:
//                ui.showInvalidCommandError();
//                break;
//            }
//        }
//    }
//}
