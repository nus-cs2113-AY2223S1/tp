package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceException;
import seedu.duke.newcurrency.NewCurrency;
import seedu.duke.newcurrency.PersonalCurrencyList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.newcurrency.PersonalCurrencyList.personalCurrencyList;

public class TestTestAddNewCurrency {

    @Test
    public void testAddNewCurrency() throws FinanceException, IOException {
        InputStream sysInBackup = System.in;
        PersonalCurrencyList.initializeCurrencyList();
        String simulatedUserInput = "btc" +
                System.getProperty("line.separator")
                + "bitcoin"
                + System.getProperty("line.separator")
                + "bc"
                + System.getProperty("line.separator")
                + "10"
                + System.getProperty("line.separator");
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);
        NewCurrency.addNewCurrency();

        assertEquals(1, personalCurrencyList.size());
        //System.setIn(sysInBackup);
    }
}
