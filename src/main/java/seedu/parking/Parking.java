package seedu.parking;

import seedu.api.Api;
import seedu.api.exception.EmptyResponseException;

import seedu.ui.Ui;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Parking {
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
            System.out.println("Complete fetch data"); // Debug line
        } catch (EmptyResponseException e) {
            ui.showFetchError();
        } catch (IOException e) {
            ui.showSaveError();
        } finally {
            System.out.println("Fetching and save data sequence terminated"); // Debug line
        }
    }
}
