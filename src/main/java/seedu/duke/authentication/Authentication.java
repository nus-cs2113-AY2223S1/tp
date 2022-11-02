package seedu.duke.authentication;

import seedu.duke.*;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;
import seedu.duke.newcurrency.NewCurrency;

import java.io.IOException;

public class Authentication {
    public static boolean handleAuthenticationRequest() {
        boolean isProgramEnd = false;
        try {
            AuthenticationUi.showPromptInfo();
            String userInput = InputManager.receiveInputLine();
            Commands commandType = getCommandType(userInput);
            switch (commandType) {
            case REGISTER:
                RegisterCommand.handleRegister();
                break;
            case LOGIN:
                LoginCommand.handleLogin();
                break;
            case CURRENCIES:
                Currency.exchangeCommands(new CurrencyStructure("usd", "us dollar", "$", 1));
                break;
            case BYE:
                isProgramEnd = true;
                BasicUi.showExitMessage();
                break;
            case NEW_CURRENCY:
                NewCurrency.addNewCurrency();
                break;
            case REMOVE:
                NewCurrency.removeCurrency();
                break;
            default:
                throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
            }
        } catch (FinanceException e) {
            e.handleException();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
