package seedu.duke;

import java.util.Arrays;

public class FlightParser {
    public static void parse(String lineInput) {
        String[] inputWords = lineInput.split(" ");
        String command = inputWords[1];
        switch (command) {
        case "add":
            FlightManager.addFlight(lineInput);
            break;
        case "list":
            FlightManager.printFlights();
            break;
        case "delete":
            FlightManager.deleteFlight(lineInput);
            break;
        default:
            // temporary sout, change to exception handling
            System.out.println("Unknown command, please re-enter.");
        }
    }
}

