package recipeditor;

import recipeditor.parser.Parser;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import recipeditor.command.Command;
import recipeditor.command.ExitCommand;
import recipeditor.command.CommandResult;


public class Recipeditor {
    public static final String DATA_FILE_PATH = "./data/data.txt";
    public static final String TEMPORARY_DATA_FILE_PATH = "./data/temporary_data.txt";


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
        Storage.createDataFolder();
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
<<<<<<< HEAD

=======
        Storage.createFile(DATA_FILE_PATH);
        Storage.loadRecipesFromFile(DATA_FILE_PATH);
>>>>>>> 0a8bfb181ad20afb97632e53a88afa0a60928182
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
