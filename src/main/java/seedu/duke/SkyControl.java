package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.AddFlightCommand;
import seedu.duke.command.flightcommand.DeleteFlightCommand;
import seedu.duke.command.flightcommand.ListFlightCommand;
import seedu.duke.command.flightcommand.ModifyFlightNumCommand;
import seedu.duke.command.flightcommand.ModifyGateNumCommand;
import seedu.duke.command.flightcommand.DelayFlightCommand;
import seedu.duke.command.passengercommand.AddPassengerCommand;
import seedu.duke.command.passengercommand.DeletePassengerCommand;
import seedu.duke.command.passengercommand.ListPassengerCommand;

import seedu.duke.exceptions.SkyControlException;
import seedu.duke.exceptions.SyncException;
import seedu.duke.operationlist.FlightList;
import seedu.duke.operationlist.OperationList;
import seedu.duke.operationlist.PassengerList;
import seedu.duke.parsers.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.terminalinfo.PassengerInfo;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SkyControl {
    private Ui ui;
    private OperationList passengers;
    private OperationList flights;
    private Storage storage;
    private PassengerInfo passengerInfo;
    private static boolean isPassenger = false;
    private static boolean isFlight = false;
    private static boolean isModify = false;
    private static boolean isAdd = false;
    private static boolean isDelete = false;
    private static boolean isDelay = false;


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public SkyControl() {
        ui = new Ui();
        passengers = new PassengerList();
        flights = new FlightList();
        storage = new Storage();
        passengerInfo = new PassengerInfo();
    }

    private void executeEntity(String lineInput, Command command) throws SkyControlException {
        assert lineInput != null;
        checkEntity(lineInput);
        try {
            if (isPassenger) {
                executePassengerCommand(lineInput, command);
            } else if (isFlight) {
                command.execute(flights, lineInput);
                storage.insertIntoFile(flights.getFlights(), passengers.getPassengers());
            } else if (isModify) {
                command.execute(flights, lineInput);
                command.execute(passengers, lineInput);
                storage.insertIntoFile(flights.getFlights(), passengers.getPassengers());
            } else if (isDelay) {
                command.execute(flights, lineInput);
                command.execute(passengers, lineInput);
                storage.insertIntoFile(flights.getFlights(), passengers.getPassengers());
            } else {
                command.execute(flights, lineInput);
            }
        } catch (SkyControlException | IOException e) {
            ui.showError(e.getMessage());
        }
    }

    private void executePassengerCommand(String lineInput, Command command) {
        try {
            String passengerDetail;
            if (isAdd) {
                String passengerAddInput = syncFlightDetail(lineInput, command);
                passengerDetail = Parser.getPassengerDetail(passengerAddInput);
                command.execute(passengers, passengerDetail);
            } else if (isDelete) {
                passengerDetail = Parser.getPassengerDetail(lineInput);
                command.execute(passengers, passengerDetail);
            } else {
                command.execute(passengers, lineInput);
            }
            storage.insertIntoFile(flights.getFlights(), passengers.getPassengers());
        } catch (SkyControlException | SyncException | IOException e) {
            ui.showError(e.getMessage());
        }
    }

    //@@author shengiv
    private String syncFlightDetail(String lineInput, Command command) throws SkyControlException, SyncException {
        String passengerDetail = Parser.getPassengerDetail(lineInput);
        command.checkFlightDetailSync(flights, passengers, passengerDetail);
        String departureTime = command.getPassengerDepartureTime(flights, passengerDetail);
        String reformatDepartureTime = passengerInfo.reformatDepartureTime(departureTime);
        String gateNumber = command.getPassengerGateNumber(flights, passengerDetail);
        String boardingTime = passengerInfo.getFormattedBoardingTime(reformatDepartureTime);
        String passengerAddInput = getLineInputForPassengerAdd(lineInput, departureTime,
                gateNumber, boardingTime);
        return passengerAddInput;
    }

    //@@author shengiv
    private String getLineInputForPassengerAdd(String lineInput, String departureTime,
                                               String gateNumber, String boardingTime) {
        return lineInput.trim() + " dt/" + departureTime + " gn/" + gateNumber + " bt/" + boardingTime;
    }

    //@@author ivanthengwr
    private void checkEntity(String lineInput) throws SkyControlException {
        isPassenger = Parser.isPassengerEntity(lineInput);
        isFlight = Parser.isFlightEntity(lineInput);
        isModify = Parser.isModifyCommand(lineInput);
        isDelay = Parser.isDelayCommand(lineInput);
        if (isPassenger) {
            isAdd = Parser.getAdd(lineInput);
            isDelete = Parser.getDelete(lineInput);
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

    //@@author JordanKwua
    private void loadStorage() {
        try {
            storage.loadPassengers(passengers);
            storage.loadFlights(flights);
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundMessage();
        }
    }

    //@@author ivanthengwr
    public void run() {
        ui.showWelcomeMessage();
        Storage.checkFileStatus();
        setUpAllLogger();
        loadStorage();
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

    //@@author shengiv
    public static void main(String[] args) {
        new SkyControl().run();
    }
}

