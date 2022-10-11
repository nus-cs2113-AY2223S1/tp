package seedu.duke;

import java.util.Scanner;

public class Account {
    public static void accountCommands(Wallet wallet) {
        Ui.accountEntry();
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine().toLowerCase();
        while (!in.equals("bye")) {
            String[] splitInput = in.split(" ");
            String command = splitInput[0];
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
            default:
                //throw COMMAND_NOT_KNOWN;
                System.out.println("Unknown command");
            }
            in = scan.nextLine().toLowerCase();
        }
    }
}
