package seedu.duke.exceptions;

public class InvalidSemesterException extends Exception {
    /**
     * function to check if format of semester entered is invalid or not
     * @param semester semester entered by user. Format: String
     * @return returns true if the format is invalid. otherwise returns false. Format: boolean
     */
    public static boolean invalidFormat(String semester) {
        if (semester.length() == 4) {
            if (semester.contains("Y")) {
                if (semester.contains("S")) {
                    return semester.indexOf("Y") >= semester.indexOf("S");
                }
            }
        }

        return true;
    }

    /**
     * Function to check if year number is invalid or not
     * @param semester semester entered by user. Format: String
     * @return returns true if year number is invalid. otherwise returns false. Format: boolean
     */
    public static boolean invalidYearNumber(String semester) {
        String year = semester.substring(semester.indexOf('Y') + 1,semester.indexOf('S'));
        if (year.length() == 1 && year.matches("[0-9]+")) {
            return Integer.parseInt(year) >= 5 || Integer.parseInt(year) <= 0;

        }
        return true;
    }
    /**
     * Function to check if semester number is invalid or not
     * @param semester semester entered by user. Format: String
     * @return returns true if semester number is invalid. otherwise returns false. Format: boolean
     */

    public static boolean invalidSemesterNumber(String semester) {
        String year = semester.substring(semester.indexOf('S') + 1);
        if (year.length() == 1 && year.matches("[0-9]+")) {
            return Integer.parseInt(year) <= 0 || Integer.parseInt(year) >= 3;
        }
        return true;
    }

    public String getMessage() {
        return "* Invalid Semester/Year input." + "\n";
    }
}
