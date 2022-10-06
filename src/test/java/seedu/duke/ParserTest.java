package seedu.duke;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private Parser parser;
    private ArrayList<Transaction> transactions;

    @BeforeEach
    public void setUp() {
        TransactionList inputArray = new TransactionList();
    }

    @Test
    public void parse_helpCommand_parsedCorrectly() {
        TransactionList inputArray = new TransactionList();
        final String input = "help";
        boolean output = Parser.parse(input, inputArray);
        assertEquals(output, "True");
    }
}
