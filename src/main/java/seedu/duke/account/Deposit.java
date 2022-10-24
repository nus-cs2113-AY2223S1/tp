package seedu.duke.account;

import seedu.duke.CurrencyStructure;

public class Deposit {
    protected CurrencyStructure currency;
    protected double balance;
    
    public Deposit(CurrencyStructure currency, double balance){
        this.currency = currency;
        this.balance = balance;
    }

    public CurrencyStructure getCurrency(){
        return currency;
    }

    public double getBalance(){
        return balance;
    }

    public void save(Double amount){
        balance = balance + amount;
    }

    public void withdraw(Double amount){
        balance = balance - amount;
    }
}
