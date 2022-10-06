package seedu.duke.user;

import seedu.duke.id.IdGenerator;

public class User {
    protected String name;
    protected int age;
    protected String contactNumber;
    protected String userId;

    // add list of items after item class created

    public User(String name, int age, String contactNumber) {
        this.userId = IdGenerator.generateId();
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String toString() {
        return getUserId() + " | " + getName() + " | " + getAge() + " | " + getContactNumber();
    }
}
