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

public class DeletePassengerCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(DeletePassengerCommand.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        DeletePassengerCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        DeletePassengerCommand.LOGGER.addHandler(consoleHandler);
        createFileHandle();
        DeletePassengerCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandle() {
        try {
            FileHandler fileHandler = new FileHandler("DeletePassenger-logger.log");
            fileHandler.setLevel(Level.WARNING);
            DeletePassengerCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            DeletePassengerCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

    @Override
    public void execute(OperationList passengers, String lineInput) {
        try {
            getPassengerDetail(lineInput);
            passengers.deleteOperation(passengerDetail);
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
        }
    }
}
