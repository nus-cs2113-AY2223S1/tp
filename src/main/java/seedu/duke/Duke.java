package seedu.duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import seedu.duke.authentication.Authentication;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //List<String> existingUserNames;
        //List<List<String>> allCurrencies;
        //allCurrencies = Currency.getListOfAllCurrencies();
        //existingUserNames = UserNameFileWorkings.userNameFile();
        BasicUi.showWelcomeMessage();
        initializeCurrencyList();
        boolean isProgramEnd = false;
        while (!isProgramEnd) {
            isProgramEnd = Authentication.handleAuthenticationRequest();
            /*
            try {
                Ui.showPromptInfo();
                Commands commandType;
                try {
                    commandType = Commands.valueOf(in.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
                switch (commandType) {
                case REGISTER:
                    Ui.showRegisterInfo();

                    String userName;

                    //checks if the username already exists
                    do {
                        System.out.print("Username: ");
                        userName = in.nextLine();
                        System.out.println(" ");
                    } while (existingUserNames.contains(userName));

                    int passWordLength;
                    String passWord;
                    do {
                        System.out.print("Password: ");
                        passWord = in.nextLine();
                        System.out.println(" ");
                        passWordLength = passWord.length();
                    } while (passWordLength < 8);

                    existingUserNames.add(userName);
                    UserNameFileWorkings.writeToUserNames(userName);

                    Wallet newWallet = new Wallet(userName, passWord);
                    WalletFile.createNewWallet(newWallet);
                    Ui.showRegisterSuccessInfo();
                    break;
                case BYE:
                    isProgramEnd = true;
                    break;
                case LOGIN:
                    boolean user = false;
                    do {
                        try {
                            Ui.showEnterUsernamePrompt();
                            userName = in.nextLine();
                            Wallet wallet = WalletFile.getWallet(userName);
                            Ui.showEnterPasswordPrompt();
                            passWord = in.nextLine();
                            if (wallet.getPassWord().equals(passWord)) {
                                user = true;
                                System.out.println("Successful login, you can now access your account.");
                                Account.accountCommands(wallet);
                            } else {
                                Ui.showInvalidPasswordPrompt();
                            }
                        } catch (FileNotFoundException e) {
                            Ui.showInvalidUsernamePrompt();
                        }
                    } while (!user);
                    break;
                case CURRENCIES:
                    Currency.exchangeCommands();
                    break;
                default:
                    throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            } catch (FinanceException e) {
                e.handleException();
            }
            */
        }
    }

    private static void initializeCurrencyList(){
        try {
            CurrencyList.initializeCurrencyList();
        } catch (FinanceException e) {
            e.handleException();
        }
    }
}
