package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.*;
import seedu.duke.command.passengercommand.AddPassengerCommand;
import seedu.duke.command.passengercommand.DeletePassengerCommand;
import seedu.duke.command.passengercommand.ListPassengerCommand;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.operationlist.FlightList;
import seedu.duke.operationlist.OperationList;
import seedu.duke.operationlist.PassengerList;
import seedu.duke.parsers.Parser;
import seedu.duke.ui.Ui;

public class SkyControl {
    private Ui ui;
    private OperationList passengers;
    private OperationList flights;
    private static boolean isPassenger = false;
    private static boolean isFlight = false;
    private static boolean isModify = false;
    private static boolean isAdd = false;
    private static boolean isDelay = false;


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public SkyControl() {
        ui = new Ui();
        passengers = new PassengerList();
        flights = new FlightList();
    }

    private void executeEntity(String lineInput, Command command) throws SkyControlException {
        assert lineInput != null;
        checkEntity(lineInput);
        try {
            if (isPassenger) {
                executePassengerCommand(lineInput, command);
            } else if (isFlight) {
                command.execute(flights, lineInput);
            } else if (isModify) {
                command.execute(flights, lineInput);
                command.execute(passengers, lineInput);
            } else if (isDelay) {
                command.execute(flights, lineInput);
                command.execute(passengers, lineInput);
            } else {
                command.execute(flights, lineInput);
            }
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
        }
    }

    private void executePassengerCommand(String lineInput, Command command) {
        try {
            if (isAdd) {
                command.checkFlightDetailSync(flights, passengers, lineInput);
            }
            command.execute(passengers, lineInput);
        } catch (SkyControlException | SyncException e) {
            ui.showError(e.getMessage());
        }
    }

    private void checkEntity(String lineInput) throws SkyControlException {
        isPassenger = Parser.isPassengerEntity(lineInput);
        isFlight = Parser.isFlightEntity(lineInput);
        isModify = Parser.isModifyCommand(lineInput);
        isDelay = Parser.isDelayCommand(lineInput);
        if (isPassenger) {
            isAdd = Parser.getAdd(lineInput);
        }
    }

    private void setUpAllLogger() {
        Parser.setupLogger();
        AddPassengerCommand.setupLogger();
        DeletePassengerCommand.setupLogger();
        ListPassengerCommand.setupLogger();
        DeleteFlightCommand.setUpLogger();
        AddFlightCommand.setupLogger();
        ListFlightCommand.setupLogger();
        ModifyFlightNumCommand.setupLogger();
        ModifyGateNumCommand.setupLogger();
        DelayFlightCommand.setupLogger();
    }

    public void run() {
        ui.showWelcomeMessage();
        setUpAllLogger();
        boolean isExit = false;
        while (!isExit) {
            try {
                String lineInput = ui.readCommand();
                ui.showLineSeparator();
                Command command = Parser.parse(lineInput);
                executeEntity(lineInput, command);
                isExit = command.isExit();
            } catch (SkyControlException e) {
                ui.showError(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new SkyControl().run();
    }
}

