package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.authentication.LoginCommand;
import seedu.duke.exception.FinanceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTestLoginCommandExit {
    @Test
    public void testLoginCommandExit() throws FinanceException {
        InputStream sysInBackup = System.in;
        String simulatedUserInput = "user" + System.getProperty("line.separator")
                + "exit" + System.getProperty("line.separator");
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);

        LoginCommand login = new LoginCommand();
        assertThrows(FinanceException.class, () -> login.handleLogin());
        //System.setIn(sysInBackup);
    }
}
