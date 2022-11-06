package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.CurrencyStructure;
import seedu.duke.Wallet;
import seedu.duke.account.Account;
import seedu.duke.account.Deposit;
import seedu.duke.exception.FinanceException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTestHelpCentre {
    CurrencyStructure USD = new CurrencyStructure("usd", "us dollar", "$", 1);
    List<Deposit> depositList = new ArrayList<>();
    Wallet filledWallet = new Wallet("FilledWalletUser" , "FilledWalletPass", USD, 1000, depositList);
    @Test
    public void testHelpCentre() throws FinanceException, IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);
        Account account = new Account(filledWallet);

        boolean accountRequest = account.helpCenter();

        assertEquals(false, accountRequest);
        //System.setIn(sysInBackup);
    }
}
