package seedu.duke;

import java.io.FileNotFoundException;

import seedu.duke.FinanceException.ExceptionCollection;

public class Authentication {
    public static boolean handleAuthenticationRequest() {
        Boolean isProgramEnd = false;
        try {
            Ui.showPromptInfo();
            String userInput = InputManager.receiveInputLine();
            Commands commandType = getCommandType(userInput);
            switch (commandType) {
            case REGISTER:
                RegisterCommand.handleRegister();
                break;
            case LOGIN:
                LoginCommand.handleLogin();
                break;
            case BYE:
                isProgramEnd = true;
                Ui.showExitMessage();
                break;
            default:
                throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
            }
        } catch (FinanceException e) {
            e.handleException();
        }
        return isProgramEnd;
    }

    public static Commands getCommandType(String inputLine) throws FinanceException {
        Commands commandType;
        try {
            commandType = Commands.valueOf(inputLine.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
        }
        return commandType;
    }
}
