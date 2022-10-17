package recipeditor;

import recipeditor.parser.Parser;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import recipeditor.command.Command;
import recipeditor.command.ExitCommand;
import recipeditor.command.CommandResult;


public class Recipeditor {


    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Greetings and Load recipe titles to the RecipeList.
     *
     * @param args arguments
     */
    private static void start(String[] args) {
        Ui.showGreeting();
        //TODO: Load from storage
    }

    /**
     * Exit and do clean up.
     */
    private static void exit() {
        Ui.showExit();
        System.exit(0);
    }


    /**
     * Main Command Loop Input -> Command -> Command Result -> Show & Save.
     */
    private static void runCommandLoopUntilExitCommand() {
        Storage.createDataFile();
        Storage.loadRecipesFromDataFile();
        Command command;
        do {
            String input = Ui.readInput();
            command = new Parser().parseCommand(input);
            CommandResult result = executeCommand(command);
            Ui.showResult(result);

        } while (!ExitCommand.isExit(command));
    }

    private static CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute();
            //TODO: Save the recipe?
            return result;
        } catch (Exception e) {
            Ui.showMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
