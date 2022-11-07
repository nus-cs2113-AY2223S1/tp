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
import java.sql.SQLOutput;

public class SkyControl {
    private Ui ui;
    private OperationList passengers;
    private OperationList flights;
    private Storage storage;
    private PassengerInfo passengerInfo;

    public SkyControl() {
        ui = new Ui();
        passengers = new PassengerList();
        flights = new FlightList();
        storage = new Storage();
        passengerInfo = new PassengerInfo();
    }

    //@@ivanthengwr
    /**
     * Checks the boolean if the User is engaging a passenger command or not.
     *
     * @param lineInput Manager's line command input.
     * @return isPassenger which is a check to see if it is a passenger command or not.
     * @throws SkyControlException if the command is invalid.
     */

    public boolean isPassenger(String lineInput) throws SkyControlException {
        boolean isPassenger;
        isPassenger = Parser.isPassengerEntity(lineInput);
        return isPassenger;
    }

    /**
     * Checks the boolean if the User is engaging a flight command or not.
     *
     * @param lineInput Manager's line command input.
     * @return isFlight which is a check to see if it is a passenger command or not.
     * @throws SkyControlException if the command is invalid.
     */
    public boolean isFlight(String lineInput) throws SkyControlException {
        boolean isFlight;
        isFlight = Parser.isFlightEntity(lineInput);
        return isFlight;
    }

    /**
     * Checks the boolean if the User is engaging a modify command or not.
     *
     * @param lineInput Manager's line command input.
     * @return isModify which is a check to see if it is a modify command or not.
     * @throws SkyControlException if the command is invalid.
     */
    public boolean isModify(String lineInput) throws SkyControlException {
        boolean isModify;
        isModify = Parser.isModifyCommand(lineInput);
        return isModify;
    }

    /**
     * Checks the boolean if the User is engaging a delay command or not.
     *
     * @param lineInput Manager's line command input.
     * @return isFlight which is a check to see if it is a Delay command or not.
     * @throws SkyControlException if the command is invalid.
     */
    public boolean isDelay(String lineInput) throws SkyControlException {
        boolean isDelay;
        isDelay = Parser.isDelayCommand(lineInput);
        return isDelay;
    }

    /**
     * Checks the boolean if the User is engaging an add command or not.
     *
     * @param lineInput Manager's line command input.
     * @return isFlight which is a check to see if it is an add command or not.
     * @throws SkyControlException if the command is invalid.
     */
    public boolean isAdd(String lineInput) throws SkyControlException {
        boolean isAdd = false;
        if (isPassenger(lineInput)) {
            isAdd = Parser.getAdd(lineInput);
        }
        return isAdd;
    }

    /**
     * Checks the boolean if the User is engaging a delete command or not.
     *
     * @param lineInput Manager's line command input.
     * @return isFlight which is a check to see if it is a delete command or not.
     * @throws SkyControlException if the command is invalid.
     */
    public boolean isDelete(String lineInput) throws SkyControlException {
        boolean isDelete = false;
        if (isPassenger(lineInput)) {
            isDelete = Parser.getDelete(lineInput);
        }
        return isDelete;
    }

    /**
     * Executes the type of entity that the manager chooses.
     * I.e. Either a passenger, flight, modify, delay or exit command.
     *
     * @param lineInput Manager's line command input.
     * @param command obtains functions from command class to carry out execute commands.
     * @throws SkyControlException if the command is invalid.
     */
    private void executeEntity(String lineInput, Command command) throws SkyControlException {
        assert lineInput != null;
        try {
            if (isPassenger(lineInput)) {
                executePassengerCommand(lineInput, command);
            } else if (isFlight(lineInput)) {
                command.execute(flights, lineInput);
                storage.insertIntoFile(flights.getFlights(), passengers.getPassengers());
            } else if (isModify(lineInput)) {
                command.execute(flights, lineInput);
                command.execute(passengers, lineInput);
                storage.insertIntoFile(flights.getFlights(), passengers.getPassengers());
            } else if (isDelay(lineInput)) {
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

    /**
     *Executes the Passenger command based on the type of operation that the manager chooses.
     *
     * @param lineInput Manager's line command input.
     * @param command obtains functions from command class to carry out execute commands.
     */
    private void executePassengerCommand(String lineInput, Command command) {
        try {
            String passengerDetail;
            if (isAdd(lineInput)) {
                Parser.checkAddOperationDetail(lineInput);
                String passengerAddInput = syncFlightDetail(lineInput, command);
                passengerDetail = Parser.getPassengerDetail(passengerAddInput);
                command.execute(passengers, passengerDetail);
            } else if (isDelete(lineInput)) {
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

    /**
     * For passenger add feature, retrieves flight details for the passenger based on the flight number of the
     * passenger. Synchronises passenger details with flight details.
     *
     * @param lineInput Manager's command line input.
     * @param command Command object for the particular user input.
     * @return passengerAddInput which is a String object consisting of lineInput from the user
     *         and flight details for the passenger retrieved from flight list.
     * @throws SkyControlException If there is incorrect input.
     * @throws SyncException If there does not exist a FlightInfo object with the specified
     *         flight number for the passenger.
     */
    private String syncFlightDetail(String lineInput, Command command) throws SkyControlException, SyncException {
        command.checkFlightDetailSync(flights, passengers, lineInput);
        String passengerAddInput;
        passengerAddInput =  getPassengerAddInput(lineInput, command);
        return passengerAddInput;
    }

    //@@author ivanthengwr
    private String getPassengerAddInput(String lineInput, Command command) throws SkyControlException, SyncException {
        String passengerDetail = Parser.getPassengerDetail(lineInput);
        command.checkFlightDetailSync(flights, passengers, passengerDetail);
        String departureTime = command.getPassengerDepartureTime(flights, passengerDetail);
        String reformatDepartureTime = passengerInfo.reformatDepartureTime(departureTime);
        String gateNumber = command.getPassengerGateNumber(flights, passengerDetail);
        String boardingTime = passengerInfo.getFormattedBoardingTime(reformatDepartureTime);
        return getLineInputForPassengerAdd(lineInput, departureTime, gateNumber, boardingTime);
    }

    //@@author shengiv
    private String getLineInputForPassengerAdd(String lineInput, String departureTime,
                                               String gateNumber, String boardingTime) {
        return lineInput.trim() + " dt/" + departureTime + " gn/" + gateNumber + " bt/" + boardingTime;
    }

    /**
     * Sets up all loggers from all the respective classes.
     *
     */
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
    /**
     * This is the main execution function of the bot. It runs the necessary set up and parameters that is
     * responsible for the behaviour of the program.
     *
     */
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
    /**
     * Main entry-point for the java.duke.SkyControl application.
     */
    public static void main(String[] args) {
        new SkyControl().run();
    }
}

