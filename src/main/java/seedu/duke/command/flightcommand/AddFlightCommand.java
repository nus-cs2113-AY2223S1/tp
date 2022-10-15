package seedu.duke.command.flightcommand;

import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.OperationList;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class AddFlightCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(AddFlightCommand.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        AddFlightCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        AddFlightCommand.LOGGER.addHandler(consoleHandler);
        createFileHandler();
        AddFlightCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandler() {
        try {
            FileHandler fileHandler = new FileHandler("AddFlight-logger.log");
            fileHandler.setLevel(Level.WARNING);
            AddFlightCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            AddFlightCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError());
        }
    }

    @Override
    public void execute(OperationList flights, String lineInput) {
        try {
            flights.addOperation(lineInput);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
