//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_ADDRESS_FORMAT_UNIT_TYPE_MISMATCH;

/**
 * Represents exception when there is a mismatch between address format and unit type when adding property.
 * Landed Property unitType should not have unit level and vice versa.
 */
public class AddressFormatUnitTypeMismatchException extends ParseAddPropertyException {
    @Override
    public String toString() {
        return MESSAGE_ADDRESS_FORMAT_UNIT_TYPE_MISMATCH;
    }
}
