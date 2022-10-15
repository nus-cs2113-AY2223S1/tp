package seedu.duke.operationlist;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.parsers.Parser;
import seedu.duke.terminalinfo.FlightInfo;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public abstract class OperationList extends Parser {
    protected static ArrayList<FlightInfo> flights = new ArrayList<>();
    protected static ArrayList<PassengerInfo> passengers = new ArrayList<>();
    protected static Ui ui = new Ui();

    public abstract void addOperation(String detail) throws SkyControlException;

    public abstract void deleteOperation(String detail) throws SkyControlException;

    public abstract void listOperation();
}
