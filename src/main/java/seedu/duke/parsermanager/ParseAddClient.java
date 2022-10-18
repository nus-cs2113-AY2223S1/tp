package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddClient;
import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.MissingFlagException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.CommandStructure.ADD_CLIENT_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_ADD_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_CONTACT_NUMBER;
import static seedu.duke.Messages.MESSAGE_INVALID_EMAIL;
import static seedu.duke.Messages.MESSAGE_INVALID_BUDGET_FORMAT;

public class ParseAddClient extends Parser {
    private final String commandDescription;

    private static final int CLIENT_NAME_INDEX = 0;
    private static final int CLIENT_CONTACT_NUMBER_INDEX = 1;
    private static final int CLIENT_EMAIL_INDEX = 2;
    private static final int CLIENT_BUDGET_INDEX = 3;

    private static final int MISSING_FLAG_VALUE = -1;
    private static final int FLAG_JUMPER_VALUE = 2;


    public ParseAddClient(String addCommandDescription) {
        this.commandDescription = addCommandDescription;
    }

    public Command parseCommand() throws MissingFlagException, IncorrectFlagOrderException, EmptyDetailException,
            InvalidContactNumberException, InvalidEmailException, InvalidBudgetFormatException {
        try {
            checkForEmptyDetails(commandDescription);
            ArrayList<String> clientDetails = processCommandAddClientDetails(commandDescription);
            validateClientDetails(clientDetails);
            return new CommandAddClient(clientDetails);
        } catch (EmptyDetailException e) {
            throw new EmptyDetailException(MESSAGE_ADD_CLIENT_WRONG_FORMAT);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_ADD_CLIENT_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_ADD_CLIENT_WRONG_FORMAT);
        } catch (InvalidContactNumberException e) {
            throw new InvalidContactNumberException(MESSAGE_INVALID_CONTACT_NUMBER);
        } catch (InvalidEmailException e) {
            throw new InvalidEmailException(MESSAGE_INVALID_EMAIL);
        } catch (InvalidBudgetFormatException e) {
            throw new InvalidBudgetFormatException(MESSAGE_INVALID_BUDGET_FORMAT);
        }
    }

    private void checkForEmptyDetails(String commandDetail) throws EmptyDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyDetailException(EXCEPTION);
        }
    }

    private boolean isEmptyString(String commandDetail) {
        return commandDetail.trim().isEmpty();
    }

    private ArrayList<String> processCommandAddClientDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {
        String[] flags = ADD_CLIENT_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingClientFlags(flagIndexPositions);
        checkClientFlagsOrder(flagIndexPositions);
        return extractClientDetails(rawCommandDetail, flagIndexPositions);
    }

    private int[] getFlagIndexPositions(String commandDetail, String[] flags) {
        int[] flagIndexPositions = new int[flags.length];

        for (int flagIndex = 0; flagIndex < flags.length; flagIndex++) {
            flagIndexPositions[flagIndex] = commandDetail.indexOf(flags[flagIndex]);
        }
        return flagIndexPositions;
    }

    private void checkForMissingClientFlags(int[] flagIndexPositions) throws MissingFlagException {
        for (int flagIndex = 0; flagIndex < flagIndexPositions.length; flagIndex++) {
            boolean isEmailIndex = (flagIndex == CLIENT_EMAIL_INDEX);
            //Skip empty check for email as email is optional
            if (!isEmailIndex && !isFlagPresent(flagIndex)) {
                throw new MissingFlagException(EXCEPTION);
            }
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != MISSING_FLAG_VALUE);
    }

    private void checkClientFlagsOrder(int[] flagIndexPositions) throws IncorrectFlagOrderException {
        checkForCorrectFlagOrder(flagIndexPositions[CLIENT_NAME_INDEX],
                flagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX]);
        boolean hasEmail = (flagIndexPositions[CLIENT_EMAIL_INDEX] != MISSING_FLAG_VALUE);
        if (hasEmail) {
            checkForCorrectFlagOrder(flagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX],
                    flagIndexPositions[CLIENT_EMAIL_INDEX]);
            checkForCorrectFlagOrder(flagIndexPositions[CLIENT_EMAIL_INDEX], flagIndexPositions[CLIENT_BUDGET_INDEX]);
        }  else {
            checkForCorrectFlagOrder(flagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX],
                    flagIndexPositions[CLIENT_BUDGET_INDEX]);
        }
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException(EXCEPTION);
        }
    }

    private ArrayList<String> extractClientDetails(String rawClientDetail, int[] addClientFlagIndexPositions) {
        boolean hasEmail = (addClientFlagIndexPositions[CLIENT_EMAIL_INDEX] != MISSING_FLAG_VALUE);
        String clientContactNumber;
        String clientEmail = "";
        if (hasEmail) {
            clientContactNumber = extractDetail(rawClientDetail,
                    addClientFlagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX] + FLAG_JUMPER_VALUE,
                    addClientFlagIndexPositions[CLIENT_EMAIL_INDEX]);
            clientEmail = extractDetail(rawClientDetail, addClientFlagIndexPositions[CLIENT_EMAIL_INDEX]
                    + FLAG_JUMPER_VALUE, addClientFlagIndexPositions[CLIENT_BUDGET_INDEX]);
        } else {
            clientContactNumber = extractDetail(rawClientDetail,
                    addClientFlagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX] + FLAG_JUMPER_VALUE,
                    addClientFlagIndexPositions[CLIENT_BUDGET_INDEX]);
        }
        String clientName = extractDetail(rawClientDetail, addClientFlagIndexPositions[CLIENT_NAME_INDEX]
                + FLAG_JUMPER_VALUE, addClientFlagIndexPositions[CLIENT_CONTACT_NUMBER_INDEX]);
        String clientBudgetPerMonth = extractDetail(rawClientDetail,
                addClientFlagIndexPositions[CLIENT_BUDGET_INDEX] + FLAG_JUMPER_VALUE);

        ArrayList<String> extractedClientDetails = new ArrayList<>();
        extractedClientDetails.add(clientName.trim());
        extractedClientDetails.add(clientContactNumber.trim());
        extractedClientDetails.add(clientEmail.trim());
        extractedClientDetails.add(clientBudgetPerMonth.trim());
        return extractedClientDetails;
    }

    private static String extractDetail(String rawDetail, int beginIndex) {
        return rawDetail.substring(beginIndex).trim();
    }

    private static String extractDetail(String rawDetail, int beginIndex, int endIndex) {
        return rawDetail.substring(beginIndex, endIndex).trim();
    }

    private void validateClientDetails(ArrayList<String> clientDetails) throws EmptyDetailException,
            InvalidContactNumberException, InvalidEmailException, InvalidBudgetFormatException {
        //Checks for Missing Client Name, Contact Number, Budget Per Month (SGD)
        checkForEmptyDetails(clientDetails.get(CLIENT_NAME_INDEX));
        checkForEmptyDetails(clientDetails.get(CLIENT_CONTACT_NUMBER_INDEX));
        checkForEmptyDetails(clientDetails.get(CLIENT_BUDGET_INDEX));

        //Checks for Contact Number, Email and Budget Format
        checkForValidSingaporeContactNumber(clientDetails.get(CLIENT_CONTACT_NUMBER_INDEX));
        boolean hasEmail = !clientDetails.get(CLIENT_EMAIL_INDEX).isEmpty();
        if (hasEmail) {
            checkForValidEmail(clientDetails.get(CLIENT_EMAIL_INDEX));
        }
        checkForBudgetNumberFormat(clientDetails.get(CLIENT_BUDGET_INDEX));
    }

    private void checkForValidSingaporeContactNumber(String clientContactNumber) throws InvalidContactNumberException {
        boolean hasValidContactNumber = checkForDetailFormat("^[689]\\d{7}$", clientContactNumber);
        if (!hasValidContactNumber) {
            throw new InvalidContactNumberException(EXCEPTION);
        }
    }

    private void checkForValidEmail(String clientEmail) throws InvalidEmailException {
        //General Email Regex (RFC 5322 Official Standard)
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\"
                + "x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-"
                + "9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-"
                + "9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0"
                + "c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        boolean hasValidEmail = checkForDetailFormat(regex, clientEmail);
        if (!hasValidEmail) {
            throw new InvalidEmailException(EXCEPTION);
        }
    }

    private void checkForBudgetNumberFormat(String budget) throws InvalidBudgetFormatException {
        //Accepts only positive whole number
        String regex = "^[1-9]\\d*$";
        boolean hasValidBudgetNumberFormat = checkForDetailFormat(regex, budget);
        if (!hasValidBudgetNumberFormat) {
            throw new InvalidBudgetFormatException(EXCEPTION);
        }
    }

    private boolean checkForDetailFormat(String regex, String detail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(detail);
        return matcher.matches();
    }
}
