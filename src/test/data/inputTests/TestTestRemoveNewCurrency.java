package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceException;
import seedu.duke.newcurrency.NewCurrency;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestTestRemoveNewCurrency {

    @Test
    public void testRemoveNewCurrency() throws FinanceException, IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "btc" +
                System.getProperty("line.separator")
                + "bitcoin"
                + System.getProperty("line.separator")
                + "bc"
                + System.getProperty("line.separator")
                + "10"
                + System.getProperty("line.separator")
                + "btc"
                + System.getProperty("line.separator");
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);
        NewCurrency.addNewCurrency();

        NewCurrency.removeCurrency();
        //System.setIn(sysInBackup);
    }
}
