package recipeditor;

import recipeditor.parser.Parser;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import recipeditor.command.Command;
import recipeditor.command.ExitCommand;
import recipeditor.command.CommandResult;

public class Recipeditor {

    public static void main(String[] args) {
        run();
        System.exit(0);

    }

    public static void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private static void start() {
        Storage.createAppFolder();
        Storage.createFile(Storage.ALL_RECIPES_FILE_PATH);
        Storage.loadRecipesToRecipeTitlesList();
        Ui.showGreeting();
    }

    private static void exit() {
        Storage.rewriteRecipeListToFile();
        Storage.deleteAllRecipe();
        Storage.saveAllRecipe();
        Ui.showExit();
    }

    private static void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String input = Ui.readInput();
            command = Parser.parseCommand(input);
            CommandResult result = executeCommand(command);
            Ui.showResult(result);

        } while (!ExitCommand.isExit(command));
    }

    private static CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            Ui.showMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
