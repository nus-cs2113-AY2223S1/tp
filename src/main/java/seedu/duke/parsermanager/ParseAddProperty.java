package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandAddProperty;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidPriceFormatException;
import seedu.duke.exception.InvalidSingaporeAddressException;
import seedu.duke.exception.MissingFlagException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.CommandStructure.ADD_PROPERTY_FLAGS;
import static seedu.duke.Messages.EXCEPTION;
import static seedu.duke.Messages.MESSAGE_ADD_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static seedu.duke.Messages.MESSAGE_INVALID_PRICE_FORMAT;
import static seedu.duke.Messages.MESSAGE_INVALID_SINGAPORE_ADDRESS;

public class ParseAddProperty extends Parser {
    private final String commandDescription;

    public ParseAddProperty(String addCommandDescription) {
        this.commandDescription = addCommandDescription;
    }

    public Command parseCommand() throws EmptyDescriptionException, InvalidSingaporeAddressException,
            MissingFlagException, IncorrectFlagOrderException, InvalidPriceFormatException, EmptyDetailException {

        checkForEmptyDescription(commandDescription);
        ArrayList<String> propertyDetails;
        try {

            checkForEmptyDetails(commandDescription);

            propertyDetails = processCommandDetails(commandDescription);
            validatePropertyDetails(propertyDetails);

            return new CommandAddProperty(propertyDetails);

        } catch (InvalidSingaporeAddressException e) {
            throw new InvalidSingaporeAddressException(MESSAGE_INVALID_SINGAPORE_ADDRESS);
        } catch (MissingFlagException e) {
            throw new MissingFlagException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        } catch (IncorrectFlagOrderException e) {
            throw new IncorrectFlagOrderException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        } catch (InvalidPriceFormatException e) {
            throw new InvalidPriceFormatException(MESSAGE_INVALID_PRICE_FORMAT);
        } catch (EmptyDetailException e) {
            throw new EmptyDetailException(MESSAGE_ADD_PROPERTY_WRONG_FORMAT);
        }


    }

    private void checkForEmptyDescription(String commandDetail) throws EmptyDescriptionException {
        boolean isEmptyDescription = isEmptyString(commandDetail);
        if (isEmptyDescription) {
            throw new EmptyDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
    }

    private boolean isEmptyString(String commandDetail) {
        String trimmedString = commandDetail.trim();

        return trimmedString.isEmpty();
    }

    private void checkForEmptyDetails(String commandDetail) throws EmptyDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyDetailException(EXCEPTION);
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws MissingFlagException, IncorrectFlagOrderException {

        String[] flags = ADD_PROPERTY_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkFlagsOrder(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void validatePropertyDetails(ArrayList<String> propertyDetails) throws EmptyDetailException,
            InvalidSingaporeAddressException, InvalidPriceFormatException {
        //Checks for Missing Landlord Name, Property Address, Renting Price (SGD/month) and Unit-Type
        for (String propertyDetail : propertyDetails) {
            checkForEmptyDetails(propertyDetail);
        }

        //Checks Format for Address (Singapore) and Renting Price
        checkForValidSingaporeAddress(propertyDetails.get(1));
        checkForPriceNumberFormat(propertyDetails.get(2));
    }

    private int[] getFlagIndexPositions(String commandDetail, String[] flags) {
        int[] flagIndexPositions = new int[flags.length];

        for (int i = 0; i < flags.length; i++) {
            flagIndexPositions[i] = commandDetail.indexOf(flags[i]);
        }
        return flagIndexPositions;
    }

    private void checkForMissingFlags(int[] flagIndexPositions) throws MissingFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!isFlagPresent(flagIndex)) {
                throw  new MissingFlagException(EXCEPTION);
            }
        }
    }

    private void checkFlagsOrder(int[] flagIndexPositions) throws IncorrectFlagOrderException {
        for (int i = 0; i < flagIndexPositions.length - 1; i++) {
            checkForCorrectFlagOrder(flagIndexPositions[i], flagIndexPositions[i + 1]);
        }
    }

    private ArrayList<String> extractCommandDetails(String rawCommandDetail, String[] flags,
                                                    int[] flagIndexPositions) {
        ArrayList<String> extractedCommandDetails = new ArrayList<>();
        for (int i = 0; i < flags.length; i++) {
            String extractedDetail;
            if (i == flags.length - 1) {
                /* The extracted detail for the last flag starts from the char after the flag, to the end of
                   rawCommandDetails */
                extractedDetail = extractDetail(rawCommandDetail, flagIndexPositions[i] + flags[i].length());
            } else {
                // The extracted detail for non-last starts from the char after the flag, to index before the next flag
                extractedDetail = extractDetail(
                        rawCommandDetail,
                        flagIndexPositions[i] + flags[i].length(),
                        flagIndexPositions[i + 1]);
            }
            extractedCommandDetails.add(extractedDetail.trim());
        }
        return extractedCommandDetails;
    }

