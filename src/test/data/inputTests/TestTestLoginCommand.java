package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.CurrencyList;
import seedu.duke.authentication.LoginCommand;
import seedu.duke.authentication.RegisterCommand;
import seedu.duke.exception.FinanceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestTestLoginCommand {
    @Test
    public void testLoginCommand() throws FinanceException {
        CurrencyList.initializeCurrencyList();
        String test =
                "FilledWalletUser" +
                        System.getProperty("line.separator")
                        + "FilledWalletPass"
                        + System.getProperty("line.separator")
                        + "FilledWalletUser" +
                        System.getProperty("line.separator")
                        + "FilledWalletPass"
                        + System.getProperty("line.separator")
                        + "log out"
                        + System.getProperty("line.separator")
                        + "bye"
                        + System.getProperty("line.separator") ;
        ByteArrayInputStream in = new ByteArrayInputStream(test.getBytes());
        System.setIn(in);
        RegisterCommand.handleRegister();

        LoginCommand login = new LoginCommand();
        login.handleLogin();
    }
}
