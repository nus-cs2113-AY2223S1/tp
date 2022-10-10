package seedu.parking;

import seedu.api.Api;
import seedu.exception.EmptyResponseException;
import seedu.exception.ParkingException;
import seedu.data.CarparkList;

import seedu.ui.Ui;

import java.io.IOException;

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
            carparkList = new CarparkList();
            System.out.println("Load data sequence successful!");
        } catch (ParkingException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Load data sequence terminated"); // Debug line
        }

    }
}
