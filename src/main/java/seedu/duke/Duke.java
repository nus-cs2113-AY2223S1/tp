package seedu.duke;

import seedu.duke.data.TransactionList;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TransactionList transactions = new TransactionList();
        String inData;
        Scanner scan = new Scanner(System.in);
        Ui.showGreeting();

        while (scan.hasNextLine()) {
            // Receives user input continuously
            inData = scan.nextLine();
            inData = inData.trim();

            if (!Parser.parse(inData, transactions)) {
                break;
            }
        }
    }
}
