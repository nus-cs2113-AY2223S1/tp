package seedu.duke.exceptions;

public class InvalidInputFormatException extends Exception {

    public static boolean checkMod(String input) {
        boolean isCorrect = input.contains("m/");
        if (isCorrect) {
            isCorrect = repetitionField(input, "m/");
        }
        return isCorrect;
    }

    public static boolean checkSem(String input) {
        boolean isCorrect = input.contains("s/");
        if (isCorrect) {
            isCorrect = repetitionField(input, "s/");
        }
        return isCorrect;
    }

    public static boolean checkMC(String input) {
        boolean isCorrect = input.contains("mc/");
        if (isCorrect) {
            isCorrect = repetitionField(input, "mc/");
        }
        return isCorrect;
    }

    public static boolean checkGrade(String input) {
        boolean isCorrect = input.contains("g/");
        if (isCorrect) {
            isCorrect = repetitionField(input, "g/");
        }
        return isCorrect;
    }

    public static boolean repetitionField(String input, String type) {
        int i = input.indexOf(type);
        i += type.length();
        input = input.substring(i);
        return !input.contains(type);
    }
    public String getMessage() {
        String message = "INPUT FORMAT IS WRONG!! PLEASE KEY IN THE CORRECT INPUT!!"
                + "\n"  + "input 'help' if you are unsure about the requirements" + "\n";
        return message;
    }
}
