package seedu.duke.user;

public class User {
    protected String name;
    protected int age;
    protected String contactNumber;

    public User(String name, int age, String contactNumber) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String username = "Username: " + name + " ";
        String age = "Age: " + this.age + " ";
        String contactNumber = "Contact: " + this.contactNumber + " ";
        return username + age + contactNumber;
    }
}
