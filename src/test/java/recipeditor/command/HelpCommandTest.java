package recipeditor.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import recipeditor.parser.Parser;

public class HelpCommandTest {


    @Test
    void incorrectHelpCommand_invalidInput_syntaxForHelpCommand() {
        String input = "/help";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Try /help <command type>\n"
                + "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void incorrectHelpCommand_invalidCommandInput_syntaxForHelpCommand() {
        String input = "/help invalid";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "invalid is not a valid command.\n"
                + "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForAddCommand_validInput_syntaxForAddCommand() {
        String input = "/help add";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: /add\n"
                + "Description: Edit a new recipe and add it to recipeditor.";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForDeleteCommand_validInput_syntaxForDeleteCommand() {
        String input = "/help delete";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: "
                + '\n' + "\t /delete -id <recipe_index>"
                + '\n' + "\t /delete -t <recipe_title>" + '\n'
        + "Description: Delete the recipe of given title or index from recipeditor.";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForEditCommand_validInput_syntaxForEditCommand() {
        String input = "/help edit";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: \n"
                + "Syntax for /edit GUI: \n"
                + "\t /edit <index> \n"
                + "\n"
                + "Syntax for /edit CLI: \n"
                + "\t /edit <recipe index> (command flags) (parameters) \n"
                + "Command flags: \n"
                + "\t -add (recipe flag) (input) \n"
                + "\t -del (recipe flag) (index) \n"
                + "\t -swp (recipe flag) (index 1) (index 2) \n"
                + "\t -chg (recipe flag) (index) (input) \n"
                + "Recipe flags: \n"
                + "\t -i: ingredient, -s: step, -t: title, -d: description \n"
                + "\n"
                + "Description: \n"
                + "Edit an existing recipe by: \n"
                + "-add Add a new step or ingredient \n"
                + "-del Delete an existing step or ingredient \n"
                + "-swp Swap the order of two ingredients or steps \n"
                + "-chg Change the title, description, ingredients or steps \n";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForExitCommand_validInput_syntaxForExitCommand() {
        String input = "/help exit";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: /exit\n"
                + "Description: Exit recipeditor.";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForFindCommand_validInput_syntaxForFindCommand() {
        String input = "/help find";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: /find -<flag> <recipe title/ingredient name>\n"
                + "Description: For the given ingredient or title, find recipes which contains it.";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForHelpCommand_validInput_syntaxForHelpCommand() {
        String input = "/help help";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: /help <command>\n"
                + "Description: Show help message for the given command.";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForListCommand_validInput_syntaxForListCommand() {
        String input = "/help list";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: /list\n"
                + "Description: Show a list of all recipe titles in recipeditor.";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

    @Test
    void helpSyntaxForViewCommand_validInput_syntaxForViewCommand() {
        String input = "/help view";
        Command commandExecuted = Parser.parseCommand(input);
        CommandResult commandExecutedResult = commandExecuted.execute();
        String expected = "Syntax: "
                + "\n" + "\t /view -id <recipe_index>"
                + '\n' + "\t /view -t <recipe_title> " + '\n'
                + "Description: View the full recipe of the sepcific index or title in the list of recipes";
        assertEquals(expected, commandExecutedResult.getMessage());
    }

}
