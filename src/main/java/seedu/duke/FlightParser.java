package seedu.duke;

import java.util.Arrays;

public class FlightParser {
    public static void parse(String[] inputWords) {
        String command = inputWords[1];
        switch (command) {
        case "add":
            FlightManager.addFlight(command);
            break;
        case "list":
            FlightManager.printFlights();
            break;
        case "delete":
            FlightManager.deleteFlight(command);
            break;
        default:
            // temporary sout, change to exception handling
            System.out.println("Unknown command, please re-enter.");
        }
    }
}

