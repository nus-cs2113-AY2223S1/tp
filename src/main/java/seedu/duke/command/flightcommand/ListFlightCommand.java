package seedu.duke.command.flightcommand;

import seedu.duke.command.Command;
import seedu.duke.operationlist.OperationList;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class ListFlightCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ListFlightCommand.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        ListFlightCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        ListFlightCommand.LOGGER.addHandler(consoleHandler);
        createFileHandler();
        ListFlightCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandler() {
        try {
            FileHandler fileHandler = new FileHandler("ListFlights-logger.log");
            fileHandler.setLevel(Level.WARNING);
            ListFlightCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            ListFlightCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

    @Override
    public void execute(OperationList flights, String lineInput) {
        flights.listOperation();
    }
}
