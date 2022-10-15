package seedu.duke.operationlist;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

public class PassengerList extends OperationList {
    protected static final String NAME_DELIMITER = "n/";
    protected static final String DEPARTURE_DATE_DELIMITER = " dod/";
    protected static final String DEPARTURE_TIME_DELIMITER = " dt/";
    protected static final String FLIGHT_NUMBER_DELIMITER = " fn/";
    protected static final String GATE_NUMBER_DELIMITER = " gn/";
    protected static final String BOARDING_GROUP_DELIMITER = " bg/";
    protected static final String SEAT_NUMBER_DELIMITER = " sn/";
    protected static final String BOARDING_TIME_DELIMITER = " bt/";
    public static final int NO_PASSENGER = 0;
    protected static boolean isNamePresent = false;
    protected static boolean isFlightNumberPresent = false;
    protected static boolean isSeatNumberPresent = false;
    protected static boolean isDepartureTimePresent = false;
    protected static boolean isSuccess = false;
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
        for (int i = 0; i < numOfPassengers; i++) {
            validatePassenger(i);
            if (isFlightNumberPresent && isSeatNumberPresent && isDepartureTimePresent) {
                throw new SkyControlException(ui.getDuplicatePassengerError());
            }
        }
        getPassengerDetails(passengerDetail);
        PassengerInfo passenger = new PassengerInfo(name, departureDate,
                departureTime, flightNumber, gateNumber, boardingGroup, seatNumber, boardingTime);
        passengers.add(passenger);
        Ui.showAddedPassenger(passenger);
    }
    //make sure all fields are present in the correct sequence

    @Override
    public void deleteOperation(String passengerDetail) throws SkyControlException {
        getNumberOfPassengers();
        getPassengerName(passengerDetail);
        getFlightNumber(passengerDetail);
        getSeatNumber(passengerDetail);
        for (int i = 0; i < numOfPassengers; i++) {
            validatePassenger(i);
            if (isNamePresent && isFlightNumberPresent && isSeatNumberPresent && isDepartureTimePresent) {
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
    }

    public void getNumberOfPassengers() {
        numOfPassengers = passengers.size();
    }

    public void getPassengerDetails(String passengerDetail) {
        getPassengerName(passengerDetail);
        getDepartureDate(passengerDetail);
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
        departureDate = passengers.get(index).getDepartureDate();
        departureTime = passengers.get(index).getDepartureTime();
        flightNumber = passengers.get(index).getFlightNumber();
        gateNumber = passengers.get(index).getGateNumber();
        boardingGroup = passengers.get(index).getBoardingGroup();
        seatNumber = passengers.get(index).getSeatNumber();
        boardingTime = passengers.get(index).getBoardingTime();
    }

    private void getPassengerName(String passengerDetail) {
        if (isAdd) {
            name = getSubstringBetweenDelimiters(passengerDetail,
                    NAME_DELIMITER, DEPARTURE_DATE_DELIMITER);
        } else if (isDelete) {
            name = getSubstringBetweenDelimiters(passengerDetail,
                    NAME_DELIMITER, FLIGHT_NUMBER_DELIMITER);
        }
    }

    private void getDepartureDate(String passengerDetail) {
        departureDate = getSubstringBetweenDelimiters(passengerDetail,
                DEPARTURE_DATE_DELIMITER, DEPARTURE_TIME_DELIMITER);
    }

    private void getDepartureTime(String passengerDetail) {
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

    private void getFlightNumber(String passengerDetail) {
        if (isAdd) {
            flightNumber = getSubstringBetweenDelimiters(passengerDetail,
                    FLIGHT_NUMBER_DELIMITER, GATE_NUMBER_DELIMITER);
        } else if (isDelete) {
            flightNumber = getSubstringBetweenDelimiters(passengerDetail,
                    FLIGHT_NUMBER_DELIMITER, SEAT_NUMBER_DELIMITER);
        }
    }

    private void getGateNumber(String passengerDetail) {
        gateNumber = getSubstringBetweenDelimiters(passengerDetail,
                GATE_NUMBER_DELIMITER, BOARDING_GROUP_DELIMITER);
    }

    private void getBoardingGroup(String passengerDetail) {
        String boardingGroupDetail = getSubstringBetweenDelimiters(passengerDetail,
                BOARDING_GROUP_DELIMITER, SEAT_NUMBER_DELIMITER);
        boardingGroup = Integer.parseInt(boardingGroupDetail);
        assert boardingGroup >= 0;
    }

    private void getSeatNumber(String passengerDetail) {
        if (isAdd) {
            seatNumber = getSubstringBetweenDelimiters(passengerDetail,
                    SEAT_NUMBER_DELIMITER, BOARDING_TIME_DELIMITER);
        } else if (isDelete) {
            seatNumber = getSubstringBetweenDelimiters(passengerDetail,
                    SEAT_NUMBER_DELIMITER, DEPARTURE_TIME_DELIMITER);
        }
    }

    private void getBoardingTime(String passengerDetail) {
        startIndex = passengerDetail.indexOf(BOARDING_TIME_DELIMITER)
                + BOARDING_TIME_DELIMITER.length();
        assert startIndex >= 0;
        boardingTime = passengerDetail.substring(startIndex).toUpperCase();
    }

    private String getSubstringBetweenDelimiters(String inputString, String startDelimiter, String endDelimiter) {
        int startIndex = inputString.indexOf(startDelimiter) + startDelimiter.length();
        int endIndex = inputString.lastIndexOf(endDelimiter);
        String outputString = inputString.substring(startIndex, endIndex);
        return outputString.toUpperCase();
    }
}
