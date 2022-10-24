package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import seedu.duke.account.Deposit;
import seedu.duke.authentication.Authentication;
import seedu.duke.exception.FinanceException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DukeTest {
    Wallet emptyWallet = new Wallet("EmptyWalletUser" , "EmptyWalletPass");
    CurrencyStructure USD = new CurrencyStructure("usd", "us dollar", "$", 1);
    Deposit deposit = new Deposit(USD, 1000);
    List<Deposit> depositList = new ArrayList<>();
    //depositList.add(deposit);
    Wallet filledWallet = new Wallet("FilledWalletUser" , "FilledWalletPass", USD, 1000, depositList);

    Path path = Paths.get("src","test","data");
    List<String> usernamesList = new ArrayList<>(
            Arrays.asList("markustei",
                    "dukeman",
                    "finefood good food",
                    "f1 big car fan"));


    List<String> currUsd = new ArrayList<>(
            Arrays.asList("usd us dollar $",
                    "1")
    );
    List<String> currEur = new ArrayList<>(
            Arrays.asList("eur euro €",
                    "1.0152")
    );
    List<String> currGbp = new ArrayList<>(
            Arrays.asList("gbp british pound sterling £",
                    "0.8818")
    );
    List<String> currInr = new ArrayList<>(
            Arrays.asList("inr indian rupee ₹",
                    "82.2626")
    );
    List<String> currArp = new ArrayList<>(
            Arrays.asList("arp argentine peso arp$",
                    "152.5054")
    );

    List<List<String>> currenciesList = new ArrayList<>(
            List.of(currUsd,
                    currEur,
                    currGbp,
                    currInr,
                    currArp)
    );




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

    //Checks whether retrieved usernames match the list we expect to get
    @Test
    public void testRetrievingUserNames() throws FileNotFoundException {
        List<String> retrievedUsernames = UserNameFileWorkings.getUserNames(path);
        assertEquals(usernamesList,retrievedUsernames);
    }

    //Testing that the input is transformed to correct Command type
    @Test
    public void testGettingCommand() throws FinanceException {
        Commands expectedCommand = Commands.LOGIN;
        Commands retrievedCommand = Authentication.getCommandType("login");
        assertSame(expectedCommand,retrievedCommand);
    }

    @Test
    public void testReadingInCurrencies (){
        try {
            List<List<String>> retrievedUsernames = Currency.readInCurrencies(path);
            assertEquals(currenciesList, retrievedUsernames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //checks that the index is correctly retrieved
    @Test public void testGettingCurrencyIndex () throws FinanceException {
        int actualCurrencyIndex = Currency.findIndexOfCurrency("inr", currenciesList);
        assertEquals(3, actualCurrencyIndex);
    }
}
