package seedu.duke.user;

//@@author chiewyx

/**
 * A representation of user.
 */
public class User {
    protected String name;
    protected int age;
    protected String contactNumber;

    /**
     * Constructor method for user.
     *
     * @param name          The name of the user
     * @param age           The age of the user
     * @param contactNumber The contact number of the user
     */
    public User(String name, int age, String contactNumber) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * Overrides toString method of Object to get string representation of user.
     *
     * @return A string representation of User
     */
    public String toString() {
        String username = "Username: " + name + " ";
        String age = "Age: " + this.age + " ";
        String contactNumber = "Contact: " + this.contactNumber + " ";
        return username + age + contactNumber;
    }

    /**
     * Formats the user information to store in memory.
     *
     * @return A formatted string of user information
     */
    public String convertItemToFileFormat() {
        String separator = " | ";
        return name + separator + age + separator + contactNumber;
    }
}
