package seedu.duke.commands;

import seedu.duke.UI;
import seedu.duke.timetable.Timetable;

import java.util.logging.Logger;

//@@author HT-T

public class CommandDeleteModule {

    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Delete a module that is in the timetable.
     *
     * @return Delete successful message. If error occurs, return appropriate error message.
     */
    public static String deleteModule() {

        int listLength = Timetable.getListLength();

        if (listLength == 0) {
            return "You have no modules to delete now!";
        }


        String indexToSet = "0";
        boolean isTryAgain = false;

        while (isInvalidInput(indexToSet, listLength)) {
            if (isTryAgain) {
                System.out.println("Please try again!\n");
            }

            System.out.println("Which module you would like to delete? "
                    + "Please enter the index of that module. ");

            System.out.println(Timetable.listModules());

            indexToSet = UI.sc.nextLine();

            assert indexToSet != null;

            try {
                Timetable.deleteModule(Integer.parseInt(indexToSet));
            } catch (NumberFormatException e) {
                return "Please input an integer!";
            } catch (IndexOutOfBoundsException e) {
                return "Please input an index in range!";
            }

            isTryAgain = true;

            lgr.fine("Delete Module command run successful, no runtime error");
        }
        return "Successfully deleted module!";
    }

    /**
     * Checks if an input is inside index bounds of lesson list.
     *
     * @param index User's input index.
     * @return True if index is within lesson list index bounds, false otherwise.
     */
    private static boolean isInvalidInput(String index, int length) {
        boolean isInteger = isInteger(index);
        boolean lessThanLength = Integer.parseInt(index) < 1;
        boolean moreThanLength = Integer.parseInt(index) > length;
        boolean isInvalidFlag = !isInteger || lessThanLength || moreThanLength;
        return isInvalidFlag;
    }

    /**
     * Checks if an input is integer.
     *
     * @param str User's input index.
     * @return True if index is an integer, false otherwise.
     */
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
