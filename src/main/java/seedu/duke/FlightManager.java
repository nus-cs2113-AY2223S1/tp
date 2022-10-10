package seedu.duke;

import java.util.ArrayList;

import seedu.duke.exceptions.SkyControlException;

public class FlightManager {
    public static ArrayList<Flight> flightList = new ArrayList<>();
    public static int flightIndex = 0;

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
        try {
            checkCommandLength(input.substring("flight_add".length()));
            String flightNum = extractDetail(input, "fn/", "a/");
            String airline = extractDetail(input, "a/", "d/");
            String destination = extractDetail(input, "d/", "t/");
            String departureTime = extractDetail(input, "t/", "gn/");
            String gateNum = extractDetail(input, "gn/", "tl/");
            String terminal = extractDetail(input, "tl/", "c/");
            String seat = extractDetail(input, "c/", "empty");

            Flight flight = new Flight(flightNum,airline,destination,departureTime,gateNum,terminal,seat);
            flightList.add(flightIndex,flight);
            flightIndex++;
            System.out.println("Flight added!");

        } catch (SkyControlException e) {
            System.out.println("oops! The description is empty :(");
        }
    }

    //not done
    public static void deleteFlight(String input) {
        try {
            checkCommandLength(input.substring("flight_delete".length()));

        } catch (SkyControlException e) {
            System.out.println("oops! The description is empty :(");
        }
    }

    // change print output to table form
    public static void printFlights() {
        System.out.println("---------------------------------------------------------"
                + "-----------------------------------------------------------------------");
        System.out.printf("%5s %10s %27s %20s %20s %12s %22s", "FLIGHT NUM", "AIRLINE",
                "DESTINATION", "DEPARTURE TIME", "BOARDING GATE", "TERMINAL", "CHECK-IN ROW/DOOR");
        System.out.println("\n-------------------------------------------------------"
                + "-------------------------------------------------------------------------");

        for (int i = 0; i < flightList.size(); i++) {
            System.out.println(
                    flightList.get(i)
            );
        }
    }

}
