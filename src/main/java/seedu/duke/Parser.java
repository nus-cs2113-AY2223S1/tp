package seedu.duke;

import java.util.Arrays;

public class Parser {

    public static final String[] BIOMETRICS_SEPARATOR_LIST = new String[]{"a/", "g/", "h/", "w/", "f/"};
    public static final String SET_BIOMETRICS = "set biometrics";

    public static final String[] GENDER_OPTIONS = new String[] {"male", "female", "other"};
    public static final String VIEW_BIOMETRICS = "view biometrics";

    public Parser() {
    }

    public String parse(String fullCommand, Biometrics biometrics) {
        String output = "";
        try {
            if (fullCommand.startsWith("exit")) {
                executeExit();
            } else if (fullCommand.startsWith(SET_BIOMETRICS)) {
                output = executeSetBiometrics(fullCommand.substring(SET_BIOMETRICS.length() + 1), biometrics);
            } else if (fullCommand.equals(VIEW_BIOMETRICS)){
                output =  executeViewBiometrics(biometrics);
            }else {
                output = handleInvalidCommand();
            }
        } catch (IllegalValueException e) {
            output = getErrorMessage(e);
        }
        return output;
    }

    private static void executeExit() {
        System.out.println("bye");
        System.exit(0);
    }

    private static String getErrorMessage(IllegalValueException e) {
        return e.getMessage();
    }

    private String handleInvalidCommand() {
        return "Invalid command";
    }

    private String executeViewBiometrics(Biometrics biometrics) {
        return biometrics.toString();
    }

    private String executeSetBiometrics(String parameters, Biometrics biometrics) throws IllegalValueException{
        String[] parameterList = parameters.split(" ");
        String output = "";
        if (parameterList.length < 5) {
            throw  new IllegalValueException("Invalid biometrics");
        }
        try {
            int age = Integer.parseInt(parameterList[0]);
            int height = Integer.parseInt(parameterList[2]);
            int weight = Integer.parseInt(parameterList[3]);
            int fatPercentage = Integer.parseInt(parameterList[4]);
            if (age <= 0 || !Arrays.asList(GENDER_OPTIONS).contains(parameterList[1])
                    || height <= 0 || weight <= 0 || fatPercentage <= 0) {
                throw new IllegalValueException("Invalid biometrics");
            }
            biometrics.setBiometrics(age, parameterList[1], height, weight, fatPercentage);
            output = "biometrics set";
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Invalid biometrics");
        }
        return output;
    }
}
