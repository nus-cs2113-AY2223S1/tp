package seedu.duke;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.account.Deposit;

public class Wallet {
    final static CurrencyStructure INITIAL_CURRENCY = new CurrencyStructure("usd", "us dollar", "$", 1);

    protected String userName;
    protected String passWord;
    protected CurrencyStructure defaultCurrency;
    protected double totalBalance;
    protected List<Deposit> deposits;

    public Wallet(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        totalBalance = 0;
        defaultCurrency = INITIAL_CURRENCY;
        deposits = new ArrayList<>();
    }

    public Wallet(String userName, String passWord, CurrencyStructure currency, double balance,
            List<Deposit> deposits) {
        this.userName = userName;
        this.passWord = passWord;
        this.totalBalance = balance;
        this.defaultCurrency = currency;
        this.deposits = deposits;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public CurrencyStructure getDefaultCurrency() {
        return defaultCurrency;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setDefaultCurrency(CurrencyStructure defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void saveMoney(CurrencyStructure currency, double amount) {
        changeMoney(currency, amount);
    }

    public void withdrawMoney(CurrencyStructure currency, double amount) {
        changeMoney(currency, -amount);
    }

    private boolean changeMoney(CurrencyStructure currency, double amount) {
        boolean hasDeposit = false;
        for (Deposit deposit : deposits) {
            CurrencyStructure currencyCompared = deposit.getCurrency();
            if (currency.isSameCurrency(currencyCompared)) {
                hasDeposit = true;
                deposit.save(amount);
                break;
            }
        }
        if (!hasDeposit) {
            Deposit deposit = new Deposit(currency, amount);
            deposits.add(deposit);
        }
        updateTotalBalance(currency, amount);
        return hasDeposit;
    }

    private void updateTotalBalance(CurrencyStructure currency, double amount) {
        double defaultRate = defaultCurrency.getRate();
        double rate = currency.getRate();
        totalBalance += amount * (defaultRate / rate);
    }
}
