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
}
