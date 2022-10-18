package seedu.duke;

import java.util.Scanner;

import seedu.duke.FinanceException.ExceptionCollection;

public class Account {
    public static void accountCommands(Wallet wallet) {
        boolean isAccountExit = false;
        Ui.showAccountEntryMessage(wallet.getUserName());
        while (!isAccountExit) {
            String in = InputManager.receiveInputLine().toLowerCase();
            String[] splitInput = in.split(" ");
            String command = splitInput[0];
            try{
                switch (command) {
                    case "balance":
                        System.out.println("Your current balance is: " + wallet.getBalance());
                        break;
                    case "deposit":
                        //Ui.deposit;
                        break;
                    case "withdraw":
                        //Ui.withdraw;
                        break;
                    case "delete":
                        //delete account
                        break;
                    case "exit":
                        isAccountExit = true;
                        Ui.showAccountExitMessage(wallet.getUserName());
                        break;
                    default:
                        throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            } catch (FinanceException e) {
                e.handleException();
            }
        }
    }
}
