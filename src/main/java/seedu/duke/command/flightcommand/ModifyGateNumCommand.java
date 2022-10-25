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

public class ModifyGateNumCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ModifyGateNumCommand.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        ModifyGateNumCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        ModifyGateNumCommand.LOGGER.addHandler(consoleHandler);
        createFileHandle();
        ModifyGateNumCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandle() {
        try {
            FileHandler fileHandler = new FileHandler("ModifyGateNum-logger.log");
            fileHandler.setLevel(Level.WARNING);
            ModifyGateNumCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            ModifyGateNumCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

    public void execute(OperationList entityList, String lineInput) throws SkyControlException {
        inputWords = lineInput.split("\\s+");
        String flightNum = getFlightNumFromModifyCmd(inputWords);
        String newGateNum = getModifiedDetail(inputWords);
        entityList.modifyGateNum(flightNum, newGateNum);
    }
}
