package seedu.parking;

import java.io.IOException;

import seedu.api.Api;
import seedu.common.CommonFiles;
import seedu.data.CarparkList;
import seedu.exception.EmptyResponseException;
import seedu.exception.ParkingException;
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
        Ui ui = new Ui();
        ui.greetUser();

        Api api = new Api();
        api.asyncExecuteRequest(); // Send request to API and wait asynchronously

        // More code here while waiting for data to come back


        // This should be the last code block of the initialising phase
        try {
            System.out.println("Trying to fetch data"); // Debug line
            api.fetchData();
            System.out.println("Completed fetch data!"); // Debug line
        } catch (EmptyResponseException e) {
            ui.showFetchError();
        } catch (IOException e) {
            ui.showSaveError();
        } finally {
            System.out.println("Fetching and save data sequence terminated"); // Debug line
        }

        // Load file from json
        System.out.println("Trying to load data");
        try {
            carparkList = new CarparkList(CommonFiles.LTA_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
            System.out.println("Load data sequence successful!");
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Load data sequence terminated"); // Debug line
        }

    }
}
