package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DukeTest {
    Duke duke = new Duke();
    Wallet emptyWallet = new Wallet("EmptyWalletUser" , "EmptyWalletPass");
    Wallet filledWallet = new Wallet("FilledWalletUser" , "FilledWalletPass", 1000);


    @Test
    public void testEmptyWalletBalance() {
        assertEquals(0, emptyWallet.getBalance());
    }

    @Test
    public void testEmptyWalletUserName() {
        assertEquals("EmptyWalletUser", emptyWallet.getUserName());
    }

    @Test
    public void testEmptyWalletPassWord() {
        assertEquals("EmptyWalletPass", emptyWallet.getPassWord());
    }

    @Test
    public void testFilledWalletBalance() {
        assertEquals(1000, filledWallet.getBalance());
    }

    @Test
    public void testFilledWalletUserName() {
        assertEquals("FilledWalletUser", filledWallet.getUserName());
    }

    @Test
    public void testFilledWalletPassWord() {
        assertEquals("FilledWalletPass", filledWallet.getPassWord());
    }

    @Test
    public void setWalletUserName() {
        emptyWallet.setUserName("ChangedUserName");
        assertEquals("ChangedUserName", emptyWallet.getUserName());
    }

    @Test
    public void setWalletPassName() {
        emptyWallet.setPassWord("ChangedPassWord");
        assertEquals("ChangedPassWord", emptyWallet.getPassWord());
    }


}
