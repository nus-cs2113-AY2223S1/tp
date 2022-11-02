package seedu.duke.command;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.operationlist.OperationList;
import seedu.duke.parsers.Parser;
import seedu.duke.ui.Ui;

public abstract class Command {
    public static final int START_INDEX = 3;
    protected static Ui ui = new Ui();
    protected static final int DETAIL_INDEX = 1;

    public static String getFlightNumFromModifyCmd(String[] inputWords) {
        return inputWords[DETAIL_INDEX].toUpperCase();
    }

    public static String getModifiedDetail(String[] inputWords) {
        return inputWords[2].substring(START_INDEX).toUpperCase();
    }

    public void checkFlightDetailSync(OperationList flights,
                                      OperationList passengers,
                                      String passengerDetail) throws SkyControlException, SyncException {
        passengers.checkPassengerFlightSync(flights, passengerDetail);
    }

    public String getPassengerDepartureTime(OperationList flights, String passengerDetail) throws SkyControlException {
        String departureTime = OperationList.getPassengerDepartureTime(flights, passengerDetail);
        return departureTime;
    }

    public String getPassengerGateNumber(OperationList flights, String passengerDetail) throws SkyControlException {
        String gateNumber = OperationList.getPassengerGateNumber(flights, passengerDetail);
        return gateNumber;
    }

    public abstract void execute(OperationList operations, String lineInput) throws SkyControlException;

    public boolean isExit() {
        return false;
    }
}
