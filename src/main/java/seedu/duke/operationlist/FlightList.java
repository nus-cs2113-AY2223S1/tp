package seedu.duke.operationlist;

import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.terminalinfo.PassengerInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightList extends OperationList {
    private static final int FLIGHT_NUMBER_LETTER_LENGTH = 2;
    private static final int FLIGHT_NUMBER_MAX_LENGTH = 6;
    private static final int FLIGHT_NUMBER_MIN_LENGTH = 4;
    public static final int INITIAL_INDEX = 0;
    private static final int FLIGHT_NUMBER_INDEX = 14;
    public static int flightIndex = 0;
    private static final String FLIGHT_ADD_COMMAND = "flight add";
    private static final String FLIGHT_ADD_DELIMITER = "flight add ";
    private static final String FLIGHT_DELETE_COMMAND = "flight delete";
    private static final String FLIGHT_NUMBER_DELIMITER = " fn/";
    private static final String AIRLINE_DELIMITER = " a/";
    private static final String DESTINATION_DELIMITER = " d/";
    private static final String DEPARTURE_TIME_DELIMITER = " dt/";
    private static final String GATE_NUMBER_DELIMITER = " gn/";
    private static final String CHECK_IN_ROW_DELIMITER = " c/";
    protected static final String EMPTY_STRING = "";
    protected static final int CHECK_MISSING_DETAIL = 0;
    protected static final int AIRLINE_LENGTH_LIMIT = 22;
    protected static final int DESTINATION_LENGTH_LIMIT = 22;
    protected static boolean isFlightNumberPresent = false;
    protected static boolean isDepartureTimePresent = false;
    protected static boolean isGateNumberPresent = false;
    protected static boolean isAdd = false;
    protected static boolean isDelay = false;
    protected static boolean isModify = false;
    protected static final String TIME_REGEX = "([01]\\d|2[0-3])[0-5]\\d";
    protected static final String CHECK_IN_REGEX = "[0-9]{2}-[0-9]{2}";
    protected static final String GATE_NUM_REGEX = "^([0-9]{2}$)";
    protected String flightNumber;
    protected String airline;
    protected String destination;
    protected String departureTime;
    protected String gateNumber;
    protected String checkIn;
    protected String oldDepartureTime;

    protected static int numOfFlights = 0;

    protected PassengerList passengerList = new PassengerList();

    //@@author Franky4566

    /**
     * Checks if user has inputted command with the correct format.
     *
     * @param description user input
     * @throws SkyControlException empty description error
     */
    public static void checkCommandLength(String description) throws SkyControlException {
        if (description.isEmpty()) {
            throw new SkyControlException(ui.showEmptyDescriptionMessage());
        }
    }

    /**
     * Extracts specific flight details from user input.
     *
     * @param command user input before extraction.
     * @param start   delimiter for flight detail.
     * @param end     delimiter for next flight detail.
     * @return flight detail
     */
    public static String extractDetail(String command, String start, String end) throws SkyControlException {
        String extractedDetail;
        int startIndex = command.indexOf(start) + start.length();
        int endIndex = command.lastIndexOf(end);
        checkNoDetailsMissing(startIndex, endIndex);
        extractedDetail = command.substring(startIndex, endIndex).trim();
        if (extractedDetail.equals(EMPTY_STRING)) {
            throw new SkyControlException(ui.getMissingDetailsError());
        }
        return extractedDetail;
    }

    /**
     * Calls complex method to check if flight detail is missing.
     *
     * @param startIndex beginning index of flight detail.
     * @param endIndex   end index of flight detail.
     * @throws SkyControlException if flight detail is empty.
     */
    public static void checkNoDetailsMissing(int startIndex, int endIndex) throws SkyControlException {
        if (missingDetailsChecker(startIndex, endIndex)) {
            throw new SkyControlException(ui.getErrorMessage());
        }
    }

    /**
     * Checks if flight detail is empty.
     *
     * @param startIndex beginning index of flight detail.
     * @param endIndex   end index of flight detail.
     * @return true if detail is missing else, return false.
     */
    public static boolean missingDetailsChecker(int startIndex, int endIndex) {
        boolean isMissing = false;
        if (endIndex <= startIndex || endIndex < CHECK_MISSING_DETAIL || startIndex < CHECK_MISSING_DETAIL) {
            isMissing = true;
        }
        return isMissing;
    }

    @Override
    public void addOperation(String command) throws SkyControlException {
        setIsAdd();
        getNumberOfFlights();
        checkCommandLength(command.substring(FLIGHT_ADD_COMMAND.length()));
        getFlightDetails(command.substring(FLIGHT_ADD_DELIMITER.length()));
        validateDetailFormat();
        checkFlightNumberDuplicates();
        checkAvailableGateNumber();
        FlightInfo flight = new FlightInfo(flightNumber, airline, destination,
                departureTime, gateNumber, checkIn);
        flights.add(flightIndex, flight);
        flightIndex++;
        ui.showFlightAddedMessage();
    }

    //@@author JordanKwua
    @Override
    public void deleteOperation(String detail) throws SkyControlException {
        checkCommandLength(detail.substring(FLIGHT_DELETE_COMMAND.length()));
        checkValidFlightNumber(detail.substring(FLIGHT_NUMBER_INDEX));
        String flightNum = detail.substring(FLIGHT_NUMBER_INDEX).toUpperCase();
        findAndRemoveFlight(flightNum);
        deletePassengersOnSameFlightNumber(flightNum);
    }

    //@@author ivanthengwr

    /**
     * deletes the corresponding passengers in passengerList that shares the same flight number.
     *
     * @param flightNum a parameter that is taken from the deleteOperation function
     */
    private void deletePassengersOnSameFlightNumber(String flightNum) {
        boolean isCheckDone = false;
        int index = INITIAL_INDEX;
        while (!isCheckDone) {
            passengerList.getNumberOfPassengers();
            int lastPassenger = passengerList.getNumberOfPassengersForFlight();
            if (index == lastPassenger) {
                isCheckDone = true;
            } else if (isFlightNumberPresent(flightNum, index)) {
                passengers.remove(index);
                index = INITIAL_INDEX;
            } else {
                index++;
            }
        }
    }

    /**
     * Checks if the flight number of the passenger matches the flight number that is deleted in flight list.
     *
     * @param flightNum a parameter that is taken from the deleteOperation function
     * @param index     increment of the index in passengers
     * @return a boolean value that indicates if the flight number of the passenger
     *          matches the flight number that is deleted.
     */
    private boolean isFlightNumberPresent(String flightNum, int index) {
        boolean isFlightNumberPresent;
        PassengerInfo passenger = passengers.get(index);
        String flightNumberOfPassenger = passenger.getFlightNumber();
        isFlightNumberPresent = flightNumberOfPassenger.equals(flightNum);
        return isFlightNumberPresent;
    }

    //@@author JordanKwua

    /**
     * Checks if the flight number from the user input is a valid flight number to be added or deleted.
     *
     * @param substring the String containing the flight number to be checked
     * @throws SkyControlException if the flight number is invalid
     */
    private void checkValidFlightNumber(String substring) throws SkyControlException {
        String[] letters = substring.split("");
        assert letters.length > 0;
        if (letters.length > FLIGHT_NUMBER_MAX_LENGTH || letters.length < FLIGHT_NUMBER_MIN_LENGTH) {
            throw new SkyControlException(ui.getFlightNumberError());
        }
        for (int i = 0; i < FLIGHT_NUMBER_LETTER_LENGTH; i++) {
            if (!Character.isLetter(substring.charAt(i))) {
                throw new SkyControlException(ui.getFlightNumberError());
            }
        }
        for (int i = FLIGHT_NUMBER_LETTER_LENGTH; i < letters.length; i++) {
            if (!Character.isDigit(substring.charAt(i))) {
                throw new SkyControlException(ui.getFlightNumberError());
            }
        }
    }

    //@@author shengiv
    @Override
    public void modifyFlightNum(String flightNum, String newFlightNum) throws SkyControlException {
        setIsModify();
        getNumberOfFlights();
        FlightInfo flight = findFlightInfo(flightNum);
        getFlightAttributes(flight);
        flightNumber = newFlightNum;
        validateModificationDetails(flight);
        flight.setFlightNum(newFlightNum);
        flights.add(flight);
        flightIndex++;
        ui.showUpdatedFlightNumber(flightNum, newFlightNum);
    }

    @Override
    public void modifyGateNum(String flightNum, String newGateNum) throws SkyControlException {
        setIsModify();
        getNumberOfFlights();
        FlightInfo flight = findFlightInfo(flightNum);
        getFlightAttributes(flight);
        gateNumber = newGateNum;
        validateModificationDetails(flight);
        flight.setGateNum(newGateNum);
        flights.add(flight);
        flightIndex++;
        ui.showUpdatedGateNumber(flightNum, newGateNum);
    }

    private void getFlightAttributes(FlightInfo flight) {
        flightNumber = flight.getFlightNumber();
        airline = flight.getAirline();
        destination = flight.getDestination();
        departureTime = flight.getDepartureTime();
        gateNumber = flight.getGateNum();
        checkIn = flight.getCheckLn();
    }

    /**
     * Retrieves the FlightInfo object using flight number and temporarily removes it from the flight list.
     *
     * @param flightNum Flight number of flight being modified.
     * @return FlightInfo object which is being modified
     * @throws SkyControlException If the flight number is not found in flight list
     */
    private static FlightInfo findFlightInfo(String flightNum) throws SkyControlException {
        FlightInfo modifiedFlight = null;
        for (FlightInfo flight : flights) {
            if (flight.getFlightNumber().equals(flightNum)) {
                modifiedFlight = flight;
                flights.remove(flight);
                flightIndex--;
                break;
            }
        }
        if (modifiedFlight == null) {
            throw new SkyControlException(ui.getFlightNotFoundMessage(flightNum));
        } else {
            return modifiedFlight;
        }
    }

    /**
     * Checks if the modified flight details produce any discrepancies or format errors.
     *
     * @param flight FlightInfo object of modified flight.
     * @throws SkyControlException If the new FlightInfo object does not follow the correct format for parameters.
     */
    private void validateModificationDetails(FlightInfo flight) throws SkyControlException {
        try {
            validateDetailFormat();
            checkFlightNumberDuplicates();
            checkAvailableGateNumber();
            if (isDelay) {
                checkDelayTime();
            }
        } catch (Exception e) {
            flights.add(flight);
            flightIndex++;
            throw new SkyControlException(e.getMessage());
        }
    }

    private void setIsAdd() {
        isAdd = true;
        isDelay = false;
        isModify = false;
    }

    private void setIsDelay() {
        isAdd = false;
        isDelay = true;
        isModify = false;
    }

    private void setIsModify() {
        isAdd = false;
        isDelay = false;
        isModify = true;
    }

    //@@author Franky4566
    @Override
    public void listOperation() {
        ui.showListOfFlights(flights);
    }

    //@@author JordanKwua

    /**
     * Searches through the entire list of flights to find if the specified flight number exists,
     * and removes it from the list if it exists.
     *
     * @param flightNumber the String containing the flight number of the flight to be removed
     * @throws SkyControlException if the flight is not found within the list
     */
    private void findAndRemoveFlight(String flightNumber) throws SkyControlException {
        getNumberOfFlights();
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
        if (!isFlightFound) {
            throw new SkyControlException(ui.getFlightNotFoundMessage(flightNumber));
        }
    }

    //@@author Franky4566
    @Override
    public void delayFlightDeparture(String flightNum, String newDepartureTime) throws SkyControlException {
        setIsDelay();
        getNumberOfFlights();
        FlightInfo flight = findFlightInfo(flightNum);
        getFlightAttributes(flight);
        oldDepartureTime = departureTime;
        departureTime = newDepartureTime;
        validateModificationDetails(flight);
        flight.setDepartureTime(newDepartureTime);
        flights.add(flight);
        flightIndex++;
        ui.showUpdatedDepartureTime(flightNum, oldDepartureTime, newDepartureTime);
    }

    /**
     * Checks if new delayed departure time is later than the original departure time.
     *
     * @throws SkyControlException if it is earlier than the orginal departure time.
     */
    private void checkDelayTime() throws SkyControlException {
        if (Integer.parseInt(oldDepartureTime) > Integer.parseInt(departureTime)) {
            throw new SkyControlException(ui.getWrongDelayTimeError(flightNumber, oldDepartureTime));
        }
    }

    /**
     * Gets number of flights within flight logbook.
     */
    public void getNumberOfFlights() {
        assert numOfFlights >= 0;
        flightIndex = flights.size();
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
        if (isAdd) {
            flightNumber = extractDetail(detail, FLIGHT_NUMBER_DELIMITER, AIRLINE_DELIMITER).toUpperCase();
        }
    }

    private void getAirline(String detail) throws SkyControlException {
        airline = extractDetail(detail, AIRLINE_DELIMITER, DESTINATION_DELIMITER).toUpperCase();
    }

    private void getDestination(String detail) throws SkyControlException {
        destination = extractDetail(detail, DESTINATION_DELIMITER, DEPARTURE_TIME_DELIMITER).toUpperCase();
    }

    private void getDepartureTime(String detail) throws SkyControlException {
        departureTime = extractDetail(detail, DEPARTURE_TIME_DELIMITER, GATE_NUMBER_DELIMITER).toUpperCase();
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
    }

    private void validateDetailFormat() throws SkyControlException {
        validateAirlineLength(airline);
        validateDestinationLength(destination);
        validateTime(departureTime);
        validateCheckIn(checkIn);
        validateGateNumber(gateNumber);
        checkValidFlightNumber(flightNumber);
    }

    /**
     * Checks if a flight number is already present.
     *
     * @throws SkyControlException if flight number is present and that there should be no duplicates.
     */
    private void checkFlightNumberDuplicates() throws SkyControlException {
        getNumberOfFlights();
        for (int i = 0; i < flightIndex; i++) {
            validateFlight(i);
            if (isFlightDuplicate()) {
                resetChecks();
                if (isModify) {
                    throw new SkyControlException(ui.getDuplicateModifyFlightError());
                } else {
                    throw new SkyControlException(ui.getDuplicateFlightError());
                }
            }
        }
    }

    /**
     * Check if the same gate number is occupied at a specific time.
     *
     * @throws SkyControlException if gate is occupied and cannot be used.
     */
    private void checkAvailableGateNumber() throws SkyControlException {
        getNumberOfFlights();
        for (int i = 0; i < flightIndex; i++) {
            validateFlight(i);
            if (isGateOccupied()) {
                resetChecks();
                if (isModify) {
                    throw new SkyControlException(ui.getDuplicateModifyGateError());
                } else {
                    throw new SkyControlException(ui.getGateOccupiedError());
                }
            }
        }
    }

    /**
     * Validates flight details and makes sure they are correct.
     *
     * @param index index of flight.
     */
    private void validateFlight(int index) {
        getNumberOfFlights();
        assert index < numOfFlights;
        checkFlightNumberExists(index);
        checkDepartureTimeExists(index);
        checkGateNumberExists(index);
    }

    private void checkFlightNumberExists(int index) {
        isFlightNumberPresent = flights.get(index).getFlightNumber().equals(flightNumber);
    }

    private void checkDepartureTimeExists(int index) {
        isDepartureTimePresent = flights.get(index).getDepartureTime().contains(departureTime);
    }

    private void checkGateNumberExists(int index) {
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

    /**
     * Validates that the airline specified can fit into the logbook table.
     *
     * @param airline airline name
     * @throws SkyControlException if number of characters is exceeded.
     */
    private void validateAirlineLength(String airline) throws SkyControlException {
        if (airline.length() > AIRLINE_LENGTH_LIMIT) {
            throw new SkyControlException(ui.getExceedAirlineLengthError(airline));
        }
    }

    /**
     * Validates that the destination specified can fit into the logbook table.
     *
     * @param destination destination of flight.
     * @throws SkyControlException if number of characters is exceeded.
     */
    private void validateDestinationLength(String destination) throws SkyControlException {
        if (destination.length() > DESTINATION_LENGTH_LIMIT) {
            throw new SkyControlException(ui.getExceedDestinationLengthError(destination));
        }
    }

    /**
     * Ensures time entered is in 24HR format.
     *
     * @param time input time.
     * @throws SkyControlException if time does not follow 24HR format.
     */
    private void validateTime(String time) throws SkyControlException {
        Pattern p = Pattern.compile(TIME_REGEX);
        Matcher m = p.matcher(time);
        if (!m.matches()) {
            throw new SkyControlException(ui.getDepartureTimeError());
        }
    }

    /**
     * Ensures check in row/door entered are valid digits.
     *
     * @param checkIn input check-in number.
     * @throws SkyControlException if check in value does not follow row-door format.
     */
    private void validateCheckIn(String checkIn) throws SkyControlException {
        Pattern p = Pattern.compile(CHECK_IN_REGEX);
        Matcher m = p.matcher(checkIn);
        if (!m.matches()) {
            throw new SkyControlException(ui.getCheckInFormatError());
        }
    }

    /**
     * Ensures the gate number entered is a valid gate.
     *
     * @param gateNumber input gate number.
     * @throws SkyControlException if gate number is not valid.
     */
    private void validateGateNumber(String gateNumber) throws SkyControlException {
        Pattern p = Pattern.compile(GATE_NUM_REGEX);
        Matcher m = p.matcher(gateNumber);
        if (!m.matches()) {
            throw new SkyControlException(ui.getGateNumberError());
        }
    }

    private void resetChecks() {
        isFlightNumberPresent = false;
        isGateNumberPresent = false;
        isDepartureTimePresent = false;
    }

}