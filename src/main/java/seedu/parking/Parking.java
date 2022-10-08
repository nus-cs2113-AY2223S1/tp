package seedu.parking;

import seedu.api.Api;
import seedu.api.exception.EmptyResponseException;
import seedu.data.CarparkList;

import java.io.IOException;
import java.util.Scanner;

public class Parking {
    private static CarparkList carparkList;
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = "LOGO for parKING";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        Api api = new Api();
        api.asyncExecuteRequest(); // Send request to API and wait asynchronously

        // More code here while waiting for data to come back


        // This should be the last code block of the initialising phase
        try {
            System.out.println("Trying to fetch data"); // Debug line
            api.fetchData();
            System.out.println("Completed fetch data!"); // Debug line
        } catch (EmptyResponseException e) {
            System.out.println("Failed to fetch data!");
        } catch (IOException e) {
            System.out.println("Failed to save data!");
        } finally {
            System.out.println("Fetching and save data sequence terminated"); // Debug line
        }

        // Load file from json
        System.out.println("Trying to load data");
        try {
            carparkList = new CarparkList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Load data sequence terminated"); // Debug line
        }

    }
}
