package seedu.moneygowhere.parser;

import seedu.moneygowhere.commands.ConsoleCommand;
import seedu.moneygowhere.commands.ConsoleCommandBye;
import seedu.moneygowhere.exceptions.ConsoleParserCommandNotFoundException;

public class ConsoleParser {
    public static final String CONSOLE_COMMAND_BYE = "bye";

    private static ConsoleCommandBye parseCommandBye() {
        return new ConsoleCommandBye();
    }

    public static ConsoleCommand parseConsoleInput(String consoleInput) throws
            ConsoleParserCommandNotFoundException {
        String[] consoleInputArr = consoleInput.split(" ", 2);

        String command = consoleInputArr[0];
        String arguments = "";
        int numOperands = 2;
        if (consoleInputArr.length == numOperands) {
            arguments = consoleInputArr[1];
        }

        if (command.equalsIgnoreCase(CONSOLE_COMMAND_BYE)) {
            return parseCommandBye();
        } else {
            throw new ConsoleParserCommandNotFoundException();
        }
    }
}
