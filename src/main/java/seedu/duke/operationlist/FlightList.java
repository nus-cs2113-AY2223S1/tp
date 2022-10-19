package seedu.duke.operationlist;

import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.exceptions.SkyControlException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightList extends OperationList {
    public static int flightIndex = 0;
    private static final String FLIGHT_ADD_COMMAND = "flight add";
    private static final String FLIGHT_ADD_DELIMITER = "flight add ";
    private static final String FLIGHT_DELETE_COMMAND = "flight delete";
    private static final String FLIGHT_NUMBER_DELIMITER = " fn/";
    private static final String AIRLINE_DELIMITER = " a/";
    private static final String DESTINATION_DELIMITER = " d/";
    private static final String DEPARTURE_TIME_DELIMITER = " t/";
    private static final String GATE_NUMBER_DELIMITER = " gn/";
    private static final String CHECK_IN_ROW_DELIMITER = " c/";

    protected static final String EMPTY_STRING = "";
    protected static final int CHECK_MISSING_DETAIL = 0;
    protected static final int AIRLINE_LENGTH_LIMIT = 22;
    protected static final int DESTINATION_LENGTH_LIMIT = 22;
    protected static boolean isFlightNumberPresent = false;
    protected static boolean isDepartureTimePresent = false;
    protected static boolean isGateNumberPresent = false;
    protected static String timeRegex = "([01]?[0-9]|2[0-3])[0-5][0-9]";
    protected static String checkInRegex = "[0-9]{2}-[0-9]{2}";
    protected String flightNumber;
    protected String airline;
    protected String destination;
    protected String departureTime;
    protected String gateNumber;
    protected String checkIn;

    protected static int numOfFlights = 0;

    public static void checkCommandLength(String description) throws SkyControlException {
        if (description.isEmpty()) {
            throw new SkyControlException(ui.showEmptyDescriptionMessage());
        }
    }

    public static String extractDetail(String command, String start, String end) throws SkyControlException {

        String extractedDetail;
        int startIndex = command.indexOf(start) + start.length();
        int endIndex = command.lastIndexOf(end);
        checkNoDetailsMissing(startIndex,endIndex);
        extractedDetail = command.substring(startIndex, endIndex).trim();
        if (extractedDetail.equals(EMPTY_STRING)) {
            throw new SkyControlException(ui.getMissingDetailsError());
        }
        return extractedDetail;
    }

    public static void checkNoDetailsMissing(int startIndex, int endIndex) throws SkyControlException {
        if (endIndex <= startIndex || endIndex < CHECK_MISSING_DETAIL || startIndex < CHECK_MISSING_DETAIL) {
            throw new SkyControlException(ui.getErrorMessage());
        }
    }

    @Override
    public void addOperation(String command) throws SkyControlException {
        checkCommandLength(command.substring(FLIGHT_ADD_COMMAND.length()));
        getFlightDetails(command.substring(FLIGHT_ADD_DELIMITER.length()));
        checkFlightNumberDuplicates();
        checkAvailableGateNumber();
        FlightInfo flight = new FlightInfo(flightNumber, airline, destination,
                departureTime, gateNumber, checkIn);
        flights.add(flightIndex, flight);
        flightIndex++;
        ui.showFlightAddedMessage();
    }

    @Override
    public void deleteOperation(String detail) {
        boolean isFlightFound;
        try {
            checkCommandLength(detail.substring(FLIGHT_DELETE_COMMAND.length()));
            checkValidFlightNumber(detail.substring("flight delete ".length()));
            String flightNum = detail.substring("flight delete ".length()).toUpperCase();
            isFlightFound = findAndRemoveFlight(flightNum);
            if (!isFlightFound) {
                ui.showFlightNotFoundMessage(flightNum);
            }
        } catch (SkyControlException e) {
            ui.showEmptyDescriptionMessage();
        } catch (NumberFormatException e) {
            ui.showWrongFlightFormatMessage();
        }
    }

    private void checkValidFlightNumber(String substring) throws NumberFormatException {
        String[] letters = substring.split("");
        for (int i = 0; i < letters.length; i++) {
            if (!Character.isLetterOrDigit(substring.charAt(i))) {
                throw new NumberFormatException(substring);
            }
        }
    }

    //@@author Franky4566
    @Override
    public void listOperation() {
        ui.showListOfFlights(flights);
    }

    //@@author JordanKwua
    private static boolean findAndRemoveFlight(String flightNumber) {
        boolean isFlightFound = false;
        assert !flights.isEmpty();
        for (FlightInfo flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                isFlightFound = true;
                flights.remove(flight);
                flightIndex--;
                ui.showFlightRemovedMessage(flightNumber);
                break;
            }
        }
        return isFlightFound;
    }

    //@@author Franky4566
    public void getNumberOfFlights() {
        assert numOfFlights >= 0;
        numOfFlights = flights.size();
    }

    public void getFlightDetails(String flightDetail) throws SkyControlException {
        getFlightNumber(flightDetail);
        getAirline(flightDetail);
        getDestination(flightDetail);
        getDepartureTime(flightDetail);
        getGateNumber(flightDetail);
        getCheckIn(flightDetail);
    }

    private void getFlightNumber(String detail) throws SkyControlException {
        flightNumber = extractDetail(detail, FLIGHT_NUMBER_DELIMITER, AIRLINE_DELIMITER).toUpperCase();
    }

    private void getAirline(String detail) throws SkyControlException {
        airline = extractDetail(detail, AIRLINE_DELIMITER, DESTINATION_DELIMITER).toUpperCase();
        validateAirlineLength(airline);
    }

    private void getDestination(String detail) throws SkyControlException {
        destination = extractDetail(detail, DESTINATION_DELIMITER, DEPARTURE_TIME_DELIMITER).toUpperCase();
        validateDestinationLength(destination);
    }

    private void getDepartureTime(String detail) throws SkyControlException {
        departureTime = extractDetail(detail, DEPARTURE_TIME_DELIMITER, GATE_NUMBER_DELIMITER).toUpperCase();
        validateTime(departureTime);
    }

    private void getGateNumber(String detail) throws SkyControlException {
        gateNumber = extractDetail(detail, GATE_NUMBER_DELIMITER, CHECK_IN_ROW_DELIMITER).toUpperCase();
    }

    private void getCheckIn(String detail) throws SkyControlException {
        int indexOfCheckIn = detail.indexOf(CHECK_IN_ROW_DELIMITER);
        int startIndex = indexOfCheckIn + CHECK_IN_ROW_DELIMITER.length();
        checkIn = detail.substring(startIndex).toUpperCase();
        if (checkIn.equals(EMPTY_STRING)) {
            throw new SkyControlException(ui.getMissingDetailsError());
        }
        validateCheckIn(checkIn);
    }

    private void checkFlightNumberDuplicates() throws SkyControlException {
        for (int i = 0; i < flightIndex; i++) {
            validateFlight(i);
            if (isFlightDuplicate()) {
                resetChecks();
                throw new SkyControlException(ui.getDuplicateFlightError());
            }
        }
    }

    private void checkAvailableGateNumber() throws SkyControlException {
        for (int i = 0; i < flightIndex; i++) {
            validateFlight(i);
            if (isGateOccupied()) {
                resetChecks();
                throw new SkyControlException(ui.getGateOccupiedError());
            }
        }
    }

    private void validateFlight(int index) {
        getNumberOfFlights();
        assert index < numOfFlights;
        isFlightNumberPresent = flights.get(index).getFlightNumber().contains(flightNumber);
        isDepartureTimePresent = flights.get(index).getDepartureTime().contains(departureTime);
        isGateNumberPresent = flights.get(index).getGateNum().contains(gateNumber);
    }

    private boolean isFlightDuplicate() {
        boolean isFlightDuplicate;
        isFlightDuplicate = isFlightNumberPresent;
        return isFlightDuplicate;
    }

    private boolean isGateOccupied() {
        boolean isGateOccupied;
        isGateOccupied = isDepartureTimePresent && isGateNumberPresent;
        return isGateOccupied;
    }

    private void validateAirlineLength(String airline) throws SkyControlException {
        if (airline.length() > AIRLINE_LENGTH_LIMIT) {
            throw new SkyControlException(ui.getExceedAirlineLengthError(airline));
        }
    }

    private void validateDestinationLength(String destination) throws SkyControlException {
        if (destination.length() > DESTINATION_LENGTH_LIMIT) {
            throw new SkyControlException(ui.getExceedDestinationLengthError(destination));
        }
    }

    private void validateTime(String time) throws SkyControlException {
        Pattern p = Pattern.compile(timeRegex);
        Matcher m = p.matcher(time);
        if (!m.matches()) {
            throw new SkyControlException(ui.getDepartureTimeError());
        }
    }

    private void validateCheckIn(String checkIn) throws SkyControlException {
        Pattern p = Pattern.compile(checkInRegex);
        Matcher m = p.matcher(checkIn);
        if (!m.matches()) {
            throw new SkyControlException(ui.getCheckInFormatError());
        }
    }

    private void resetChecks() {
        isFlightNumberPresent = false;
        isGateNumberPresent = false;
        isDepartureTimePresent = false;
    }

}