package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello and welcome to Currency Manager.\n "
                +
                "Would you like to login or register?");

        List<String> existingUserNames;

        existingUserNames = UserNameFileWorkings.userNameFile();

        boolean bye = false;

        while (!bye) {
            try {
                System.out.println("Would you like to register or login?");
                Commands commandType = Commands.valueOf(in.nextLine().toUpperCase());
                switch (commandType) {
                case REGISTER:
                    System.out.println("Nice, we will be creating you a new wallet right away, but first of all, \n "
                            +
                            "please first add your username and then a password for your wallet");

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
                    System.out.println("Nice, a new wallet has been created for you, \nyou can now login to it");
                    break;

                case BYE:
                    bye = true;
                    break;
                case LOGIN:
                    System.out.println("login will be implemented soon");
                    break;
                default:
                    System.out.println("We aren't sure what you are trying to say.");
                }
            } catch (Exception e) {
                System.out.println("We aren't sure what you are trying to say.");
            }
        }


    }
}
