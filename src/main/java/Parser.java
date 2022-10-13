import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    // todo add exit message
    private static final String EXIT_MESSAGE = "bye";
    private static final String MAIN_PATIENT_COMMAND = "1";
    private static final String MAIN_VISIT_COMMAND = "2";
    private static final String MAIN_PRESCRIPTION_COMMAND = "3";

    private final PatientList patientList;
    private final VisitList visitList;
    private final PrescriptionList prescriptionList;

    public Parser(PatientList patientList, VisitList visitList, PrescriptionList prescriptionList) {
        this.patientList = patientList;
        this.visitList = visitList;
        this.prescriptionList = prescriptionList;
    }

    public MainMenuState mainMenuParser(String input) {
        switch (input) {
        case MAIN_PATIENT_COMMAND:
            return MainMenuState.PATIENT;
        case MAIN_VISIT_COMMAND:
            return MainMenuState.VISIT;
        case MAIN_PRESCRIPTION_COMMAND:
            return MainMenuState.PRESCRIPTION;
        case EXIT_MESSAGE:
            return MainMenuState.EXIT;
        default:
            return MainMenuState.INVALID;
        }

    }

    private boolean shouldExit(String input) {
        return input.equalsIgnoreCase("bye") || input.equalsIgnoreCase("exit");
    }

    private boolean shouldBackToMain(String input) {
        return input.equalsIgnoreCase("main");
    }

    public SubMenuState patientParser(String input) {
        if (shouldExit(input)) {
           return SubMenuState.EXIT;
        }

        if (shouldBackToMain(input)) {
            return SubMenuState.BACK_TO_MAIN;
        }

        try {
            boolean matchesView = input.equalsIgnoreCase("viewall");
            Matcher matcherAdd = patientAddMatcher(input);
            Matcher matcherRetrieve = patientRetrieveMatcher(input);
            Matcher matcherEdit = patientEditMatcher(input);
            if (matchesView) {
                patientList.listPatients();
            } else if (matcherAdd.find()) {
                patientList.addPatient(matcherAdd.group(1), matcherAdd.group(3),
                        matcherAdd.group(2), matcherAdd.group(4));
            } else if (matcherRetrieve.find()) {
                patientList.retrievePatient(matcherRetrieve.group(1));
            } else if (matcherEdit.find()) {
                parseEditPatient(matcherEdit.group(1), matcherEdit.group(2), matcherEdit.group(3));
            } else {
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + "\nTo add a patient: add n/[name] g/[M/F] d/[DOB] i/[ID]"
                        + "\nTo edit a patient's info: edit i/[ID] (n/[name] or g/[M/F] or d/[DOB])"
                        + "\nTo retrieve a patient's info: retrieve i/[ID]");
            }
        } catch (OneDocException e) {
            System.out.println("Incorrect format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected issue: " + e.getMessage());
        }

        return SubMenuState.IN_SUB_MENU;
    }

    public SubMenuState visitParser(String input) {
        if (shouldExit(input)) {
            return SubMenuState.EXIT;
        }

        if (shouldBackToMain(input)) {
            return SubMenuState.BACK_TO_MAIN;
        }

        try {
            boolean matchesView = input.equalsIgnoreCase("viewall");
            Matcher matcherAdd = addVisitMatcher(input);
            Matcher matcherEdit = editVisitMatcher(input);
            if (matchesView) {
                visitList.viewAll();
            } else if (matcherAdd.find()) {
                String reason = matcherAdd.group(4);
                if (reason.isEmpty()) {
                    visitList.addVisit(matcherAdd.group(1), matcherAdd.group(2), matcherAdd.group(3));
                } else {
                    visitList.addVisit(matcherAdd.group(1), matcherAdd.group(2),
                            matcherAdd.group(3), matcherAdd.group(4));
                }
            } else if (matcherEdit.find()) {
                visitList.editReason(matcherEdit.group(1), matcherEdit.group(2));
            } else {
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + "\nTo add a visit: add i/[ID] d/[date] t/[time] r/[reason]"
                        + "\nTo edit a visit's reason: edit i/[ID] r/[reason]");
            }
        } catch (OneDocException e) {
            System.out.println("Incorrect format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected issue: " + e.getMessage());
        }

        return SubMenuState.IN_SUB_MENU;
    }

    public SubMenuState prescriptionParser(String input) {
        if (shouldExit(input)) {
            return SubMenuState.EXIT;
        }

        if (shouldBackToMain(input)) {
            return SubMenuState.BACK_TO_MAIN;
        }

        try {
            boolean matchesView = input.equalsIgnoreCase("viewall");
            Matcher matcherAdd = addPrescriptionMatcher(input);
            Matcher matcherEdit = editPrescriptionMatcher(input);
            if (matchesView) {
                prescriptionList.viewAll();
            } else if (matcherAdd.find()) {
                prescriptionList.add(matcherAdd.group(1), matcherAdd.group(2),
                        matcherAdd.group(3), matcherAdd.group(4));
            } else if (matcherEdit.find()) {
                parseEditPrescription(Integer.valueOf(matcherEdit.group(1)),
                        matcherEdit.group(2), matcherEdit.group(3));
            } else {
                throw new OneDocException("Your input is incorrect! Please format it as such:"
                        + "\nTo add a prescription: add i/ID n/[name] d/[dosage] t/[time interval]"
                        + "\nTo edit a prescription: edit i/[index] (n/[name] or d/[dosage] or t/[time interval])");
            }
        } catch (OneDocException e) {
            System.out.println("Incorrect format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected issue: " + e.getMessage());
        }

        return SubMenuState.IN_SUB_MENU;
    }


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

    private void parseEditPatient(String id, String type, String input) throws OneDocException {
        switch (type) {
        case "n":
            Pattern matchName = Pattern.compile("^(\\w+\\s*\\w+|\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchName.matcher(input).find()) {
                patientList.modifyPatientDetails(id, input, "", "");
            } else {
                throw new OneDocException("Name is incorrectly formatted! "
                        + "Please use First and Last name or just one name");
            }
            break;
        case "d":
            Pattern matchDob = Pattern.compile("^(\\d\\d-\\d\\d-\\d\\d\\d\\d)$", Pattern.CASE_INSENSITIVE);
            if (matchDob.matcher(input).find()) {
                patientList.modifyPatientDetails(id, "", input, "");
            } else {
                throw new OneDocException("DOC is incorrectly formatted! Please use DD-MM-YYYY format");
            }
            break;
        case "g":
            Pattern matchGender = Pattern.compile("^(M|F)$", Pattern.CASE_INSENSITIVE);
            if (matchGender.matcher(input).find()) {
                patientList.modifyPatientDetails(id, "", "", input);
            } else {
                throw new OneDocException("Gender is incorrectly formatted! Please use only one letter, M or F");
            }
            break;
        default:
            throw new OneDocException("Type is incorrectly formatted!"
                    + "Please use n/ for name, g/ for gender, and d/ for DOB");
        }
    }

    private static Matcher addVisitMatcher(String input) {
        Pattern addVisitPattern = Pattern.compile(
                "^add\\s*i/(\\w+)\\s*d/(\\d\\d-\\d\\d-\\d\\d\\d\\d)\\s*t/(\\d\\d:\\d\\d)\\s*"
                        + "(?:r/((?:\\w+\\s+\\w+)+|\\w+)\\s*)*$", Pattern.CASE_INSENSITIVE);
        return addVisitPattern.matcher(input);
    }

    private static Matcher editVisitMatcher(String input) {
        Pattern editVisitPattern = Pattern.compile(
                "^edit\\s*i/(\\w+)\\s*r/((?:\\w+\\s+\\w+)+|\\w+)\\s*$", Pattern.CASE_INSENSITIVE);
        return editVisitPattern.matcher(input);
    }

    private static Matcher addPrescriptionMatcher(String input) {
        Pattern addPrescriptionPattern = Pattern.compile(
                "^add\\s*i/(\\w+)\\s*n/(\\w+\\s*\\w+|\\w+)\\s*d/(\\d+\\s*\\w+)\\s*t/(\\w+)\\s*$",
                Pattern.CASE_INSENSITIVE);
        return addPrescriptionPattern.matcher(input);
    }

    private static Matcher editPrescriptionMatcher(String input) {
        Pattern editPrescriptionPattern = Pattern.compile(
                "^edit\\s*i/(\\d+)\\s*([n|d|t])/([\\w-]+)$", Pattern.CASE_INSENSITIVE);
        return editPrescriptionPattern.matcher(input);
    }

    private void parseEditPrescription(int id, String type, String input) throws OneDocException {
        switch (type) {
        case "n":
            Pattern matchName = Pattern.compile("^(\\w+\\s*\\w+|\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchName.matcher(input).find()) {
                prescriptionList.edit(id, input, "", "");
            } else {
                throw new OneDocException("Prescription name is incorrectly formatted! "
                        + "Please use one or two names without dashes or special characters");
            }
            break;
        case "d":
            Pattern matchDosage = Pattern.compile("^(\\d+\\s*\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchDosage.matcher(input).find()) {
                prescriptionList.edit(id, "", input, "");
            } else {
                throw new OneDocException("Dosage is incorrectly formatted! "
                        + "Please use [amount] [portion] format, i.e. 10 mg");
            }
            break;
        case "t":
            Pattern matchTimeInt = Pattern.compile("^(\\w+)$", Pattern.CASE_INSENSITIVE);
            if (matchTimeInt.matcher(input).find()) {
                prescriptionList.edit(id, "", "", input);
            } else {
                throw new OneDocException("Time interval is incorrectly formatted! "
                        + "Please use words and numbers to describe the time interval");
            }
            break;
        default:
            throw new OneDocException("Type is incorrectly formatted!"
                    + "Please use n/ for name, d/ for dosage, and t/ for time interval");
        }
    }


}
