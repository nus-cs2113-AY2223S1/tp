package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.Parser;
import seedu.duke.Ui;

import java.util.Arrays;

public class SetCommand extends Command {

    private final String[] genderOptions = new String[]{"male", "female", "other"};

    private Ui ui;
    private Biometrics biometrics;

    private String arguments;

    public SetCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        String[] argumentList = Parser.getArgumentList(arguments);
        try {
            setBiometrics(argumentList);
        } catch (IllegalValueException e) {
            ui.output(e.getMessage());
        }
    }

    private void setBiometrics(String[] argumentList) throws IllegalValueException {
        try {
            if (argumentList.length < 5) {
                throw new IllegalValueException("Insufficient parameters");
            }
            int age = extractAge(argumentList[1].trim());
            String gender = extractGender(argumentList[2].trim());
            int height = extractHeight(argumentList[3].trim());
            int weight = extractWeight(argumentList[4].trim());
            int fatPercentage = extractFatPercentage(argumentList[5].trim());
            biometrics.setBiometrics(age, gender, height, weight, fatPercentage);
            ui.output("biometrics set");
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Biometrics, except for gender, should be numerical");
        }
    }

    private int extractAge(String input) throws IllegalValueException {
        int age = Integer.parseInt(input);
        if (age <= 0 || age > 120) {
            throw new IllegalValueException("That age ain't possible");
        }
        return age;
    }

    private String extractGender(String input) throws IllegalValueException {
        if (!Arrays.asList(genderOptions).contains(input)) {
            throw new IllegalValueException("Hi, I only recognise other, female and male genders");
        }
        return input;
    }

    public int extractHeight(String input) throws IllegalValueException {
        int height = Integer.parseInt(input);
        if (height <= 0 || height > 300) {
            throw new IllegalValueException("That's a strange height...");
        }
        return height;
    }

    public int extractWeight(String input) throws IllegalValueException {
        int weight = Integer.parseInt(input);
        if (weight <= 0 || weight > 200) {
            throw new IllegalValueException("That weight can't be real!");
        }
        return weight;
    }

    public int extractFatPercentage(String input) throws IllegalValueException {
        int fatPercentage = Integer.parseInt(input);
        if (fatPercentage <= 0 || fatPercentage >= 100) {
            throw new IllegalValueException("Invalid fat percentage");
        }
        return fatPercentage;
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics) {
        this.ui = ui;
        this.biometrics = biometrics;
    }
}
