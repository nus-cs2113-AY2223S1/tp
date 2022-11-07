package seedu.duke.account;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.duke.*;
import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

public class Account {
    protected Wallet wallet;
    protected String loginTime;

    public Account(Wallet wallet) {
        this.wallet = wallet;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd>HH:mm:ss");
        loginTime = dtf.format(LocalDateTime.now());
    }

    public boolean handleAccountRequest() throws FinanceException, IOException {
        boolean isAccountExit = false;
        AccountUi.showAccountEntryMessage(wallet.getUserName());

        AccountHistoryFile.createNewLoginAccount(wallet.getUserName(), loginTime);

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
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            AccountUi.showTotalBalance(totalBalance, currency);
                            break;
                        case "details":
                            String username = wallet.getUserName();
                            currency = wallet.getDefaultCurrency();
                            List<Deposit> deposits = wallet.getDeposits();
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            AccountUi.showWalletDetails(username, currency, deposits);
                            break;
                        case "help":
                            AccountUi.showHelpPrompt();
                            isAccountExit = helpCenter();
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            if(!isAccountExit){
                                System.out.println("Back in the main account, please enter any commands.");
                            }
                            break;
                        case "logout":
                            isAccountExit = true;
                            AccountHistoryFile.deleteFile(wallet.getUserName(), loginTime);
                            AccountUi.showAccountExitMessage(wallet.getUserName());
                            break;
                        case "list":
                            AccountUi.listCommands();
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            break;
                        case "currencies":
                            Currency.exchangeCommands(wallet.getDefaultCurrency());
                            System.out.println("Hi");
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            System.out.println("Back in the main account, please enter any commands.");
                            break;
                        default:
                            throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                    }
                } catch (FinanceException e) {
                    e.handleException();
                }
            }
            else if (splits.length == 2) {
                String commandType = splits[0];
                String commandArg = splits[1];
                try {
                    switch (commandType) {
                        case "deposit":
                            MoneyCommand.saveCommand(wallet,commandArg);
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            break;
                        case "withdraw":
                            MoneyCommand.withdrawCommand(wallet,commandArg);
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            break;
                        default:
                            if(in.equals("account history")){
                                AccountHistoryFile.printFile(wallet.getUserName(), loginTime);
                                AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            }
                            else if(in.equals("log out")){
                                isAccountExit = true;
                                AccountHistoryFile.deleteFile(wallet.getUserName(), loginTime);
                                AccountUi.showAccountExitMessage(wallet.getUserName());
                            }
                            else{
                                throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                            }
                    }

                } catch (FinanceException e) {
                    e.handleException();
                }
            }
            else if (splits.length == 3) {
                String commandType = splits[0];
                String commandArg = splits[1] + " " + splits[2];
                String commandType2 = splits[0] + " " + splits[1];
                String commandArg2 = splits[2];
                try {
                    switch (commandType) {
                        case "deposit":
                            MoneyCommand.saveCommand(wallet,commandArg);
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            break;
                        case "withdraw":
                            MoneyCommand.withdrawCommand(wallet, commandArg);
                            AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            break;
                        default:
                            if(commandType2.equals("convert all")){
                                MoneyCommand.convertAllCommand(wallet,commandArg2);
                                AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                            }
                            throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                    }

                } catch (FinanceException e) {
                    e.handleException();
                }
            }
            else if (splits.length == 4){
                String commandType = splits[0];
                String recipientUsername = splits[1];
                try {
                    int amount = Integer.parseInt(splits[2]);
                    String transferCurrency = splits[3];

                    try {

                        switch (commandType) {
                            case "convert":
                                MoneyCommand.exchangeCommand(wallet, splits[1]+" "+splits[2]+" "+splits[3]);
                                AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);
                                break;
                            case "transfer":
                                MoneyCommand.transferMoney(wallet, recipientUsername, transferCurrency, amount);
                                AccountUi.showTransfer(recipientUsername, transferCurrency, amount);
                                AccountHistoryFile.updateLoginAccount(wallet.getUserName(), loginTime, in);

                                break;
                            default:
                                throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
                        }
                    } catch (FinanceException e) {
                        e.handleException();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please make sure to use integers for transfer amount");
                }
            }
            else {
                throw new FinanceException(ExceptionCollection.COMMAND_TYPE_EXCEPTION);
            }
        }
        return isAccountExit;
    }

    public boolean helpCenter(){
        boolean isExit = false;
        while(!isExit){
            String in = InputManager.receiveInputLine().toLowerCase();
            try{
                boolean isCorrectPass;
                switch (in) {
                    case "change default currency":
                        AccountUi.reenterPassword();
                        isCorrectPass = false;
                        while (!isCorrectPass) {
                            String pass = InputManager.receiveInputLine();
                            if (wallet.getPassWord().equals(pass)) {
                                boolean isCurrValid = false;
                                while (!isCurrValid) {
                                    try {
                                        System.out.print("Enter the abbreviation of currency you would like to change your default to: ");
                                        String tempCurr = InputManager.receiveInputLine();
                                        if (tempCurr.equals("exit")) {
                                            System.out.println("Exiting, your default currency has not been changed.");
                                            isCurrValid = true;
                                        }
                                        else {
                                            CurrencyList.findCurrencyByAbbrName(tempCurr);
                                            setDefaultCurrency(tempCurr);
                                            System.out.println("Please enter exit to leave the help menu or enter another help command.");
                                        }
                                    }
                                    catch(FinanceException e){
                                        e.handleException();
                                    }
                                }
                                isCorrectPass = true;
                            }
                            else if(pass.equalsIgnoreCase("exit")){
                                isCorrectPass = true;
                                System.out.println("Exiting, your default currency has not been changed.");
                            }
                            else {
                                System.out.println("Incorrect password, please try again.\nPassword:");
                            }
                        }
                        break;
                    case "exit":
                        isExit = true;
                        break;
                    case "change password":
                        AccountUi.reenterPassword();
                        isCorrectPass = false;
                        while (!isCorrectPass) {
                            String pass = InputManager.receiveInputLine();
                            if (wallet.getPassWord().equals(pass)) {
                                String tempPass;
                                boolean isPassValid = false;
                                while(!isPassValid){
                                    System.out.print("New password: ");
                                    tempPass = InputManager.receiveInputLine();
                                    if(tempPass.equals(wallet.getPassWord())){
                                        System.out.println("Same password as the previous, please enter something else");
                                    }
                                    else if(tempPass.equals("exit")){
                                        System.out.println("Exiting, your password has not been changed.");
                                        isPassValid = true;
                                    }
                                    else if(tempPass.length() < 8){
                                        System.out.println("Password is not strong enough, please make it more than 8 characters");
                                    }
                                    else{
                                        System.out.println("Your password has been successfully changed, type exit to leave the help menu");
                                        wallet.setPassWord(tempPass);
                                        WalletFile.updateWallet(wallet);
                                        isPassValid = true;
                                    }
                                }

                                isCorrectPass = true;
                            }
                            else if(pass.equalsIgnoreCase("exit")){
                                System.out.println("Exiting, your password has not been changed.");
                                isCorrectPass = true;
                            }
                            else {
                                System.out.print("Incorrect password, please try again.\nPassword:");
                            }
                        }
                        break;
                    case "change username":
                        AccountUi.reenterPassword();
                        isCorrectPass = false;
                        while (!isCorrectPass) {
                            String pass = InputManager.receiveInputLine();
                            if (wallet.getPassWord().equals(pass)) {
                                System.out.println("New username: ");
                                List<String> existingUsernames;
                                try {
                                    existingUsernames = UserNameFileWorkings.userNameFile();
                                } catch (IOException e) {
                                    throw new FinanceException(ExceptionCollection.USERFILE_CREATE_EXCEPTION);
                                }
                                boolean isUserExists = false;
                                while (!isUserExists) {
                                    String tempUser = InputManager.receiveInputLine();
                                    if (existingUsernames.contains(tempUser)) {
                                        System.out.println("This username already exists, please enter another one");
                                    }
                                    else if(tempUser.equals("exit")){
                                        System.out.println("Exiting, your username has not been changed.");
                                        isUserExists = true;
                                    }
                                    else if(tempUser.length()<5){
                                        System.out.println("Username is too short please enter another one");
                                    }
                                    else{
                                        System.out.println("Your username has been successfully changed, please type exit to leave the help menu.");
                                        UserNameFileWorkings.deleteUserName(wallet.getUserName());
                                        try{
                                            AccountHistoryFile.deleteFile(wallet.getUserName());
                                            UserNameFileWorkings.writeToUserNames(tempUser);
                                        }
                                        catch(IOException e){

                                        }
                                        wallet.setUserName(tempUser);
                                        WalletFile.updateWallet(wallet);
                                        isUserExists = true;
                                    }
                                }
                                isCorrectPass = true;
                            }
                            else if(pass.equalsIgnoreCase("exit")){
                                System.out.println("Exiting, your username has not been changed.");
                                isCorrectPass = true;
                            }
                            else {
                                System.out.println("Incorrect password, please try again.\nPassword:");
                            }
                        }
                        break;
                    case "delete account":
                        isCorrectPass = false;
                        AccountUi.reenterPassword();
                        while(!isCorrectPass){
                            String pass = InputManager.receiveInputLine();
                            if(wallet.getPassWord().equals(pass)){
                                return DeleteCommand.handleDelete(wallet);
                            }
                            else if(pass.equalsIgnoreCase("exit")){
                                System.out.println("Exiting, your account has not been deleted.");
                                isCorrectPass = true;
                            }
                            else {
                                System.out.print("Incorrect password, please try again.\nPassword:");
                            }
                        }

                    default:
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
