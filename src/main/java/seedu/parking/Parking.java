package seedu.parking;

import seedu.api.Api;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Parking {
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
        try {
            api.asyncGetRequest();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Something goes wrong.");
        }
    }
}
