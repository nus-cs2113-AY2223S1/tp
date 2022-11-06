package seedu.duke.command;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.operationlist.OperationList;
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

    //@@author ivanthengwr
    /**
     * Executes the main functionity of the bot based on the type of command given.
     *
     * @param operations is the type of operation that would be carried out.
     * @param lineInput Input of the user.
     * @throws SkyControlException an error if the execution faced an issue specific to the task within the function.
     */
    public abstract void execute(OperationList operations, String lineInput) throws SkyControlException;

    public boolean isExit() {
        return false;
    }
}
