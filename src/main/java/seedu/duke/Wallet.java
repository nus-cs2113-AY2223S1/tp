package seedu.duke;

public class Wallet {

    protected String userName;
    protected String passWord;
    protected Integer balance; //Integer should be fine now of up to 2,147,483,647, long seems to much

    public Wallet(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        balance = 0;
    }

    public Wallet(String userName, String passWord, Integer balance) {
        this.userName = userName;
        this.passWord = passWord;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
