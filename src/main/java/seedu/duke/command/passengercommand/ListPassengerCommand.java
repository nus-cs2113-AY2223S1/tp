package seedu.duke.command.passengercommand;

import seedu.duke.command.Command;
import seedu.duke.operationlist.OperationList;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class ListPassengerCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ListPassengerCommand.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        ListPassengerCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        ListPassengerCommand.LOGGER.addHandler(consoleHandler);
        createFileHandle();
        ListPassengerCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandle() {
        try {
            FileHandler fileHandler = new FileHandler("ListPassenger-logger.log");
            fileHandler.setLevel(Level.WARNING);
            ListPassengerCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            ListPassengerCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

    @Override
    public void execute(OperationList passengers, String lineInput) {
        passengers.listOperation();
    }
}
