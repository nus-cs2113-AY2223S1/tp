package seedu.duke.operationlist;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

public class PassengerList extends OperationList {
    protected static final String REGEX_LETTER = "[a-zA-Z ]*";
    protected static final String REGEX_NUMBER = "[0-9]*";
    protected static final String NAME_DELIMITER = "n/";
    protected static final String DEPARTURE_TIME_DELIMITER = " dt/";
    protected static final String FLIGHT_NUMBER_DELIMITER = " fn/";
    protected static final String GATE_NUMBER_DELIMITER = " gn/";
    protected static final String BOARDING_GROUP_DELIMITER = " bg/";
    protected static final String SEAT_NUMBER_DELIMITER = " sn/";
    protected static final String BOARDING_TIME_DELIMITER = " bt/";
    protected static final String EMPTY_STRING = "";
    public static final int NO_PASSENGER = 0;
    protected static final int NAME_LENGTH_LIMIT = 24;
    protected static final int FN_MIN_LENGTH = 4;
    protected static final int TIME_FORMAT_LENGTH = 4;
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int MIN_FN_LENGTH = 2;
    public static final int MAX_FN_LENGTH = 4;
    protected static boolean isNamePresent = false;
    protected static boolean isFlightNumberPresent = false;
    protected static boolean isSeatNumberPresent = false;
    protected static boolean isDepartureTimePresent = false;
    protected static boolean isValidPassenger = false;
    protected static boolean isSuccess = false;
    protected static boolean isExceedNameLength = false;
    protected static boolean isWrongNameFormat = false;
    protected static boolean isWrongDepartureFormat = false;
    protected static boolean isWrongFlightNumFormat = false;
    protected static boolean isWrongBoardingTimeFormat = false;
    protected String name;
    protected String departureDate;
    protected String departureTime;
    protected String flightNumber;
    protected String gateNumber;
    protected String seatNumber;
    protected String boardingTime;
    protected int numOfPassengers = 0;
    protected int boardingGroup;
    protected static int startIndex;

    public PassengerList() {
    }

    @Override
    public void addOperation(String passengerDetail) throws SkyControlException {
        getNumberOfPassengers();
        getPassengerDetails(passengerDetail);
        validateDetailFormat();
        checkPassengerDetails();
        checkPassengerDuplicate();
        PassengerInfo passenger = new PassengerInfo(name, departureTime, flightNumber,
                gateNumber, boardingGroup, seatNumber, boardingTime);
        passengers.add(passenger);
        Ui.showAddedPassenger(passenger);
    }

    private void checkPassengerDetails() throws SkyControlException {
        if (isExceedNameLength) {
            resetCheckFormat();
            throw new SkyControlException(ui.getExceedNameLengthError(name));
        } else if (isWrongNameFormat) {
            resetCheckFormat();
            throw new SkyControlException(ui.getNameError());
        } else if (isWrongDepartureFormat) {
            resetCheckFormat();
            throw new SkyControlException(ui.getDepartureTimeError());
        } else if (isWrongFlightNumFormat) {
            resetCheckFormat();
            throw new SkyControlException(ui.getFlightNumberError());
        } else if (isWrongBoardingTimeFormat) {
            throw new SkyControlException(ui.getBoardingTimeError());
        }
    }

    private void checkPassengerDuplicate() throws SkyControlException {
        for (int i = 0; i < numOfPassengers; i++) {
            validatePassenger(i);
            if (isPassengerDuplicate()) {
                resetCheckDuplicate();
                throw new SkyControlException(ui.getDuplicatePassengerError());
            }
        }
    }

    @Override
    public void deleteOperation(String passengerDetail) throws SkyControlException {
        getNumberOfPassengers();
        getPassengerName(passengerDetail);
        getFlightNumber(passengerDetail);
        getSeatNumber(passengerDetail);
        for (int i = 0; i < numOfPassengers; i++) {
            validatePassenger(i);
            if (isValidPassenger) {
                passengers.remove(i);
                getNumberOfPassengers();
                Ui.showDeleteMessage(name, flightNumber, seatNumber, numOfPassengers);
                isSuccess = true;
                break;
            }
        }
        if (!isSuccess) {
            throw new SkyControlException(ui.getDeleteError());
        }
        isSuccess = false;
    }

