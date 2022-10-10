package seedu.duke;

import java.util.Arrays;

public class PassengerParser {
    private static PassengerList passengers = new PassengerList();

    public static void parse(String[] inputWords) {
        if (inputWords[1].equalsIgnoreCase("add")) {
            String[] passengerDetailsArray = Arrays.copyOfRange(inputWords, 2, inputWords.length);
            String passengerDetails = String.join(" ", passengerDetailsArray);
            passengers.addPassenger(passengerDetails);
        }
    }
}
