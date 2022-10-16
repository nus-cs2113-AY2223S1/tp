package seedu.duke.exceptions;

public class InvalidInputFormatException extends Exception {

    public static boolean checkMod(String input) {
        return input.contains("m/");
    }

    public static boolean checkSem(String input) {
        return input.contains("s/");
    }

    public static boolean checkMC(String input) {
        return input.contains("mc/");
    }

    public static boolean checkGrade(String input) {
        return input.contains("g/");
    }

    //more to add: repetition of fields with diff content,
    //extra words in btw fields eg add jkdvn m/cs1231..
    //additional info that is not required
}