    private void checkForValidSingaporeAddress(String address) throws InvalidSingaporeAddressException {
        boolean hasValidSingaporeLandedPropertyAddress = checkForValidSingaporeLandedPropertyAddress(address);
        boolean hasValidSingaporeBuildingAddress = checkForValidSingaporeBuildingAddress(address);

        boolean hasValidSingaporeAddress = hasValidSingaporeLandedPropertyAddress || hasValidSingaporeBuildingAddress;
        if (!hasValidSingaporeAddress) {
            throw new InvalidSingaporeAddressException(EXCEPTION);
        }
    }

    private void checkForPriceNumberFormat(String budget) throws InvalidPriceFormatException {
        //Accepts only positive whole number
        String regex = "^[1-9]\\d*$";
        boolean hasValidPriceNumberFormat = checkForDetailFormat(regex, budget);
        if (!hasValidPriceNumberFormat) {
            throw new InvalidPriceFormatException(EXCEPTION);
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != -1);
    }

    private void checkForCorrectFlagOrder(int flagPosition, int nextFlagPosition) throws IncorrectFlagOrderException {
        boolean hasCorrectOrder = (flagPosition < nextFlagPosition);
        if (!hasCorrectOrder) {
            throw new IncorrectFlagOrderException(EXCEPTION);
        }
    }

    private static String extractDetail(String rawDetail, int beginIndex) {
        return rawDetail.substring(beginIndex).trim();
    }

    private static String extractDetail(String rawDetail, int beginIndex, int endIndex) {
        return rawDetail.substring(beginIndex, endIndex).trim();
    }

    private boolean checkForValidSingaporeLandedPropertyAddress(String address) {
        String landedPropertyUnitNumberRegex = "^([0-9]{1,4})([A-Z]?) ";
        String streetNameRegex = "[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,"
                + "{}|-]";
        String streetNumberRegex = " ([1-9]{1}[0-9]{0,3})";
        String postalCodeRegex = ", (Singapore [0-9]{6})$";

        String landedPropertyAddressRegex = landedPropertyUnitNumberRegex + streetNameRegex + postalCodeRegex;
        String landedPropertyAddressWithStreetNumberRegex = landedPropertyUnitNumberRegex + streetNameRegex
                + streetNumberRegex + postalCodeRegex;

        boolean hasValidLandedPropertyAddress = checkForDetailFormat(landedPropertyAddressRegex, address);
        boolean hasValidLandedPropertyAddressWithStreetNumber
                = checkForDetailFormat(landedPropertyAddressWithStreetNumberRegex, address);
        return hasValidLandedPropertyAddress || hasValidLandedPropertyAddressWithStreetNumber;
    }

    private boolean checkForValidSingaporeBuildingAddress(String address) {
        String buildingBlockNumberRegex = "^([0-9]{1,4})([A-Z]?) ";
        String streetNameRegex = "[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+=<>\\s\\n?`~0-9,"
                + "{}|-]";
        String streetNumberRegex = " ([1-9]{1}[0-9]{0,3})";
        String buildingUnitFloorAndNumberRegex = " #(([0]{1}[1-9]{1})|([1-9]{1}[0-9]{1,2}))-(([0]{1}[1-9]{1})|([1-9]"
                + "{1}[0-9]{1,3}))([A-Z]?)";
        String buildingNameRegex = " [^.!@#$%^&*()_+=<>\\s\\n?`~0-9,{}|-]([a-zA-Z\\s]+)[^.!@#$%^&*()_+=<>\\s\\n?`~0-9"
                + ",{}|-]";
        String postalCodeRegex = ", (Singapore [0-9]{6})$";

        String buildingAddressRegex = buildingBlockNumberRegex + streetNameRegex + buildingUnitFloorAndNumberRegex
                + postalCodeRegex;
        String buildingAddressWithStreetNumberRegex = buildingBlockNumberRegex + streetNameRegex + streetNumberRegex
                + buildingUnitFloorAndNumberRegex + postalCodeRegex;
        String buildingAddressWithBuildingNameRegex = buildingBlockNumberRegex + streetNameRegex
                + buildingUnitFloorAndNumberRegex + buildingNameRegex + postalCodeRegex;
        String buildingAddressWithStreetNumberAndBuildingNameRegex = buildingBlockNumberRegex + streetNameRegex
                + streetNumberRegex + buildingUnitFloorAndNumberRegex + buildingNameRegex + postalCodeRegex;

        boolean hasValidBuildingAddress = checkForDetailFormat(buildingAddressRegex, address);
        boolean hasValidBuildingAddressWithStreetNumber
                = checkForDetailFormat(buildingAddressWithStreetNumberRegex, address);
        boolean hasValidBuildingAddressWithBuildingName
                = checkForDetailFormat(buildingAddressWithBuildingNameRegex, address);
        boolean hasValidBuildingAddressWithStreetNumberAndBuildingName
                = checkForDetailFormat(buildingAddressWithStreetNumberAndBuildingNameRegex, address);
        return hasValidBuildingAddress || hasValidBuildingAddressWithStreetNumber
                || hasValidBuildingAddressWithBuildingName || hasValidBuildingAddressWithStreetNumberAndBuildingName;
    }

    private boolean checkForDetailFormat(String regex, String detail) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(detail);
        return matcher.matches();
    }


}
