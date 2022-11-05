package seedu.duke.exceptions;

public class InvalidGradeException extends Exception {
    public static boolean checkGradeFormat(String grade) {
        return !grade.matches("[0-9]+");
    }
    public static boolean checkValidGrade(String grade) {
        String[] possibleGrades = {"A", "A+","A-","B","B+","B-", "C", "C+","D", "D+", "F", "F*", "S", "U", "-"};
        for (String possibleGrade : possibleGrades) {
            if (grade.equals(possibleGrade)) {
                return true;
            }
        }
        return false;
    }
    public String getMessage() {
        String message = "* Invalid grade input." + "\n";
        return message;
    }

}
