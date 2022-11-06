package seedu.duke.operationlist;

import jdk.dynalink.Operation;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.parsers.Parser;
import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public abstract class OperationList {
    public static final int EMPTY_FLIGHT_LIST = 0;
    protected static ArrayList<FlightInfo> flights = new ArrayList<>();
    protected static ArrayList<PassengerInfo> passengers = new ArrayList<>();
    protected static Ui ui = new Ui();
    protected static PassengerList passengerList = new PassengerList();
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

    public static String getPassengerDepartureTime(OperationList flights,
                                                   String passengerDetail) throws SkyControlException {
        String departureTime = null;
        String flightNumber = passengerList.getFlightNumberForSync(passengerDetail);
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
        String flightNumber = passengerList.getFlightNumberForSync(passengerDetail);
        ArrayList<FlightInfo> flightsInFlightList = flights.getFlights();
        for (FlightInfo flight : flightsInFlightList) {
            if (flightNumber.equalsIgnoreCase(flight.getFlightNumber())) {
                gateNumber = flight.getGateNum();
            }
        }
        return gateNumber;
    }

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
        flightNumber = passengerList.getFlightNumberForSync(passengerDetail);
        isEmptyFlightList = flightsThatExist.size() == EMPTY_FLIGHT_LIST;

    }

    private void getFlightNumberSync(FlightInfo flightInfo) {
        isFlightNumberSync = flightInfo.getFlightNumber().matches(flightNumber);
    }

    private void resetSync() {
        isFlightNumberSync = false;
    }

    public abstract void addOperation(String detail) throws SkyControlException;

    public abstract void deleteOperation(String detail) throws SkyControlException;

    public abstract void listOperation();

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
