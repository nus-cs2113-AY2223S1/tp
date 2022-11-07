package seedu.duke.inputTests;

import org.junit.jupiter.api.Test;
import seedu.duke.authentication.RegisterCommand;
import seedu.duke.exception.FinanceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestTestRegisterCommand {
    @Test
    public void testRegisterCommand() throws FinanceException {
        InputStream sysInBackup = System.in;
        String simulatedUserInput = "Test" +
                System.getProperty("line.separator")
                + "TestPass"
                + System.getProperty("line.separator")
                + "log out"
                + System.getProperty("line.separator")
                + "exit"
                + System.getProperty("line.separator")
                + "bye"
                + System.getProperty("line.separator") ;
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);
        RegisterCommand.handleRegister();
        //System.setIn(sysInBackup);
    }
}
