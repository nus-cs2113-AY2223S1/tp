package seedu.duke.command.passengercommand;

import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.OperationList;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class AddPassengerCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(AddPassengerCommand.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        AddPassengerCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        AddPassengerCommand.LOGGER.addHandler(consoleHandler);
        createFileHandle();
        AddPassengerCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandle() {
        try {
            FileHandler fileHandler = new FileHandler("AddPassenger-logger.log");
            fileHandler.setLevel(Level.WARNING);
            AddPassengerCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            AddPassengerCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

    @Override
    public void execute(OperationList passengers, String lineInput) {
        try {
            getPassengerDetail(lineInput);
            passengers.addOperation(passengerDetail);
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
        }
    }
}
