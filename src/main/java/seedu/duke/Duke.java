package seedu.duke;

import seedu.duke.data.TransactionList;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TransactionList transactions = new TransactionList();
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
