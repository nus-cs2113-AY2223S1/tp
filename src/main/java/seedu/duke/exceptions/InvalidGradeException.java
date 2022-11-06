package seedu.duke.exceptions;

public class InvalidGradeException extends Exception {
    /**
     * function to check if format of grade is correct or not
     * @param grade grade entered by user. Format: String
     * @return returns true if the format is correct. otherwise false. Format: boolean
     */
    public static boolean checkGradeFormat(String grade) {
        return !grade.matches("[0-9]+");
    }

    /**
     * Function to check if grade is valid or not
     * @param grade the grade entered by user. Format: String
     * @return returns true if grade is valid. Otherwise eturns false. Format: boolean
     */
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
        return "* Invalid grade input." + "\n";
    }

}
