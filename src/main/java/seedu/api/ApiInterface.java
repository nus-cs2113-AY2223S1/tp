package seedu.api;

import seedu.exception.EmptyResponseException;
import seedu.exception.FileWriteException;
import seedu.exception.UnauthorisedAccessApiException;

/**
 * Interface for the purpose of production class and unit testing.
 */
public interface ApiInterface {
    void syncFetchData()
            throws FileWriteException, EmptyResponseException, UnauthorisedAccessApiException;
}
