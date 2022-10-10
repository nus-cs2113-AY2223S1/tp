package seedu.duke;

import java.util.Arrays;

public class PassengerParser {
    private static PassengerList passengers = new PassengerList();

    public static void parse(String[] inputWords) {
        String command = inputWords[1];
        if (command.equalsIgnoreCase("add")) {
            String[] passengerDetailsArray = Arrays.copyOfRange(inputWords, 2, inputWords.length);
            String passengerDetails = String.join(" ", passengerDetailsArray);
            passengers.addPassenger(passengerDetails);
        }
    }
}
