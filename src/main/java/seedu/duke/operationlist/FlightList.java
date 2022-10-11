package seedu.duke.operationlist;

import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.exceptions.SkyControlException;

public class FlightList extends OperationList {
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
    @Override
    public void addOperation(String detail) {
        try {
            checkCommandLength(detail.substring("flight add".length()));
            String flightNum = extractDetail(detail, "fn/", "a/");
            String airline = extractDetail(detail, "a/", "d/");
            String destination = extractDetail(detail, "d/", "t/");
            String departureTime = extractDetail(detail, "t/", "gn/");
            String gateNum = extractDetail(detail, "gn/", "tl/");
            String terminal = extractDetail(detail, "tl/", "c/");
            String seat = extractDetail(detail, "c/", "empty");

            FlightInfo flight = new FlightInfo(flightNum, airline, destination, departureTime, gateNum, terminal, seat);
            flights.add(flightIndex, flight);
            flightIndex++;
            System.out.println("Flight added!");

        } catch (SkyControlException e) {
            System.out.println("oops! The description is empty :(");
        }
    }

    //not done
    @Override
    public void deleteOperation(String detail) {
        try {
            checkCommandLength(detail.substring("flight_delete".length()));

        } catch (SkyControlException e) {
            System.out.println("oops! The description is empty :(");
        }
    }

    @Override
    public void listOperation() {
        System.out.println("---------------------------------------------------------"
                + "-----------------------------------------------------------------------");
        System.out.printf("%5s %10s %27s %20s %20s %12s %22s", "FLIGHT NUM", "AIRLINE",
                "DESTINATION", "DEPARTURE TIME", "BOARDING GATE", "TERMINAL", "CHECK-IN ROW/DOOR");
        System.out.println("\n-------------------------------------------------------"
                + "-------------------------------------------------------------------------");

        for (FlightInfo flightInfo : flights) {
            System.out.println(
                    flightInfo
            );
        }
    }
}