    @Override
    public void listOperation() {
        getNumberOfPassengers();
        ui.showPassengerListHeader();
        if (numOfPassengers == NO_PASSENGER) {
            ui.showEmptyPassengerList();
        }
        for (int i = 0; i < numOfPassengers; i++) {
            iteratePassengerDetail(i);
            ui.showPassengerListBody(name, departureDate, departureTime,
                    flightNumber, gateNumber, boardingGroup, seatNumber, boardingTime);
        }
        System.out.print("\n");
    }

    private void validatePassenger(int index) {
        getNumberOfPassengers();
        assert index < numOfPassengers;
        isNamePresent = passengers.get(index).getName().contains(name);
        isFlightNumberPresent = passengers.get(index).getFlightNumber().contains(flightNumber);
        isSeatNumberPresent = passengers.get(index).getSeatNumber().contains(seatNumber);
        isDepartureTimePresent = passengers.get(index).getDepartureTime().contains(departureTime);
        isValidPassenger = isNamePresent && isFlightNumberPresent && isSeatNumberPresent && isDepartureTimePresent;
    }

    public void getNumberOfPassengers() {
        numOfPassengers = passengers.size();
    }

    public void getPassengerDetails(String passengerDetail) throws SkyControlException {
        getPassengerName(passengerDetail);
        getDepartureDate();
        getDepartureTime(passengerDetail);
        getFlightNumber(passengerDetail);
        getGateNumber(passengerDetail);
        getBoardingGroup(passengerDetail);
        getSeatNumber(passengerDetail);
        getBoardingTime(passengerDetail);
    }

    private void iteratePassengerDetail(int index) {
        getNumberOfPassengers();
        assert index < numOfPassengers;
        name = passengers.get(index).getName();
        departureDate = PassengerInfo.getDepartureDate();
        departureTime = passengers.get(index).getDepartureTime();
        flightNumber = passengers.get(index).getFlightNumber();
        gateNumber = passengers.get(index).getGateNumber();
        boardingGroup = passengers.get(index).getBoardingGroup();
        seatNumber = passengers.get(index).getSeatNumber();
        boardingTime = passengers.get(index).getBoardingTime();
    }

    private void getPassengerName(String passengerDetail) throws SkyControlException {
        if (isAdd) {
            name = getSubstringBetweenDelimiters(passengerDetail,
                    NAME_DELIMITER, DEPARTURE_TIME_DELIMITER);
        } else if (isDelete) {
            name = getSubstringBetweenDelimiters(passengerDetail,
                    NAME_DELIMITER, FLIGHT_NUMBER_DELIMITER);
        }
    }

    private void getDepartureDate() {
        departureDate = PassengerInfo.getDepartureDate();
    }

    private void getDepartureTime(String passengerDetail) throws SkyControlException {
        if (isAdd) {
            departureTime = getSubstringBetweenDelimiters(passengerDetail,
                    DEPARTURE_TIME_DELIMITER, FLIGHT_NUMBER_DELIMITER);
        } else if (isDelete) {
            startIndex = passengerDetail.indexOf(DEPARTURE_TIME_DELIMITER)
                    + DEPARTURE_TIME_DELIMITER.length();
            assert startIndex >= 0;
            departureTime = passengerDetail.substring(startIndex);
            departureTime = departureTime.toUpperCase();
        }
    }

    private void getFlightNumber(String passengerDetail) throws SkyControlException {
        if (isAdd) {
            flightNumber = getSubstringBetweenDelimiters(passengerDetail,
                    FLIGHT_NUMBER_DELIMITER, GATE_NUMBER_DELIMITER);
        } else if (isDelete) {
            flightNumber = getSubstringBetweenDelimiters(passengerDetail,
                    FLIGHT_NUMBER_DELIMITER, SEAT_NUMBER_DELIMITER);
        }
    }

    private void getGateNumber(String passengerDetail) throws SkyControlException {
        gateNumber = getSubstringBetweenDelimiters(passengerDetail,
                GATE_NUMBER_DELIMITER, BOARDING_GROUP_DELIMITER);
    }

    private void getBoardingGroup(String passengerDetail) throws SkyControlException {
        String boardingGroupDetail = getSubstringBetweenDelimiters(passengerDetail,
                BOARDING_GROUP_DELIMITER, SEAT_NUMBER_DELIMITER);
        try {
            boardingGroup = Integer.parseInt(boardingGroupDetail);
        } catch (Exception e) {
            throw new SkyControlException(ui.getErrorMessage());
        }
        assert boardingGroup >= 0 : "Invalid boarding group";
    }

