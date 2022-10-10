package seedu.duke;

import java.util.ArrayList;

import seedu.duke.exceptions.SkyControlException;

public class FlightManager {
    public static ArrayList<Flight> flightList = new ArrayList<>();
    public static int flightIndex = 0;
    private static final String FLIGHT_NUMBER_DELIMITER = "fn/";
    private static final String AIRLINE_DELIMITER = "a/";
    private static final String DESTINATION_DELIMITER = "d/";
    private static final String DEPARTURE_TIME_DELIMITER = "t/";
    private static final String GATE_NUMBER_DELIMITER = "gn/";
    private static final String TERMINAL_DELIMITER = "tl/";
    private static final String CHECK_IN_ROW_DELIMITER = "c/";
    private static final String END_OF_INPUT = "empty";

    public static String checkCommandLength(String description) throws SkyControlException {
        if (description.isEmpty()) {
            throw new SkyControlException(description);
        }
        return description;
    }

    public static String extractDetail(String command, String start, String end) {
        String extractedDetail;
        if (end.equals("empty")) {
            extractedDetail = command.substring(command.indexOf(start) + start.length());
        } else {
            extractedDetail = command.substring(command.indexOf(start) + start.length(), command.indexOf(end) - 1);
        }
        return extractedDetail;
    }

    //    flight_add fn/SQ712 a/Singapore Airlines d/Bangkok t/1600 gn/B1 tl/T1 c/03-3
    public static void addFlight(String input) {
        Ui ui = new Ui();
        try {
            checkCommandLength(input.substring("flight add".length()));
            String flightNum = extractDetail(input, FLIGHT_NUMBER_DELIMITER, AIRLINE_DELIMITER);
            String airline = extractDetail(input, AIRLINE_DELIMITER, DESTINATION_DELIMITER);
            String destination = extractDetail(input, DESTINATION_DELIMITER, DEPARTURE_TIME_DELIMITER);
            String departureTime = extractDetail(input, DEPARTURE_TIME_DELIMITER, GATE_NUMBER_DELIMITER);
            String gateNum = extractDetail(input, GATE_NUMBER_DELIMITER, TERMINAL_DELIMITER);
            String terminal = extractDetail(input, TERMINAL_DELIMITER, CHECK_IN_ROW_DELIMITER);
            String checkInRowAndDoor = extractDetail(input, CHECK_IN_ROW_DELIMITER, END_OF_INPUT);

            Flight flight = new Flight(flightNum, airline, destination,
                    departureTime, gateNum, terminal, checkInRowAndDoor);
            flightList.add(flightIndex, flight);
            flightIndex++;
            ui.showFlightAddedMessage();

        } catch (SkyControlException e) {
            ui.showEmptyDescriptionMessage();
        }
    }

    //not done
    public static void deleteFlight(String input) {
        Ui ui = new Ui();
        boolean isFlightFound;
        try {
            checkCommandLength(input.substring("flight delete".length()));
            String flightNum = input.substring("flight delete ".length());
            isFlightFound = findAndRemoveFlight(flightNum);
            if (!isFlightFound) {
                ui.showFlightNotFoundMessage(flightNum);
            }
        } catch (SkyControlException e) {
            ui.showEmptyDescriptionMessage();
        }
    }

    private static boolean findAndRemoveFlight(String flightNumber) {
        Ui ui = new Ui();
        boolean isFlightFound = false;
        for (Flight flight : flightList) {
            if (flight.flightNum.equals(flightNumber)) {
                isFlightFound = true;
                flightList.remove(flight);
                flightIndex--;
                ui.showFlightRemovedMessage(flightNumber.toUpperCase());
                break;
            }
        }
        return isFlightFound;
    }

    public static void printFlights() {
        Ui ui = new Ui();
        ui.showListOfFlights(flightList);
    }
}
