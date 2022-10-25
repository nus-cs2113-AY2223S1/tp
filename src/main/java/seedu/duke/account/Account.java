package seedu.duke.account;

import java.io.IOException;
import java.util.List;

import seedu.duke.*;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class Account {
    protected Wallet wallet;

    public Account(Wallet wallet) {
        this.wallet = wallet;
    }

    public void handleAccountRequest() throws FinanceException {
        boolean isAccountExit = false;
        AccountUi.showAccountEntryMessage(wallet.getUserName());
        CurrencyStructure currency;
        while (!isAccountExit) {
            String in = InputManager.receiveInputLine().toLowerCase();
            String[] splits = in.split(" ");

            if (splits.length == 1) {
                String commandType = splits[0];
                try {
                    switch (commandType) {
                    case "balance":
                        double totalBalance = wallet.getTotalBalance();
                        currency = wallet.getDefaultCurrency();
                        AccountUi.showTotalBalance(totalBalance, currency);
                        break;
                    case "detail":
                        String username = wallet.getUserName();
                        currency = wallet.getDefaultCurrency();
                        List<Deposit> deposits = wallet.getDeposits();
                        AccountUi.showWalletDetails(username, currency, deposits);
                        break;
                    case "delete":
                        isAccountExit = DeleteCommand.handleDelete(wallet);
                        break;
                    case "exit":
                        isAccountExit = true;
                        AccountUi.showAccountExitMessage(wallet.getUserName());
                        break;
                    case "list":
                        AccountUi.listCommands();
                        break;
                    }
                } catch (FinanceException e) {
                    e.handleException();
                }
            }
            else if (splits.length == 3) {
                String commandType = splits[0];
                String commandArg = splits[1] + " " + splits[2];
                try {
                    switch (commandType) {
                        case "setdefault":
                            setDefaultCurrency(commandArg);
                            break;
                        case "save":
                            MoneyCommand.saveCommand(wallet,commandArg);
                            break;
                        case "withdraw":
                            MoneyCommand.withdrawCommand(wallet, commandArg);
                            break;
                        default:
                            throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                    }

                } catch (FinanceException e) {
                    e.handleException();
                }
            }
            else if (splits.length == 4){
                String commandType = splits[0];
                String recipientUsername = splits[1];
                int amount = Integer.parseInt(splits[2]);
                String trasnferCurrency = splits[3];

                try {
                    switch (commandType) {
                    case "transfer":
                        MoneyCommand.transferMoney(wallet, recipientUsername, trasnferCurrency, amount);
                        AccountUi.showTransfer(recipientUsername, trasnferCurrency, amount);
                        break;
                    default:
                        throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                    }
                } catch (FinanceException e) {
                    e.handleException();
                }
            }
            else {
                throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
            }
        }
    }

    private boolean helpCenter(){
        boolean isExit = false;
        while(!isExit){
            String in = InputManager.receiveInputLine().toLowerCase();
            try{
                if(in.equals("change default currency")){
                    AccountUi.reenterPassword();
                    boolean isCorrectPass = false;
                    while(!isCorrectPass){
                        if(wallet.getPassWord().equals(InputManager.receiveInputLine())) {
                            System.out.println("Enter the currency you would like to change your default to: ");
                            try {
                                setDefaultCurrency(InputManager.receiveInputLine());
                            } catch (FinanceException e) {
                                e.handleException();
                            }
                            isCorrectPass = true;
                        }
                        else{
                            System.out.println("Incorrect password, please try again.\nPassword:");
                        }
                    }
                }
                else if(in.equals("exit")){
                    isExit = true;
                }
                else if(in.equals("change password")){
                    AccountUi.reenterPassword();
                    boolean isCorrectPass = false;
                    while(!isCorrectPass){
                        if(wallet.getPassWord().equals(InputManager.receiveInputLine())) {
                            System.out.println("New password: ");
                            wallet.setPassWord(InputManager.receiveInputLine());
                            isCorrectPass = true;
                        }
                        else{
                            System.out.println("Incorrect password, please try again.\nNew Password:");
                        }
                    }
                }
                else if(in.equals("change username")){
                    AccountUi.reenterPassword();
                    boolean isCorrectPass = false;
                    while(!isCorrectPass){
                        if(wallet.getPassWord().equals(InputManager.receiveInputLine())) {
                            System.out.println("New username: ");
                            List<String> existingUsernames;
                            try {
                                existingUsernames = UserNameFileWorkings.userNameFile();
                            } catch (IOException e) {
                                throw new FinanceException(ExceptionCollection.USERFILE_CREATE_EXCEPTION);
                            }
                            boolean isUserExists = false;
                            while(!isUserExists) {
                                String tempUser = InputManager.receiveInputLine();
                                if (existingUsernames.contains(tempUser)) {
                                    System.out.println("This username already exists, please enter another one\nNew Username: ");
                                }
                            }
                            isCorrectPass = true;
                        }
                        else{
                            System.out.println("Incorrect password, please try again.\nPassword:");
                        }
                    }
                }
                else if(in.equals("delete account")){
                    return true;
                }
                else{
                    throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            }
            catch (FinanceException e){
                e.handleException();
            }
        }
        return false;
    }

    private void setDefaultCurrency(String commandArg) throws FinanceException {
        if (commandArg.isEmpty()) {
            throw new FinanceException(ExceptionCollection.SET_DEFAULT_CURRENCY_EXCEPTION);
        }
        CurrencyStructure oldCurrency = wallet.getDefaultCurrency();
        CurrencyStructure currency = CurrencyList.findCurrencyByAbbrName(commandArg);
        wallet.setDefaultCurrency(currency);
        CurrencyStructure newCurrency = wallet.getDefaultCurrency();
        double rate = newCurrency.getRate() / oldCurrency.getRate();
        double totalBalance = wallet.getTotalBalance() * rate;
        wallet.setTotalBalance(totalBalance);
        WalletFile.updateWallet(wallet);
        AccountUi.showSetDefaultCurrencyResult(oldCurrency.getAbbrName(), newCurrency.getAbbrName());
    }

}
