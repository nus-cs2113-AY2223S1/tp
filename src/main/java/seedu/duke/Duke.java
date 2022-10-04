package seedu.duke;

import seedu.duke.data.transaction.Transaction;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        String inData;
        Scanner scan = new Scanner(System.in);
        Ui.showGreeting();

        while (scan.hasNextLine() && true) {
            // continuously receive user input
            inData = scan.nextLine();
            inData = inData.trim();

            if (!Parser.processInput(inData, transactions)) {
                break;
            }
        }
    }
}
