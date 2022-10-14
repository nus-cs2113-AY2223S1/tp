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

public class DeleteFlightCommand extends Command {
    private final static Logger LOGGER = Logger.getLogger(DeleteFlightCommand.class.getName());

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        DeleteFlightCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        DeleteFlightCommand.LOGGER.addHandler(consoleHandler);
        createFileHandler();
        DeleteFlightCommand.LOGGER.log(Level.INFO,ui.getLoggerStartUpMessage());
    }

    private static void createFileHandler() {
        try {
            FileHandler fileHandler = new FileHandler("DeleteFlightCommand.log");
            fileHandler.setLevel(Level.FINE);
            DeleteFlightCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            DeleteFlightCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

    @Override
    public void execute(OperationList flights, String lineInput) {
        try {
            flights.deleteOperation(lineInput);
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
        }
    }
}
