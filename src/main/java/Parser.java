import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    // todo add exit message
    private final String exitMessage = "";
    private final String patients = "1";
    private final String visits = "2";
    private final String prescription = "3";

    private Scanner scanner;
    private PatientList patientList;

    public Parser(PatientList patientList) {
        this.patientList = patientList;
    }

    public void mainMenuParser() {
        String line;
        scanner = new Scanner(System.in);
        line = scanner.nextLine();
        if (!line.equals(exitMessage)) {
            // todo change add to each case accordingly
            // String line = checkInputForMainMenu(line);
            switch (line) {
            case patients:
                Messages.printPatientMenuMessage();
                patientParser();
                break;
            case visits:
                Messages.printVisitsMenuMessage();
                break;
            case prescription:
                Messages.printPrescriptionMenuMessage();
                break;
            default:
                break;
            }
        }
    }

    //todo add:
    //    private static String  checkInputForMainMenu(String input);
    //    public static void patientParser();
    //    public static void visitsParser();
    //    public static void medicineParser();
    //    public void addPatientParser(String input) {}

    public void patientParser() {
        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();
                Matcher matcherAdd = patientAddMatcher(input);
                Matcher matcherRetrieve = patientRetrieveMatcher(input);
                Matcher matcherEdit = patientEditMatcher(input);
                if (matcherAdd.find()) {
                    patientList.addPatient(matcherAdd.group(1), matcherAdd.group(3),
                            matcherAdd.group(2), matcherAdd.group(4));
                } else if (matcherRetrieve.find()) {
                    patientList.retrievePatient(matcherRetrieve.group(1));
                } else if (matcherEdit.find()) {
                    parseEditInput(matcherEdit.group(1), matcherEdit.group(2), matcherEdit.group(3));
                } else {
                    throw new OneDocException("Your input is incorrect! Please format it as such:"
                            + "\nTo add a patient: add i/[ID] n/[name] g/[M/F] d/[DOB]"
                            + "\nTo edit a patient's info: edit i/[ID] (n/[name] or g/[M/F] or d/[DOB]"
                            + "\nTo retrieve a patient's info: retrieve i/[ID]");
                }
            } catch (OneDocException e) {
                System.out.println("Incorrect format: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected issue: " + e.getMessage());
            }
        }

    }


    /** REGEX PATTERNS */
    private static Matcher patientAddMatcher(String input) {
        Pattern patientAddPattern = Pattern.compile(
                "^add\\s*n/(\\w+\\s*\\w+|\\w+)\\s*g/(M|F)\\s*d/(\\d\\d-\\d\\d-\\d\\d\\d\\d)\\s*i/(\\w+)\\s*$",
                Pattern.CASE_INSENSITIVE);
        return patientAddPattern.matcher(input);
    }

    private static Matcher patientRetrieveMatcher(String input) {
        Pattern patientRetrievePattern = Pattern.compile(
                "^retrieve\\s*i/(\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
        return patientRetrievePattern.matcher(input);
    }

    private static Matcher patientEditMatcher(String input) {
        Pattern patientEditPattern = Pattern.compile(
                "^edit\\s*i/(\\w+)\\s*([n|g|d])/([\\w-]+)$", Pattern.CASE_INSENSITIVE);
        return patientEditPattern.matcher(input);
    }

    private void parseEditInput(String id, String type, String input) throws OneDocException {
        switch (type) {
        case "n/":
            Pattern matchName = Pattern.compile("^(\\w+\\s*\\w+|\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchName.matcher(input).find()) {
                patientList.modifyPatientDetails(id, input, null, null);
            } else {
                throw new OneDocException("Name is incorrectly formatted! "
                        + "Please use First and Last name or just one name");
            }
            break;
        case "d/":
            Pattern matchDob = Pattern.compile("^(\\d\\d-\\d\\d-\\d\\d\\d\\d)$", Pattern.CASE_INSENSITIVE);
            if (matchDob.matcher(input).find()) {
                patientList.modifyPatientDetails(id, null, input, null);
            } else {
                throw new OneDocException("DOC is incorrectly formatted! Please use DD-MM-YYYY format");
            }
            break;
        case "g/":
            Pattern matchGender = Pattern.compile("^(M|F)$", Pattern.CASE_INSENSITIVE);
            if (matchGender.matcher(input).find()) {
                patientList.modifyPatientDetails(id, null, null, input);
            } else {
                throw new OneDocException("Gender is incorrectly formatted! Please use only one letter, M or F");
            }
            break;
        default:
            throw new OneDocException("Type is incorrectly formatted!"
                    + "Please use n/ for name, g/ for gender, and d/ for DOB");
        }
    }


}
