package seedu.duke.command;

import seedu.duke.operationlist.OperationList;
import seedu.duke.parsers.Parser;
import seedu.duke.ui.Ui;

public abstract class Command extends Parser {
    protected static Ui ui = new Ui();
    protected static final int DETAIL_INDEX = 1;

    protected static String passengerDetail;
    protected static String[] passengerDetailArray;

    public Command() {

    }

    public static void getPassengerDetail(String lineInput) {
        Parser.getInputWords(lineInput);
        Parser.checkOperation(inputWords);
        if (isAdd) {
            passengerDetailArray = lineInput.split("add");
        } else if (isDelete) {
            passengerDetailArray = lineInput.split("delete");
        }
        passengerDetail = passengerDetailArray[DETAIL_INDEX].trim();
    }

    public abstract void execute(OperationList operations, String lineInput);

    public boolean isExit() {
        return false;
    }
}
