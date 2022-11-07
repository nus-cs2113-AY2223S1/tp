package seedu.duke.operationlist;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public abstract class OperationList {
    public static final int EMPTY_FLIGHT_LIST = 0;
    protected static ArrayList<FlightInfo> flights = new ArrayList<>();
    protected static ArrayList<PassengerInfo> passengers = new ArrayList<>();
    protected static Ui ui = new Ui();
    protected static boolean isFlightNumberSync = false;
    protected static boolean isEmptyFlightList = false;
    protected static String flightNumber;

    public ArrayList<PassengerInfo> getPassengers() {
        return passengers;
    }

    public ArrayList<FlightInfo> getFlights() {
        return flights;
    }

    public void checkPassengerFlightSync(OperationList flights,
                                         String passengerDetail) throws SkyControlException, SyncException {
        ArrayList<FlightInfo> flightsThatExist;
        flightsThatExist = flights.getFlights();
        getFlightSyncDetails(passengerDetail, flightsThatExist);
        executeCheck(flightsThatExist);
    }

    /**
     * Uses the passengerDetail String to retrieve flight number of the passenger. Then the departure
     * time is retrieved for that particular flight from the flight list.
     *
     * @param flights Existing list of flights.
     * @param passengerDetail User input where the command is removed and only passenger details are present.
     * @return departure time of passenger.
     * @throws SkyControlException If there are any errors retrieving flight number from passengerDetail or if
     *         flight number is invalid.
     */
    public static String getPassengerDepartureTime(OperationList flights,
                                                   String passengerDetail) throws SkyControlException {
        String departureTime = null;
        String flightNumber = PassengerList.getFlightNumberForSync(passengerDetail);
        ArrayList<FlightInfo> flightsInFlightList = flights.getFlights();
        for (FlightInfo flight : flightsInFlightList) {
            if (flightNumber.equalsIgnoreCase(flight.getFlightNumber())) {
                departureTime = flight.getDepartureTime();
            }
        }
        return departureTime;
    }

    public static String getPassengerGateNumber(OperationList flights,
                                                String passengerDetail) throws SkyControlException {
        String gateNumber = null;
        String flightNumber = PassengerList.getFlightNumberForSync(passengerDetail);
        ArrayList<FlightInfo> flightsInFlightList = flights.getFlights();
        for (FlightInfo flight : flightsInFlightList) {
            if (flightNumber.equalsIgnoreCase(flight.getFlightNumber())) {
                gateNumber = flight.getGateNum();
            }
        }
        return gateNumber;
    }

    /**
     * Execute a check to see if the relevant flight number exist in the flight list.
     *
     * @param flightsThatExist boolean variable that sees if a flight exist or not
     * @throws SyncException an error if the flight number does not exist in the flight list.
     */
    private void executeCheck(ArrayList<FlightInfo> flightsThatExist) throws SyncException {
        if (isEmptyFlightList) {
            throw new SyncException(ui.getFlightNumberSyncError());
        }
        for (FlightInfo flightInfo : flightsThatExist) {
            getFlightNumberSync(flightInfo);
            if (isFlightNumberSync) {
                break;
            }
        }
        if (!isFlightNumberSync) {
            throw new SyncException(ui.getFlightNumberSyncError());
        } else {
            resetSync();
        }
    }

    private void getFlightSyncDetails(String passengerDetail,
                                      ArrayList<FlightInfo> flightsThatExist) throws SkyControlException {
        flightNumber = PassengerList.getFlightNumberForSync(passengerDetail);
        isEmptyFlightList = flightsThatExist.size() == EMPTY_FLIGHT_LIST;

    }

    private void getFlightNumberSync(FlightInfo flightInfo) {
        isFlightNumberSync = flightInfo.getFlightNumber().matches(flightNumber);
    }

    private void resetSync() {
        isFlightNumberSync = false;
    }

    /**
     * Add is a type of operation, i.e. a passenger or a flight to their respective list.
     *
     * @param detail which is the input of the user that regards to the operation that is being carried out.
     *               i.e. if add Operation, the following details would be input after the operation.
     * @throws SkyControlException an error if the detail of the operation is left blank of not according to the format.
     */
    public abstract void addOperation(String detail) throws SkyControlException;

    /**
     * Delete is a type of operation that will delete a flight or passenger from its respective lists.
     */
    public abstract void deleteOperation(String detail) throws SkyControlException;

    /**
     * List is a type of operation that will list out all flights/passengers details.
     */
    public abstract void listOperation();

    /**
     * This is a method to delay flight departure times.
     *
     * @param flightNum        Number of flight to append
     * @param newDepartureTime new Departure Time after delay
     */
    public abstract void delayFlightDeparture(String flightNum, String newDepartureTime) throws SkyControlException;

    public abstract void modifyFlightNum(String flightNum, String newFlightNum) throws SkyControlException;

    public abstract void modifyGateNum(String flightNum, String newFlightNum) throws SkyControlException;

    public void insertFlights(ArrayList<FlightInfo> flights) {
        OperationList.flights = flights;
    }

    public void insertPassengers(ArrayList<PassengerInfo> passengers) {
        OperationList.passengers = passengers;
    }
}
