package seedu.duke.exception.parsefindexception;


import static seedu.duke.Messages.MESSAGE_NO_FIND_CLIENT_TAG;

public class NoFindClientTagException extends ParseFindException {

    @Override
    public String toString() {
        return MESSAGE_NO_FIND_CLIENT_TAG;
    }
}
