package seedu.duke.command;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.OperationList;
import seedu.duke.parsers.Parser;
import seedu.duke.ui.Ui;

public abstract class Command extends Parser {
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
            passengerDetail = passengerDetailArray[DETAIL_INDEX].trim();
        } else if (isDelete) {
            passengerDetailArray = lineInput.split("delete");
            checkBlankDetailInput();
            passengerDetail = passengerDetailArray[DETAIL_INDEX].trim();
        } else {
            throw new SkyControlException(ui.getOperationError());
        }
    }

    public static void checkBlankDetailInput() throws SkyControlException {
        if (passengerDetailArray.length < 2) {
            throw new SkyControlException(ui.getBlankOpsError());
        }
    }

    public abstract void execute(OperationList operations, String lineInput);

    public boolean isExit() {
        return false;
    }
}
