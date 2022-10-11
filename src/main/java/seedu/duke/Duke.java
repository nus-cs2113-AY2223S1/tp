package seedu.duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.duke.FinanceException.exceptionCollection;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Ui.showWelcomeMessage();

        List<String> existingUserNames;

        existingUserNames = UserNameFileWorkings.userNameFile();

        boolean isProgramEnd = false;

        while (!isProgramEnd) {
            try {
                Ui.showPromptInfo();
                Commands commandType;
                try {
                    commandType = Commands.valueOf(in.nextLine().toUpperCase());
                }
                catch (IllegalArgumentException e) {
                    throw new FinanceException(exceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
                switch (commandType) {
                    case REGISTER:
                        Ui.showRegisterInfo();

                        String userName = " ";

                        //checks if the username already exists
                        do {
                            System.out.print("Username: ");
                            userName = in.nextLine();
                            System.out.println(" ");
                        } while (existingUserNames.contains(userName));

                        int passWordLength = 0;
                        String passWord = "";
                        do {
                            System.out.print("Password: ");
                            passWord = in.nextLine();
                            System.out.println(" ");
                            passWordLength = passWord.length();
                        } while (passWordLength > 8);

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
                                Ui.usernameLogin();
                                userName = in.nextLine();
                                Wallet wallet = WalletFile.getWallet(userName);
                                Ui.passwordLogin();
                                passWord = in.nextLine();
                                if(wallet.getPassWord().equals(passWord)){
                                    user = true;
                                    System.out.println("Successful login, you can now access your account.");
                                    Account.accountCommands(wallet);
                                }
                                else{
                                    System.out.println("Your password is incorrect, please re-enter your login information.");
                                }
                            }
                            catch (FileNotFoundException e) {
                                System.out.println("Username not found, please enter another username");
                            }
                        } while(!user);
                        break;
                    default:
                        throw new FinanceException(exceptionCollection.COMMAND_TYPE_EXCEPTION);
                }
            }
            catch (FinanceException e) {
                e.handleException();
            }
        }


    }
}
