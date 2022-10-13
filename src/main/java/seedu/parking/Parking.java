package seedu.parking;

import static seedu.common.CommonFiles.API_JSON_DIRECTORY;
import static seedu.common.CommonFiles.API_KEY_FILE;
import static seedu.common.CommonFiles.LTA_JSON_FILE;

import java.io.IOException;
import java.util.Objects;

import seedu.api.Api;
import seedu.commands.Auth;
import seedu.commands.Find;
import seedu.common.CommonFiles;
import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.EmptyResponseException;
import seedu.exception.EmptySecretFileException;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoCommandArgumentException;
import seedu.exception.NoFileFoundException;
import seedu.exception.ParkingException;
import seedu.exception.UnauthorisedAccessApiException;
import seedu.exception.UnneededArgumentsException;
import seedu.parser.Command;
import seedu.parser.Parser;
import seedu.ui.Ui;

/**
 * Main class of the program.
 */
public class Parking {
    private static CarparkList carparkList;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        Find find = new Find();
        Ui ui = new Ui();
        ui.greetUser();
        Auth auth = new Auth();
        Api api = new Api(LTA_JSON_FILE, API_JSON_DIRECTORY);

        try {
            api.loadApiKey(API_KEY_FILE, API_JSON_DIRECTORY); // Will give exception when file is missing or empty key
            api.asyncExecuteRequest(); // Send request to API and wait asynchronously
            // More code here while waiting for data to come back
            // This should be the last code block of the initialising phase
            api.fetchData();
            ui.print("Fetching data from API successful!");
        } catch (ParkingException e) {
            ui.print(e.getMessage());
            ui.print("Loading from local backup: ");
        } catch (IOException e) {
            ui.print(new FileWriteException(API_JSON_DIRECTORY).getMessage());
            ui.print("Loading from local backup: ");
        }

        // Load file from json
        ui.showLoadingDataMessage();
        try {
            carparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
            ui.showLoadingDataSuccess();
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        }

        // Main loop for user to use program
        boolean isExit = false;
        while (!isExit) {
            String input = ui.getCommand();
            Command command = null;
            try {
                command = parser.parseInputString(input);
            } catch (ParkingException e) {
                ui.print(e.getMessage());
                continue;
            }
            switch (Objects.requireNonNull(command)) {
            case EXIT:
                ui.showByeMessage();
                isExit = true;
                break;
            case FIND:
                try {
                    String carparkID = find.getCarparkID(input);
                    Carpark carpark = carparkList.findCarpark(carparkID);
                    ui.print(carpark.toString());
                    ui.print(String.format("Available lots: %s", carpark.getAvailableLots()));
                } catch (NoCommandArgumentException | NoCarparkFoundException exception) {
                    System.out.println(exception.getMessage());
                }
                break;
            case UPDATE:
                try {
                    //fetch api
                    api.fetchData();

                    //update json
                    carparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
                    ui.showUpdateDataSuccess();
                } catch (ParkingException e) {
                    ui.print(e.getMessage());
                } catch (IOException e) {
                    ui.showUpdateError();
                } finally {
                    System.out.println("Update data terminated"); // Debug line
                }
                break;
            case AUTH:
                try {
                    auth.authenticate(input);
                    ui.showAuthSuccess();
                } catch (IOException e) {
                    ui.showAuthError();
                } catch (EmptySecretFileException | NoFileFoundException | EmptyResponseException
                         | UnauthorisedAccessApiException | NoCommandArgumentException | FileWriteException f) {
                    ui.print(f.getMessage());
                }
                break;
            case LIST:
                ui.print(carparkList.toString());
                break;
            default:
                ui.showInvalidCommandError();
                break;
            }
        }
    }
}
