package seedu.duke.exceptions;

public class InvalidSemesterException extends Exception {

    public static boolean invalidFormat(String semester) {
        if (semester.contains("Y")) {
            if (semester.contains("S")) {
                if (semester.indexOf("Y") < semester.indexOf("S")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean invalidYearNumber(String semester) {
        String year = semester.substring(semester.indexOf('Y') + 1,semester.indexOf('S'));
        if (year.length() == 1 && year.matches("[0-9]+")) {
            return false;
        }
        return true;
    }

    public static boolean invalidSemesterNumber(String semester) {
        String year = semester.substring(semester.indexOf('S') + 1);
        if (year.length() == 1 && year.matches("[0-9]+")) {
            if (Integer.parseInt(year) < 4) {
                return false;
            }
        }
        return true;
    }
}
