package seedu.duke;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.account.Account;
import seedu.duke.account.DeleteCommand;
import seedu.duke.account.Deposit;
import seedu.duke.account.MoneyCommand;
import seedu.duke.authentication.Authentication;
import seedu.duke.authentication.LoginCommand;
import seedu.duke.authentication.RegisterCommand;
import seedu.duke.exception.FinanceException;
import seedu.duke.newcurrency.NewCurrency;
import seedu.duke.newcurrency.PersonalCurrencyList;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static seedu.duke.newcurrency.PersonalCurrencyList.personalCurrencyList;
import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    Wallet emptyWallet = new Wallet("EmptyWalletUser" , "EmptyWalletPass");
    CurrencyStructure USD = new CurrencyStructure("usd", "us dollar", "$", 1.0);
    Deposit deposit = new Deposit(USD, 1000);
    List<Deposit> depositList = new ArrayList<>();

    Wallet filledWallet = new Wallet("FilledWalletUser" , "FilledWalletPass", USD, 1000, depositList);

    Path path = Paths.get("src","test","data");
    List<String> usernamesList = new ArrayList<>(
            Arrays.asList("markustei",
                    "dukeman",
                    "finefood good food",
                    "f1 big car fan"));


    List<String> currUsd = new ArrayList<>(
            Arrays.asList("usd,us dollar $",
                    "1")
    );
    List<String> currEur = new ArrayList<>(
            Arrays.asList("eur,euro €",
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

    DukeTest() throws FinanceException {
    }

    @BeforeEach
    public void setUpBeforeEachClass() throws FinanceException {
        CurrencyList.initializeCurrencyList();
    }

    @Test
    public void testEmptyWalletBalance() {
        assertEquals(0, emptyWallet.getTotalBalance());
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
        assertEquals(1000, filledWallet.getTotalBalance());
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
    public void testRetrievingUserNames() throws FileNotFoundException, FinanceException {
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

    //checks that the index is correctly retrieved
    @Test public void testGettingCurrencyIndex () throws FinanceException {
        int actualCurrencyIndex = Currency.findIndexOfCurrency("inr", currenciesList);
        assertEquals(3, actualCurrencyIndex);
    }

    @Test public void testDepositGetCurrency() {
        CurrencyStructure currency = deposit.getCurrency();
        assertEquals(USD, currency);
    }

    @Test public void testDepositGetBalance() {
        Double balance = deposit.getBalance();
        assertEquals(1000, balance);
    }

    @Test public void testSave(){
        Deposit newDeposit = new Deposit(USD, 10.0);
        assertThrows(FinanceException.class, () -> newDeposit.save(-20.0));
    }

    @Test public void testSaveTrue() throws FinanceException {
        deposit.save(90.0);
        assertEquals(1090.0, deposit.getBalance());
    }

    @Test public void testWithdraw(){
        deposit.withdraw(100.0);
        assertEquals(900.0, deposit.getBalance());
    }

    @Test public void testSaveCommandException(){
        assertThrows(FinanceException.class, () -> MoneyCommand.saveCommand(filledWallet, "a b c d a a a"));
    }

    @Test public void testSaveCommand() throws FinanceException {
        MoneyCommand.saveCommand(filledWallet, "100");
        assertEquals(1100, filledWallet.getTotalBalance());
    }

    @Test public void testSaveCommandNewCurrency() throws FinanceException {
        MoneyCommand.saveCommand(filledWallet, "sgd 100");
        assertEquals(1070.4671975195547, filledWallet.getTotalBalance());
    }

    @Test public void testWithdrawCommandException(){
        assertThrows(FinanceException.class, () -> MoneyCommand.withdrawCommand(filledWallet, "a b c d a a a"));
    }

    @Test public void testWithdrawCommandNewCurrency() throws FinanceException {
        MoneyCommand.withdrawCommand(filledWallet, "usd 100");
        assertEquals(900, filledWallet.getTotalBalance());
    }


    @Test public void testTransferWithdrawCommandException() throws FinanceException {
        assertThrows(FinanceException.class, () -> MoneyCommand.transferWithdrawCommand(filledWallet, "a"));
    }

    @Test public void testTransferWithdrawCommand() throws FinanceException {
        MoneyCommand.transferWithdrawCommand(filledWallet, "100");
        assertEquals(900, filledWallet.getTotalBalance());
    }
    @Test public void testTransferWithdrawCommandNewCurrency() throws FinanceException {
        MoneyCommand.transferWithdrawCommand(filledWallet, "usd 100");
        assertEquals(900, filledWallet.getTotalBalance());
    }

    @Test public void testConvertAllCommand() throws FinanceException {
        MoneyCommand.saveCommand(filledWallet, "sgd 100");
        MoneyCommand.convertAllCommand(filledWallet, "usd");
        assertEquals(1070.4671975195547, filledWallet.getTotalBalance());
    }

    @Test public void testTransferSaveCommandException() throws FinanceException {
        assertThrows(FinanceException.class, () -> MoneyCommand.transferWithdrawCommand(filledWallet, "a"));
    }

    @Test public void testTransferSaveCommandNewCurrency() throws FinanceException {
        MoneyCommand.transferSaveCommand(filledWallet, "usd 100");
        assertEquals(1100, filledWallet.getTotalBalance());
    }

    @Test public void testParsePositiveAmount() throws FinanceException {
        Double amount = MoneyCommand.parsePositiveAmount("100");
        assertEquals(100.0, amount);
    }

    @Test public void testParsePositiveAmountException() throws FinanceException {
        assertThrows(FinanceException.class, () -> MoneyCommand.parsePositiveAmount("-10"));
    }

    @Test public void testParsePositiveAmountException2() throws FinanceException {
        assertThrows(FinanceException.class, () -> MoneyCommand.parsePositiveAmount("a"));
    }

    @Test public void testParseAmount() throws FinanceException {
        Double amount = MoneyCommand.parseAmount("100");
        assertEquals(100.0, amount);
    }

    @Test public void testParseAmountNeg() throws FinanceException {
        Double amount = MoneyCommand.parseAmount("-100");
        assertEquals(-100.0, amount);
    }

    @Test public void testParsePositiveException2() throws FinanceException {
        assertThrows(FinanceException.class, () -> MoneyCommand.parseAmount("a"));
    }



    @Test
    public void testReadInCurrencies() throws FinanceException {
        List<CurrencyStructure> currencies = PersonalCurrencyList.readInCurrencies();
        assertEquals(0,currencies.size());
    }

    @Test
    public void testGetPersonalCurrencyFileLine() {
        String line = PersonalCurrencyList.getPersonalCurrencyFileLine("btc","bitcoin", "bc", 10.0);
        assertEquals("btc,bitcoin,bc,10.0" , line);
    }

    @Test
    public void testGetListOfAllCurrencies() throws FinanceException {
        List<CurrencyStructure> currencies = Currency.getListOfAllCurrencies();
        assertEquals(54, currencies.size());
    }

    @Test
    public void testReadInCurrencies2() throws FinanceException {
        List<CurrencyStructure> currencies = Currency.readInCurrencies();
        assertEquals(54, currencies.size());
    }




    // I had to put all tests under one method so i could use one System.setIn, otherwise
    //it wouldn't work
    @Test public void testHandleDeleteNo() throws FinanceException, IOException {
        CurrencyList.initializeCurrencyList();
        String simulatedUserInput = "no" + System.getProperty("line.separator") + "logout" + System.getProperty("line.separator")
                + "exit" + System.getProperty("line.separator")
                + "user" + System.getProperty("line.separator") + "exit" + System.getProperty("line.separator")
                + "exit" + System.getProperty("line.separator") + "pass" + System.getProperty("line.separator") //testLoginCommand2
                +"Test" + System.getProperty("line.separator") + "TestPass"
                + System.getProperty("line.separator")
                + "btc" +
                System.getProperty("line.separator")
                + "bitcoin"
                + System.getProperty("line.separator")
                + "bc"
                + System.getProperty("line.separator")
                + "10"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator") + "btc" + System.getProperty("line.separator")
                + "exit" + System.getProperty("line.separator")
                + "conversion" + System.getProperty("line.separator")
                + "exit" + System.getProperty("line.separator")
                + "y" + System.getProperty("line.separator")
                + "yes" + System.getProperty("line.separator");


        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);

        boolean isDeleted = DeleteCommand.handleDelete(filledWallet);

        assertEquals(false, isDeleted);//testHandleDeleteNo

        Account account = new Account(filledWallet);
        boolean accountRequest = account.handleAccountRequest();

        assertEquals(true, accountRequest);//testAccount

        boolean accountReq = account.helpCenter();

        assertEquals(false, accountReq); //testAccount

        LoginCommand login = new LoginCommand();
        assertThrows(FinanceException.class, () -> login.handleLogin()); //testLoginCommandExit

        LoginCommand login2 = new LoginCommand();
        assertThrows(FinanceException.class, () -> login2.handleLogin()); //testLoginCommandExit2

        RegisterCommand.handleRegister(); //Register new account

        NewCurrency.addNewCurrency(); //testAddingNewCurrency

        NewCurrency.removeCurrency(); //testRemoveNewCurrency

        Currency.exchangeCommands(USD); //testExchangeCommands

        Currency.exchangeCommands(USD); //testExchangeCommands2

        Wallet testWallet = WalletFile.getWallet("Test");

        boolean isDeletedTest = DeleteCommand.handleDelete(testWallet);
        assertEquals(true, isDeletedTest); //testHandleDeleteYes


    }

    @Test
    public void testGetListOfAllCurrenciesList() throws FinanceException {
        List<CurrencyStructure> currencies = CurrencyList.getListOfAllCurrencies();
        assertEquals(54, currencies.size());
    }

    @Test
    public void testReadInCurrenciesList() throws FinanceException {
        List<CurrencyStructure> currencies = CurrencyList.readInCurrencies();
        assertEquals(54, currencies.size());
    }


    @Test
    public void testGetAbbrName() {
        assertEquals("usd", USD.getAbbrName());
    }

    @Test
    public void testGetFullName() {
        assertEquals("us dollar", USD.getFullName());
    }

    @Test
    public void testGetSymbol() {
        assertEquals("$", USD.getSymbol());
    }

    @Test
    public void testGetRate() {
        assertEquals(1.0, USD.getRate());
    }

    @Test
    public void testIsSameCurrency() {
        assertEquals(true, USD.isSameCurrency(USD));
    }

    @Test
    public void testIsMatchedCurrencyByAbbrName() throws FinanceException {
        assertEquals(true, USD.isMatchedCurrencyByAbbrName("usd"));
        DeleteCommand.handleDelete(filledWallet);
    }
}
