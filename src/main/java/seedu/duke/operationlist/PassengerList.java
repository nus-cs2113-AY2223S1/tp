package seedu.duke.operationlist;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerList extends OperationList {
    protected static final String REGEX_LETTER = "[a-zA-Z ]*";
    protected static final String REGEX_NUMBER = "[0-9]*";
    protected static final String REGEX_TIME = "([01]\\d|2[0-3])[0-5]\\d";
    protected static final String NAME_DELIMITER = " n/";
    protected static final String DEPARTURE_TIME_DELIMITER = " dt/";
    protected static final String FLIGHT_NUMBER_DELIMITER = " fn/";
    protected static final String GATE_NUMBER_DELIMITER = " gn/";
    protected static final String BOARDING_GROUP_DELIMITER = " bg/";
    protected static final String SEAT_NUMBER_DELIMITER = " sn/";
    protected static final String BOARDING_TIME_DELIMITER = " bt/";
    protected static final String FORWARD_SLASH = "/";
    protected static final String EMPTY_STRING = "";
    public static final int NO_PASSENGER = 0;
    protected static final int NAME_LENGTH_LIMIT = 24;
    protected static final int FN_MIN_LENGTH = 4;
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int MIN_FN_LENGTH = 2;
    public static final int MAX_FN_LENGTH = 4;
    public static final int INVALID_NUMBER = 0;
    public static final int SEAT_NUM_SECOND_INDEX = 1;
    public static final int SEAT_NUM_FIRST_INDEX = 0;
    public static final int SEAT_NUM_THIRD_INDEX = 2;
    protected boolean isValidPassenger;
    protected boolean isDeleteSuccess;
    protected boolean isExceedNameLength;
    protected boolean isWrongNameFormat;
    protected boolean isWrongBoardingTimeFormat;
    protected boolean isWrongBoardingGroupFormat;
    protected boolean isWrongSeatNumberFormat;
    protected String name;
    protected String departureDate;
    protected String departureTime;
    protected String flightNumber;
    protected String gateNumber;
    protected String seatNumber;
    protected String boardingTime;
    protected int numOfPassengers;
    protected int boardingGroup;
    protected int startIndex;

    public PassengerList() {
        this.isValidPassenger = false;
        this.isDeleteSuccess = false;
        this.isExceedNameLength = false;
        this.isWrongNameFormat = false;
        this.isWrongBoardingTimeFormat = false;
        this.isWrongBoardingGroupFormat = false;
        this.isWrongSeatNumberFormat = false;
        this.name = null;
        this.departureDate = null;
        this.departureTime = null;
        this.flightNumber = null;
        this.gateNumber = null;
        this.seatNumber = null;
        this.boardingTime = null;
        this.numOfPassengers = 0;
        this.boardingGroup = 0;
        this.startIndex = 0;

    }

    //@@author ivanthengwr
    public boolean isNamePresent(int index) {
        boolean isNamePresent;
        PassengerInfo passenger = passengers.get(index);
        String nameOfPassenger = passenger.getName();
        isNamePresent = nameOfPassenger.equals(name);
        return isNamePresent;
    }

    public boolean isFlightNumberPresent(int index) {
        boolean isFlightNumberPresent;
        PassengerInfo passenger = passengers.get(index);
        String flightNumberOfPassenger = passenger.getFlightNumber();
        isFlightNumberPresent = flightNumberOfPassenger.equals(flightNumber);
        return isFlightNumberPresent;
    }

    public boolean isSeatNumberPresent(int index) {
        boolean isSeatNumberPresent;
        PassengerInfo passenger = passengers.get(index);
        String passengerSeatNumber = passenger.getSeatNumber();
        isSeatNumberPresent = passengerSeatNumber.equals(seatNumber);
        return isSeatNumberPresent;
    }

    public void getValidPassenger(int index) {
        isValidPassenger = isNamePresent(index)
                && isFlightNumberPresent(index) && isSeatNumberPresent(index);
    }

    public void setDeleteSuccess() {
        isDeleteSuccess = true;
    }

    public void resetDeleteSuccess() {
        isDeleteSuccess = false;
    }

    public void getExceedNameLength() {
        isExceedNameLength = name.length() > NAME_LENGTH_LIMIT;
    }

    public void getWrongNameFormat() {
        isWrongNameFormat = !name.matches(REGEX_LETTER);
    }

    public void getWrongBoardingTimeFormat() {
        isWrongBoardingTimeFormat = isValidTime(boardingTime);
    }

    public void getWrongBoardingGroupFormat() {
        isWrongBoardingGroupFormat = isValidBoardingGroup();
    }

    public void isWrongSeatNumberFormat() {
        isWrongSeatNumberFormat = isValidSeatNumber();
    }

    public void getIterateName(int index) {
        name = passengers.get(index).getName();
    }

    //@@author shengiv
    private void getPassengerName(String passengerDetail) throws SkyControlException {
        if (isAdd) {
            name = getSubstringBetweenDelimiters(passengerDetail,
                    NAME_DELIMITER, FLIGHT_NUMBER_DELIMITER);
        } else if (isDelete) {
            name = getSubstringBetweenDelimiters(passengerDetail,
                    NAME_DELIMITER, FLIGHT_NUMBER_DELIMITER);
        }
    }

    //@@author ivanthengwr
    private void getDepartureDate() {
        departureDate = PassengerInfo.getDepartureDate();
    }

    public void getIterateDepartureTime(int index) {
        departureTime = passengers.get(index).getDepartureTime();
    }

    //@@author shengiv
    private void getDepartureTime(String passengerDetail) throws SkyControlException {
        departureTime = getSubstringBetweenDelimiters(passengerDetail,
                DEPARTURE_TIME_DELIMITER, GATE_NUMBER_DELIMITER);
    }

    //@@ author ivanthengwr
    public void getIterateFlightNumber(int index) {
        flightNumber = passengers.get(index).getFlightNumber();
    }


    private void getFlightNumber(String passengerDetail) throws SkyControlException {
        if (isAdd) {
            flightNumber = getSubstringBetweenDelimiters(passengerDetail,
                    FLIGHT_NUMBER_DELIMITER, BOARDING_GROUP_DELIMITER);
        } else if (isDelete) {
            flightNumber = getSubstringBetweenDelimiters(passengerDetail,
                    FLIGHT_NUMBER_DELIMITER, SEAT_NUMBER_DELIMITER);
        }
    }

    public void getIterateGateNumber(int index) {
        gateNumber = passengers.get(index).getGateNumber();
    }

    //@@author shengiv
    private void getGateNumber(String passengerDetail) throws SkyControlException {
        gateNumber = getSubstringBetweenDelimiters(passengerDetail,
                GATE_NUMBER_DELIMITER, BOARDING_TIME_DELIMITER);
    }

    //@@author ivanthengwr
    public void getIterateSeatNumber(int index) {
        seatNumber = passengers.get(index).getSeatNumber();
    }

    //@@author shengiv
    public void getSeatNumberFromDelimiter(String passengerDetail) throws SkyControlException {
        seatNumber = getSubstringBetweenDelimiters(passengerDetail,
                SEAT_NUMBER_DELIMITER, DEPARTURE_TIME_DELIMITER);
    }

    //@@author ivanthengwr
    public void getSeatNumberFromDetail(String passengerDetail) {
        seatNumber = passengerDetail.substring(startIndex).toUpperCase();
    }

    public void getIterateBoardingTime(int index) {
        boardingTime = passengers.get(index).getBoardingTime();
    }

    public void getBoardingTimeFromDetail(String passengerDetail) {
        boardingTime = passengerDetail.substring(startIndex).toUpperCase();
    }

    public void getNumberOfPassengers() {
        numOfPassengers = passengers.size();
    }

    public void getIterateBoardingGroup(int index) {
        PassengerInfo passenger = passengers.get(index);
        boardingGroup = passenger.getBoardingGroup();
    }

    public void getBoardingGroupFromDetail(String boardingGroupDetail) {
        boardingGroup = Integer.parseInt(boardingGroupDetail);
    }

    public void getStartIndex(int indexOfInterest, String delimiterOfInterest) {
        startIndex = indexOfInterest + delimiterOfInterest.length();
    }

    //@@author shengiv
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

    //@@author Franky4566
    @Override
    public void delayFlightDeparture(String flightNum, String newDepartureTime) {
        modifyPassengersDepartureTime(flightNum, newDepartureTime);
    }

    //@@author shengiv
    @Override
    public void modifyFlightNum(String flightNum, String newFlightNum) {
        modifyPassengersFlightNum(flightNum, newFlightNum);
    }

    @Override
    public void modifyGateNum(String flightNum, String newGateNum) {
        modifyPassengersGateNum(flightNum, newGateNum);
    }

    //@@author ivanthengwr
    private void checkPassengerDetails() throws SkyControlException {
        if (isExceedNameLength) {
            throw new SkyControlException(ui.getExceedNameLengthError(name));
        } else if (isWrongNameFormat) {
            throw new SkyControlException(ui.getNameError());
        } else if (isWrongBoardingTimeFormat) {
            throw new SkyControlException(ui.getErrorMessage());
        } else if (isWrongBoardingGroupFormat) {
            throw new SkyControlException(ui.getBoardingGroupError());
        } else if (isWrongSeatNumberFormat) {
            throw new SkyControlException(ui.getSeatNumberError());
        }
    }

    //@@author shengiv
    private void checkPassengerDuplicate() throws SkyControlException {
        for (int i = 0; i < numOfPassengers; i++) {
            validatePassenger(i);
            if (isPassengerDuplicate(i)) {
                throw new SkyControlException(ui.getDuplicatePassengerError());
            }
        }
    }

    private void modifyPassengersFlightNum(String flightNum, String newFlightNum) {
        getNumberOfPassengers();
        for (int i = 0; i < numOfPassengers; i++) {
            PassengerInfo passenger = passengers.get(i);
            if (passenger.getFlightNumber().equals(flightNum)) {
                passenger.setFlightNumber(newFlightNum);
            }
        }
    }

    private void modifyPassengersGateNum(String flightNum, String newGateNum) {
        getNumberOfPassengers();
        for (int i = 0; i < numOfPassengers; i++) {
            PassengerInfo passenger = passengers.get(i);
            if (passenger.getFlightNumber().equals(flightNum)) {
                passenger.setGateNumber(newGateNum);
            }
        }
    }

    //@@author Franky4566
    private void modifyPassengersDepartureTime(String flightNum, String newDepartureTime) {
        getNumberOfPassengers();
        for (int i = 0; i < numOfPassengers; i++) {
            PassengerInfo passenger = passengers.get(i);
            if (passenger.getFlightNumber().equals(flightNum)) {
                modifyDepartureAndBoardingTime(newDepartureTime, passenger);
            }
        }
    }

    //@@author ivanthengwr
    private static void modifyDepartureAndBoardingTime(String newDepartureTime, PassengerInfo passenger) {
        passenger.setDepartureTime(newDepartureTime);
        String reformatDepartureTime = passenger.reformatDepartureTime(newDepartureTime);
        String newBoardingTime = passenger.getFormattedBoardingTime(reformatDepartureTime);
        passenger.setBoardingTime(newBoardingTime);
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
                setDeleteSuccess();
                break;
            }
        }
        if (!isDeleteSuccess) {
            throw new SkyControlException(ui.getDeleteError());
        }
        resetDeleteSuccess();
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

    //@@author shengiv
    private void validatePassenger(int index) {
        getNumberOfPassengers();
        assert index < numOfPassengers;
        getValidPassenger(index);
    }

    //@@author ivanthengwr
    public void getPassengerDetails(String passengerDetail) throws SkyControlException {
        getPassengerName(passengerDetail);
        getDepartureDate();
        getFlightNumber(passengerDetail);
        getBoardingGroup(passengerDetail);
        getSeatNumber(passengerDetail);
        getDepartureTime(passengerDetail);
        getGateNumber(passengerDetail);
        getBoardingTime(passengerDetail);
    }

    //@@author shengiv
    private void iteratePassengerDetail(int index) {
        getNumberOfPassengers();
        assert index < numOfPassengers;
        getIterateName(index);
        getDepartureDate();
        getIterateDepartureTime(index);
        getIterateFlightNumber(index);
        getIterateGateNumber(index);
        getIterateBoardingGroup(index);
        getIterateSeatNumber(index);
        getIterateBoardingTime(index);
    }

    //@@author ivanthengwr
    public String getFlightNumberForSync(String passengerDetail) throws SkyControlException {
        flightNumber = getSubstringBetweenDelimiters(passengerDetail,
                FLIGHT_NUMBER_DELIMITER, BOARDING_GROUP_DELIMITER);
        if (!isValidFlightNumber(flightNumber)) {
            if (flightNumber.contains(FORWARD_SLASH)) {
                throw new SkyControlException(ui.getErrorMessage());
            } else {
                throw new SkyControlException(ui.getFlightNumberError());
            }
        }
        return flightNumber;
    }

    //@@author shengiv
    private void getBoardingGroup(String passengerDetail) throws SkyControlException {
        String boardingGroupDetail = getSubstringBetweenDelimiters(passengerDetail,
                BOARDING_GROUP_DELIMITER, SEAT_NUMBER_DELIMITER);
        try {
            getBoardingGroupFromDetail(boardingGroupDetail);
        } catch (Exception e) {
            throw new SkyControlException(ui.getBoardingGroupError());
        }
        assert boardingGroup >= 0 : "Invalid boarding group";
    }

    private void getSeatNumber(String passengerDetail) throws SkyControlException {
        if (isAdd) {
            getSeatNumberFromDelimiter(passengerDetail);
        } else if (isDelete) {
            int indexOfSeatNumber = passengerDetail.indexOf(SEAT_NUMBER_DELIMITER);
            int indexOfFlightNumber = passengerDetail.indexOf(FLIGHT_NUMBER_DELIMITER);
            getStartIndex(indexOfSeatNumber, SEAT_NUMBER_DELIMITER);
            boolean isNotCorrectFormat = indexOfSeatNumber < indexOfFlightNumber;
            boolean isSeatNumberNotFound = indexOfSeatNumber < 0;
            if (isSeatNumberNotFound || isNotCorrectFormat) {
                throw new SkyControlException(ui.getErrorMessage());
            }
            getSeatNumberFromDetail(passengerDetail);
            if (seatNumber.equals(EMPTY_STRING)) {
                throw new SkyControlException(ui.getMissingDetailsError());
            }
        }
    }

    //@@author ivanthengwr
    private void getBoardingTime(String passengerDetail) {
        int indexOfBoardingTime = passengerDetail.indexOf(BOARDING_TIME_DELIMITER);
        getStartIndex(indexOfBoardingTime, BOARDING_TIME_DELIMITER);
        getBoardingTimeFromDetail(passengerDetail);

    }

    //@@author shengiv
    private String getSubstringBetweenDelimiters(String inputString, String startDelimiter, String endDelimiter)
            throws SkyControlException {
        int indexOfStartDelimiter = inputString.indexOf(startDelimiter);
        getStartIndex(indexOfStartDelimiter, startDelimiter);
        int endIndex = inputString.lastIndexOf(endDelimiter);
        boolean isNotCorrectFormat = endIndex < startIndex;
        boolean isEndDelimiterNotFound = endIndex < 0;
        boolean isStartDelimiterNotFound = indexOfStartDelimiter < 0;
        if (isNotCorrectFormat || isEndDelimiterNotFound || isStartDelimiterNotFound) {
            throw new SkyControlException(ui.getErrorMessage());
        }
        String outputString = inputString.substring(startIndex, endIndex).trim();
        if (outputString.equals(EMPTY_STRING)) {
            throw new SkyControlException(ui.getMissingDetailsError());
        }
        return outputString.toUpperCase();
    }

    //@@author ivanthengwr
    private void validateDetailFormat() {
        getExceedNameLength();
        getWrongNameFormat();
        getWrongBoardingTimeFormat();
        getWrongBoardingGroupFormat();
        isWrongSeatNumberFormat();
    }

    private boolean isValidSeatNumber() {
        boolean isNotValidLength = seatNumber.length() != 3;
        if (isNotValidLength) {
            return true;
        }
        boolean isNotValidTag = !Character.isLetter(seatNumber.charAt(SEAT_NUM_THIRD_INDEX));
        if (isNotValidTag) {
            return true;
        }
        boolean isNotValidNumber = !Character.isDigit(seatNumber.charAt(SEAT_NUM_FIRST_INDEX))
                || !Character.isDigit(seatNumber.charAt(SEAT_NUM_SECOND_INDEX));
        if (isNotValidNumber) {
            return true;
        }
        return false;
    }

    private boolean isValidBoardingGroup() {
        String boardingGroupStr = String.valueOf(boardingGroup);
        boolean isNotValidNumber = !boardingGroupStr.matches(REGEX_NUMBER);
        boolean isExceedNumber = boardingGroup > 10;
        if (isNotValidNumber) {
            return true;
        } else {
            return isExceedNumber;
        }
    }

    private boolean isValidTime(String time) {
        Pattern format = Pattern.compile(REGEX_TIME);
        Matcher timeCheck = format.matcher(time);
        boolean isNotValidTime;
        isNotValidTime = !timeCheck.matches();
        return isNotValidTime;
    }

    private boolean isValidFlightNumber(String flightNumber) {
        int lenOfFlightNum = flightNumber.length();
        if (lenOfFlightNum < FN_MIN_LENGTH) {
            return false;
        } else if (isValidFlightNumberTag(flightNumber)) {
            int numOfDigits = checkNumOfDigits();
            return isValidFLightNum(numOfDigits);
        }
        return false;
    }

    //@@author shengiv
    private boolean isPassengerDuplicate(int index) {
        boolean isPassengerDuplicate;
        isPassengerDuplicate = isFlightNumberPresent(index)
                && isSeatNumberPresent(index);
        return isPassengerDuplicate;
    }

    //@@author ivanthengwr
    private int checkNumOfDigits() {
        int numOfDigits = 0;
        for (int i = 2; i < flightNumber.length(); i++) {
            if (Character.isDigit(flightNumber.charAt(i))) {
                numOfDigits++;
            } else {
                return INVALID_NUMBER;
            }
        }
        return numOfDigits;
    }

    private static boolean isValidFlightNumberTag(String flightNumber) {
        boolean isValidFlightNumberTag;
        isValidFlightNumberTag = Character.isLetter(flightNumber.charAt(FIRST_INDEX))
                && Character.isLetter(flightNumber.charAt(SECOND_INDEX));
        return isValidFlightNumberTag;
    }

    private static boolean isValidFLightNum(int numOfDigits) {
        boolean isValidFlightNumber;
        isValidFlightNumber = numOfDigits >= MIN_FN_LENGTH
                && numOfDigits <= MAX_FN_LENGTH;
        return isValidFlightNumber;
    }
}
