package seedu.duke.command;

//@@author chinhan99
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;

/**
 * Represents an object that can be inherited by other command objects.
 */
public abstract class Command {
    //@@author wcwy
    // The command word used to trigger the execution of Moolah Manager's operations
    public static String COMMAND_WORD;
    // The description for the usage of command
    public static String COMMAND_DESCRIPTION;
    // The guiding information for the usage of command
    public static String COMMAND_USAGE;
    // The formatting information for the parameters used by the command
    public static String COMMAND_PARAMETERS_INFO;

    //@@author paullowse

    /**
     * Get the default mandatory tags of the command (no mandatory tag).
     * To be overridden by subclasses which the command requires mandatory tag.
     *
     * @return A string array containing all mandatory tags.
     */
    public String[] getMandatoryTags() {
        String[] mandatoryTags = new String[0];
        return mandatoryTags;
    }

    /**
     * Gets the default optional tags of the command (no optional tag).
     * To be overridden by subclasses which the command requires optional tag.
     *
     * @return A string array containing all optional tags.
     */
    public String[] getOptionalTags() {
        String[] optionalTags = new String[0];
        return optionalTags;
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    public abstract void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException;

    /**
     * Enables the program to exit when the Bye command is issued.
     *
     * @return A boolean value that indicates whether the program shall exit.
     */
    public abstract boolean isExit();

    // Methods below are to be overridden by the subclass whenever applicable
    public void setType(String type) {
    }

    public void setDescription(String description) {
    }

    public void setAmount(int amount) {
    }

    public void setBudgetAmount(long amount) {
    }

    public void setCategory(String category) {
    }

    public void setDate(LocalDate date) {
    }

    public void setEntryNumber(int entryNumber) {
    }

    public void setIsDetailedOption(boolean isDetailed) {
    }

    public void setStatsType(String statsType) {
    }

    public void setGlobalMonth(int month) {
    }

    public void setGlobalYear(int year) {
    }

    public void setGlobalNumber(int number) {
    }

    public void setGlobalPeriod(String period) {
    }
}
