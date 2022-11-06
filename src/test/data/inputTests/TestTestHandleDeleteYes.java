package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.CurrencyStructure;
import seedu.duke.Wallet;
import seedu.duke.account.DeleteCommand;
import seedu.duke.account.Deposit;
import seedu.duke.exception.FinanceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTestHandleDeleteYes {
    CurrencyStructure USD = new CurrencyStructure("usd", "us dollar", "$", 1);
    List<Deposit> depositList = new ArrayList<>();
    Wallet filledWallet = new Wallet("FilledWalletUser" , "FilledWalletPass", USD, 1000, depositList);
    @Test
    public void testHandleDeleteYes() throws FinanceException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
        System.setIn(in);

        boolean isDeleted = DeleteCommand.handleDelete(filledWallet);
        assertEquals(true, isDeleted);
        //System.setIn(sysInBackup);
    }
}
