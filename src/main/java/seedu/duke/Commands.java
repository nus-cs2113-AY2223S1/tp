package seedu.duke;


public class Commands {
    protected ReviewList reviewList;

    public Commands(ReviewList reviews) {
        this.reviewList = reviews;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return the output message from the command
     */
    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

}
