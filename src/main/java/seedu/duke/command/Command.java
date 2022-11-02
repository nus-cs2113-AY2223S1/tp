package seedu.duke.command;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.operationlist.OperationList;
import seedu.duke.parsers.Parser;
import seedu.duke.ui.Ui;

public abstract class Command extends Parser {
    public static final int MIN_LENGTH = 2;
    public static final int START_INDEX = 3;
    protected static Ui ui = new Ui();
    protected static final int DETAIL_INDEX = 1;

    protected static String passengerDetail;
    protected static String[] passengerDetailArray;

    public static void getPassengerDetail(String lineInput) throws SkyControlException {
        Parser.getInputWords(lineInput);
        Parser.checkOperation(inputWords);
        if (isAdd) {
            passengerDetailArray = lineInput.split("add");
            checkBlankDetailInput();
            passengerDetail = passengerDetailArray[DETAIL_INDEX].stripTrailing();
        } else if (isDelete) {
            passengerDetailArray = lineInput.split("delete");
            checkBlankDetailInput();
            passengerDetail = passengerDetailArray[DETAIL_INDEX].stripTrailing();
        } else {
            throw new SkyControlException(ui.getOperationError());
        }
    }

    public static String getFlightNumFromModifyCmd(String[] inputWords) {
        return inputWords[DETAIL_INDEX].toUpperCase();
    }

    public static String getModifiedDetail(String[] inputWords) {
        return inputWords[2].substring(START_INDEX).toUpperCase();
    }

    public static void checkBlankDetailInput() throws SkyControlException {
        if (passengerDetailArray.length < MIN_LENGTH) {
            throw new SkyControlException(ui.getBlankOpsError());
        }
    }

    public void checkFlightDetailSync(OperationList flights,
                                      OperationList passengers,
                                      String lineInput) throws SkyControlException, SyncException {
        getPassengerDetail(lineInput);
        passengers.checkPassengerFlightSync(flights, passengerDetail);
    }

    public String getPassengerDepartureTime(OperationList flights, String lineInput) throws SkyControlException {
        getPassengerDetail(lineInput);
        String departureTime = OperationList.getPassengerDepartureTime(flights, passengerDetail);
        return departureTime;
    }

    public String getPassengerGateNumber(OperationList flights, String lineInput) throws SkyControlException {
        getPassengerDetail(lineInput);
        String gateNumber = OperationList.getPassengerGateNumber(flights, passengerDetail);
        return gateNumber;
    }

    public abstract void execute(OperationList operations, String lineInput) throws SkyControlException;

    public boolean isExit() {
        return false;
    }
}
