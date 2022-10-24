package seedu.duke.operationlist;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.parsers.Parser;
import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public abstract class OperationList extends Parser {
    public static final int EMPTY_FLIGHT_LIST = 0;
    protected static ArrayList<FlightInfo> flights = new ArrayList<>();
    protected static ArrayList<PassengerInfo> passengers = new ArrayList<>();
    protected static Ui ui = new Ui();
    protected static boolean isFlightNumberSync = false;
    protected static boolean isEmptyFlightList = false;
    protected static String flightNumber;

    public ArrayList<FlightInfo> getFlights() {
        return flights;
    }

    public void checkPassengerFlightSync(OperationList flights,
                                         String passengerDetail) throws SyncException {
        try {
            ArrayList<FlightInfo> flightsThatExist;
            flightsThatExist = flights.getFlights();
            getFlightSyncDetails(passengerDetail, flightsThatExist);
            executeCheck(flightsThatExist);
        } catch (SyncException e) {
            throw new SyncException(ui.getFlightNumberSyncError());
        }
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
                                      ArrayList<FlightInfo> flightsThatExist) {
        flightNumber = PassengerList.getFlightNumberForSync(passengerDetail);
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
}
