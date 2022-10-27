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

public class DelayFlightCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(DelayFlightCommand.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        DelayFlightCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        DelayFlightCommand.LOGGER.addHandler(consoleHandler);
        createFileHandler();
        DelayFlightCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandler() {
        try {
            FileHandler fileHandler = new FileHandler("DelayFlight-logger.log");
            fileHandler.setLevel(Level.WARNING);
            DelayFlightCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            DelayFlightCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError());
        }
    }

    public void execute(OperationList entityList, String lineInput) throws SkyControlException {
        inputWords = lineInput.split("\\s+");
        String flightNum = getFlightNumFromModifyCmd(inputWords);
        String newDepartureTime = getModifiedDetail(inputWords);
        entityList.delayFlightDeparture(flightNum, newDepartureTime);
    }
}