    private void getSeatNumber(String passengerDetail) throws SkyControlException {
        if (isAdd) {
            seatNumber = getSubstringBetweenDelimiters(passengerDetail,
                    SEAT_NUMBER_DELIMITER, BOARDING_TIME_DELIMITER);
        } else if (isDelete) {
            seatNumber = getSubstringBetweenDelimiters(passengerDetail,
                    SEAT_NUMBER_DELIMITER, DEPARTURE_TIME_DELIMITER);
        }
    }

    private void getBoardingTime(String passengerDetail) throws SkyControlException {
        int indexOfBoardingTime = passengerDetail.indexOf(BOARDING_TIME_DELIMITER);
        int indexOfSeatNumber = passengerDetail.indexOf(SEAT_NUMBER_DELIMITER);
        if (indexOfBoardingTime < 0 || indexOfBoardingTime < indexOfSeatNumber) {
            throw new SkyControlException(ui.getErrorMessage());
        }
        startIndex = indexOfBoardingTime + BOARDING_TIME_DELIMITER.length();
        boardingTime = passengerDetail.substring(startIndex).toUpperCase();
        if (boardingTime.equals(EMPTY_STRING)) {
            throw new SkyControlException(ui.getMissingDetailsError());
        }
    }

    private String getSubstringBetweenDelimiters(String inputString, String startDelimiter, String endDelimiter)
            throws SkyControlException {
        int startIndex = inputString.indexOf(startDelimiter) + startDelimiter.length();
        int endIndex = inputString.lastIndexOf(endDelimiter);
        if (endIndex <= startIndex || endIndex < 0 || startIndex < 0) {
            throw new SkyControlException(ui.getErrorMessage());
        }
        String outputString = inputString.substring(startIndex, endIndex).trim();
        if (outputString.equals(EMPTY_STRING)) {
            throw new SkyControlException(ui.getMissingDetailsError());
        }
        return outputString.toUpperCase();
    }

    private void validateDetailFormat() {
        isExceedNameLength = name.length() > NAME_LENGTH_LIMIT;
        isWrongNameFormat = !name.matches(REGEX_LETTER);
        isWrongDepartureFormat = (departureTime.length() != TIME_FORMAT_LENGTH)
                || !departureTime.matches(REGEX_NUMBER);
        isWrongFlightNumFormat = isValidFlightNumber();
        isWrongBoardingTimeFormat = (boardingTime.length() != TIME_FORMAT_LENGTH)
                || !boardingTime.matches(REGEX_NUMBER);
    }

    private boolean isValidFlightNumber() {
        int lenOfFlightNum = flightNumber.length();
        if (lenOfFlightNum < FN_MIN_LENGTH) {
            return isWrongFlightNumFormat = true;
        } else if (isValidFlightNumberTag()) {
            int numOfDigits = checkNumOfDigits();
            if (isValidFLightNum(numOfDigits)) {
                return isWrongFlightNumFormat;
            }
        }
        return isWrongFlightNumFormat;
    }

    private boolean isPassengerDuplicate() {
        boolean isPassengerDuplicate;
        isPassengerDuplicate = isFlightNumberPresent
                && isSeatNumberPresent && isDepartureTimePresent;
        return isPassengerDuplicate;
    }

    private int checkNumOfDigits() {
        int numOfDigits = 0;
        for (int i = 0; i < flightNumber.length(); i++) {
            if (Character.isDigit(flightNumber.charAt(i))) {
                numOfDigits++;
            }
        }
        return numOfDigits;
    }

    private boolean isValidFlightNumberTag() {
        boolean isValidFlightNumberTag;
        isValidFlightNumberTag = Character.isLetter(flightNumber.charAt(FIRST_INDEX))
                && Character.isLetter(flightNumber.charAt(SECOND_INDEX));
        return isValidFlightNumberTag;
    }

    private boolean isValidFLightNum(int numOfDigits) {
        boolean isValidFlightNumber;
        isValidFlightNumber = numOfDigits >= MIN_FN_LENGTH
                && numOfDigits <= MAX_FN_LENGTH;
        return isValidFlightNumber;
    }

    private void resetCheckFormat() {
        isExceedNameLength = false;
        isWrongNameFormat = false;
        isWrongDepartureFormat = false;
        isWrongFlightNumFormat = false;
        isWrongBoardingTimeFormat = false;
    }

    private void resetCheckDuplicate() {
        isFlightNumberPresent = false;
        isSeatNumberPresent = false;
        isDepartureTimePresent = false;
    }


}
