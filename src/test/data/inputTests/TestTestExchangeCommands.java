package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.Currency;
import seedu.duke.CurrencyStructure;
import seedu.duke.Wallet;
import seedu.duke.account.Deposit;
import seedu.duke.exception.FinanceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestTestExchangeCommands {
    CurrencyStructure USD = new CurrencyStructure("usd", "us dollar", "$", 1);
    List<Deposit> depositList = new ArrayList<>();
    Wallet filledWallet = new Wallet("FilledWalletUser" , "FilledWalletPass", USD, 1000, depositList);
    @Test
    public void testExchangeCommands() throws FinanceException {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);
        Currency.exchangeCommands(USD);
        //System.setIn(sysInBackup);
    }
}
